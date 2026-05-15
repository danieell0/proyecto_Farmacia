package fachada;

import Bo.NegocioException;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;

/**
 *
 * @author munos
 */
public interface IVenta {
    public void eliminarDelCarrito(String idProducto) throws NegocioException;
    public void agregarAlCarrito(DetalleCarritoDTO detalle) throws NegocioException;
    public CarritoDTO obtenerCarritoActual();
    Double finalizarVenta(Double cantidadRecibida, Long idEmpleado, Long idCliente);   
    void setFolioRecetaActual(String folio);        
    public VentaDTO registrarVenta(CarritoDTO carrito);
    public Boolean verificarExistenciaReceta(String folio);
    public void cancelarVentaActual();
}
