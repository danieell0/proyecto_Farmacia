package Interfaces;

import Entidades.Cliente;
import Excepciones.PersistenciaException;

/**
 * Interfaz de los metodos de la DAO de clientes.
 * @author Dario
 */
public interface IClienteDAO {

    /**
     * Obtiene el cliente por su ID.
     * @param idCliente ID del cliente a buscar.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @return El cliente encontrado.
     */
    public abstract Cliente obtenerCliente(String idCliente) throws PersistenciaException;

    /**
     * Obtiene los puntos del cliente.
     * @param idCliente ID del cliente a consultar sus puntos.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @return Puntos del cliente consultado.
     */
    public abstract Double obtenerPuntos(String idCliente) throws PersistenciaException;

    /**
     * Actualiza los puntos de un cliente.
     * @param idCliente ID del cliente al actualizar sus puntos.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @param puntos Puntos que se daran o quitaran al cliente.
     */
    public abstract void actualizarPuntos(String idCliente, Double puntos) throws PersistenciaException;

}
