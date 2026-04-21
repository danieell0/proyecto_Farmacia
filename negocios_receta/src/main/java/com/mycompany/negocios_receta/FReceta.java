package com.mycompany.negocios_receta;

import com.mycompany.dto_negocios.RecetaDTO;
import com.mycompany.dto_negocios.enums.Estado;
import com.mycompany.objetos_negocio.RecetaBO;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * Fachada del subsistema de receta.
 * @author Dario
 */
public class FReceta implements IReceta{

    private RecetaBO recetaBO;
    
    /**
     * Contructor de la clase FReceta.
     */
    public FReceta(){
        this.recetaBO = new RecetaBO();
    }
    
    /**
     * Busca la receta recorriendo la lista uno por uno.
     * @param folioBuscado El folio que el usuario ingresa.
     * @return La receta si el folio coincide, null si no.
     */
    @Override
    public RecetaDTO buscarPorFolio(String folioBuscado) {
        List<RecetaDTO> recetas = recetaBO.getRecetas();
        for (int i = 0; i < recetas.size(); i++) {
            RecetaDTO recetaActual = recetas.get(i);
            
            if (Objects.equals(recetaActual.getFolio(), folioBuscado)) {
                return recetaActual;
            }
        }
        return null;
    }

    /**
     * Reduce los usos de la receta y actualiza su estado.
     * @param folio Folio de la receta a la que se le registrara un uso.
     */
    @Override
    public void registrarUsoDeReceta(String folio) throws RecetaException {
        RecetaDTO receta = buscarPorFolio(folio);
        if (receta != null && receta.getUsos() > 0) {
            int nuevosUsos = receta.getUsos() - 1;
            receta.setUsos(nuevosUsos);
            
            if (nuevosUsos == 0) {
                receta.setEstado(Estado.SURTIDA);
            } else {
                receta.setEstado(Estado.ACTIVA); 
            }
        }
    }

    /**
     * Retorna el estado actual de la receta consultada.
     * @param folio Folio de la receta para consultar.
     * @return El estado de la receta o null.
     */
    @Override
    public Estado obtenerEstadoDeReceta(String folio) throws RecetaException {
        RecetaDTO receta = buscarPorFolio(folio);
        if (receta != null) {
            return receta.getEstado();
        }
        return null;
    }

    /**
     * Valida si la receta es apta para usarse basandose en fecha y estado actual.
     * @param receta La receta que se calidara. 
     * @return Si la receta se puede usar o no.
     */
    @Override
    public boolean validarUsoReceta(RecetaDTO receta) throws RecetaException {
        if (receta == null){
            return false;
        } 
        LocalDate hoy = LocalDate.now();
        if (receta.getFechaCaducidad().isBefore(hoy)) {
            receta.setEstado(Estado.CADUCADA);
            return false;
        }
        if (receta.getEstado() == Estado.SURTIDA) {
            return false;
        }
        return receta.getEstado() == Estado.ACTIVA;
    }
    
}
