/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

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

    /**
     * Agrega un producto al carrito y descuenta stock temporal.
     *
     * @param nuevoDetalle Producto a agregar.
     */
    protected void agregarProductoAlCarrito(
            DetalleCarritoDTO nuevoDetalle
    ) {

        if (nuevoDetalle == null
                || nuevoDetalle.getProducto() == null
                || nuevoDetalle.getCantidad() == null
                || nuevoDetalle.getCantidad() <= 0) {

            throw new RuntimeException("Detalle inválido.");
        }

        ProductoDTO producto = nuevoDetalle.getProducto();

        // VALIDAR STOCK TEMPORAL
        if (producto.getStock() < nuevoDetalle.getCantidad()) {

            throw new RuntimeException("Stock insuficiente.");
        }

        // DESCONTAR STOCK TEMPORAL
        producto.setStock(
                producto.getStock() - nuevoDetalle.getCantidad()
        );

        // VERIFICAR SI YA EXISTE EN CARRITO
        for (DetalleCarritoDTO existente
                : this.carritoActual.getListaProductos()) {

            if (existente.getProducto()
                    .getIdProducto()
                    .equals(producto.getIdProducto())) {

                int nuevaCantidad
                        = existente.getCantidad()
                        + nuevoDetalle.getCantidad();

                existente.setCantidad(nuevaCantidad);

                existente.setSubtotal(
                        calcularSubtotal(
                                producto.getPrecio(),
                                nuevaCantidad
                        )
                );

                actualizarTotalesCarrito(this.carritoActual);

                return;
            }
        }

        // NUEVO PRODUCTO
        nuevoDetalle.setSubtotal(
                calcularSubtotal(
                        producto.getPrecio(),
                        nuevoDetalle.getCantidad()
                )
        );

        this.carritoActual
                .getListaProductos()
                .add(nuevoDetalle);

        actualizarTotalesCarrito(this.carritoActual);
    }

    /**
     * Devuelve todo el stock reservado temporalmente.
     */
    protected void devolverTodoElStockTemporal() {

        for (DetalleCarritoDTO detalle
                : this.carritoActual.getListaProductos()) {

            int cantidad = detalle.getCantidad();

            detalle.getProducto().setStock(
                    detalle.getProducto().getStock() + cantidad
            );
        }
    }

    /**
     * Elimina un producto del carrito y regresa stock temporal.
     *
     * @param idProductoAEliminar Producto a eliminar.
     */
    protected void eliminarProductoDelCarrito(
            String idProductoAEliminar
    ) {

        for (DetalleCarritoDTO detalle
                : this.carritoActual.getListaProductos()) {

            if (detalle.getProducto()
                    .getIdProducto()
                    .equals(idProductoAEliminar)) {

                int cantidadARegresar = detalle.getCantidad();

                detalle.getProducto().setStock(
                        detalle.getProducto().getStock()
                        + cantidadARegresar
                );
            }
        }

        this.carritoActual.getListaProductos().removeIf(
                detalle -> detalle.getProducto()
                        .getIdProducto()
                        .equals(idProductoAEliminar)
        );

        actualizarTotalesCarrito(this.carritoActual);
    }

    /**
     * Obtiene el carrito actual.
     *
     * @return Carrito actual.
     */
    protected CarritoDTO obtenerCarrito() {
        return this.carritoActual;
    }

    /**
     * Limpia el carrito actual.
     */
    protected void limpiarCarrito() {

        this.carritoActual = new CarritoDTO();

        this.carritoActual.setFecha(LocalDate.now());
    }

    /**
     * Calcula subtotal.
     *
     * @param precioUnitario Precio unitario.
     * @param cantidad Cantidad.
     * @return subtotal.
     */
    protected Double calcularSubtotal(
            Double precioUnitario,
            Integer cantidad
    ) {

        if (precioUnitario == null || cantidad == null) {
            return 0.0;
        }

        return precioUnitario * cantidad;
    }

    /**
     * Actualiza total del carrito.
     *
     * @param carrito Carrito actual.
     */
    protected void actualizarTotalesCarrito(
            CarritoDTO carrito
    ) {

        Double totalPagar = 0.0;

        Integer totalArticulos = 0;

        for (DetalleCarritoDTO detalle
                : carrito.getListaProductos()) {

            totalPagar += detalle.getSubtotal();

            totalArticulos += detalle.getCantidad();
        }

        carrito.setTotalAPagar(totalPagar);

        carrito.setTotalArticulos(totalArticulos);
    }
}
