/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTO.DetalleVentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import java.time.LocalDate;

/**
 *
 * @author munos
 */
public class ControlCariito {

    private CarritoDTO carritoActual;
    private ControlCalculos controlCalculos;

    public ControlCariito() {
        this.controlCalculos = new ControlCalculos();
        this.carritoActual = new CarritoDTO();
        this.carritoActual.setFecha(LocalDate.now());
    }

    public void agregarProductoAlCarrito(DetalleVentaDTO nuevoDetalle) {

        Double subtotal = controlCalculos.calcularSubtotal(
                nuevoDetalle.getProducto().getPrecio(),
                nuevoDetalle.getCantidad()
        );
        nuevoDetalle.setSubtotal(subtotal);

        this.carritoActual.getListaProductos().add(nuevoDetalle);

        controlCalculos.actualizarTotalesCarrito(this.carritoActual);
    }

    public void eliminarProductoDelCarrito(Long idProductoAEliminar) {

        this.carritoActual.getListaProductos().removeIf(
                detalle -> detalle.getProducto().getId().equals(idProductoAEliminar)
        );
        controlCalculos.actualizarTotalesCarrito(this.carritoActual);
    }

    public CarritoDTO obtenerCarrito() {
        return this.carritoActual;
    }

    public void limpiarCarrito() {
        this.carritoActual = new CarritoDTO();
        this.carritoActual.setFecha(LocalDate.now());
    }
}
