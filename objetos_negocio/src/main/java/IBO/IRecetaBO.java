package IBO;

import Bo.NegocioException;
import DTO.RecetaDTO;
import Enums.EstadoReceta;

/**
 *
 * @author Dario
 */
public interface IRecetaBO {
    
    /**
     * Obtiene la receta por el folio de esta.
     * @param folio Folio de la receta a encontrar.
     * @return La receta encontrada.
     * @throws NegocioException La causa del error.
     */
    public abstract RecetaDTO buscarRecetaPorFolio(String folio) 
            throws NegocioException;

    /**
     * Resta la cantidad de medicamentos utilizables sumando la cantidad surtida a la receta.
     * @param folio Folio de la receta a la que se le sumara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le sumara la cantidad surtida.
     * @param cantidad Cantidad que se le sumara del medicamento a la cantidad surtida.
     * @return El resultado de la operacion.
     * @throws NegocioException La causa del error.
     */
    public abstract Boolean restarMedicamento(String folio, String idMedicamento, Integer cantidad) 
            throws NegocioException;

    /**
     * Suma la cantidad de medicamentos utilizables resatando la cantidad surtida a la receta.
     * @param folio Folio de la receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad que se le restara del medicamento a la cantidad surtida.
     * @return El resultado de la operacion.
     * @throws NegocioException La causa del error.
     */
    public abstract Boolean sumarMedicamento(String folio, String idMedicamento, Integer cantidad) 
            throws NegocioException;

    /**
     * Se cambia el estado de la receta dependiendo si esta se surtio
     * completamente o a pasado la fecha limite para utilizarla.
     * @param folio Folio de la receta que se le modificara el estado.
     * @param nuevoEstado Nuevo estado que se le asignara a la receta.
     * @return El resultado de la operacion.
     * @throws NegocioException La causa del error.
     */
    public abstract Boolean actualizarEstado(String folio, EstadoReceta nuevoEstado) 
            throws NegocioException;
}
