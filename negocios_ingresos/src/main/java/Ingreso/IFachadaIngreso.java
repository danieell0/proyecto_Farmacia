package Ingreso;

import DTO.ClienteDTO;

/**
 * Interfaz que define los metodos del subsistema de Ingresos.
 * @author Dario
 */
public interface IFachadaIngreso {
    
    /**
     * Ingresa un cliente al sistema.
     * @param idCliente ID del cliente a ingresar.
     * @return El cliente ingresado.
     */
    public abstract ClienteDTO ingresarCliente(String idCliente);
    
    /**
     * Verifica los puntos de un cliente.
     * @param idCliente ID del cliente a consultar.
     * @return Los puntos del cliente consultado.
     */
    public abstract Double verificarPuntosCliente(String idCliente);
    
     /**
     * Limpiar el cliente actual.
     * @return Cliente.
     */
    public abstract ClienteDTO limpiarClienteActual();
}
