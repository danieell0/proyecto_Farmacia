package interfaces;

import DTO.ProductoDTO;
import DTO.CarritoDTO;
import DTO.ClienteDTO;
import DTO.CuentaAccesoDTO;
import DTO.SesionActualDTO;
import java.util.List;
import pantallas.VentaFrame;
import pantallas.menuFrame;
import pantallas.menuPuntosFrame;
import pantallas.validarRecetaDlg;
import pantallas.VentaPuntosFrame;

/**
 * 
 * @author Benjamin
 */
public interface ICoordinador {
    
    void setVentaFrame(VentaFrame ventaFrame);
    void setVentaPuntosFrame(VentaPuntosFrame ventaPuntosFrame);
    void setMenuFrame(menuFrame menuJFrame);
    void setRecetaDlg(validarRecetaDlg recetaDlg);
    ClienteDTO getClienteActual();
    List<ProductoDTO> ObtenerProductos();
    List<ProductoDTO> ObtenerProductosPorNombre(String nombre);
    void agregarProductoAlCarrito(ProductoDTO producto, Integer cantidad);
    void eliminarProductoDelCarrito(String idProducto, Integer cantidad);
    CarritoDTO obtenerCarritoActual();
    public Double ejecutarFinalizarCompra(String tipoPago, Double cantidadRecibida, String idEmpleado, String idCliente) throws Exception;
    Double procesarCalculoCambio(Double total, Double pago);
    void setFolioRecetaActual(String folio);
    void limpiarFolioReceta();
    String getFolioRecetaActual();
    void cancelarVenta();
    Boolean validarInicioSesion(CuentaAccesoDTO login);    
    public List<ProductoDTO> ObtenerProductoPorCodigo(String codigo);
    void actualizarCarrito();
    void setMenuPuntosFrame(menuPuntosFrame menuPuntos);
    public SesionActualDTO obtenerSesionActual();
    public Boolean setClientePorId(String idCliente);
    public Boolean limpiarClienteActual();
}
