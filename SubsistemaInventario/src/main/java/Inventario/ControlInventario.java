/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Inventario;

import Bo.NegocioException;
import DTO.DetalleLoteDTO;
import DTO.LoteDTO;
import DTO.MovimientoDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.ProductoDTO;
import DTO.SolicitudDTO;
import Entidades.Solicitud;
import IBO.IInventarioBO;
import IBO.IProductoBO;
import com.mycompany.objetos_negocio.InventarioBO;
import com.mycompany.objetos_negocio.ProductoBO;
import excepciones.NegocioExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class ControlInventario {

    private InventarioBO inventarioBO;
    private ProductoBO productoBO;
    private static final Logger logger = Logger.getLogger(ControlInventario.class.getSimpleName());

    public ControlInventario() {
        inventarioBO = new InventarioBO();
        productoBO = new ProductoBO();
    }

    public SolicitudDTO buscarSolicitud(String claveSolicitud) throws NegocioException {
        try {
            if (claveSolicitud == null || claveSolicitud.trim().isEmpty()) {
                throw new NegocioException("La clave de la solicitud no puede estar vacia.");
            }
            return inventarioBO.buscarSolicitud(claveSolicitud);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al buscar la solicitud", ex);
            throw new NegocioException("Error al buscar la solicitud", ex);
        }
    }

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
            if (movimiento.getCodigoSolicitud() == null || movimiento.getCodigoSolicitud().trim().isEmpty()) {
                throw new NegocioException("El codigo de solicitud es obligatorio");
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
            LoteDTO loteExistente = inventarioBO.obtenerLote(movimiento.getLote().getCodigoLote());
            if (loteExistente != null) {
                throw new NegocioException("Ya existe un lote con ese codigo");
            }
            for (DetalleLoteDTO detalle : movimiento.getLote().getDetalles()) {
                if (detalle.getProducto() == null) {
                    throw new NegocioException("Todos los detalles deben tener producto");
                }
                if (detalle.getCantidadSolicitada() == null || detalle.getCantidadSolicitada() <0) {
                    throw new NegocioException("La cantidad solicitada debe ser mayor a cero");
                }
                if (detalle.getCantidadRecibida() == null || detalle.getCantidadRecibida() < 0) {
                    throw new NegocioException("La cantidad recibida no puede ser negativa");
                }
                ProductoDTO producto = productoBO.obtenerProducto(detalle.getProducto().getIdProducto());
                if (producto == null) {
                    throw new NegocioException("El producto no existe");
                }
                Integer stockAnterior = producto.getStock();
                Integer nuevoStock = stockAnterior + detalle.getCantidadRecibida();
                detalle.setCantidadAnterior(stockAnterior);
                detalle.setCantidadNueva(nuevoStock);
                Boolean actualizado = productoBO.aumentar(producto.getIdProducto(), nuevoStock);
                if (!actualizado) {
                    throw new NegocioException("No se pudo actualizar el stock");
                }
            }
            movimiento.setIdMovimiento(inventarioBO.generarIdMovimiento());
            inventarioBO.guardarLote(movimiento.getLote());
            Boolean registrado = inventarioBO.registrarMovimientoEntrada(movimiento);
            if (registrado) {
                Boolean estadoActualizado = inventarioBO.actualizarEstadoSolicitud(movimiento.getCodigoSolicitud());
                if (!estadoActualizado) {
                    throw new NegocioException("No se pudo actualizar el estado de la solicitud");
                }
            }
            return registrado;
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al registrar movimiento de entrada", ex);
            throw new NegocioException("Error al registrar movimiento de entrada", ex);
        }
    }

    public Boolean registrarMovimientoSalida(
            MovimientoSalidaDTO movimiento) throws NegocioException {
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
            ProductoDTO producto = productoBO.obtenerProducto(movimiento.getProducto().getIdProducto());
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
            movimiento.setIdMovimiento(inventarioBO.generarIdMovimiento()
            );
            Boolean actualizado = productoBO.disminuir(producto.getIdProducto(), nuevoStock);
            if (!actualizado) {
                throw new NegocioException("No se pudo actualizar el stock");
            }
            return inventarioBO.registrarMovimientoSalida(movimiento);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al registrar movimiento de salida", ex);
            throw new NegocioException("Error al registrar movimiento de salida", ex);
        }
    }

    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        try {
            if (codigoLote == null || codigoLote.trim().isEmpty()) {
                throw new NegocioException("El codigo del lote no puede estar vacio");
            }
            return inventarioBO.obtenerLote(codigoLote);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al obtener el lote", ex);
            throw new NegocioException("Error al obtener el lote", ex);
        }
    }

    public Boolean guardarLote(LoteDTO lote) throws NegocioException {
        try {
            if (lote == null) {
                throw new NegocioException("El lote no puede ser null");
            }
            if (lote.getCodigoLote() == null || lote.getCodigoLote().trim().isEmpty()) {
                throw new NegocioException("El codigo del lote no puede estar vacio");
            }
            return inventarioBO.guardarLote(lote);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al guardar el lote", ex);
            throw new NegocioException("Error al guardar el lote", ex);
        }
    }

    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws NegocioException {
        try {
            if (codigoSolicitud == null || codigoSolicitud.trim().isEmpty()) {
                throw new NegocioException("El codigo de la solicitud no puede estar vacio");
            }
            return inventarioBO.actualizarEstadoSolicitud(codigoSolicitud);
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al actualizar el estado de la solicitud", ex);
            throw new NegocioException("Error al actualizar el estado de la solicitud", ex);
        }
    }

    public String generarIdMovimiento() throws NegocioException {
        try {
            return inventarioBO.generarIdMovimiento();
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al generar el id del movimiento", ex);
            throw new NegocioException("Error al generar el id del movimiento", ex);
        }
    }

}
