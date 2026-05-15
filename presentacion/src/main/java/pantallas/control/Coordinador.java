package pantallas.control;

import Catalogo.Fachada;
import Catalogo.ICatalogo;
import DTO.ProductoDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.EmpleadoDTO;
import DTO.CuentaAccesoDTO;
import DTO.SesionActualDTO;
import fachada.FVentas;
import fachada.IVenta;
import interfaces.ICoordinador;
import java.util.List;
import javax.swing.JOptionPane;
import pantallas.VentaFrame;
import pantallas.menuFrame;
import pantallas.validarRecetaDlg;
import Sesion.FachadaSesion;
import Sesion.IFachadaSesion;

/**
 *
 * @author Dario
 */
public class Coordinador implements ICoordinador {

    private IFachadaSesion fachadaSesion;
    // Atributo de navegación
    private controlNavegacion navegacion;

    private static Coordinador cordinador;
    private IVenta fVentas;
    private VentaFrame ventaFrame;
    private ICatalogo catalogo;
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
        this.fachadaSesion = new FachadaSesion();
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

    public SesionActualDTO getEmpleadoLogueado() {
        return fachadaSesion.obtenerSesionActual();
    }

    public void cerrarSesion() {
        fachadaSesion.cerrarSesion();
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
            // la fachada hace todas las validaciones y el almacenamiento
            SesionActualDTO sesion = fachadaSesion.verificarCredenciales(login);
             
            //si la sesion noe s nula, significa que el login fue correcto
            if (sesion != null) {
                
                //hace que se espere un tiempo para que se termine de cerrar el login antes de abrir el otro (el bug raro que hacia que se fuera hacia atras del netbeans la otra pantalla)
                javax.swing.SwingUtilities.invokeLater(() -> {
                    pantallas.control.controlNavegacion.getcontrolNavegacion().abrirMenuFrame();
                });
                
                return true;
            }

            return false;
            
        } catch (Exception e) {
            //manejo de errores inesperados
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

        if (producto == null || cantidad == null || cantidad <= 0) {
            JOptionPane.showMessageDialog(ventaFrame, "Cantidad inválida.");
            return;
        }

        try {
            DetalleCarritoDTO detalle = new DetalleCarritoDTO();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);

            // 1. El subsistema valida. Si falta folio, lanza la RuntimeException que ya vimos.
            fVentas.agregarAlCarrito(detalle);

            actualizarCarrito();

        } catch (RuntimeException e) {
            // 2. Aquí atrapamos el error que viene del subsistema (FVentas)
            String mensaje = e.getMessage();

            // 3. Si el mensaje indica que falta folio, abrimos el Dialog
            if (mensaje.contains("Es obligatorio ingresar un folio")) {

                // Usamos el JDialog que ya creaste
                validarRecetaDlg dlg = new validarRecetaDlg(null, true, this);
                dlg.setVisible(true);

                // 4. Una vez cerrado el dialog, si el usuario ingresó un folio, reintentamos
                if (this.folioRecetaActual != null) {
                    this.agregarProductoAlCarrito(producto, cantidad); 
                }
            } else {
                // Si es cualquier otro error (como que el folio no existe en Mongo), se muestra normal
                JOptionPane.showMessageDialog(
                        ventaFrame != null ? ventaFrame : menuJFrame,
                        mensaje,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
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

        actualizarCarrito();
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
    public Double ejecutarFinalizarCompra(
            Double cantidadRecibida,
            Long idEmpleado,
            Long idCliente
    ) throws Exception {

        if (cantidadRecibida == null
                || idEmpleado == null
                || idCliente == null) {

            throw new Exception("Datos incompletos para finalizar la venta.");
        }

        Double cambio = fVentas.finalizarVenta(
                cantidadRecibida,
                idEmpleado,
                idCliente
        );

        if (cambio == null) {
            throw new Exception("Error interno al procesar la venta.");
        }

        return cambio;
    }

    /**
     * Limpia el folio actual (por ejemplo, si se cancela el uso de receta).
     */
    @Override
    public void limpiarFolioReceta() {
        this.folioRecetaActual = null;
        this.fVentas.setFolioRecetaActual(null);
    }

    /**
     * Asigna el folio actual que se usara en la venta.
     *
     * @param folio Folio de la receta.
     */
    @Override
    public void setFolioRecetaActual(String folio) {
        this.folioRecetaActual = folio;
        this.fVentas.setFolioRecetaActual(folio);
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
     * @param producto Producto a validar.
     * @param cantidad Cantidad a validar.
     * @return Si es valido.
     */
    @Override
    public Boolean validarProductoConReceta(ProductoDTO producto, Integer cantidad) {
        boolean esValido = fVentas.validarProductoParaVenta(producto, cantidad);
        if (!esValido) {
            JOptionPane.showMessageDialog(ventaFrame,
                    "El producto no está en la receta, el folio es inexistente o la cantidad excede lo recetado.",
                    "Validación de Receta", JOptionPane.WARNING_MESSAGE);
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

        fVentas.cancelarVentaActual();

        this.folioRecetaActual = null;

        if (menuJFrame != null) {
            menuJFrame.limpiarVenta();
        }

        if (ventaFrame != null) {
            ventaFrame.limpiarVenta();
        }

        pantallas.control.controlNavegacion
                .getcontrolNavegacion()
                .abrirMenuFrame();
    }

    /**
     * Verifica la existencia de una receta.
     *
     * @param folio Folio de la receta a verificar.
     * @return El resultado de la busqueda.
     */
    public boolean existeReceta(String folio) {
        boolean resultado = fVentas.verificarExistenciaReceta(folio);
        return resultado;
    }

    @Override
    public List<ProductoDTO> ObtenerProductoPorCodigo(Long codigo) {
        return catalogo.buscarProductoPorCodigo(codigo);
    }

    @Override
    public void actualizarCarrito() {

        CarritoDTO carritoActualizado
                = fVentas.obtenerCarritoActual();

        if (ventaFrame != null) {
            ventaFrame.actualizarTablaCarrito(carritoActualizado);
        }

        if (menuJFrame != null) {
            menuJFrame.actualizarTablaCarrito(carritoActualizado);
        }
    }
}
