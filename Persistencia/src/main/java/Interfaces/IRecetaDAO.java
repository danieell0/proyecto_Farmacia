package Interfaces;

import Entidades.Receta;
import Excepciones.PersistenciaException;

/**
 * Interfaz de los metodos de la DAO de receta.
 * @author Dario
 */
public interface IRecetaDAO {
    
    /**
     * Obtiene la receta por el folio de esta.
     * @param folio Folio de la receta a encontrar.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @return La receta encontrada.
     */
    public abstract Receta obtenerRecetaPorFolio(String folio) throws PersistenciaException;
    
    /**
     * Se cambia el estado de la receta dependiendo si esta se surtio
     * completamente o a pasado la fecha limite para utilizarla.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @param receta Receta que se modificara el estado.
     */
    public abstract void actualizarEstadoReceta(Receta receta) throws PersistenciaException;
    
    /**
     * Resta la cantidad de medicamentos utilizables sumando la cantidad surtida a la receta.
     * @param receta Receta a la que se le sumara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le sumara la cantidad surtida.
     * @param cantidad Cantidad que se le sumara del medicamento a la cantidad surtida.
     * @throws PersistenciaException Error al ejecutar la operacion.
     */
    public abstract void restarMedicamentos(Receta receta, String idMedicamento, Integer cantidad) throws PersistenciaException;
    
    /**
     * Suma la cantidad de medicamentos utilizables resatando la cantidad surtida a la receta.
     * @param receta Receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad que se le restara del medicamento a la cantidad surtida.
     * @throws PersistenciaException Error al ejecutar la operacion.
     */
    public abstract void sumarMedicamentos(Receta receta, String idMedicamento, Integer cantidad) throws PersistenciaException;
}
