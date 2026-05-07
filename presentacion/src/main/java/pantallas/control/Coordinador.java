package pantallas.control;

import Catalogo.Fachada;
import Catalogo.ICatalogo;
import DTO.DetalleVentaDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import DTO.CarritoDTO;
import DTO.CuentaAccesoDTO;
import DTO.DetalleCarritoDTO;
import DTO.EmpleadoDTO;
import DTO.CuentaAccesoDTO;
import subsistemaRecetas.FachadaSubsistemaReceta;
import fachada.FVentas;
import fachada.IVenta;
import interfaces.ICoordinador;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import pantallas.VentaFrame;
import pantallas.menuFrame;
import pantallas.validarRecetaDlg;
import Sesion.FachadaSesion;
import subsistemaRecetas.IControlRecetas;
import Sesion.IFachadaSesion;

/**
 *
 * @author Dario
 */
public class Coordinador implements ICoordinador {

    private EmpleadoDTO empleadoLogueado;

    private IFachadaSesion controlSesion;
    // Atributo de navegación
    private controlNavegacion navegacion;

    private static Coordinador cordinador;
    private IVenta fVentas;
    private VentaFrame ventaFrame;
    private ICatalogo catalogo;
    private IControlRecetas recetaSub;
    private String folioRecetaActual;
    private validarRecetaDlg recetaDlg;
    private menuFrame menuJFrame;

    /**
     * Constructor del coordinador. Inicializa el acceso al subsistema de ventas
     * a través de la fachada.
     */
    public Coordinador() {
        this.catalogo = new Fachada();
        this.fVentas = new FVentas();
        this.recetaSub = new FachadaSubsistemaReceta();
        this.controlSesion = new FachadaSesion();
    }

    /**
     * Asigna la referencia de la pantalla de ventas.
     *
     * @param ventaFrame La instancia de la vista.
     */
    @Override
    public void setVentaFrame(VentaFrame ventaFrame) {
        this.ventaFrame = ventaFrame;
    }

    /**
     * Asigna la referencia de la pantalla de menu.
     *
     * @param menuJFrame La instancia de la vista.
     */
    @Override
    public void setMenuFrame(menuFrame menuJFrame) {
        this.menuJFrame = menuJFrame;
    }

    /**
     * Asigna la referencia del dialog de validar receta.
     *
     * @param recetaDlg La instancia de la vista.
     */
    @Override
    public void setRecetaDlg(validarRecetaDlg recetaDlg) {
        this.recetaDlg = recetaDlg;
    }

    /**
     * Singelton del coordinador.
     *
     * @return Instancia del coordinador.
     */
    public static Coordinador getCoordinador() {
        if (cordinador == null) {
            cordinador = new Coordinador();
        }
        return cordinador;
    }

    /**
     * Obtiene todos los productos activos existentes.
     *
     * @return Resultado de la busqueda.
     */
    @Override
    public List<ProductoDTO> ObtenerProductos() {
        return catalogo.obtenerProductos();
    }

    /**
     * Obtiene todos los productos en base un filtro por su nombre.
     *
     * @param nombre Filtro nombre con el que se realizara la busqueda.
     * @return El resultado de la busqueda.
     */
    @Override
    public List<ProductoDTO> ObtenerProductosPorNombre(String nombre) {
        return catalogo.buscarProductosNombre(nombre);
    }

    public EmpleadoDTO getEmpleadoLogueado() {
        return empleadoLogueado;
    }

    public void cerrarSesion() {
        this.empleadoLogueado = null;

    }

    /**
     * Procesa el cálculo del cambio (Si tu IControlVentas/FVentas aún tiene
     * este método).
     */
    @Override
    public Double procesarCalculoCambio(Double total, Double pago) {
        if (pago < total) {
            //JOptionPane.showMessageDialog(ventaFrame, "El pago es insuficiente. Faltan $" + (total - pago), "Aviso", JOptionPane.WARNING_MESSAGE);
            return -1.0;
        }
        return pago - total;
    }

    @Override
    public Boolean validarInicioSesion(CuentaAccesoDTO login) {
        try {
            // Llamamos a la capa de negocios (Subsistema Sesion)
            EmpleadoDTO empleadoQueEntro = controlSesion.verificarCredenciales(login);

            if (empleadoQueEntro != null) {

                this.empleadoLogueado = empleadoQueEntro;

                pantallas.control.controlNavegacion.getcontrolNavegacion().abrirMenuFrame();
                return true;
            }

            return false;

        } catch (Exception e) {
            // Manejo de errores (ej. se cayó la base de datos)
            System.err.println("Error al validar sesión: " + e.getMessage());
            return false;
        }
    }

    /**
     * Toma el producto seleccionado en la pantalla, arma el renglón (Detalle) y
     * lo manda a la fachada para agregarlo al carrito.
     *
     * @param producto El producto seleccionado de la tabla catálogo.
     * @param cantidad Cantidad ingresada por el usuario.
     */
    @Override
    public void agregarProductoAlCarrito(ProductoDTO producto, Integer cantidad) {
        if (producto == null || cantidad <= 0) {
            return;
        }
        if (producto instanceof MedicamentoDTO) {
            MedicamentoDTO medicamento = (MedicamentoDTO) producto;
            if (medicamento.isEsControlado()) {
                String folioValido = recetaSub.buscarEnRecetasActivas(medicamento.getId(), cantidad);
                if (folioValido != null) {
                    this.folioRecetaActual = folioValido;
                } else {
                    boolean exito = false;
                    if (folioRecetaActual != null) {
                        exito = recetaSub.validarYReservar(folioRecetaActual, medicamento.getId(), cantidad);
                    }
                    if (!exito) {
                        validarRecetaDlg dlg = new validarRecetaDlg(null, true, this);
                        dlg.setVisible(true);
                        if (this.folioRecetaActual == null) {
                            return;
                        }
                        if (!recetaSub.validarYReservar(this.folioRecetaActual, medicamento.getId(), cantidad)) {
                            JOptionPane.showMessageDialog(null, "Receta invalida para este producto.");
                            return;
                        }
                    }
                }
            }
        }

        DetalleCarritoDTO detalle = new DetalleCarritoDTO();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        fVentas.agregarAlCarrito(detalle);
        CarritoDTO carrito = fVentas.obtenerCarritoActual();

        if (ventaFrame != null) {
            ventaFrame.actualizarTablaCarrito(carrito);
        }
        if (menuJFrame != null) {
            menuJFrame.actualizarTablaCarrito(carrito);
        }
    }

