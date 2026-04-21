package com.mycompany.objetos_negocio;

import com.mycompany.dto_negocios.RecetaDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase BO (base de datos temporal del sistema de las recetas).
 * @author Dario
 */
public class RecetaBO {
    
    private List<RecetaDTO> recetas;
    
    public RecetaBO() {
        this.recetas = new ArrayList<>();
    }
    
    public List<RecetaDTO> getRecetas() {
        return recetas;
    }
    
}
