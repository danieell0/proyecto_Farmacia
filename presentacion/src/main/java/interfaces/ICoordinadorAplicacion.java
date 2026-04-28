/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTO.ProductoDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface ICoordinadorAplicacion {
    
    public void setCoordinador();
    
    public List<ProductoDTO> ObtenerProductos();

    public List<ProductoDTO> ObtenerProductosPorNombre(String nombre);

    public ProductoDTO ObtenerProductoConId(Long id);

    public void agregarProductoAlCarrito(ProductoDTO producto, int cantidad);

    public void eliminarProductoDelCarrito(Long idProducto, Integer cantidad);

    public CarritoDTO obtenerCarritoActual();

    public void ejecutarFinalizarVenta(Long idEmpleado, Long idCliente);

    public double procesarCalculoCambio(double total, double pago);

    public void cancelarVenta();

    public void limpiarFolioReceta();

    public void setFolioRecetaActual(String folio);

    public String getFolioRecetaActual();

    public boolean validarProductoConReceta(Long idProducto, Integer cantidad);

    public boolean existeReceta(String folio);
    
    public void mostrarPantallaVenta();
}
