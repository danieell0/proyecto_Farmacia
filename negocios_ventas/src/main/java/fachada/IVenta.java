package fachada;

import DTO.ProductoDTO;
import DTO.VentaDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;

/**
 *
 * @author munos
 */
public interface IVenta {
    public void eliminarDelCarrito(Long idProducto);
    public void agregarAlCarrito(DetalleCarritoDTO detalle);
    public CarritoDTO obtenerCarritoActual();
    Double finalizarVenta(Double cantidadRecibida, Long idEmpleado, Long idCliente);   
    void setFolioRecetaActual(String folio);        
    public VentaDTO registrarVenta(CarritoDTO carrito);
    public Boolean verificarExistenciaReceta(String folio);
    public void cancelarVentaActual();
    public Boolean validarProductoParaVenta(ProductoDTO producto, Integer cantidad);
}
