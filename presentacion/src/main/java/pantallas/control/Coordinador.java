package pantallas.control;

import Bo.NegocioException;
import Catalogo.Fachada;
import Catalogo.ICatalogo;
import DTO.ProductoDTO;
import DTO.CarritoDTO;
import DTO.ClienteDTO;
import DTO.DetalleCarritoDTO;
import DTO.EmpleadoDTO;
import DTO.CuentaAccesoDTO;
import DTO.LoteDTO;
import DTO.MovimientoEntradaDTO;
import DTO.MovimientoSalidaDTO;
import DTO.SesionActualDTO;
import DTO.SolicitudDTO;
import Ingreso.FachadaIngreso;
import Ingreso.IFachadaIngreso;
import Inventario.FachadaInventario;
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
import javax.swing.JFrame;
import pantallas.menuPuntosFrame;
import pantallas.VentaPuntosFrame;
import Inventario.IFachadaInventario;

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
    private VentaPuntosFrame ventaPuntosFrame;
    private ICatalogo catalogo;
    private IFachadaIngreso ingreso;
    private String folioRecetaActual;
    private EmpleadoDTO empleado;
    private ClienteDTO clienteActual;
    private validarRecetaDlg recetaDlg;
    private menuFrame menuJFrame;
    private menuPuntosFrame menuPuntos;
    private IFachadaInventario fachadaInventario;
    
    /**
     * Constructor del coordinador. Inicializa el acceso al subsistema de ventas
     * a través de la fachada.
     */
    public Coordinador() {
        this.catalogo = new Fachada();
        this.fVentas = new FVentas();
        this.fachadaSesion = new FachadaSesion();
        this.ingreso = new FachadaIngreso();
        this.fachadaInventario=new FachadaInventario();
    }

    @Override
    public SesionActualDTO obtenerSesionActual() {
        return fachadaSesion.obtenerSesionActual(); 
    }
    
    /**
     * Asigna la referencia de la pantalla de ventas.
     * @param ventaFrame La instancia de la vista.
     */
    @Override
    public void setVentaFrame(VentaFrame ventaFrame) {
        this.ventaFrame = ventaFrame;
    }

    /**
     * Asigna la referencia de la pantalla de venta de puntos.
     * @param ventaPuntos La instancia de la vista.
     */
    @Override
    public void setVentaPuntosFrame(VentaPuntosFrame ventaPuntos) {
        this.ventaPuntosFrame = ventaPuntos;
    }
    
    /**
     * Asigna la referencia de la pantalla de menu.
     * @param menuJFrame La instancia de la vista.
     */
    @Override
    public void setMenuFrame(menuFrame menuJFrame) {
        this.menuJFrame = menuJFrame;
    }

    /**
     * Asigna la referencia del dialog de validar receta.
     * @param recetaDlg La instancia de la vista.
     */
    @Override
    public void setRecetaDlg(validarRecetaDlg recetaDlg) {
        this.recetaDlg = recetaDlg;
    }
    
    /**
     * Asigna la referencia de la pantalla de menu de puntos.
     * @param menuPuntos La instancia de la vista.
     */
    @Override
    public void setMenuPuntosFrame(menuPuntosFrame menuPuntos) {
        this.menuPuntos = menuPuntos;
    }
    
    @Override
    public void regresarTiendaNormal() {
        this.limpiarClienteActual(); 
        this.cancelarVenta();
        if (this.navegacion != null) {
            this.navegacion.abrirMenuFrame();
        }
    }

    /**
     * Singelton del coordinador.
     * @return Instancia del coordinador.
     */
    public static Coordinador getCoordinador() {
        if (cordinador == null) {
            cordinador = new Coordinador();
        }
        return cordinador;
    }

    /**
     * Obtiene el cliente que se encuentra activo en el flujo de venta.
     * @return El cliente.
     */
    @Override
    public ClienteDTO getClienteActual() {
        return clienteActual;
    }

    /**
     * Asigna al cliente al flujo de venta.
     * @param clienteActual El cliente.
     */
    public void setClienteActual(ClienteDTO clienteActual) {
        this.clienteActual = clienteActual;
    }
    
    /**
     * Retorna el JFrame que se esta mostrando actualmente en pantalla.
     * @return La pantalla.
     */
    public JFrame getVentanaActiva() {
        if (this.ventaFrame != null && this.ventaFrame.isShowing()) {
            return this.ventaFrame;
        }
        if (this.menuPuntos != null && this.menuPuntos.isShowing()) {
            return this.menuPuntos;
        }
        if (this.menuJFrame != null && this.menuJFrame.isShowing()) {
            return this.menuJFrame;
        }
        return null;
    }

    /**
     * Obtiene todos los productos activos existentes.
     * @return Resultado de la busqueda.
     */
    @Override
    public List<ProductoDTO> ObtenerProductos() {
        return catalogo.obtenerProductos();
    }
    
    /**
     * Obtiene todos los productos concordantes con los puntos del cliente.
     * @return Lista de productos concordantes.
     */
    public List<ProductoDTO> ObtenerProductosProductosConcordantes() {
        return catalogo.obtenerProductosConcordantes(clienteActual.getIdCliente(), clienteActual.getPuntos());
    }

    /**
     * Obtiene todos los productos en base un filtro por su nombre.
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
     * @param producto El producto seleccionado de la tabla catálogo.
     * @param cantidad Cantidad ingresada por el usuario.
     */
    @Override
    public void agregarProductoAlCarrito(ProductoDTO producto, Integer cantidad) {
    if (producto == null || cantidad == null || cantidad <= 0) {
            JOptionPane.showMessageDialog(getVentanaActiva(), "Cantidad inválida.");
            return;
        }
        try {
            DetalleCarritoDTO detalle = new DetalleCarritoDTO();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            fVentas.agregarAlCarrito(detalle);
            actualizarCarrito();
        } catch (NegocioException e) {
            String mensaje = e.getMessage();

            if (mensaje.contains("Es obligatorio ingresar un folio")) {
                validarRecetaDlg dlg = new validarRecetaDlg(null, true, this);
                dlg.setVisible(true);
                if (this.folioRecetaActual != null) {
                    this.agregarProductoAlCarrito(producto, cantidad);
                }
            } else {
                this.folioRecetaActual = null;
                fVentas.setFolioRecetaActual(null);

                JOptionPane.showMessageDialog(
                        getVentanaActiva() != null ? getVentanaActiva() : getVentanaActiva(),
                        mensaje,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    /**
     * Manda la orden de borrar un producto del carrito y actualiza la pantalla.
     * @param idProducto ID del producto que se eliminara del carrito.
     * @param cantidad La cantidad a eliminar del producto.
     */
    @Override
    public void eliminarProductoDelCarrito(String idProducto, Integer cantidad) {
        try {
            fVentas.eliminarDelCarrito(idProducto);
            actualizarCarrito();
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(getVentanaActiva(), e.getMessage(), "Error al eliminar", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Pide el carrito actual a la fachada para mostrarlo.
     * @return el carrito actual.
     */
    @Override
    public CarritoDTO obtenerCarritoActual() {
        return fVentas.obtenerCarritoActual();
    }

    /**
     * Procesa la compra: valida el pago, guarda en BD y devuelve el cambio. Si
     * algo falla, lanza una Exception para que la pantalla la muestre.
     * @param tipoPago 
     * @param cantidadRecibida
     * @param idEmpleado
     * @param idCliente
     * @return double
     */
    @Override
    public Double ejecutarFinalizarCompra(String tipoPago, Double cantidadRecibida, String idEmpleado, String idCliente) throws Exception {
        if (cantidadRecibida == null || idEmpleado == null) {
            throw new Exception("Datos incompletas para finalizar la venta (Falta Empleado o Pago).");
        }
        
        String idClienteValido = (idCliente == null || idCliente.trim().isEmpty()) ? "0" : idCliente.trim();
        
        Double cambio = fVentas.finalizarVenta(tipoPago, cantidadRecibida, idEmpleado, idClienteValido);
        if (cambio == null) {
            throw new Exception("Error interno al procesar la venta.");
        }
        
        this.limpiarClienteActual();
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

    public void mostrarPantallaVentaPuntos() {
        if (ventaPuntosFrame == null) {
            ventaPuntosFrame = new VentaPuntosFrame();
            ventaPuntosFrame.setCoordinador(this);
        }
        ventaPuntosFrame.actualizarTablaCarrito(fVentas.obtenerCarritoActual());
        ventaPuntosFrame.setVisible(true);
     }
    
    /**
     * Cancela la venta en curso, limpia el carrito y regresa al catalogo.
     */
    @Override
    public void cancelarVenta(){
        fVentas.cancelarVentaActual();
        this.folioRecetaActual = null;
        this.limpiarClienteActual();
        if (menuJFrame != null) {
            menuJFrame.limpiarVenta();
        }
        if (ventaFrame != null) {
            ventaFrame.limpiarVenta();
        }
        pantallas.control.controlNavegacion.getcontrolNavegacion().abrirMenuFrame();
    }

    /**
     * Verifica la existencia de una receta.
     * @param folio Folio de la receta a verificar.
     * @return El resultado de la busqueda.
     */
    public boolean existeReceta(String folio) {
        boolean resultado = fVentas.verificarExistenciaReceta(folio);
        return resultado;
    }

    @Override
    public List<ProductoDTO> ObtenerProductoPorCodigo(String codigo) {
        return catalogo.buscarProductoPorCodigo(codigo);
    }

    @Override
    public void actualizarCarrito() {
        CarritoDTO carritoActualizado = fVentas.obtenerCarritoActual();
        if (ventaFrame != null) {
            ventaFrame.actualizarTablaCarrito(carritoActualizado);
        }
        if (menuJFrame != null) {
            menuJFrame.actualizarTablaCarrito(carritoActualizado);
        }
        if (menuPuntos != null && menuPuntos.isShowing()) {
            menuPuntos.actualizarTablaCarrito(carritoActualizado);
        }
    }
    
    @Override
    public Boolean setClientePorId(String idCliente) {
        if (idCliente == null || idCliente.trim().isEmpty() || idCliente.trim().equals("0")) {
            return false; // Retorna falso inmediatamente si intentan buscar un cliente nulo o Público General
        }
        
        ClienteDTO cliente = ingreso.ingresarCliente(idCliente.trim());
        if (cliente == null) return false;
        
        this.clienteActual = cliente;
        return true;
    }
    
    @Override
    public Boolean limpiarClienteActual() {
        this.clienteActual = null;
        ingreso.limpiarClienteActual();
        return true;
    }

    @Override
    public SolicitudDTO buscarSolicitud(String codigoSolicitud) throws NegocioException {
        return fachadaInventario.buscarSolicitud(codigoSolicitud);
    }

    @Override
    public Boolean registrarMovimientoEntrada(MovimientoEntradaDTO movimiento) throws NegocioException {
        return fachadaInventario.registrarMovimientoEntrada(movimiento);
    }

    @Override
    public Boolean registrarMovimientoSalida(MovimientoSalidaDTO movimiento) throws NegocioException {
        return fachadaInventario.registrarMovimientoSalida(movimiento);
    }

    @Override
    public LoteDTO obtenerLote(String codigoLote) throws NegocioException {
        return fachadaInventario.obtenerLote(codigoLote);
    }

}
