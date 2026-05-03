package IBO;

import Bo.NegocioException;
import DTO.RecetaDTO;
import Enums.EstadoReceta;

/**
 *
 * @author Dario
 */
public interface IRecetaBO {
    
    public abstract RecetaDTO buscarRecetaPorFolio(String folio) 
            throws NegocioException;

    public abstract void restarMedicamento(String folio, Long idMedicamento, Integer cantidad) 
            throws NegocioException;

    public abstract void sumarMedicamento(String folio, Long idMedicamento, Integer cantidad) 
            throws NegocioException;

    public abstract void actualizarEstado(String folio, EstadoReceta nuevoEstado) 
            throws NegocioException;
}
