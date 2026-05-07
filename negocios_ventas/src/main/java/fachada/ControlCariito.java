/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.ProductoDTO;
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

    protected void agregarProductoAlCarrito(DetalleCarritoDTO nuevoDetalle) {
    ProductoDTO producto = nuevoDetalle.getProducto();
    if (producto.getStock() < nuevoDetalle.getCantidad()) {
        System.err.println("Stock insuficiente");
        return; 
    }
    producto.setStock(producto.getStock() - nuevoDetalle.getCantidad());
    for (DetalleCarritoDTO existente : this.carritoActual.getListaProductos()) {
        if (existente.getProducto().getId().equals(producto.getId())) {
            int nuevaCantidad = existente.getCantidad() + nuevoDetalle.getCantidad();
            existente.setCantidad(nuevaCantidad);
            existente.setSubtotal(this.calcularSubtotal(producto.getPrecio(), nuevaCantidad));
            this.actualizarTotalesCarrito(this.carritoActual);
            return;
        }
    }
    nuevoDetalle.setSubtotal(this.calcularSubtotal(producto.getPrecio(), nuevoDetalle.getCantidad()));
    this.carritoActual.getListaProductos().add(nuevoDetalle);
    this.actualizarTotalesCarrito(this.carritoActual);
    }

    protected void devolverTodoElStockTemporal() {
    for (DetalleCarritoDTO detalle : this.carritoActual.getListaProductos()) {
        int cant = detalle.getCantidad();
        detalle.getProducto().setStock(detalle.getProducto().getStock() + cant);
    }
}
    protected void eliminarProductoDelCarrito(Long idProductoAEliminar) {
        this.carritoActual.getListaProductos().forEach(detalle -> {
            if (detalle.getProducto().getId().equals(idProductoAEliminar)) {
                int cantidadARegresar = detalle.getCantidad();
                detalle.getProducto().setStock(detalle.getProducto().getStock() + cantidadARegresar);
            }
        });
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
        if (precioUnitario == null || cantidad == null) {
            return 0.0;
        }
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
    protected Double procesarPago(CarritoDTO carrito, Double cantidadRecibida) {
    Double total = carrito.getTotalAPagar();
    if (cantidadRecibida < total) {
        System.out.println("Dinero insuficiente.");
    }
    return cantidadRecibida - total;
}
    
}
