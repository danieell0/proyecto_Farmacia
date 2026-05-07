/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import com.mycompany.objetos_negocio.VentaBO;
import java.util.List;

/**
 *
 * @author munos
 */
public class FVentas implements IVenta {

    private final ControlCariito controlCarrito;
    private final ControlFinalizarVenta controlFinalizar;

    public FVentas() {
        this.controlCarrito = new ControlCariito();
        this.controlFinalizar = new ControlFinalizarVenta();
    }

    /**
     * Agrega un producto al carrito y descuenta el stock de forma temporal 
     * en el DTO para evitar sobreventas.
     */
    @Override
    public void agregarAlCarrito(DetalleCarritoDTO detalle) {
        // El control se encarga de la logica de stock temporal y agrupacion
        this.controlCarrito.agregarProductoAlCarrito(detalle);
    }

    /**
     * Elimina un producto y devuelve el stock temporal al DTO.
     */
    @Override
    public void eliminarDelCarrito(Long idProducto) {
        this.controlCarrito.eliminarProductoDelCarrito(idProducto);
    }

    /**
     * Cancela toda la operacion actual, devolviendo todo el stock temporal
     * y limpiando el carrito.
     */
    public void cancelarVentaActual() {
        this.controlCarrito.devolverTodoElStockTemporal();
        this.controlCarrito.limpiarCarrito();
    }

    @Override
    public CarritoDTO obtenerCarritoActual() {
        return this.controlCarrito.obtenerCarrito();
    }

    @Override
    public void calcularTotal(CarritoDTO carrito) {
        this.controlCarrito.actualizarTotalesCarrito(carrito);
    }

    /**
     * Procesa la finalizacion de la compra.
     * Valida el pago, registra en la base de datos (descuento definitivo) 
     * y limpia el carrito.
     * * @return El cambio a entregar o codigos de error (-1: Vacio, -2: Dinero insuficiente)
     */
    @Override
    public Double finalizarVenta(Double cantidadRecibida, Long idEmpleado, Long idCliente) {
        CarritoDTO carrito = this.controlCarrito.obtenerCarrito();

        // 1. Validacion de negocio: Carrito vacio
        if (carrito == null || carrito.getListaProductos().isEmpty()) {
            return -1.0; 
        }

        // 2. Validacion de negocio: Pago suficiente
        Double total = carrito.getTotalAPagar();
        if (cantidadRecibida < total) {
            return -2.0; 
        }

        try {
            // 3. Orquestacion de la persistencia
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, idEmpleado, idCliente);
            
            // Al registrar, el VentaBO hara el descuento de stock definitivo en las entidades
            boolean exito = this.controlFinalizar.registrarVenta(ventaEmpacada);

            if (exito) {
                this.controlCarrito.limpiarCarrito(); // Se limpia porque la venta fue exitosa
                return cantidadRecibida - total;
            }
        } catch (Exception e) {
            System.err.println("Error en el subsistema de ventas: " + e.getMessage());
        }

        return null; // Error interno
    }

    /**
     * Metodo legado para compatibilidad, delega a finalizarVenta.
     */
    @Override
    public VentaDTO registrarVenta(CarritoDTO carrito) {
        try {
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, 1L, 1L);
            if (this.controlFinalizar.registrarVenta(ventaEmpacada)) {
                this.controlCarrito.limpiarCarrito();
                return ventaEmpacada;
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }
}