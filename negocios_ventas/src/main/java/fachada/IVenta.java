/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import exception.VentaException;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IVenta {
     public void eliminarDelCarrito(Long idProducto);
    
     public void agregarAlCarrito(DetalleVentaDTO detalle);

    
     public CarritoDTO obtenerCarritoActual();

    
     public void calcularTotal(CarritoDTO carrito);

        
    public VentaDTO registrarVenta(CarritoDTO carrito);

}
