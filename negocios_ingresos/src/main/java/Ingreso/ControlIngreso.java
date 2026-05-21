package Ingreso;

import Bo.NegocioException;
import DTO.ClienteDTO;
import com.mycompany.objetos_negocio.ClienteBO;

/**
 * Clase control que se encarga de las operaciones de clientes.
 * @author Dario
 */
public class ControlIngreso {
    
    private final ClienteBO clienteBO;
    
    /**
     * Contructor de la clase.
     */
    protected ControlIngreso() {
        this.clienteBO = new ClienteBO();
    }
    
    /**
     * Metodo control de preparacion para ingresar al cliente.
     * @param idCliente ID del cliente a ingresar.
     * @return El cliente ingresado.
     * @throws NegocioException Error en la capa de negocio.
     */
    protected ClienteDTO ingresarCliente(String idCliente) throws NegocioException {
        return clienteBO.obtenerCliente(idCliente);
    }
    
    /**
     * Metodo control de preparacion para verificar los puntos del cliente.
     * @param idCliente ID del cliente a consultar.
     * @return El cliente a consultar.
     * @throws NegocioException Error en la capa de negocio.
     */
    protected ClienteDTO obtenerCliente(String idCliente) throws NegocioException {
        return clienteBO.obtenerCliente(idCliente);
    }
    
    /**
     * Metodo control de preparacion para verificar los puntos del cliente.
     * @param idCliente ID del cliente a consultar.
     * @return Los puntos del cliente consultado.
     * @throws NegocioException Error en la capa de persistencia.
     */
    protected Double verificarPuntosCliente(String idCliente) throws NegocioException {
        return clienteBO.obtenerPuntos(idCliente);
    }
    
    /**
     * Limpia el cliente actual.
     * @return Cliente.
     */
    protected ClienteDTO limpiarClienteActual() {
        return null;
    }
}
