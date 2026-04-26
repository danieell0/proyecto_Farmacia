package subsistemaRecetas;

import DTO.RecetaDTO;
import DTO.DetalleRecetaDTO;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Clase control que se encarga de hacer las validaciones de la receta.
 * @author Dario
 */
public class ControlValidarReceta {
    
    /**
     * Valida si la fecha de la receta no esta expirada.
     * @param receta Receta a las que se la validara.
     * @return Si la receta es valida o invalida.
     */
    protected boolean validarFechaReceta(RecetaDTO receta) {
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
    protected boolean validarExistenciaEnReceta(RecetaDTO receta, Long idProducto) {
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
     * @return Si la cantidad es valida o invalida.
     */
    protected boolean validarMedicamentosReceta(RecetaDTO receta, Long idProducto, Integer cantidadSolicitada) {
        if (receta == null || receta.getDetalles() == null) {
            return false;
        }
        for (DetalleRecetaDTO d : receta.getDetalles()) {
            if (Objects.equals(d.getIdMedicamento(), idProducto)) {
                int disponible = d.getCantidadRecetada() - d.getCantidadSurtida();
                if (disponible >= cantidadSolicitada) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
