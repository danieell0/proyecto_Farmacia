package pantallas.control;

import DTO.DetalleVentaDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import FCatalogo.FCatalogo;
import ICatalogo.ICatalogo;
import com.mycompany.dto_negocios.CarritoDTO;
import subsistemaRecetas.FachadaSubsistemaReceta;
import fachada.FVentas;
import fachada.IVenta;
import java.util.List; 
import javax.swing.JOptionPane;
import pantallas.VentaFrame;
import pantallas.menuFrame;
import pantallas.validarRecetaDlg;
import subsistemaRecetas.IControlRecetas;

/**
 *
 * @author Dario
 */
public class Coordinador {
    private static Coordinador cordinador;
    private IVenta fVentas; 
    private ICatalogo catalogo;
    private IControlRecetas recetaSub;
    
    private String folioRecetaActual;
    
    private validarRecetaDlg recetaDlg;
    private VentaFrame ventaFrame;
    private menuFrame menuJFrame; 
    
    /**
     * Constructor del coordinador.
     * Inicializa el acceso al subsistema de ventas a través de la fachada.
     */
    public Coordinador() {
        this.catalogo=new FCatalogo();
        this.fVentas = new FVentas();
        this.recetaSub = new FachadaSubsistemaReceta();
    }

    /**
     * Asigna la referencia de la pantalla de ventas.
     * @param ventaFrame La instancia de la vista.
     */
    public void setVentaFrame(VentaFrame ventaFrame) {
        this.ventaFrame = ventaFrame;
    }
    
    /**
     * Asigna la referencia de la pantalla de menu.
     * @param menuJFrame La instancia de la vista.
     */
    public void setMenuFrame(menuFrame menuJFrame) {
        this.menuJFrame = menuJFrame;
    }
    
    /**
     * Asigna la referencia del dialog de validar receta.
     * @param recetaDlg La instancia de la vista.
     */
    public void setRecetaDlg(validarRecetaDlg recetaDlg) {
        this.recetaDlg = recetaDlg;
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
     * Obtiene todos los productos activos existentes.
     * @return Resultado de la busqueda.
     */
    public List<ProductoDTO> ObtenerProductos() {
        return catalogo.obtenerProductos();
    }
    
    /**
     * Obtiene todos los productos en base un filtro por su nombre.
     * @param nombre Filtro nombre con el que se realizara la busqueda.
     * @return El resultado de la busqueda.
     */
    public List<ProductoDTO> ObtenerProductosPorNombre(String nombre){
        return catalogo.buscarProductosNombre(nombre);
    }
    
    /**
     * Obtiene un producto en base su id.
     * @param id ID del producto a buscar.
     * @return El resultado de la busqueda.
     */
    public ProductoDTO ObtenerProductoConId(Long id){
        return catalogo.obtenerProductoId(id);
    }
    
    /**
     * Toma el producto seleccionado en la pantalla, arma el renglón (Detalle) 
     * y lo manda a la fachada para agregarlo al carrito.
     * @param producto El producto seleccionado de la tabla catálogo.
     * @param cantidad Cantidad ingresada por el usuario.
     */
    public void agregarProductoAlCarrito(ProductoDTO producto, int cantidad) {
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
        
        DetalleVentaDTO detalle = new DetalleVentaDTO();
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
     * @param idProducto ID del producto que se eliminara del carrito.
     * @param cantidad La cantidad a eliminar del producto.
     */
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
     * @return el carrito actual.
     */
    public CarritoDTO obtenerCarritoActual() {
        return fVentas.obtenerCarritoActual();
    }

    /**
     * Ejecuta el proceso de cobrar. Toma el carrito temporal y manda la orden de convertirlo en Venta.
     * @param idEmpleado ID del empleado que realizo la venta.
     * @param idCliente ID del cliente asociado a la venta.
     */
    public void ejecutarFinalizarVenta(Long idEmpleado, Long idCliente) {
        
        CarritoDTO carrito = fVentas.obtenerCarritoActual();
        
        if (carrito == null || carrito.getListaProductos().isEmpty()) {
            JOptionPane.showMessageDialog(ventaFrame, "El carrito esta vacio, agregue productos para vender.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        VentaDTO ventaRegistrada = fVentas.registrarVenta(carrito);
        
        if (ventaRegistrada != null) {
            recetaSub.confirmarDescuentoReceta(); 
            this.folioRecetaActual = null; 

            JOptionPane.showMessageDialog(ventaFrame, 
                "Venta " + ventaRegistrada.getIdVenta() + " registrada con exito!", 
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
                        ventaFrame.limpiarVenta(); 
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "No se pudo procesar la venta en la Base de Datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Procesa el calculo del cambio.
     * @param total Total de la venta.
     * @param pago Pago dado por el cliente.
     * @return El cambio de la operacion.
     */
    public double procesarCalculoCambio(double total, double pago) {
        if (pago < total) {
            return -1; 
        }
        return pago - total; 
    }
    
    /**
     * Limpia el folio actual (por ejemplo, si se cancela el uso de receta).
     */
    public void limpiarFolioReceta() {
        this.folioRecetaActual = null;
        recetaSub.limpiarRecetasGuardadas();
    }

    /**
     * Asigna el folio actual que se usara en la venta.
     * @param folio Folio de la receta.
     */
    public void setFolioRecetaActual(String folio) {
        this.folioRecetaActual = folio;
    }
    
    /**
     * Metodo para obtener el folio que se esta trabajando.
     * @return El folio con el que se esta trabajando.
     */
    public String getFolioRecetaActual() {
        return folioRecetaActual;
    }

    /**
     * Valida si un producto requiere receta y si hay disponibilidad en la misma.
     * @param idProducto Producto a validar.
     * @param cantidad Cantidad a validar.
     * @return Si es valido.
     */
    public boolean validarProductoConReceta(Long idProducto, Integer cantidad) {
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
    public void cancelarVenta() {
        if (fVentas.obtenerCarritoActual() != null) {
            fVentas.obtenerCarritoActual().getListaProductos().clear();
            fVentas.obtenerCarritoActual().setTotalAPagar(0.0);
        }
        if (menuJFrame != null) {
            menuJFrame.limpiarVenta(); 
        }
        if (ventaFrame != null) {
            ventaFrame.limpiarVenta();
            ventaFrame.setVisible(false); 
        }
        if (menuJFrame != null) {
            menuJFrame.setVisible(true);
        }
    }
    
    /**
     * Verifica la existencia de una receta.
     * @param folio Folio de la receta a verificar.
     * @return El resultado de la busqueda.
     */
    public boolean existeReceta(String folio) {
        boolean resultado = recetaSub.existeReceta(folio);
        return resultado;
    }
}