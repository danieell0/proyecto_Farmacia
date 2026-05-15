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
     * Agrega un producto al carrito y descuenta el stock de forma temporal 
     * en el DTO para evitar sobreventas.
     * @param detalle La especificacion de cantidad y producto que se agregara al carrito.
     */
    @Override
    public void agregarAlCarrito(DetalleCarritoDTO detalle) {
        ProductoDTO producto = detalle.getProducto();

    // 1. Verificamos si es medicamento y si es controlado
    if (producto instanceof MedicamentoDTO med && med.getEsControlado()) {
        
        // --- VALIDACIÓN INMEDIATA DEL FOLIO ---
        // Si el folio es null, es porque la UI no lo capturó antes de llamar a este método
        if (this.folioRecetaActual == null || this.folioRecetaActual.isEmpty()) {
            throw new RuntimeException("Es obligatorio ingresar un folio de receta para: " + med.getNombre());
        }

        List<Especialidades> especialidadesMedicamento = med.getEspecialidades();
        if (especialidadesMedicamento == null || especialidadesMedicamento.isEmpty()) {
            throw new RuntimeException("El medicamento " + med.getNombre() + " no tiene especialidades configuradas.");
        }

        // 2. BUSCAR EN LA BASE DE DATOS (FachadaReceta debe ir a MongoDB)
        boolean esValido = false;
        for (Especialidades esp : especialidadesMedicamento) {
            // Aquí se conecta con el subsistema que busca en la colección 'recetas'
            if (fachadaReceta.validarYReservar(
                    this.folioRecetaActual, 
                    med.getIdProducto(), 
                    detalle.getCantidad(), 
                    esp)) {
                esValido = true;
                break; 
            }
        }

        if (!esValido) {
            // Si llegamos aquí es porque el folio existe pero no para este producto/especialidad
            String folioError = this.folioRecetaActual;
            this.folioRecetaActual = null; // Limpiamos para el siguiente intento
            throw new RuntimeException("La receta con folio [" + folioError + "] no es válida para este medicamento o especialidad.");
        }
    }
    
    // 3. Si pasó las validaciones (o no es controlado), se agrega al carrito
    this.controlCarrito.agregarProductoAlCarrito(detalle);
    }


    /**
     * Elimina un producto y devuelve el stock temporal al DTO.
     * @param idProducto el producto a eliminarse del carrito.
     */
    @Override
    public void eliminarDelCarrito(String idProducto) {
        DetalleCarritoDTO detalleEncontrado = null;
        for (DetalleCarritoDTO d : this.controlCarrito.obtenerCarrito().getListaProductos()) {
            if (d.getProducto().getIdProducto().equals(idProducto)) {
                detalleEncontrado = d;
                break;
            }
        }
        if (detalleEncontrado != null && detalleEncontrado.getProducto() instanceof MedicamentoDTO) {
            MedicamentoDTO med = (MedicamentoDTO) detalleEncontrado.getProducto();
            if (med.getEsControlado() && this.folioRecetaActual != null) {
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
    
    
    @Override
    public Boolean validarProductoParaVenta(ProductoDTO producto, Integer cantidad) {
        if (producto instanceof MedicamentoDTO med && med.getEsControlado()) {

        if (this.folioRecetaActual == null || this.folioRecetaActual.isEmpty()) {
            // Aquí deberías lanzar la alerta UI para pedir el folio
            return false; 
        }

        List<Especialidades> especialidades = med.getEspecialidades();
        if (especialidades == null || especialidades.isEmpty()) {
            return false;
        }

        // Validación flexible: recorre todas las especialidades permitidas del producto
        boolean esValido = false;
        for (Especialidades esp : especialidades) {
            if (fachadaReceta.validarYReservar(this.folioRecetaActual, med.getIdProducto(), cantidad, esp)) {
                esValido = true;
                break;
            }
        }

        if (!esValido) {
            this.folioRecetaActual = null; // Limpiamos el folio si no sirvió para este producto
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
     * @param folio Folio receta.
     * @return true si existe.
     */
    @Override
    public Boolean verificarExistenciaReceta(String folio) {
        return fachadaReceta.existeReceta(folio);
    }

}
