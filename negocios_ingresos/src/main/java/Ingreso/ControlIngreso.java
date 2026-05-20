package Ingreso;

import Bo.NegocioException;
import DTO.ClienteDTO;
import com.mycompany.objetos_negocio.ClienteBO;

/**
 *
 * @author Dario
 */
public class ControlIngreso {
    
    protected ClienteBO clienteBO;
    
    protected ControlIngreso() {
        this.clienteBO = new ClienteBO();
    }
    
    protected ClienteDTO ingresarCliente(String idCliente) throws NegocioException {
        return clienteBO.obtenerCliente(idCliente);
    }
    
    protected ClienteDTO obtenerCliente(String idCliente) throws NegocioException {
        return clienteBO.obtenerCliente(idCliente);
    }
    
    protected Double verificarPuntosCliente(String idCliente) throws NegocioException {
        return clienteBO.obtenerPuntos(idCliente);
    }
    
    protected ClienteDTO limpiarClienteActual() {
        return null;
    }
}
