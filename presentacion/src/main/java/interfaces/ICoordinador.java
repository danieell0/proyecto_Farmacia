/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import DTO.ProductoDTO;
import DTO.CarritoDTO;
import DTO.CuentaAccesoDTO;
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
    void agregarProductoAlCarrito(ProductoDTO producto, Integer cantidad);
    void eliminarProductoDelCarrito(String idProducto, Integer cantidad);
    CarritoDTO obtenerCarritoActual();
    public Double ejecutarFinalizarCompra(Double cantidadRecibida, String idEmpleado, String idCliente) throws Exception;
    Double procesarCalculoCambio(Double total, Double pago);
    void setFolioRecetaActual(String folio);
    void limpiarFolioReceta();
    String getFolioRecetaActual();
    void cancelarVenta();
    Boolean validarInicioSesion(CuentaAccesoDTO login);    
    public List<ProductoDTO> ObtenerProductoPorCodigo(String codigo);
    void actualizarCarrito();
}
