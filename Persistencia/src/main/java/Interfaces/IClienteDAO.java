package Interfaces;

import Entidades.Cliente;

/**
 *
 * @author Dario
 */
public interface IClienteDAO {

    public abstract Cliente obtenerCliente(String idCliente);

    public abstract Double obtenerPuntos(String idCliente);

    public abstract void actualizarPuntos(String idCliente, Double puntos);

}
