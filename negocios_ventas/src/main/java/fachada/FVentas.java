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
import DTO.MedicamentoDTO;
import Enums.Especialidades;
import com.mycompany.objetos_negocio.VentaBO;
import java.util.List;
import subsistemaRecetas.FachadaSubsistemaReceta;
import subsistemaRecetas.IFachadaSubsistemaRecetas;

/**
 *
 * @author munos
 */
public class FVentas implements IVenta {

    private final ControlCariito controlCarrito;
    private final ControlFinalizarVenta controlFinalizar;
    
    private final IFachadaSubsistemaRecetas fachadaReceta; 
    private String folioRecetaActual;

    public FVentas() {
        this.controlCarrito = new ControlCariito();
        this.controlFinalizar = new ControlFinalizarVenta();
        this.fachadaReceta = new FachadaSubsistemaReceta();
    }
    
    @Override
    public void setFolioRecetaActual(String folio) {
        this.folioRecetaActual = folio;
    }

    /**
     * Agrega un producto al carrito y descuenta el stock de forma temporal 
     * en el DTO para evitar sobreventas.
     */
    @Override
    public void agregarAlCarrito(DetalleCarritoDTO detalle) {

        ProductoDTO producto = detalle.getProducto();

        if (producto instanceof MedicamentoDTO med && med.isEsControlado()) {

            List<Especialidades> especialidades = med.getEspecialidades();

            if (especialidades == null || especialidades.isEmpty()) {
                throw new RuntimeException("El medicamento no tiene especialidad.");
            }

            Especialidades espProd = especialidades.get(0);

            boolean esValido = fachadaReceta.validarYReservar(
                    this.folioRecetaActual,
                    med.getId(),
                    detalle.getCantidad(),
                    espProd
            );

            if (!esValido) {
                throw new RuntimeException("Sin receta válida.");
            }
        }

        this.controlCarrito.agregarProductoAlCarrito(detalle);
    }
    /**
     * Elimina un producto y devuelve el stock temporal al DTO.
     */
    @Override
    public void eliminarDelCarrito(Long idProducto) {
        DetalleCarritoDTO detalleEncontrado = null;
        for (DetalleCarritoDTO d : this.controlCarrito.obtenerCarrito().getListaProductos()) {
            if (d.getProducto().getId().equals(idProducto)) {
                detalleEncontrado = d;
                break;
            }
        }
        if (detalleEncontrado != null && detalleEncontrado.getProducto() instanceof MedicamentoDTO) {
            MedicamentoDTO med = (MedicamentoDTO) detalleEncontrado.getProducto();
            if (med.isEsControlado() && this.folioRecetaActual != null) {
                this.fachadaReceta.cancelarReserva(this.folioRecetaActual, idProducto, detalleEncontrado.getCantidad());
            }
        }
        this.controlCarrito.eliminarProductoDelCarrito(idProducto);
    }

    /**
     * Cancela toda la operacion actual, devolviendo todo el stock temporal
     * y limpiando el carrito.
     */
    public void cancelarVentaActual() {
        this.fachadaReceta.limpiarRecetasGuardadas();
        this.controlCarrito.devolverTodoElStockTemporal();
        this.controlCarrito.limpiarCarrito();
        this.folioRecetaActual = null;
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

        if (carrito == null || carrito.getListaProductos().isEmpty()) return -1.0;
        Double total = carrito.getTotalAPagar();
        if (cantidadRecibida < total) return -2.0;

        try {
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, idEmpleado, idCliente);
            boolean exito = this.controlFinalizar.registrarVenta(ventaEmpacada);

            if (exito) {
                this.fachadaReceta.confirmarDescuentoReceta();
                this.controlCarrito.limpiarCarrito();
                this.folioRecetaActual = null;
                return cantidadRecibida - total;
            }
        } catch (Exception e) {
            System.err.println("Error en el subsistema de ventas: " + e.getMessage());
        }

        return null;
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
    
    @Override
    public Boolean validarProductoParaVenta(ProductoDTO producto, Integer cantidad) {
        if (this.folioRecetaActual == null || this.folioRecetaActual.isEmpty()) {
            return true;
        }
        if (producto instanceof MedicamentoDTO med && med.isEsControlado()) {
            List<Especialidades> especialidades = med.getEspecialidades();
            if (especialidades == null || especialidades.isEmpty()) {
                return false;
            }
            Especialidades espProducto = especialidades.get(0);
            boolean esValido = fachadaReceta.validarYReservar(
                    this.folioRecetaActual,
                    med.getId(),
                    cantidad,
                    espProducto
            );
            if (!esValido) {
                this.folioRecetaActual = null;
            }
            return esValido;
        }
        return true;
    }
    
    @Override
    public boolean verificarExistenciaReceta(String folio) {
        return fachadaReceta.existeReceta(folio);
    }
}