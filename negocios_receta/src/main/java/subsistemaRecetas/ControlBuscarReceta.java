package subsistemaRecetas;

import Bo.NegocioException;
import DTO.RecetaDTO;
import com.mycompany.objetos_negocio.RecetaBO;

/**
 * Clase Control que se encarga de la busqueda de recetas.
 * @author Dario
 */
public class ControlBuscarReceta {
    
    protected RecetaBO recetaBO;
    
    /**
     * Contructor de la clase ControlBuscarReceta.
     */
    protected ControlBuscarReceta(){
        this.recetaBO = new RecetaBO();
    }
    
     /**
     * Busca la receta recorriendo la lista uno por uno.
     * @param folio El folio que el usuario ingresa.
     * @return La receta si el folio coincide, null si no.
     */
    protected RecetaDTO obtenerRecetaPorFolio(String folio) {
        try {
            return recetaBO.buscarRecetaPorFolio(folio);
        } catch (NegocioException ex) {
            return null;
        }
    }
    
}
