package IBO;

import Bo.NegocioException;
import DTO.ClienteDTO;

/**
 * Interfaz que define los metodos BO de los clientes.
 * @author Dario
 */
public interface IClienteBO {
    
    /**
     * Obtiene el cliente por su ID.
     * @param idCliente ID del cliente a buscar.
     * @throws NegocioException La causa del error.
     * @return El cliente encontrado.
     */
    public abstract ClienteDTO obtenerCliente(String idCliente)
            throws NegocioException;

    /**
     * Obtiene los puntos del cliente.
     * @param idCliente ID del cliente a consultar sus puntos.
     * @return Puntos del cliente consultado.
     * @throws NegocioException La causa del error.
     */
    public abstract Double obtenerPuntos(String idCliente)
            throws NegocioException;

    /**
     * Actualiza los puntos de un cliente.
     * @param idCliente ID del cliente al actualizar sus puntos.
     * @param puntos Puntos que se daran o quitaran al cliente.
     * @throws NegocioException La causa del error.
     */
    public abstract void actualizarPuntos(String idCliente, Double puntos)
            throws NegocioException;

}
