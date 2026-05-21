/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.InventarioDAO;
import Clases.ProductoDAO;
import ConexionMongo.ManejadorConexiones;
import DTO.DetalleLoteDTO;
import DTO.LoteDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SolicitudDTO;
import Entidades.Lote;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;
import Entidades.Producto;
import Entidades.Solicitud;
import Excepciones.PersistenciaException;
import IBO.IInventarioBO;
import Interfaces.IInventarioDAO;
import Interfaces.IProductoDAO;
import Mappers.LoteMapper;
import Mappers.MovimientoMapper;
import Mappers.SolicitudMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class InventarioBO implements IInventarioBO {

    private IInventarioDAO inventarioDAO;
    private static final Logger logger = Logger.getLogger(InventarioBO.class.getSimpleName());

    public InventarioBO() {
        inventarioDAO = new InventarioDAO();
    }

    @Override
    public SolicitudDTO buscarSolicitud(String claveSolicitud) throws NegocioException {
        try {
            return SolicitudMapper.toDTO(inventarioDAO.buscarSolicitud(claveSolicitud));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al buscar la solicitud");
            throw new NegocioException("Error al buscar la solicitud");
        }
    }

    @Override
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException {
        try {
            MovimientoEntrada entidad = (MovimientoEntrada) MovimientoMapper.toEntity(movimiento);
            return inventarioDAO.crearMovimientoEntrada(entidad);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar el movimiento de entrada");
            throw new NegocioException("Error al registrar el movimiento de entrada");
        }
    }

    @Override
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException {
        try {
            MovimientoSalida salida = (MovimientoSalida) MovimientoMapper.toEntity(movimiento);
            return inventarioDAO.crearMovimientoSalida(salida);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar el movimiento de salida");
            throw new NegocioException("Error al registrar el movimiento de salida");
        }
    }

    @Override
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        try {
            return LoteMapper.toDTO(inventarioDAO.obtenerLote(codigoLote));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al obtener el lote");
            throw new NegocioException("Error al obtener el lote");
        }
    }

    @Override
    public Boolean guardarLote(LoteDTO lote) throws NegocioException {
        try {
            return inventarioDAO.guardarLote(LoteMapper.toEntity(lote));
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al guardar el lote");
            throw new NegocioException("Error al guardar el lote");
        }
    }

    @Override
    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws NegocioException {
        try {
            return inventarioDAO.actualizarEstadoSolicitud(codigoSolicitud);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el estado de la solicitud");
            throw new NegocioException("Error al actualizar el estado de la solicitud");
        }
    }

    @Override
    public String generarIdMovimiento() throws NegocioException {
        try {
            return inventarioDAO.generarIdMovimiento();
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al generar el id del movimiento");
            throw new NegocioException("Error al generar el id del movimiento");
        }
    }
}
