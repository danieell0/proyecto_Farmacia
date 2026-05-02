/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import com.mycompany.dto_negocios.DetalleCarritoDTO;
import java.time.LocalDate;

/**
 *
 * @author munos
 */
public class ControlCariito {

    protected CarritoDTO carritoActual;

    protected ControlCariito() {
        this.carritoActual = new CarritoDTO();
        this.carritoActual.setFecha(LocalDate.now());
    }

    public void agregarProductoAlCarrito(DetalleCarritoDTO nuevoDetalle) {

        Double subtotal = this.calcularSubtotal(
                nuevoDetalle.getProducto().getPrecio(),
                nuevoDetalle.getCantidad()
        );
        nuevoDetalle.setSubtotal(subtotal);

        this.carritoActual.getListaProductos().add(nuevoDetalle);

        this.actualizarTotalesCarrito(this.carritoActual);
    }

    protected void eliminarProductoDelCarrito(Long idProductoAEliminar) {

        this.carritoActual.getListaProductos().removeIf(
                detalle -> detalle.getProducto().getId().equals(idProductoAEliminar)
        );
        
        this.actualizarTotalesCarrito(this.carritoActual);
    }

    protected CarritoDTO obtenerCarrito() {
        return this.carritoActual;
    }

    protected void limpiarCarrito() {
        this.carritoActual = new CarritoDTO();
        this.carritoActual.setFecha(LocalDate.now());
    }
    
    protected Double calcularSubtotal(Double precioUnitario, Integer cantidad) {
        if (precioUnitario == null || cantidad == null) return 0.0;
        return precioUnitario * cantidad;
    }
    
    protected void actualizarTotalesCarrito(CarritoDTO carrito) {
        Double totalPagar = 0.0;
        Integer totalArticulos = 0;
        for (DetalleCarritoDTO detalle : carrito.getListaProductos()) {
            totalPagar += detalle.getSubtotal();
            totalArticulos += detalle.getCantidad();
        }    
        carrito.setTotalAPagar(totalPagar);
        carrito.setTotalArticulos(totalArticulos);
    }
}