package subsistemaRecetas;

import Bo.NegocioException;
import DTO.RecetaDTO;
import com.mycompany.objetos_negocio.RecetaBO;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase Control que se encarga de la busqueda de recetas.
 * @author Dario
 */
public class ControlBuscarReceta {
    
    private final RecetaBO recetaBO;
    private static final Logger logger = Logger.getLogger(ControlBuscarReceta.class.getSimpleName());
    
    /**
     * Contructor de la clase ControlBuscarReceta.
     */
    protected ControlBuscarReceta(){
        this.recetaBO = new RecetaBO();
    }
    
     /**
     * Busca la receta recorriendo la lista uno por uno.
     * @param folio El folio que el usuario ingresa.
     * @throws NegocioException La causa del error.
     * @return La receta si el folio coincide, null si no.
     */
    protected RecetaDTO obtenerRecetaPorFolio(String folio) throws NegocioException{
        try {
            return recetaBO.buscarRecetaPorFolio(folio);
        } catch (NegocioException e) {
            logger.log(Level.SEVERE, "Error al buscar la receta.");
            return null;
        }
    }
    
}
