package subsistemaRecetas.control;

import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import java.util.Objects;

/**
 * Clase Control que se encarga de los metodos de operaciones 
 * en la gestion de las recetas.
 * @author Dario
 */
public class ControlOperacionesReceta {
    
    /**
     * Incrementa la cantidad surtida en el DTO de forma temporal.
     * @param receta La receta a la que se aplicara el cambio temporalmente.
     * @param idMedicamento El medicamento al que se aplicara el cambio temporalmente.
     * @param cantidad La cantidad del cambio.
     */
    public void restarMedicamentos(RecetaDTO receta, Long idMedicamento, Integer cantidad) {
        if (receta == null || receta.getDetalles() == null) {
            return;
        }
        for (DetalleRecetaDTO detalle : receta.getDetalles()) {
            if (Objects.equals(detalle.getIdMedicamento(), idMedicamento)) {
                int surtidoActual = detalle.getCantidadSurtida();
                int nuevoSurtido = surtidoActual + cantidad; 
                detalle.setCantidadSurtida(nuevoSurtido);
                break; 
            }
        }
    }
    
    /**
     * Decrementa la cantidad surtida en el DTO.
     * Se usa cuando el medicamento se elimina del carrito o se cancela la reserva.
     * @param receta
     * @param idMedicamento
     * @param cantidad 
     */
    public void sumarMedicamentos(RecetaDTO receta, Long idMedicamento, Integer cantidad) {
        if (receta == null || receta.getDetalles() == null) {
            return;
        }
        for (DetalleRecetaDTO detalle : receta.getDetalles()) {
            if (Objects.equals(detalle.getIdMedicamento(), idMedicamento)) {
                int surtidoActual = detalle.getCantidadSurtida();
                int nuevoSurtido = surtidoActual - cantidad;
                if (nuevoSurtido < 0) {
                    nuevoSurtido = 0;
                }
                detalle.setCantidadSurtida(nuevoSurtido);
                break;
            }
        }
    }
    
}
