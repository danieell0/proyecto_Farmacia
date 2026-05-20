package Ingreso;

import Bo.NegocioException;
import DTO.ClienteDTO;

/**
 *
 * @author Dario
 */
public class FachadaIngreso implements IFachadaIngreso {
    
    private final ControlIngreso controlIngreso = new ControlIngreso();
    private ClienteDTO clienteActual;
    
    @Override
    public ClienteDTO ingresarCliente(String idCliente) {
        try {
            ClienteDTO cliente = controlIngreso.ingresarCliente(idCliente);
            if (cliente != null) {
                this.clienteActual = cliente;
            }
            return cliente;
        } catch (NegocioException e) {
            return null;
        }
    }
    
    @Override
    public Double verificarPuntosCliente(String idCliente) {
        try {
            ClienteDTO cliente = controlIngreso.obtenerCliente(idCliente);
            if (cliente == null) {
                return null;
            }
            return controlIngreso.verificarPuntosCliente(idCliente);
        } catch (NegocioException e) {
            return null;
        }
    }
    
    @Override
    public ClienteDTO limpiarClienteActual() {
        this.clienteActual = null;
        return null;
    }
    
}
