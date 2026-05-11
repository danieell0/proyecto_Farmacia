package Clases;

import Entidades.Receta;

/**
 *
 * @author Dario
 */
public interface IRecetaDAO {
    
    public abstract Receta obtenerRecetaPorFolio(String folio);

    public abstract void actualizarEstadoReceta(Receta receta);

    public abstract void restarMedicamentos(Receta receta, Long idMedicamento, Integer cantidad);

    public abstract void sumarMedicamentos(Receta receta, Long idMedicamento, Integer cantidad);

}
