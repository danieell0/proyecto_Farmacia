/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import com.mycompany.objetos_negocio.VentaBO;
import java.util.List;

/**
 *
 * @author munos
 */
public class FVentas implements IVenta {

    private ControlCariito controlCarrito;
    private ControlCalculos controlCalculos;
    private ControlFinalizarVenta controlFinalizar;
    private ControlRegisitrarVenta controlRegistrar;

    public FVentas() {
        this.controlCarrito = new ControlCariito();
        this.controlCalculos = new ControlCalculos();
        this.controlFinalizar = new ControlFinalizarVenta();
        this.controlRegistrar = new ControlRegisitrarVenta();
    }
    
    @Override
    public void eliminarDelCarrito(Long idProducto) {
        this.controlCarrito.eliminarProductoDelCarrito(idProducto);
    }

    @Override
    public void agregarAlCarrito(DetalleVentaDTO detalle) {
        this.controlCarrito.agregarProductoAlCarrito(detalle);
    }

    @Override
    public CarritoDTO obtenerCarritoActual() {
        return this.controlCarrito.obtenerCarrito();
    }

    @Override
    public void calcularTotal(CarritoDTO carrito) {
        this.controlCalculos.actualizarTotalesCarrito(carrito);
    }

    @Override
    public VentaDTO registrarVenta(CarritoDTO carrito) {
        try {
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, 1L, 1L);
            boolean exito = this.controlRegistrar.registrarVenta(ventaEmpacada);
            if (exito) {
                this.controlCarrito.limpiarCarrito();
                return ventaEmpacada;
            }
        } catch (Exception e) {
            System.err.println("Ocurrió un error al registrar la venta: " + e.getMessage());
        }
        return null; 
    }
}