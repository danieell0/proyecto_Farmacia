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
 * Clase que actúa como controlador del módulo de inventario.
 *
 * Se encarga de validar la información recibida y coordinar las operaciones
 * entre la capa de negocio y la presentación.
 *
 * @author Jorge
 */
public class ControlInventario {

    /**
     * Objeto de negocio encargado de gestionar las operaciones relacionadas con
     * inventario.
     */
    private InventarioBO inventarioBO;
    /**
     * Objeto de negocio encargado de gestionar las operaciones relacionadas con
     * productos.
     */
    private ProductoBO productoBO;
    /**
     * Logger utilizado para registrar eventos y errores de la clase
     * ControlInventario.
     */
    private static final Logger logger = Logger.getLogger(ControlInventario.class.getSimpleName());

    /**
     * Constructor de la clase ControlInventario.
     *
     * Inicializa las instancias necesarias de las clases de negocio.
     */
    public ControlInventario() {
        inventarioBO = new InventarioBO();
        productoBO = new ProductoBO();
    }

    /**
     * Busca una solicitud pendiente utilizando su código identificador.
     *
     * Valida que la clave de la solicitud no esté vacía antes de realizar la
     * búsqueda.
     *
     * @param claveSolicitud Código de la solicitud a buscar.
     * @return Solicitud encontrada en formato DTO o {@code null} si no existe o
     * no está pendiente.
     * @throws NegocioException Error al buscar la solicitud.
     */
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

    /**
     * Registra un movimiento de entrada dentro del sistema.
     *
     * Realiza validaciones sobre el movimiento, el lote y los productos
     * asociados. También actualiza el stock de los productos y cambia el estado
     * de la solicitud relacionada.
     *
     * @param movimiento Movimiento de entrada en formato DTO.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de entrada.
     */
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
                if (detalle.getCantidadSolicitada() == null || detalle.getCantidadSolicitada() < 0) {
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
                producto.setStock(nuevoStock);
                detalle.setCantidadAnterior(stockAnterior);
                detalle.setCantidadNueva(nuevoStock);
                detalle.setProducto(producto);
                Boolean actualizado = productoBO.aumentar(producto.getIdProducto(), nuevoStock);
                if (!actualizado) {
                    throw new NegocioException("No se pudo actualizar el stock");
                }
            }
            movimiento.setIdMovimiento(inventarioBO.generarIdMovimiento());
            Boolean loteGuardado = inventarioBO.guardarLote(movimiento.getLote());
            if (!loteGuardado) {
                throw new NegocioException("No se pudo guardar el lote");
            }
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

    /**
     * Registra un movimiento de salida dentro del sistema.
     *
     * Realiza validaciones sobre el movimiento y el stock disponible del
     * producto antes de actualizar las existencias.
     *
     * @param movimiento Movimiento de salida en formato DTO.
     * @return {@code true} si el movimiento fue registrado correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al registrar el movimiento de salida.
     */
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

    /**
     * Obtiene un lote utilizando su código identificador.
     *
     * Valida que el código del lote no esté vacío antes de realizar la
     * búsqueda.
     *
     * @param codigoLote Código del lote a buscar.
     * @return Lote encontrado en formato DTO o {@code null} si no existe.
     * @throws NegocioException Error al obtener el lote.
     */
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

    /**
     * Guarda un lote dentro del sistema.
     *
     * Valida que el lote y su código identificador sean válidos antes de
     * registrarlo.
     *
     * @param lote Lote en formato DTO que se desea registrar.
     * @return {@code true} si el lote fue guardado correctamente, {@code false}
     * en caso contrario.
     * @throws NegocioException Error al guardar el lote.
     */
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

    /**
     * Actualiza el estado de una solicitud.
     *
     * Valida que el código de la solicitud no esté vacío antes de realizar la
     * actualización.
     *
     * @param codigoSolicitud Código de la solicitud a actualizar.
     * @return {@code true} si la solicitud fue actualizada correctamente,
     * {@code false} en caso contrario.
     * @throws NegocioException Error al actualizar el estado de la solicitud.
     */
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

    /**
     * Genera un identificador único para un movimiento.
     *
     * @return Identificador generado para el movimiento.
     * @throws NegocioException Error al generar el identificador del
     * movimiento.
     */
    public String generarIdMovimiento() throws NegocioException {
        try {
            return inventarioBO.generarIdMovimiento();
        } catch (NegocioException ex) {
            logger.log(Level.SEVERE, "Error al generar el id del movimiento", ex);
            throw new NegocioException("Error al generar el id del movimiento", ex);
        }
    }

}
