package subsistemaRecetas.control;

import DTO.RecetaDTO;
import com.mycompany.objetos_negocio.RecetaBO;
import java.util.List;
import java.util.Objects;

/**
 * Clase Control que se encarga de la busqueda de recetas.
 * @author Dario
 */
public class ControlBuscarReceta {
    
    private RecetaBO recetaBO;
    
    /**
     * Contructor de la clase ControlBuscarReceta.
     */
    public ControlBuscarReceta(){
        this.recetaBO = new RecetaBO();
    }
    
     /**
     * Busca la receta recorriendo la lista uno por uno.
     * @param folio El folio que el usuario ingresa.
     * @return La receta si el folio coincide, null si no.
     */
    public RecetaDTO obtenerRecetaPorFolio(String folio) {
        List<RecetaDTO> recetas = recetaBO.getRecetas();
        for (RecetaDTO receta : recetas) {
            if (Objects.equals(receta.getFolio(), folio)) {
                return receta;
            }
        }
        return null;
    }
    
}
