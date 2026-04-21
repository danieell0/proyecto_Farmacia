package pantallas.control;

import Bo.VentaBO;
import com.mycompany.dto_negocios.ProductoDTO;
import java.util.List; 
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JOptionPane;
import pantallas.VentaFrame;

/**
 *
 * @author Dario
 */
public class Coordinador {
    private static Coordinador cordinador;
    private VentaBO ventaBO;
    private VentaFrame ventaFrame;

    public Coordinador() {
        this.ventaBO = new VentaBO();
    }

    public void setVentaFrame(VentaFrame ventaFrame) {
        this.ventaFrame = ventaFrame;
    }

    public void agregarProductoAVenta(String nombreProducto, int cantidad) {
        ProductoDTO producto = ventaBO.buscarProducto(nombreProducto);

        if (producto != null) {
            double precio = producto.getPrecio();
            double subtotal = ventaBO.calcularSubtotal(precio, cantidad);
            
            ventaFrame.agregarProducto(producto.getNombre(), cantidad, precio);
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "Producto no encontrado.");
        }
    }

    public double procesarCalculoCambio(double total, double pago) {
        try {
            return ventaBO.calcularCambio(total, pago);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventaFrame, e.getMessage());
            return -1;
        }
    }

    public void ejecutarFinalizarVenta(List<ProductoDTO> listaProductos) {
        if (ventaBO.finalizarVenta(listaProductos)) {
            JOptionPane.showMessageDialog(ventaFrame, "Venta registrada con éxito.");
            ventaFrame.limpiarVenta();
        } else {
            JOptionPane.showMessageDialog(ventaFrame, "Error al procesar la venta.");
        }
    }
}