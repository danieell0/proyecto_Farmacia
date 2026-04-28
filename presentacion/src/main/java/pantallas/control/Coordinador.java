package pantallas.control;

import Catalogo.Fachada;
import Catalogo.ICatalogo;
import DTO.DetalleVentaDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;

import DTO.VentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import dtos.EmpleadoDTO;
import dtos.LoginDTO;

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

import fachadas.FachadaSesion;
import subsistemaRecetas.IControlRecetas;
import interfaces.IFachadaSesion;

/**
 *
 * @author Dario
 */
public class Coordinador implements ICoordinador{
    
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
     * Constructor del coordinador.
     * Inicializa el acceso al subsistema de ventas a través de la fachada.
     */
    public Coordinador() {
        this.catalogo=new Fachada();
        this.fVentas = new FVentas();
        this.recetaSub = new FachadaSubsistemaReceta();
        this.controlSesion = new FachadaSesion(); 
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
    
    public EmpleadoDTO getEmpleadoLogueado(){
        return empleadoLogueado;
    }
    
    public void cerrarSesion(){
        this.empleadoLogueado = null;
        
    }
    
    /**
     * Toma el producto seleccionado en la pantalla, arma el renglón (Detalle) 
     * y lo manda a la fachada para agregarlo al carrito.
     * * @param producto El producto seleccionado de la tabla catálogo.
     * @param cantidad Cantidad ingresada por el usuario.
     */
    public void agregarProductoAlCarrito(ProductoDTO producto, int cantidad) {
        if (producto == null || cantidad <= 0) return;

        // 1. Verificamos si el producto es en realidad un Medicamento
        if (producto instanceof MedicamentoDTO) {
            // Convertimos la referencia de Producto a Medicamento para acceder a sus atributos únicos
            MedicamentoDTO med = (MedicamentoDTO) producto;

            // 2. Ahora sí podemos preguntar si es controlado
            if (med.isEsControlado()) {

                // Si no hay folio activo, lo pedimos
                if (folioRecetaActual == null) {
                    JOptionPane.showMessageDialog(ventaFrame, 
                        "El medicamento '" + med.getNombre() + "' es controlado. Ingrese el folio.");

                    validarRecetaDlg dlg = new validarRecetaDlg(ventaFrame, true, this);
                    dlg.setVisible(true);

                    // Si después del diálogo sigue sin haber folio, abortamos
                    if (folioRecetaActual == null) return;
                }

                // 3. Validamos contra la receta
                if (!validarProductoConReceta(med.getId(), cantidad)) {
                    return; 
                }
            }
        }

        // 4. Proceso normal de agregar al carrito de ventas
        DetalleVentaDTO detalle = new DetalleVentaDTO();
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);

        fVentas.agregarAlCarrito(detalle);

        //se obtiene el carrito fresco de la capa de negocio
        CarritoDTO carritoActual = fVentas.obtenerCarritoActual();
        
        // Actualizamos la vista
        if (this.menuJFrame != null){
            this.menuJFrame.actualizarTablaCarrito(carritoActual);
        }
        if(this.ventaFrame != null){
            this.ventaFrame.actualizarTablaCarrito(carritoActual);
        }
        
    }
        

    /**
     * Manda la orden de borrar un producto del carrito y actualiza la pantalla.
     */
    public void eliminarProductoDelCarrito(Long idProducto, Integer cantidad) {
        // 1. Borramos lógicamente
        fVentas.eliminarDelCarrito(idProducto);
        
        if (folioRecetaActual != null) {
            recetaSub.cancelarReserva(folioRecetaActual, idProducto, cantidad);
        }
        
        // 2. Refrescamos visualmente
        CarritoDTO carritoActualizado = fVentas.obtenerCarritoActual();
        
        // Actualizamos la vista
        if (this.menuJFrame != null){
            this.menuJFrame.actualizarTablaCarrito(carritoActualizado);
        }
        if(this.ventaFrame != null){
            this.ventaFrame.actualizarTablaCarrito(carritoActualizado);
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
            if (folioRecetaActual != null) {
                recetaSub.confirmarDescuentoReceta();
                folioRecetaActual = null; // Limpiamos para la siguiente venta
            }
            JOptionPane.showMessageDialog(ventaFrame, 
                "¡Venta #" + ventaRegistrada.getIdVenta() + " registrada con éxito!\nTotal cobrado: $" + ventaRegistrada.getTotal(), 
                "Venta Exitosa", JOptionPane.INFORMATION_MESSAGE);
            
                
            // Limpiamos la pantalla porque ya se cobró
            ventaFrame.limpiarVenta(); 
            
            //cierra la ventana actual y regresa al menu despues de la venta exitosa
            pantallas.control.controlNavegacion.getcontrolNavegacion().abrirMenuFrame();
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "Error: No se pudo procesar la venta en la Base de Datos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Procesa el cálculo del cambio (Si tu IControlVentas/FVentas aún tiene este método).
     */
    public double procesarCalculoCambio(double total, double pago) {
        if (pago < total) {
            //JOptionPane.showMessageDialog(ventaFrame, "El pago es insuficiente. Faltan $" + (total - pago), "Aviso", JOptionPane.WARNING_MESSAGE);
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
        // Si no hay folio capturado, asumimos que es venta libre 
        // (A menos que tu lógica obligue a tener folio para ciertos productos)
        if (folioRecetaActual == null || folioRecetaActual.isEmpty()) {
            return true; 
        }

        // Llamamos a la fachada del subsistema de recetas
        boolean esValido = recetaSub.validarYReservar(folioRecetaActual, idProducto, cantidad);
        
        if (!esValido) {
            JOptionPane.showMessageDialog(ventaFrame, 
                "El producto no está en la receta o la cantidad excede lo recetado.", 
                "Validación de Receta", JOptionPane.WARNING_MESSAGE);
        }
        
        return esValido;
    }
    
    @Override
    public boolean validarInicioSesion(LoginDTO login) {
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
     *
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
     *
     * Verifica la existencia de una receta.
     *
     * @param folio Folio de la receta a verificar.
     * @return El resultado de la busqueda.
     */
    public boolean existeReceta(String folio) {
        boolean resultado = recetaSub.existeReceta(folio);
        return resultado;
    }
    
}
