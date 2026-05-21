package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.ClienteDAO;
import DTO.ClienteDTO;
import Entidades.Cliente;
import Excepciones.PersistenciaException;
import IBO.IClienteBO;
import Interfaces.IClienteDAO;
import Mappers.ClienteMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase BO con validaciones minimmas para las operaciones de los clientes.
 * @author Dario
 */
public class ClienteBO implements IClienteBO {
    
    private final IClienteDAO clienteDAO;
    private static final Logger logger = Logger.getLogger(ClienteBO.class.getSimpleName());

    /**
     * Contructor de la clase.
     */
    public ClienteBO() {
        this.clienteDAO = new ClienteDAO();
    }

    /**
     * Obtiene el cliente por su ID.
     * @param idCliente ID del cliente a buscar.
     * @throws NegocioException La causa del error.
     * @return El cliente encontrado.
     */
    @Override
    public ClienteDTO obtenerCliente(String idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException("El ID del cliente no puede ser nulo.");
        }
        try {
            Cliente cliente = clienteDAO.obtenerCliente(idCliente);
            if (cliente == null) {
                throw new NegocioException("No se encontro ningun cliente con ese ID.");
            }
            return ClienteMapper.toDTO(cliente);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al buscar el cliente", e);
            throw new NegocioException("Error al buscar el cliente", e);
        }
    }

    /**
     * Obtiene los puntos del cliente.
     * @param idCliente ID del cliente a consultar sus puntos.
     * @return Puntos del cliente consultado.
     * @throws NegocioException La causa del error.
     */
    @Override
    public Double obtenerPuntos(String idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException("El ID del cliente no puede ser nulo.");
        }
        try {
            return clienteDAO.obtenerPuntos(idCliente);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al obtener puntos del cliente", e);
            throw new NegocioException("Error al obtener puntos del cliente", e);
        } 
    }

    /**
     * Actualiza los puntos de un cliente.
     * @param idCliente ID del cliente al actualizar sus puntos.
     * @param puntos Puntos que se daran o quitaran al cliente.
     * @throws NegocioException La causa del error.
     */
    @Override
    public void actualizarPuntos(String idCliente, Double puntos) throws NegocioException {
        if (idCliente == null || puntos == null) {
            throw new NegocioException("Datos para actualizar puntos invalidos.");
        }
        try {
            clienteDAO.actualizarPuntos(idCliente, puntos);
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error al actualizar puntos del cliente", e);
            throw new NegocioException("Error al actualizar puntos del cliente", e);
        }
    }
}
