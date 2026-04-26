package com.mycompany.negocios_ventas;

import DTO.ProductoDTO;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IVenta {
    public ProductoDTO buscarProducto(String nombre);
    
    public double calcularSubtotal(double precio, int cantidad);
    
    public double calcularCambio(double total, double pago) throws VentaException;
    
//    public boolean finalizarVenta(List<ProductoDTO> productosVendidos);
}
