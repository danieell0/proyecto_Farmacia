package subsistemaRecetas;

import DTO.RecetaDTO;
import Enums.EstadoReceta;

/**
 * Clase Control que se encarga de la gestion del estado de las recetas.
 * @author Dario
 */
public class ControlEstadoReceta {
    
    /**
     * Retorna el estado actual de la receta consultada.
     * @param receta Receta de la cual se obtendra el estado.
     * @return El estado de la receta o null.
     */
    protected EstadoReceta obtenerEstadoDeReceta(RecetaDTO receta) {
        if (receta != null){
            return receta.getEstado();
        }
        return null;
    }
    
    /**
     * Actualiza el estado actual de la reseta consultada.
     * @param receta Receta de la cual se actualizara el estado.
     * @param nuevoEstado Estado que nuevo que se seteara en la receta.
     */
    protected void actualizarEstadoReceta(RecetaDTO receta, EstadoReceta nuevoEstado) {
        if (receta != null) {
            receta.setEstado(nuevoEstado);
        }
    }
    
}
