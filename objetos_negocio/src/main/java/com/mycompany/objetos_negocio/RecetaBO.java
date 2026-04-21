package com.mycompany.objetos_negocio;

import com.mycompany.dto_negocios.RecetaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Dario
 */
public class RecetaBO {
    private List<RecetaDTO> recetas;

    public RecetaBO() {
        this.recetas = new ArrayList<>();
    }



    /**
     * Busca la receta recorriendo la lista uno por uno.
     * @param folioBuscado El folio que el usuario ingresa.
     * @return La receta si el folio coincide, null si no.
     */
    public RecetaDTO buscarPorFolio(String folioBuscado) {
        for (int i = 0; i < recetas.size(); i++) {
            RecetaDTO recetaActual = recetas.get(i);
            
            if (Objects.equals(recetaActual.getFolio(), folioBuscado)) {
                return recetaActual;
            }
        }
        return null;
    }
}
