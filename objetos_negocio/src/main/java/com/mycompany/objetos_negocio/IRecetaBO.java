package com.mycompany.objetos_negocio;

import com.mycompany.dto_negocios.RecetaDTO;
import com.mycompany.dto_negocios.enums.Estado;

/**
 * Interfaz que determina los metodos de la clase RecetaBO.
 * @author Dario
 */
public interface IRecetaBO {
    /**
     * Busca la receta recorriendo la lista uno por uno.
     * @param folioBuscado El folio que el usuario ingresa.
     * @return La receta si el folio coincide, null si no.
     */
    public RecetaDTO buscarPorFolio(String folioBuscado)
            throws NegocioException;
    
    /**
     * Reduce los usos de la receta y actualiza su estado.
     * @param folio Folio de la receta a la que se le registrara un uso.
     */
    public void registrarUsoDeReceta(String folio)
            throws NegocioException;
    
    /**
     * Retorna el estado actual de la receta consultada.
     * @param folio Folio de la receta para consultar.
     * @return El estado de la receta o null.
     */
    public Estado obtenerEstadoDeReceta(String folio)
            throws NegocioException;

    /**
     * Valida si la receta es apta para usarse basandose en fecha y estado actual.
     * @param receta La receta que se calidara. 
     * @return Si la receta se puede usar o no.
     */
    public abstract boolean validarUsoReceta(RecetaDTO receta)
            throws NegocioException;
}