    /**
     * Manda la orden de borrar un producto del carrito y actualiza la pantalla.
     *
     * @param idProducto ID del producto que se eliminara del carrito.
     * @param cantidad La cantidad a eliminar del producto.
     */
    @Override
    public void eliminarProductoDelCarrito(Long idProducto, Integer cantidad) {
        fVentas.eliminarDelCarrito(idProducto);
        if (folioRecetaActual != null) {
            recetaSub.cancelarReserva(folioRecetaActual, idProducto, cantidad);
        }
        CarritoDTO carritoActualizado = fVentas.obtenerCarritoActual();
        if (ventaFrame != null) {
            ventaFrame.actualizarTablaCarrito(carritoActualizado);
        }
        if (menuJFrame != null) {
            menuJFrame.actualizarTablaCarrito(carritoActualizado);
        }
    }

    /**
     * Pide el carrito actual a la fachada para mostrarlo.
     *
     * @return el carrito actual.
     */
    @Override
    public CarritoDTO obtenerCarritoActual() {
        return fVentas.obtenerCarritoActual();
    }

    /**
     * Procesa la compra: valida el pago, guarda en BD y devuelve el cambio. Si
     * algo falla, lanza una Exception para que la pantalla la muestre.
     *
     * @param cantidadRecibida
     * @param idEmpleado
     * @param idCliente
     * @return double
     */
    @Override
    public Double ejecutarFinalizarCompra(Double cantidadRecibida, Long idEmpleado, Long idCliente) throws Exception {
        if (cantidadRecibida == null || idEmpleado == null || idCliente == null) {
            return null;
        }
        return fVentas.finalizarVenta(cantidadRecibida, idEmpleado, idCliente);
    }

    /**
     * Limpia el folio actual (por ejemplo, si se cancela el uso de receta).
     */
    @Override
    public void limpiarFolioReceta() {
        this.folioRecetaActual = null;
        recetaSub.limpiarRecetasGuardadas();
    }

    /**
     * Asigna el folio actual que se usara en la venta.
     *
     * @param folio Folio de la receta.
     */
    @Override
    public void setFolioRecetaActual(String folio) {
        this.folioRecetaActual = folio;
    }

    /**
     * Metodo para obtener el folio que se esta trabajando.
     *
     * @return El folio con el que se esta trabajando.
     */
    @Override
    public String getFolioRecetaActual() {
        return folioRecetaActual;
    }

    /**
     * Valida si un producto requiere receta y si hay disponibilidad en la
     * misma.
     *
     * @param idProducto Producto a validar.
     * @param cantidad Cantidad a validar.
     * @return Si es valido.
     */
    @Override
    public Boolean validarProductoConReceta(Long idProducto, Integer cantidad) {
        if (folioRecetaActual == null || folioRecetaActual.isEmpty()) {
            return true;
        }
        boolean esValido = recetaSub.validarYReservar(folioRecetaActual, idProducto, cantidad);
        if (!esValido) {
            JOptionPane.showMessageDialog(ventaFrame,
                    "El producto no esta en la receta, el folio es inexistente o la cantidad excede lo recetado.",
                    "Validacion de Receta", JOptionPane.WARNING_MESSAGE);
            this.folioRecetaActual = null;
        }
        return esValido;
    }

    /**
     * Muestra la pantalla de venta.
     */
    public void mostrarPantallaVenta() {
        if (ventaFrame == null) {
            ventaFrame = new VentaFrame();
            ventaFrame.setCoordinador(this);
        }
        ventaFrame.actualizarTablaCarrito(fVentas.obtenerCarritoActual());
        ventaFrame.setVisible(true);
    }

    /**
     * Cancela la venta en curso, limpia el carrito y regresa al catalogo.
     */
    @Override
public void cancelarVenta() {
    // 1. Ordenar a la fachada que devuelva el stock y limpie el carrito
    // (Asegúrate de que FVentas tenga el método cancelarVentaActual como público)
    if (this.fVentas instanceof FVentas) {
        ((FVentas) this.fVentas).cancelarVentaActual();
    }

    // 2. Limpiar las vistas
    if (menuJFrame != null) {
        menuJFrame.limpiarVenta();
    }
    
    if (ventaFrame != null) {
        ventaFrame.limpiarVenta();
        // 3. Redirigir al menú usando la navegación
        pantallas.control.controlNavegacion.getcontrolNavegacion().abrirMenuFrame();
    }
}

    /**
     * Verifica la existencia de una receta.
     *
     * @param folio Folio de la receta a verificar.
     * @return El resultado de la busqueda.
     */
    public boolean existeReceta(String folio) {
        boolean resultado = recetaSub.existeReceta(folio);
        return resultado;
    }

    @Override
    public List<ProductoDTO> ObtenerProductoPorCodigo(Long codigo) {
        return catalogo.buscarProductoPorCodigo(codigo);
    }
}
