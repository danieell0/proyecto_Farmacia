package fachada;

import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.DetalleVentaDTO;
import DTO.VentaDTO;
import Enums.TipoVenta;
import IBO.IVentaBO;
import com.mycompany.objetos_negocio.VentaBO;
import exception.VentaException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase control que se encarga de las operaciones de la venta.
 * @author munos
 */
public class ControlFinalizarVenta {

    private IVentaBO ventaBO;
    private IVentaStrategy ventaStrategy;
    

    /**
     * Contructor de la clase.
     */
    protected ControlFinalizarVenta() {
        this.ventaBO = VentaBO.getInstance();
    }

    /**
     * Registra definitivamente la venta.
     * @param ventaFinal Venta preparada.
     * @return true si se registró correctamente.
     * @throws VentaException Error al registrar.
     */
    protected boolean registrarVenta(VentaDTO ventaFinal) throws VentaException {
        if (ventaFinal == null) {
            throw new VentaException("La venta no puede ser nula.");
        }
        try {
            return ventaBO.agregarVenta(ventaFinal);
        } catch (Exception e) {
            throw new VentaException("Error al registrar la venta: " + e.getMessage());
        }
    }

    /**
     * Prepara el carrito para una venta.
     * @param idEmpleado ID del empleado asociado.
     * @param idCliente ID del cliente asociado.
     * @param carrito Conjunto de productos que se venderan.
     * @return La venta lista nomas para persistir si se confirma.
     * @throws VentaException 
     */
    protected VentaDTO prepararVenta(CarritoDTO carrito, String idEmpleado, String idCliente, String tipoPago) throws VentaException {
        if (carrito == null || carrito.getListaProductos() == null || carrito.getListaProductos().isEmpty()) {
            throw new VentaException("No se puede finalizar la venta porque el carrito esta vacio.");
        }
        if (carrito.getTotalAPagar() == null || carrito.getTotalAPagar() <= 0) {
            throw new VentaException("El total a pagar de los productos debe ser mayor a cero.");
        }
        if (idEmpleado == null) {
            throw new VentaException("El empleado es obligatorio.");
        }

        boolean esPagoPuntos = "PUNTOS".equalsIgnoreCase(tipoPago);
        
        if (esPagoPuntos) {
            if (idCliente == null || idCliente.trim().isEmpty() || "0".equals(idCliente)) {
                throw new VentaException("Es obligatorio seleccionar un cliente registrado para realizar un canje de puntos.");
            }
        } else {
            if (idCliente == null || idCliente.trim().isEmpty()) {
                idCliente = "0";
            }
        }

        VentaDTO nuevaVenta = new VentaDTO();
        nuevaVenta.setFecha(LocalDateTime.now());
        nuevaVenta.setTotal(carrito.getTotalAPagar());
        nuevaVenta.setIdEmpleado(idEmpleado);
        nuevaVenta.setIdCliente(idCliente);
        nuevaVenta.setTotal(carrito.getTotalAPagar());
    
        if (esPagoPuntos) {
            nuevaVenta.setTipo(TipoVenta.PUNTOS);
            nuevaVenta.setPuntosGenerados(0.0);
        } else {
            nuevaVenta.setTipo(TipoVenta.NORMAL);
            nuevaVenta.setPuntosGenerados(carrito.getTotalAPagar() * 0.10); 
        }
        
        List<DetalleVentaDTO> detallesVenta = new ArrayList<>();

        for (DetalleCarritoDTO itemCarrito : carrito.getListaProductos()) {
            DetalleVentaDTO itemVenta = new DetalleVentaDTO();
            itemVenta.setProducto(itemCarrito.getProducto());
            itemVenta.setCantidad(itemCarrito.getCantidad());
            itemVenta.setPrecioUnitario(itemCarrito.getPrecioUnitario());
            itemVenta.setSubtotal(itemCarrito.getSubtotal());
            detallesVenta.add(itemVenta);
        }
        nuevaVenta.setDetalles(detallesVenta);
        return nuevaVenta;
    }
    
    /**
     * Ejecuta la finalizacion de una venta.
     * @param monto Monto de la venta.
     * @param idEmpleado ID del empleado que realizo la venta.
     * @param idCliente ID del cliente que realizo la compra.
     * @param carrito Conjunto de productos que se venderan.
     * @return La feria de la venta o los puntos del cliente,
     * dependiendo el tipo de venta.
     * @throws Exception 
     */
    public Double ejecutarFinalizacionConStrategy(Double monto, String idEmpleado, String idCliente, CarritoDTO carrito) throws Exception {
        String tipoPago = "EFECTIVO"; 
        if (this.ventaStrategy.getClass().getSimpleName().toLowerCase().contains("puntos")) {
            tipoPago = "PUNTOS";
        }
        VentaDTO ventaPreparada = this.prepararVenta(carrito, idEmpleado, idCliente, tipoPago);
        this.registrarVenta(ventaPreparada);
        return this.ventaStrategy.finalizarVenta(monto, idCliente, carrito);
    }
}