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

    private ControlCariito controlCarrito;
    private ControlFinalizarVenta controlFinalizar;

    public FVentas() {
        this.controlCarrito = new ControlCariito();
        this.controlFinalizar = new ControlFinalizarVenta();
    }

    @Override
    public void eliminarDelCarrito(Long idProducto) {
        this.controlCarrito.eliminarProductoDelCarrito(idProducto);
    }

    @Override
    public void agregarAlCarrito(DetalleCarritoDTO detalle) {
        this.controlCarrito.agregarProductoAlCarrito(detalle);
    }

    @Override
    public CarritoDTO obtenerCarritoActual() {
        return this.controlCarrito.obtenerCarrito();
    }

    @Override
    public void calcularTotal(CarritoDTO carrito) {
        this.controlCarrito.actualizarTotalesCarrito(carrito);
    }

    @Override
    public VentaDTO registrarVenta(CarritoDTO carrito) {
        try {
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, 1L, 1L);
            boolean exito = this.controlFinalizar.registrarVenta(ventaEmpacada);
            if (exito) {
                this.controlCarrito.limpiarCarrito();
                return ventaEmpacada;
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error al registrar la venta: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Double finalizarVenta(Double cantidadRecibida, Long idEmpleado, Long idCliente) {
        try {
            CarritoDTO carrito = this.controlCarrito.obtenerCarrito();
            if (carrito == null || carrito.getListaProductos().isEmpty()) {
                System.err.println("Carrito vacio");
                return -1.0;
            }
            Double total = carrito.getTotalAPagar();
            if (cantidadRecibida < total) {
                System.err.println("Error: Dinero insuficiente");
                return -2.0;
            }
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, idEmpleado, idCliente);
            boolean exito = this.controlFinalizar.registrarVenta(ventaEmpacada);
            if (exito) {
                Double cambio = cantidadRecibida - total;
                this.controlCarrito.limpiarCarrito();
                return cambio;
            }
        } catch (Exception e) {
            System.err.println("Error crítico en subsistema ventas: " + e.getMessage());
        }
        return null;
    }
}
