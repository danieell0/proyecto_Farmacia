package subsistemaRecetas;

import Bo.NegocioException;
import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import com.mycompany.objetos_negocio.RecetaBO;
import java.util.Objects;

/**
 * Clase Control que se encarga de los metodos de operaciones 
 * en la gestion de las recetas.
 * @author Dario
 */
public class ControlOperacionesReceta {
    
    protected RecetaBO recetaBO;
    
    /**
     * Contructor de la clase ControlBuscarReceta.
     */
    protected ControlOperacionesReceta(){
        this.recetaBO = new RecetaBO();
    }
    
    /**
     * Incrementa la cantidad surtida en el DTO de forma temporal.
     * @param receta La receta a la que se aplicara el cambio temporalmente.
     * @param idMedicamento El medicamento al que se aplicara el cambio temporalmente.
     * @param cantidad La cantidad del cambio.
     * @return Si la operacion fue exitosa.
     * @throws NegocioException La causa del error en la capa de negocio.
     */
    protected Boolean restarMedicamentos(RecetaDTO receta, String idMedicamento, Integer cantidad) throws NegocioException {
        if (receta == null || receta.getDetalles() == null) {
            return false;
        }
        for (DetalleRecetaDTO detalle : receta.getDetalles()) {
            if (Objects.equals(detalle.getIdMedicamento(), idMedicamento)) {
                recetaBO.restarMedicamento(receta.getFolio(), idMedicamento, cantidad);
                int surtidoActual = detalle.getCantidadSurtida();
                int nuevoSurtido = surtidoActual + cantidad; 
                detalle.setCantidadSurtida(nuevoSurtido);
                return true; 
            }
        }
        return false;
    }
    
    /**
     * Decrementa la cantidad surtida en el DTO.
     * Se usa cuando el medicamento se elimina del carrito o se cancela la reserva.
     * @param receta Receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad del medicamento al que se le restara la cantidad surtida.
     * @return Si la operacion fue exitosa.
     * @throws NegocioException La causa del error en la capa de negocio.
     */
    protected Boolean sumarMedicamentos(RecetaDTO receta, String idMedicamento, Integer cantidad) throws NegocioException {
        if (receta == null || receta.getDetalles() == null) {
            return false;
        }
        for (DetalleRecetaDTO detalle : receta.getDetalles()) {
            if (Objects.equals(detalle.getIdMedicamento(), idMedicamento)) {
                recetaBO.sumarMedicamento(receta.getFolio(), idMedicamento, cantidad);
                int surtidoActual = detalle.getCantidadSurtida();
                int nuevoSurtido = surtidoActual - cantidad;
                if (nuevoSurtido < 0) {
                    nuevoSurtido = 0;
                }
                detalle.setCantidadSurtida(nuevoSurtido);
                return true;
            }
        }
        return false;
    }
    
}
    
