package pantallas.control;

import com.mycompany.objetos_negocio.VentaBO;
import com.mycompany.dto_negocios.ProductoDTO;
import com.mycompany.dto_negocios.RecetaDTO;
import com.mycompany.dto_negocios.enums.Estado;
import com.mycompany.negocios_receta.FReceta;
import com.mycompany.negocios_receta.IReceta;
import com.mycompany.negocios_receta.RecetaException;
import com.mycompany.negocios_ventas.FVentas;
import com.mycompany.negocios_ventas.IVenta;
import com.mycompany.negocios_ventas.VentaException;
import java.util.List; 
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import pantallas.VentaFrame;
import pantallas.validarRecetaDlg;

/**
 *
 * @author Dario
 */
public class Coordinador {
    private IVenta fVentas; 
    private VentaFrame ventaFrame;

    private IReceta recetaSub;
    private validarRecetaDlg recetaDlg;

    /**
     * Constructor del coordinador.
     * Inicializa el acceso al subsistema de ventas a través de la fachada.
     */
    public Coordinador() {
        this.fVentas = new FVentas();
        this.recetaSub = new FReceta();
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


    /**
     * Busca un producto por nombre y solicita a la vista agregarlo a la tabla.
     * @param nombreProducto Nombre ingresado por el usuario.
     * @param cantidad Cantidad de unidades deseadas.
     */
    public void agregarProductoAVenta(String nombreProducto, int cantidad) {
        ProductoDTO producto = fVentas.buscarProducto(nombreProducto);

        if (producto != null) {
            double precio = producto.getPrecio();
            fVentas.calcularSubtotal(precio, cantidad);
            
            ventaFrame.agregarProducto(producto.getNombre(), cantidad, precio);
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "El producto '" + nombreProducto + "' no existe.");
        }
    }

    /**
     * Procesa el cálculo del cambio a través de la fachada.
     * @param total Monto total de la venta.
     * @param pago Monto entregado por el cliente.
     * @return El cambio calculado o -1 si hubo un error (pago insuficiente).
     */
    public double procesarCalculoCambio(double total, double pago) {
        try {
            return fVentas.calcularCambio(total, pago);
        } catch (VentaException e) {
            JOptionPane.showMessageDialog(ventaFrame, e.getMessage());
            return -1;
        }
    }

    /**
     * Envía la lista de productos comprados para procesar el cierre de la venta.
     * @param listaProductos Productos que están actualmente en la tabla.
     */
    public void ejecutarFinalizarVenta(List<ProductoDTO> listaProductos) {
        if (fVentas.finalizarVenta(listaProductos)) {
            JOptionPane.showMessageDialog(ventaFrame, "Venta registrada con éxito.");
            ventaFrame.limpiarVenta();
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "Error: No se pudo procesar la venta. Verifique la lista.");
        }
    }
    
    public RecetaDTO buscarReceta(String folio) {
        try {
            return recetaSub.buscarPorFolio(folio);
        } catch (RecetaException ex){
            JOptionPane.showMessageDialog(recetaDlg, "La receta no ha sido encontrada.",
                    "Advertencia de Validacion", JOptionPane.WARNING_MESSAGE);
            return null;
        }
    }

    public boolean validarReceta(RecetaDTO recetaDTO) {
        try {
            return recetaSub.validarUsoReceta(recetaDTO);
        } catch (RecetaException ex) {
            JOptionPane.showMessageDialog(recetaDlg, "La receta es ivalida.",
                    "Error de receta", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void registrarUsoReceta(String folio) {
        try {
            recetaSub.registrarUsoDeReceta(folio);
        } catch (RecetaException ex) {
            JOptionPane.showMessageDialog(recetaDlg, "Hubo un error al registrar el uso de la receta.",
                    "Error de receta", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Estado obtenerEstado(String folio) {
        try {
            return recetaSub.obtenerEstadoDeReceta(folio);
        } catch (RecetaException ex) {
            JOptionPane.showMessageDialog(recetaDlg, "Hubo un error al obtener el estado de la receta.",
                    "Error de receta", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

}