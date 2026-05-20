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
    private IProductoDAO productoDAO;
    private static final Logger logger = Logger.getLogger(InventarioBO.class.getSimpleName());

    public InventarioBO() {
        inventarioDAO = new InventarioDAO();
        productoDAO = new ProductoDAO();
    }

    @Override
    public SolicitudDTO buscarSolicitud(String claveSolicitud) throws NegocioException {
        try {
            if (claveSolicitud == null || claveSolicitud.trim().isEmpty()) {
                throw new NegocioException("La clave de la solicitud no puede estar vacia.");
            }
            Solicitud solicitud = inventarioDAO.buscarSolicitud(claveSolicitud);
            return SolicitudMapper.toDTO(solicitud);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al buscar la solicitud", ex);
            throw new NegocioException("Error al buscar la solicitud", ex);
        }
    }

    @Override
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException {
        try {
            if (movimiento == null) {
                throw new NegocioException("El movimiento no puede ser null");
            }
            if (movimiento.getFechaHora() == null) {
                throw new NegocioException("La fecha del movimiento es obligatoria");
            }
            if (movimiento.getIdEmpleado() == null || movimiento.getIdEmpleado().trim().isEmpty()) {
                throw new NegocioException("El id del empleado es obligatorio");
            }
            if (movimiento.getLote() == null) {
                throw new NegocioException("El lote es obligatorio");
            }
            if (movimiento.getLote().getCodigoLote() == null || movimiento.getLote().getCodigoLote().trim().isEmpty()) {
                throw new NegocioException("El codigo del lote no puede estar vacio");
            }
            if (movimiento.getLote().getProveedor() == null || movimiento.getLote().getProveedor().trim().isEmpty()) {
                throw new NegocioException("El proveedor es obligatorio");
            }
            if (movimiento.getLote().getDetalles() == null || movimiento.getLote().getDetalles().isEmpty()) {
                throw new NegocioException("El lote debe contener detalles");
            }
            LoteDTO loteExistente = obtenerLote(movimiento.getLote().getCodigoLote());
            if (loteExistente != null) {
                throw new NegocioException("Ya existe un lote con ese codigo");
            }
            for (DetalleLoteDTO detalle : movimiento.getLote().getDetalles()) {
                if (detalle.getProducto() == null) {
                    throw new NegocioException("Todos los detalles deben tener producto");
                }
                if (detalle.getCantidadSolicitada() == null || detalle.getCantidadSolicitada() <= 0) {
                    throw new NegocioException("La cantidad solicitada no puede ser negativa o igual a 0");
                }
                if (detalle.getCantidadRecibida() == null || detalle.getCantidadRecibida() < 0) {
                    throw new NegocioException("La cantidad recibida no puede ser negativa");
                }
                Producto producto = productoDAO.obtenerProductoPorId(detalle.getProducto().getIdProducto());
                if (producto == null) {
                    throw new NegocioException("El producto no existe");
                }
                Integer stockAnterior = producto.getStock();
                Integer nuevoStock = stockAnterior + detalle.getCantidadRecibida();
                detalle.setCantidadAnterior(stockAnterior);
                detalle.setCantidadNueva(nuevoStock);
                Boolean actualizado = productoDAO.aumentarStock(producto.getIdProducto(), nuevoStock);
                if (!actualizado) {
                    throw new NegocioException("Error no se pudo actualizar el stock");
                }
            }
            movimiento.setIdMovimiento(generarIdMovimiento());
            guardarLote(movimiento.getLote());
            MovimientoEntrada entidad = (MovimientoEntrada) MovimientoMapper.toEntity(movimiento);
            return inventarioDAO.crearMovimientoEntrada(entidad);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar movimiento de entrada", ex);
            throw new NegocioException("Error al registrar movimiento de entrada", ex);
        }
    }

    @Override
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException {
        try {
            if (movimiento == null) {
                throw new NegocioException("El movimiento no puede ser null");
            }
            if (movimiento.getFechaHora() == null) {
                throw new NegocioException("La fecha del movimiento es obligatoria");
            }
            if (movimiento.getIdEmpleado() == null || movimiento.getIdEmpleado().trim().isEmpty()) {
                throw new NegocioException("El id del empleado es obligatorio");
            }
            if (movimiento.getProducto() == null) {
                throw new NegocioException("El producto del movimiento es obligatorio");
            }
            if (movimiento.getCantidad() == null || movimiento.getCantidad() <= 0) {
                throw new NegocioException("La cantidad debe ser mayor a cero");
            }
            Producto producto = productoDAO.obtenerProductoPorId(movimiento.getProducto().getIdProducto());
            if (producto == null) {
                throw new NegocioException("El producto no existe");
            }
            Integer stockAnterior = producto.getStock();
            if (movimiento.getCantidad() > stockAnterior) {
                throw new NegocioException("No hay suficiente stock disponible");
            }
            Integer nuevoStock = stockAnterior - movimiento.getCantidad();
            movimiento.setCantidadAnterior(stockAnterior);
            movimiento.setCantidadNueva(nuevoStock);
            movimiento.setIdMovimiento(generarIdMovimiento());
            Boolean actualizado = productoDAO.DisminuirStock(producto.getIdProducto(), nuevoStock);
            if (!actualizado) {
                throw new NegocioException("Error no se pudo actualizar el stock");
            }
            MovimientoSalida entidad = (MovimientoSalida) MovimientoMapper.toEntity(movimiento);
            return inventarioDAO.crearMovimientoSalida(entidad);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al registrar movimiento de salida", ex);
            throw new NegocioException("Error al registrar movimiento de salida", ex);
        }
    }

    @Override
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        try {
            if (codigoLote == null || codigoLote.trim().isEmpty()) {
                throw new NegocioException("El codigo del lote no puede estar vacio");
            }
            Lote lote = inventarioDAO.obtenerLote(codigoLote);
            return LoteMapper.toDTO(lote);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al obtener el lote", ex);
            throw new NegocioException("Error al obtener el lote", ex);
        }
    }

    @Override
    public Boolean guardarLote(LoteDTO lote) throws NegocioException {
        try {
            if (lote == null) {
                throw new NegocioException("El lote no puede ser null");
            }
            if (lote.getCodigoLote() == null || lote.getCodigoLote().trim().isEmpty()) {
                throw new NegocioException("El codigo del lote no puede estar vacio");
            }
            Lote loteEntidad = LoteMapper.toEntity(lote);
            return inventarioDAO.guardarLote(loteEntidad);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al guardar el lote", ex);
            throw new NegocioException("Error al guardar el lote", ex);
        }
    }

    @Override
    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws NegocioException {
        try {
            if (codigoSolicitud == null || codigoSolicitud.trim().isEmpty()) {
                throw new NegocioException("El codigo de la solicitud no puede estar vacio");
            }
            return inventarioDAO.actualizarEstadoSolicitud(codigoSolicitud);
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el estado de la solicitud", ex);
            throw new NegocioException("Error al actualizar el estado de la solicitud", ex);
        }
    }

    @Override
    public String generarIdMovimiento() throws NegocioException {
        try {
            return inventarioDAO.generarIdMovimiento();
        } catch (PersistenciaException ex) {
            logger.log(Level.SEVERE, "Error al generar el id del movimiento", ex);
            throw new NegocioException("Error al generar el id del movimiento", ex);
        }
    }

}
