/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import Bo.NegocioException;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import Enums.Especialidades;
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
     * @throws NegocioException La causa del error en la capa de negocio.
     * @param detalle La especificacion de cantidad y producto que se agregara al carrito.
     */
    @Override
    public void agregarAlCarrito(DetalleCarritoDTO detalle) throws NegocioException{
        ProductoDTO producto = detalle.getProducto();

    if (producto.getStock() < detalle.getCantidad()) {
        throw new NegocioException("No hay suficiente stock para: " + producto.getNombre());
    }

    if (producto instanceof MedicamentoDTO med && med.getEsControlado()) {

        String folioEncontrado = null;
        if (med.getEspecialidades() != null) {
            for (Especialidades esp : med.getEspecialidades()) {
                folioEncontrado = fachadaReceta.buscarEnRecetasActivas(
                    med.getIdProducto(), detalle.getCantidad(), esp
                );
                if (folioEncontrado != null) break;
            }
        }
        if (folioEncontrado == null) {
            if (this.folioRecetaActual == null || this.folioRecetaActual.trim().isEmpty()) {
                throw new NegocioException("Es obligatorio ingresar un folio de receta para: " + med.getNombre());
            }

            boolean esValido = false;
            NegocioException ultimaExcepcion = null;

            if (med.getEspecialidades() != null) {
                for (Especialidades esp : med.getEspecialidades()) {
                    try {
                        if (fachadaReceta.validarYReservar(
                                this.folioRecetaActual,
                                med.getIdProducto(),
                                detalle.getCantidad(),
                                esp)) {
                            esValido = true;
                            break;
                        }
                    } catch (NegocioException e) {
                        ultimaExcepcion = e;
                    }
                }
            }

            if (!esValido) {
                String mensaje = ultimaExcepcion != null
                    ? ultimaExcepcion.getMessage()
                    : "La receta [" + this.folioRecetaActual + "] no es válida para: " + med.getNombre();
                throw new NegocioException(mensaje);
            }
        }
    }

    this.controlCarrito.agregarProductoAlCarrito(detalle);
    }



    /**
     * Elimina un producto y devuelve el stock temporal al DTO.
     * @throws NegocioException La causa del error en la capa de negocio.
     * @param idProducto el producto a eliminarse del carrito.
     */
    @Override
    public void eliminarDelCarrito(String idProducto) throws NegocioException {
        DetalleCarritoDTO detalleEncontrado = null;
        for (DetalleCarritoDTO d : this.controlCarrito.obtenerCarrito().getListaProductos()) {
            if (d.getProducto().getIdProducto().equals(idProducto)) {
                detalleEncontrado = d;
                break;
            }
        }
        if (detalleEncontrado != null && detalleEncontrado.getProducto() instanceof MedicamentoDTO med) {
            if (med.getEsControlado()&& this.folioRecetaActual != null) {
                this.fachadaReceta.cancelarReserva(this.folioRecetaActual, idProducto, detalleEncontrado.getCantidad());
            }
        }
        this.controlCarrito.eliminarProductoDelCarrito(idProducto);
    }


    /**
     * Cancela venta actual. Devuelve stock temporal y limpia carrito.
     */
    @Override
    public void cancelarVentaActual() {
        this.fachadaReceta.limpiarRecetasGuardadas();
        this.controlCarrito.devolverTodoElStockTemporal();
        this.controlCarrito.limpiarCarrito();
        this.folioRecetaActual = null;
    }

    /**
     * Obtiene carrito actual.
     *
     * @return carrito.
     */
    @Override
    public CarritoDTO obtenerCarritoActual() {
        return this.controlCarrito
                .obtenerCarrito();
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

    /**
     * Finaliza venta. El stock definitivo se descuenta en BO.
     *
     * @param cantidadRecibida Pago recibido.
     * @param idEmpleado Empleado.
     * @param idCliente Cliente.
     * @return cambio.
     */
    @Override
    public Double finalizarVenta(
            Double cantidadRecibida,
            Long idEmpleado,
            Long idCliente
    ) {
        try {
            CarritoDTO carrito = this.controlCarrito.obtenerCarrito();
            Double total = carrito.getTotalAPagar();
            if (cantidadRecibida < total) {
                throw new RuntimeException("Dinero insuficiente.");
            }
            VentaDTO ventaPreparada= this.controlFinalizar.prepararVenta(carrito,idEmpleado,idCliente);
            boolean exito = this.controlFinalizar.registrarVenta(ventaPreparada);
            if (!exito) {
                throw new RuntimeException("No se pudo registrar la venta.");
            }
            this.fachadaReceta.confirmarDescuentoReceta();
            this.controlCarrito.limpiarCarrito();
            this.folioRecetaActual = null;
            return cantidadRecibida - total;
        } catch (Exception e) {
            throw new RuntimeException("Error al finalizar venta: "+ e.getMessage());
        }
    }

    /**
     * Verifica existencia de receta.
     * @param folio Folio receta.
     * @return true si existe.
     */
    @Override
    public Boolean verificarExistenciaReceta(String folio) {
        return fachadaReceta.existeReceta(folio);
    }

}