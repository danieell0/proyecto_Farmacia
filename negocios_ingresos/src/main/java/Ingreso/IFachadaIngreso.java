package Ingreso;

import DTO.ClienteDTO;

/**
 *
 * @author Dario
 */
public interface IFachadaIngreso {
    
    public abstract ClienteDTO ingresarCliente(String idCliente);
    
    public abstract Double verificarPuntosCliente(String idCliente);
    
    public abstract ClienteDTO limpiarClienteActual();
}
