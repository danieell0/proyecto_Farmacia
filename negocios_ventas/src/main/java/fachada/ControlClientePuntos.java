package fachada;

import Bo.NegocioException;
import DTO.ClienteDTO;
import IBO.IClienteBO;
import com.mycompany.objetos_negocio.ClienteBO;
import java.util.logging.Logger;

/**
 *
 * @author Dario
 */
public class ControlClientePuntos {
    
    private final IClienteBO clienteBO;
    private static final Logger logger = Logger.getLogger(ControlClientePuntos.class.getSimpleName());

    /**
     * Contructor de la clase.
     */
    public ControlClientePuntos() {
        this.clienteBO = new ClienteBO(); 
    }

    /**
     * Obtiene los puntos del cliente.
     * @param idCliente ID del cliente al que se le consultara.
     * @return Los puntos del cliente.
     * @throws NegocioException 
     */
    public Double obtenerPuntosClientes(String idCliente) throws NegocioException {
        if (idCliente == null || idCliente.trim().isEmpty()) {
            throw new NegocioException("El ID del cliente es requerido.");
        }
        Double puntos = clienteBO.obtenerPuntos(idCliente);
        if (puntos == null) {
            throw new NegocioException("El cliente no existe o no tiene puntos.");
        }
        
        return puntos;
    }

    /**
     * Suma puntos acumulados al cliente tras una venta normal.
     * @param idCliente ID del cliente al que se le sumaran puntos.
     * @param puntosASumar puntos a sumar a la cuenta del cliente.
     * @return El cliente con sus puntos sumados.
     * @throws NegocioException 
     */
    public ClienteDTO sumarPuntosClientes(String idCliente, Double puntosASumar) throws NegocioException {
        if (puntosASumar == null || puntosASumar <= 0) {
            throw new NegocioException("La cantidad de puntos a sumar debe ser mayor a cero.");
        }
        Double puntosActuales = clienteBO.obtenerPuntos(idCliente);
        if (puntosActuales == null) {
            throw new NegocioException("No se encontro el cliente.");
        }
        Double nuevosPuntos = puntosActuales + puntosASumar;
        clienteBO.actualizarPuntos(idCliente, nuevosPuntos);
        return clienteBO.obtenerCliente(idCliente); 
    }

    /**
     * Resta puntos al cuenta del cliente cuando realiza un canje.
     * @param idCliente ID del cliente al que se le restaran puntos.
     * @param puntosARestar puntos a restar a la cuenta del cliente.
     * @return El cliente con sus puntos restados.
     * @throws NegocioException 
     */
    public ClienteDTO restarPuntosClientes(String idCliente, Double puntosARestar) throws NegocioException {
        if (puntosARestar == null || puntosARestar <= 0) {
            throw new NegocioException("La cantidad de puntos a restar debe ser mayor a cero.");
        }
        Double puntosActuales = clienteBO.obtenerPuntos(idCliente);
        if (puntosActuales == null) {
            throw new NegocioException("No se encontro el cliente.");
        }
        if (puntosActuales < puntosARestar) {
            throw new NegocioException("Puntos insuficientes. El cliente tiene " + puntosActuales + " puntos y el canje cuesta " + puntosARestar);
        }
        Double nuevosPuntos = puntosActuales - puntosARestar;
        clienteBO.actualizarPuntos(idCliente, nuevosPuntos);
        return clienteBO.obtenerCliente(idCliente);
    }
}