package pantallas.control;

import DTO.DetalleVentaDTO;
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
    private validarRecetaDlg recetaDlg;

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
        if (producto == null || cantidad <= 0) {
            JOptionPane.showMessageDialog(ventaFrame, "Producto inválido o cantidad incorrecta.");
            return;
        }
        
        // 1. Armamos el DTO del renglón
        DetalleVentaDTO detalle = new DetalleVentaDTO();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        
        // 2. Lo mandamos al subsistema de ventas
        fVentas.agregarAlCarrito(detalle);
        
        // 3. Obtenemos el carrito actualizado y refrescamos la pantalla
        CarritoDTO carritoActualizado = fVentas.obtenerCarritoActual();
        ventaFrame.actualizarTablaCarrito(carritoActualizado); // Ocuparás crear este método en tu VentaFrame
    }

    /**
     * Manda la orden de borrar un producto del carrito y actualiza la pantalla.
     */
    public void eliminarProductoDelCarrito(Long idProducto) {
        // 1. Borramos lógicamente
        fVentas.eliminarDelCarrito(idProducto);
        
        // 2. Refrescamos visualmente
        CarritoDTO carritoActualizado = fVentas.obtenerCarritoActual();
        ventaFrame.actualizarTablaCarrito(carritoActualizado);
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
        
        // 1. Obtenemos el carrito que está guardado en memoria
        CarritoDTO carrito = fVentas.obtenerCarritoActual();
        
        // 2. Validamos que no esté vacío antes de molestar al backend
        if (carrito == null || carrito.getListaProductos().isEmpty()) {
            JOptionPane.showMessageDialog(ventaFrame, "El carrito está vacío. Agregue productos para vender.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Mandamos el carrito entero a la fachada para que registre la venta
        VentaDTO ventaRegistrada = fVentas.registrarVenta(carrito);
        
        // 4. Comprobamos el resultado
        if (ventaRegistrada != null) {
            JOptionPane.showMessageDialog(ventaFrame, 
                "¡Venta #" + ventaRegistrada.getIdVenta() + " registrada con éxito!\nTotal cobrado: $" + ventaRegistrada.getTotal(), 
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
                
            // Limpiamos la pantalla porque ya se cobró
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
            JOptionPane.showMessageDialog(ventaFrame, "El pago es insuficiente. Faltan $" + (total - pago), "Aviso", JOptionPane.WARNING_MESSAGE);
            return -1;
        }
        return pago - total; 
    }
}