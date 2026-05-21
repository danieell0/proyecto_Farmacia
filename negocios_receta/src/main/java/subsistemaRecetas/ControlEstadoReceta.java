package subsistemaRecetas;

import Bo.NegocioException;
import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import Enums.EstadoReceta;
import com.mycompany.objetos_negocio.RecetaBO;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Control que se encarga de la gestion del estado de las recetas.
 * @author Dario
 */
public class ControlEstadoReceta {
    
    private final RecetaBO recetaBO;
    private static final Logger logger = Logger.getLogger(ControlEstadoReceta.class.getSimpleName());
    
    /**
     * Contructor de la clase ControlBuscarReceta.
     */
    protected ControlEstadoReceta(){
        this.recetaBO = new RecetaBO();
    }
    
    /**
     * Retorna el estado actual de la receta consultada.
     * @param receta Receta de la cual se obtendra el estado.
     * @throws NegocioException La causa del error en la capa de negocio.d
     * @return El estado de la receta o null.
     */
    protected EstadoReceta obtenerEstadoDeReceta(RecetaDTO receta) throws NegocioException{
        if (receta == null) {
            throw new NegocioException("No se puede obtener el estado de una receta nula.");
        }
        try {
            return receta.getEstado();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener el estado de la receta", e);
            throw new NegocioException("Error al obtener el estado de la receta");
        }
    }
    
    /**
     * Actualiza el estado actual de la reseta consultada.
     * @param receta Receta de la cual se actualizara el estado.
     * @param nuevoEstado Estado que nuevo que se seteara en la receta.
     * @return El nuevo estado de la receta.
     * @throws NegocioException La causa del error en la capa de negocio.
     */
    protected EstadoReceta actualizarEstadoReceta(RecetaDTO receta, EstadoReceta nuevoEstado) throws NegocioException {
        if (receta == null) {
            throw new NegocioException("Ingrese una receta valida.");
        }
        try{
            if (receta.getFechaCaducidad() != null && LocalDate.now().isAfter(receta.getFechaCaducidad())) {
                nuevoEstado = EstadoReceta.CADUCADA; 
            } 
            else {
                boolean todoSurtido = true;
                for (DetalleRecetaDTO detalle : receta.getDetalles()) {
                    if (detalle.getCantidadSurtida() < detalle.getCantidadRecetada()) {
                        todoSurtido = false;
                        break; 
                    }
                }
                if (todoSurtido) {
                    nuevoEstado = EstadoReceta.SURTIDA;
                } else {
                    nuevoEstado = EstadoReceta.ACTIVA;
                }
            }
            recetaBO.actualizarEstado(receta.getFolio(), nuevoEstado);
            receta.setEstado(nuevoEstado);
            return nuevoEstado;
        } catch (NegocioException e) {
            logger.log(Level.SEVERE, "Error al buscar actualizar el estado de la receta.", e);
            throw new NegocioException("Error al buscar actualizar el estado de la receta.");
        }
    }
    
}
