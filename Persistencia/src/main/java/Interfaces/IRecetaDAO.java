package Interfaces;

import Entidades.Receta;

/**
 *
 * @author Dario
 */
public interface IRecetaDAO {
    
    /**
     * Obtiene la receta por el folio de esta.
     * @param folio Folio de la receta a encontrar.
     * @return La receta encontrada.
     */
    public abstract Receta obtenerRecetaPorFolio(String folio);
    
    /**
     * Se cambia el estado de la receta dependiendo si esta se surtio
     * completamente o a pasado la fecha limite para utilizarla.
     * @param receta Receta que se modificara el estado.
     */
    public abstract void actualizarEstadoReceta(Receta receta);
    
    /**
     * Resta la cantidad de medicamentos utilizables sumando la cantidad surtida a la receta.
     * @param receta Receta a la que se le sumara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le sumara la cantidad surtida.
     * @param cantidad Cantidad que se le sumara del medicamento a la cantidad surtida.
     */
    public abstract void restarMedicamentos(Receta receta, String idMedicamento, Integer cantidad);
    
    /**
     * Suma la cantidad de medicamentos utilizables resatando la cantidad surtida a la receta.
     * @param receta Receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad que se le restara del medicamento a la cantidad surtida.
     */
    public abstract void sumarMedicamentos(Receta receta, String idMedicamento, Integer cantidad);
}
