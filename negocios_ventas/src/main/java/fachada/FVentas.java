
package fachada;

import Bo.NegocioException;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import Enums.Especialidades;
import Sesion.FachadaSesion;
import Sesion.IFachadaSesion;
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
    private final IFachadaSesion fachadaSesion;
    private IVentaStrategy ventaStrategy;
    private String folioRecetaActual;

    public FVentas() {
        this.controlCarrito = new ControlCariito();
        this.controlFinalizar = new ControlFinalizarVenta();
        this.fachadaReceta = new FachadaSubsistemaReceta();
        this.fachadaSesion = new FachadaSesion();
    }

    @Override
    public void setFolioRecetaActual(String folio) {
        this.folioRecetaActual = folio;
    }
    
    public void setVentaStrategy(IVentaStrategy strategy) {
        this.ventaStrategy = strategy;
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
                folioEncontrado = fachadaReceta.buscarEnRecetasActivas(med.getIdProducto(), detalle.getCantidad(), esp);
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
                        if (fachadaReceta.validarYReservar(this.folioRecetaActual, med.getIdProducto(), detalle.getCantidad(), esp)) {
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
        try {
            this.fachadaReceta.cancelarYDevolverRecetas();
        } catch (NegocioException e) {
            this.fachadaReceta.limpiarRecetasGuardadas();
        }
        this.controlCarrito.devolverTodoElStockTemporal();
        this.controlCarrito.limpiarCarrito();
        this.folioRecetaActual = null;
    }

    /**
     * Obtiene carrito actual.
     * @return carrito.
     */
    @Override
    public CarritoDTO obtenerCarritoActual() {
        return this.controlCarrito.obtenerCarrito();
    }
    
    /**
     * Metodo legado para compatibilidad, delega a finalizarVenta.
     * @param carrito Carrito con el que se realizara la venta.
     * @param idCliente ID del cliente asociado a esta.
     * @param tipoPago El tipo de pago de la venta.
     * @return La venta lista para finalizarse.
     */
    @Override
    public VentaDTO registrarVenta(CarritoDTO carrito, String idCliente, String tipoPago) {
        try {
            String idEmpleado = fachadaSesion.obtenerSesionActual().getIdEmpleado();
            VentaDTO ventaEmpacada = this.controlFinalizar.prepararVenta(carrito, idEmpleado, idCliente, tipoPago);
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
     * @param tipoPago tipo de pago de la venta.
     * @param monto El monto que se pagara.
     * @param idEmpleado Empleado.
     * @param idCliente Cliente.
     * @return cambio.
     */
    @Override
    public Double finalizarVenta(String tipoPago, Double monto, String idEmpleado, String idCliente) {
        try {
            if ("PUNTOS".equalsIgnoreCase(tipoPago)) {
                this.ventaStrategy = new VentaPuntosStrategy();
            } else if ("EFECTIVO".equalsIgnoreCase(tipoPago)) {
                this.ventaStrategy = new VentaNormalStrategy();
            } else {
                throw new IllegalArgumentException("Tipo de pago no soportado: " + tipoPago);
            }

            CarritoDTO carrito = this.controlCarrito.obtenerCarrito();
            VentaDTO ventaPreparada = this.controlFinalizar.prepararVenta(carrito, idEmpleado, idCliente, tipoPago);
            boolean exito = this.controlFinalizar.registrarVenta(ventaPreparada);
            
            if (!exito) {
                throw new RuntimeException("No se pudo registrar la venta en la base de datos.");
            }
            
            Double resultadoOperacion = this.ventaStrategy.finalizarVenta(monto, idCliente, carrito);
            
            if (this.ventaStrategy.requiereConfirmarReceta()) {
                this.fachadaReceta.confirmarDescuentoReceta();
            }
            
            this.controlCarrito.limpiarCarrito();
            this.folioRecetaActual = null;
            this.ventaStrategy = null;
            return resultadoOperacion;
        } catch (Exception e) {
            throw new RuntimeException("Error critico al procesar la transacción: " + e.getMessage());
        }
    }

    /**
     * Verifica existencia de una receta.
     * @param folio Folio receta.
     * @return true si existe.
     */
    @Override
    public Boolean verificarExistenciaReceta(String folio) {
        return fachadaReceta.existeReceta(folio);
    }
    
}