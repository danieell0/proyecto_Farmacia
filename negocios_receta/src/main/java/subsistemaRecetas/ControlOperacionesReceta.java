package subsistemaRecetas;

import Bo.NegocioException;
import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import com.mycompany.objetos_negocio.RecetaBO;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Control que se encarga de los metodos de operaciones 
 * en la gestion de las recetas.
 * @author Dario
 */
public class ControlOperacionesReceta {
    
    private final RecetaBO recetaBO;
    private static final Logger logger = Logger.getLogger(ControlOperacionesReceta.class.getSimpleName());
    
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
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la operacion fue exitosa.
     */
    protected Boolean restarMedicamentos(RecetaDTO receta, String idMedicamento, Integer cantidad) throws NegocioException {
        if (receta == null || receta.getDetalles() == null) {
            throw new NegocioException("La receta no puede ser nula o no tener productos.");
        }
        if (idMedicamento == null || cantidad == null || cantidad <= 0) {
            throw new NegocioException("El ID del medicamento o la cantidad ingresada son invalidos.");
        }
        try {
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
        } catch (NegocioException e) {
            logger.log(Level.SEVERE, "Error al restar medicamentos de la receta.", e);
            throw new NegocioException("Error inesperado al procesar la salida del medicamento.");
        }
    }
    
    /**
     * Decrementa la cantidad surtida en el DTO.
     * Se usa cuando el medicamento se elimina del carrito o se cancela la reserva.
     * @param receta Receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad del medicamento al que se le restara la cantidad surtida.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @return Si la operacion fue exitosa.
     */
    protected Boolean sumarMedicamentos(RecetaDTO receta, String idMedicamento, Integer cantidad) throws NegocioException {
        if (receta == null || receta.getDetalles() == null) {
            throw new NegocioException("La receta no puede ser nula o no tener productos.");
        }
        if (idMedicamento == null || cantidad == null || cantidad <= 0) {
            throw new NegocioException("El ID del medicamento o la cantidad ingresada son invalidos.");
        }
        try {
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
        } catch (NegocioException e) {
            logger.log(Level.SEVERE, "Error al sumar medicamentos de la receta.", e);
            throw new NegocioException("Error inesperado al cancelar la reserva del medicamento.");
        }
    }
    
}
    
