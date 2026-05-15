package subsistemaRecetas;

import Bo.NegocioException;
import DTO.RecetaDTO;
import DTO.DetalleRecetaDTO;
import Enums.Especialidades;
import GestorMedico.ValidacionMedico;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase control que se encarga de hacer las validaciones de la receta.
 * @author Dario
 */
public class ControlValidarReceta {
    
    protected final ValidacionMedico validadorMedico = new ValidacionMedico();
    
    /**
     * Valida si la fecha de la receta no esta expirada.
     * @param receta Receta a las que se la validara.
     * @return Si la receta es valida o invalida.
     */
    protected Boolean validarFechaReceta(RecetaDTO receta) {
        if (receta == null || receta.getFechaCaducidad() == null){
            return false;
        } 
        return !LocalDate.now().isAfter(receta.getFechaCaducidad());
    }

    /**
     * Valida si existe el producto en la receta.
     * @param receta Receta que se validara.
     * @param idProducto Producto que se busca en la receta.
     * @return Si el producto se encontro en la receta.
     */
    protected Boolean validarExistenciaEnReceta(RecetaDTO receta, String idProducto) {
        if (receta == null || receta.getFechaCaducidad() == null){
            return false;
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
    }
    
}
