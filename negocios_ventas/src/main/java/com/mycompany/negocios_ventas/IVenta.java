/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios_ventas;

import com.mycompany.dto_negocios.ProductoDTO;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IVenta {
    public ProductoDTO buscarProducto(String nombre);
    
    public double calcularSubtotal(double precio, int cantidad);
    
    public double calcularCambio(double total, double pago) throws VentaException;
    
    public boolean finalizarVenta(List<ProductoDTO> productosVendidos);
}
