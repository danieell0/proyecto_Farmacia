package fachada;

import Bo.NegocioException;
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
    Double finalizarVenta(String tipoPago, Double cantidadRecibida, String idEmpleado, String idCliente);   
    void setFolioRecetaActual(String folio);        
    public VentaDTO registrarVenta(CarritoDTO carrito, String idEmpleado, String idCliente);
    public Boolean verificarExistenciaReceta(String folio);
    public void cancelarVentaActual();
}
