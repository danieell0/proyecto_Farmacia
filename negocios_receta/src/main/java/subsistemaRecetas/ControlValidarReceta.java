package subsistemaRecetas;

import Bo.NegocioException;
import DTO.RecetaDTO;
import DTO.DetalleRecetaDTO;
import Enums.Especialidades;
import GestorMedico.ValidacionMedico;
import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase control que se encarga de hacer las validaciones de la receta.
 * @author Dario
 */
public class ControlValidarReceta {
    
    private final ValidacionMedico validadorMedico = new ValidacionMedico();
    private static final Logger logger = Logger.getLogger(ControlValidarReceta.class.getSimpleName());
    
    /**
     * Valida si la fecha de la receta no esta expirada.
     * @param receta Receta a las que se la validara.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la receta es valida o invalida.
     */
    protected Boolean validarFechaReceta(RecetaDTO receta) throws NegocioException {
        if (receta == null) {
            throw new NegocioException("No se ha proporcionado una receta valida.");
        } 
        if (receta.getFechaCaducidad() == null) {
            throw new NegocioException("La receta no cuenta con una fecha de caducidad registrada.");
        }

        if (LocalDate.now().isAfter(receta.getFechaCaducidad())) {
            throw new NegocioException("La receta ha caducado.");
        }
        return true;
    }

    /**
     * Valida si existe el producto en la receta.
     * @param receta Receta que se validara.
     * @param idProducto Producto que se busca en la receta.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si el producto se encontro en la receta.
     */
    protected Boolean validarExistenciaEnReceta(RecetaDTO receta, String idProducto) throws NegocioException{
        if (receta == null || receta.getFechaCaducidad() == null){
            throw new NegocioException("La receta no es valida o no tiene productos.");
        }
        if (idProducto == null) {
            throw new NegocioException("El ID del producto a buscar no es valido.");
        }
        for (DetalleRecetaDTO d : receta.getDetalles()) {
            if (Objects.equals(d.getIdMedicamento(), idProducto)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida si la cantidad del producto solicitada esta en la receta.
     * @param receta Receta que se validara.
     * @param idProducto Producto que se validara.
     * @param cantidadSolicitada Cantidad solicitada del producto.
     * @param especialidadProducto Especialidad del producto recetado.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la cantidad es valida o invalida.
     */
    protected Boolean validarMedicamentosReceta(RecetaDTO receta, String idProducto, Integer cantidadSolicitada, Especialidades especialidadProducto) throws NegocioException{
        if (receta == null || especialidadProducto == null) {
            throw new NegocioException("Datos de validacion incompletos.");
        }
        if (cantidadSolicitada <= 0) {
            throw new NegocioException("La cantidad solicitada debe ser mayor a cero.");
        }
        try {
            if (!validadorMedico.esMedicoAutorizado(receta.getCedulaMedico(), especialidadProducto)) {
                throw new NegocioException("El medico no esta autorizado para recetar productos de esta especialidad.");
            }
            for (DetalleRecetaDTO d : receta.getDetalles()) {
                if (Objects.equals(d.getIdMedicamento(), idProducto)) {
                    int disponible = d.getCantidadRecetada() - d.getCantidadSurtida();
                    if (disponible < cantidadSolicitada) {
                        throw new NegocioException("Saldo insuficiente en la receta.");
                    }
                    return true;
                }
            }
            throw new NegocioException("El medicamento seleccionado no pertenece a esta receta.");
        } catch (NegocioException e) {
            throw e;
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error inesperado al validar los permisos del medico o detalles de receta", e);
            throw new NegocioException("Error interno al procesar la validacion medica.");
        }
    }
    
}
