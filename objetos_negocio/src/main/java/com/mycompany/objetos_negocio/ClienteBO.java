package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.ClienteDAO;
import DTO.ClienteDTO;
import Entidades.Cliente;
import IBO.IClienteBO;
import Interfaces.IClienteDAO;
import Mappers.ClienteMapper;

/**
 *
 * @author Dario
 */
public class ClienteBO implements IClienteBO {
    
    private IClienteDAO clienteDAO;

    public ClienteBO() {
        this.clienteDAO = new ClienteDAO();
    }

    @Override
    public ClienteDTO obtenerCliente(String idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException("El ID del cliente no puede ser nulo.");
        }
        Cliente cliente = clienteDAO.obtenerCliente(idCliente);
        if (cliente == null) {
            throw new NegocioException("No se encontro ningun cliente con ese ID.");
        }
        return ClienteMapper.toDTO(cliente);
    }

    @Override
    public Double obtenerPuntos(String idCliente) throws NegocioException {
        if (idCliente == null) {
            throw new NegocioException("El ID del cliente no puede ser nulo.");
        }
        return clienteDAO.obtenerPuntos(idCliente);
    }

    @Override
    public void actualizarPuntos(String idCliente, Double puntos) throws NegocioException {
        if (idCliente == null || puntos == null) {
            throw new NegocioException("Datos para actualizar puntos invalidos.");
        }
        clienteDAO.actualizarPuntos(idCliente, puntos);
    }
}
