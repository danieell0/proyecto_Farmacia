package Ingreso;

import Bo.NegocioException;
import DTO.ClienteDTO;

/**
 * Fachada del subsistema de ingresos.
 * @author Dario
 */
public class FachadaIngreso implements IFachadaIngreso {
    
    private final ControlIngreso controlIngreso = new ControlIngreso();
    private ClienteDTO clienteActual;
    
    /**
     * Ingresa un cliente al sistema.
     * @param idCliente ID del cliente a ingresar.
     * @return El cliente ingresado.
     */
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
    
    /**
     * Verifica los puntos de un cliente.
     * @param idCliente ID del cliente a consultar.
     * @return Los puntos del cliente consultado.
     */
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
    
    /**
     * Limpiar el cliente actual.
     * @return Cliente.
     */
    @Override
    public ClienteDTO limpiarClienteActual() {
        this.clienteActual = null;
        return null;
    }
    
}
