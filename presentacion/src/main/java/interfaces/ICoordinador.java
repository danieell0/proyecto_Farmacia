/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTO.ProductoDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import dtos.LoginDTO;
import java.util.List;
import pantallas.VentaFrame;
import pantallas.control.Coordinador;
import pantallas.menuFrame;
import pantallas.validarRecetaDlg;

/**
 * 
 * @author Benjamin
 */
public interface ICoordinador {
    
    void setVentaFrame(VentaFrame ventaFrame);
    void setMenuFrame(menuFrame menuJFrame);
    void setRecetaDlg(validarRecetaDlg recetaDlg);

    List<ProductoDTO> ObtenerProductos();
    List<ProductoDTO> ObtenerProductosPorNombre(String nombre);
    ProductoDTO ObtenerProductoConId(Long id);

    void agregarProductoAlCarrito(ProductoDTO producto, int cantidad);
    void eliminarProductoDelCarrito(Long idProducto, Integer cantidad);
    CarritoDTO obtenerCarritoActual();
    void ejecutarFinalizarVenta(Long idEmpleado, Long idCliente);
    double procesarCalculoCambio(double total, double pago);

    void setFolioRecetaActual(String folio);
    void limpiarFolioReceta();
    String getFolioRecetaActual();
    boolean validarProductoConReceta(Long idProducto, Integer cantidad);
    
    boolean validarInicioSesion(LoginDTO login);
    
}
