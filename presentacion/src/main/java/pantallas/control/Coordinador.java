package pantallas.control;

import DTO.DetalleVentaDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import DTO.RecetaDTO;
import DTO.VentaDTO;
import FCatalogo.FCatalogo;
import ICatalogo.ICatalogo;
import com.mycompany.dto_negocios.CarritoDTO;
import com.mycompany.objetos_negocio.VentaBO;
import subsistemaRecetas.FachadaSubsistemaReceta;
import fachada.FVentas;
import fachada.IVenta;
import java.util.List; 
import java.util.ArrayList;
import javax.swing.JOptionPane;
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
    private VentaFrame ventaFrame;
    private ICatalogo catalogo;
    private IControlRecetas recetaSub;
    private String folioRecetaActual;
    private validarRecetaDlg recetaDlg;
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
    
    public void setMenuFrame(menuFrame menuJFrame) {
        this.menuJFrame = menuJFrame;
    }
    
    public void setRecetaDlg(validarRecetaDlg recetaDlg) {
        this.recetaDlg = recetaDlg;
    }

    public static Coordinador getCoordinador() {
        if (cordinador == null) {
            cordinador = new Coordinador();
        }
        return cordinador;
    }
    
    public List<ProductoDTO> ObtenerProductos() {
        return catalogo.obtenerProductos();
    }
    
    public List<ProductoDTO> ObtenerProductosPorNombre(String nombre){
        return catalogo.buscarProductosNombre(nombre);
    }
    
    public ProductoDTO ObtenerProductoConId(Long id){
        return catalogo.obtenerProductoId(id);
    }
    /**
     * Toma el producto seleccionado en la pantalla, arma el renglón (Detalle) 
     * y lo manda a la fachada para agregarlo al carrito.
     * * @param producto El producto seleccionado de la tabla catálogo.
     * @param cantidad Cantidad ingresada por el usuario.
     */
    public void agregarProductoAlCarrito(ProductoDTO producto, int cantidad) {
        if (producto == null || cantidad <= 0) return;

        if (producto instanceof MedicamentoDTO) {
            MedicamentoDTO med = (MedicamentoDTO) producto;

            if (med.isEsControlado()) {
                if (folioRecetaActual == null) {
                    java.awt.Component parent = (ventaFrame != null) ? ventaFrame : menuJFrame;
                    JOptionPane.showMessageDialog(parent, 
                        "El medicamento '" + med.getNombre() + "' es controlado. Ingrese el folio.");

                    validarRecetaDlg dlg = new validarRecetaDlg(null, true, this);
                    dlg.setVisible(true);

                    if (folioRecetaActual == null) return;
                }

                if (!validarProductoConReceta(med.getId(), cantidad)) {
                    return; 
                }
            }
        }

        DetalleVentaDTO detalle = new DetalleVentaDTO();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        fVentas.agregarAlCarrito(detalle);

        if (ventaFrame != null) {
            ventaFrame.actualizarTablaCarrito(fVentas.obtenerCarritoActual());
        }
        if (menuJFrame != null) {
            menuJFrame.actualizarTablaCarrito(fVentas.obtenerCarritoActual());
        }
    }
    /**
     * Manda la orden de borrar un producto del carrito y actualiza la pantalla.
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
            menuJFrame.actualizarTablaCarrito(carritoActualizado); // ¡Faltaba esto!
        }
    }

    /**
     * Pide el carrito actual a la fachada para mostrarlo (útil al abrir la pantalla).
     */
    public CarritoDTO obtenerCarritoActual() {
        return fVentas.obtenerCarritoActual();
    }

    /**
     * Ejecuta el proceso de cobrar. Toma el carrito temporal y manda la orden de convertirlo en Venta.
     */
    public void ejecutarFinalizarVenta(Long idEmpleado, Long idCliente) {
        
        CarritoDTO carrito = fVentas.obtenerCarritoActual();
        
        if (carrito == null || carrito.getListaProductos().isEmpty()) {
            JOptionPane.showMessageDialog(ventaFrame, "El carrito está vacío. Agregue productos para vender.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        VentaDTO ventaRegistrada = fVentas.registrarVenta(carrito);
        
        if (ventaRegistrada != null) {
            if (folioRecetaActual != null) {
                recetaSub.confirmarDescuentoReceta();
                folioRecetaActual = null; 
            }
            JOptionPane.showMessageDialog(ventaFrame, 
                "¡Venta #" + ventaRegistrada.getIdVenta() + " registrada con exito!\nTotal cobrado: $" + ventaRegistrada.getTotal(), 
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
                    ventaFrame.limpiarVenta(); 
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "Error: No se pudo procesar la venta en la Base de Datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Procesa el cálculo del cambio (Si tu IControlVentas/FVentas aún tiene este método).
     */
    public double procesarCalculoCambio(double total, double pago) {
        if (pago < total) {
            return -1; 
        }
        return pago - total; 
    }
    
    /**
     * Establece el folio de la receta que se usará para la venta actual.
     * Se llama desde el cuadro de diálogo de recetas o desde la pantalla principal.
     */
    public void setFolioRecetaActual(String folio) {
        this.folioRecetaActual = folio;
    }

    /**
     * Limpia el folio actual (por ejemplo, si se cancela el uso de receta).
     */
    public void limpiarFolioReceta() {
        this.folioRecetaActual = null;
        recetaSub.limpiarRecetasGuardadas();
    }

    /**
     * Método para obtener el folio que se está trabajando.
     */
    public String getFolioRecetaActual() {
        return folioRecetaActual;
    }

    /**
     * Valida si un producto requiere receta y si hay disponibilidad en la misma.
     */
    public boolean validarProductoConReceta(Long idProducto, Integer cantidad) {
        if (folioRecetaActual == null || folioRecetaActual.isEmpty()) {
            return true; 
        }
        boolean esValido = recetaSub.validarYReservar(folioRecetaActual, idProducto, cantidad);
        
        if (!esValido) {
            JOptionPane.showMessageDialog(ventaFrame, 
                "El producto no está en la receta o la cantidad excede lo recetado.", 
                "Validación de Receta", JOptionPane.WARNING_MESSAGE);
        }
        
        return esValido;
    }  
   public void mostrarPantallaVenta() {
        if (ventaFrame == null) {
            ventaFrame = new VentaFrame();
            ventaFrame.setCoordinador(this);
        }
        ventaFrame.actualizarTablaCarrito(fVentas.obtenerCarritoActual());
        ventaFrame.setVisible(true);
    }
   /**
     * Cancela la venta en curso, limpia el carrito y regresa al catálogo.
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
}