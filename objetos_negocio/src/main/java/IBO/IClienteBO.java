package IBO;

import Bo.NegocioException;
import DTO.ClienteDTO;

/**
 *
 * @author Dario
 */
public interface IClienteBO {
    
    public abstract ClienteDTO obtenerCliente(String idCliente)
            throws NegocioException;

    public abstract Double obtenerPuntos(String idCliente)
            throws NegocioException;

    public abstract void actualizarPuntos(String idCliente, Double puntos)
            throws NegocioException;

}
