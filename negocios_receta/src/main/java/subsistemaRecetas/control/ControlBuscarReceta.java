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
    public RecetaDTO obtenerRecetaPorFolio(String folioTexto) {
        // se valida que no llegue vacio
        if (folioTexto == null || folioTexto.trim().isEmpty()) {
            return null;
        }

        try {
            //se convierte el texto string a long
            Long folioNumerico = Long.parseLong(folioTexto.trim());
            
            // se busca en la lista mockeada
            List<RecetaDTO> recetas = recetaBO.getRecetas();
            for (RecetaDTO receta : recetas) {
                // se compara  Long con Long
                if (Objects.equals(receta.getFolio(), folioNumerico)) {
                    return receta;
                }
            }
        } catch (NumberFormatException e) {
            //
            System.err.println("El folio ingresado no es numérico: " + folioTexto);
        }
        
        return null;
    }
    
}
