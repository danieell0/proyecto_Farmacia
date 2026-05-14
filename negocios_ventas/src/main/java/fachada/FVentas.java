/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

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
     * Agrega producto al carrito. Si es medicamento controlado valida receta.
     *
     * @param detalle Producto a agregar.
     */
    @Override
    public void agregarAlCarrito(
            DetalleCarritoDTO detalle
    ) {

        if (detalle == null
                || detalle.getProducto() == null) {

            throw new RuntimeException(
                    "Producto inválido."
            );
        }

        ProductoDTO producto = detalle.getProducto();

        // VALIDAR MEDICAMENTO CONTROLADO
        if (producto instanceof MedicamentoDTO med
                && med.isEsControlado()) {

            List<Especialidades> especialidades
                    = med.getEspecialidades();

            if (especialidades == null
                    || especialidades.isEmpty()) {

                throw new RuntimeException(
                        "El medicamento no tiene especialidad."
                );
            }

            Especialidades especialidad
                    = especialidades.get(0);

            boolean recetaValida
                    = fachadaReceta.validarYReservar(
                            this.folioRecetaActual,
                            med.getId(),
                            detalle.getCantidad(),
                            especialidad
                    );

            if (!recetaValida) {

                throw new RuntimeException(
                        "Receta inválida o insuficiente."
                );
            }
        }

        // AGREGAR AL CARRITO
        this.controlCarrito
                .agregarProductoAlCarrito(detalle);
    }

    /**
     * Elimina producto del carrito y devuelve stock temporal.
     *
     * @param idProducto Producto a eliminar.
     */
    @Override
    public void eliminarDelCarrito(
            Long idProducto
    ) {

        DetalleCarritoDTO detalleEncontrado = null;

        for (DetalleCarritoDTO detalle
                : this.controlCarrito
                        .obtenerCarrito()
                        .getListaProductos()) {

            if (detalle.getProducto()
                    .getId()
                    .equals(idProducto)) {

                detalleEncontrado = detalle;

                break;
            }
        }

        // CANCELAR RESERVA DE RECETA
        if (detalleEncontrado != null
                && detalleEncontrado.getProducto() instanceof MedicamentoDTO med
                && med.isEsControlado()
                && this.folioRecetaActual != null) {

            this.fachadaReceta.cancelarReserva(
                    this.folioRecetaActual,
                    idProducto,
                    detalleEncontrado.getCantidad()
            );
        }

        // ELIMINAR DEL CARRITO
        this.controlCarrito
                .eliminarProductoDelCarrito(idProducto);
    }

    /**
     * Cancela venta actual. Devuelve stock temporal y limpia carrito.
     */
    @Override
    public void cancelarVentaActual() {

        this.fachadaReceta
                .limpiarRecetasGuardadas();

        this.controlCarrito
                .devolverTodoElStockTemporal();

        this.controlCarrito
                .limpiarCarrito();

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
    @Override
    public Boolean validarProductoParaVenta(ProductoDTO producto, Integer cantidad) {
        if (this.folioRecetaActual == null || this.folioRecetaActual.isEmpty()) {
            return true;
        }
        if (producto instanceof MedicamentoDTO med && med.isEsControlado()) {
            List<Especialidades> especialidades = med.getEspecialidades();
            if (especialidades == null  ||  especialidades.isEmpty()) {
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
     *
     * @param folio Folio receta.
     * @return true si existe.
     */
    @Override
    public boolean verificarExistenciaReceta(
        String folio
    ){
        return fachadaReceta.existeReceta(folio);
    }
}
