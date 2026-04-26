/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;

/**
 *
 * @author munos
 */
public class ControlCalculos {
   
   protected ControlCalculos() {
       
    }
    protected Double calcularSubtotal(Double precioUnitario, Integer cantidad) {
        if (precioUnitario == null || cantidad == null) return 0.0;
        return precioUnitario * cantidad;
    }
    protected void actualizarTotalesCarrito(CarritoDTO carrito) {
        Double totalPagar = 0.0;
        Integer totalArticulos = 0;
        for (DetalleVentaDTO detalle : carrito.getListaProductos()) {
            totalPagar += detalle.getSubtotal();
            totalArticulos += detalle.getCantidad();
        }
        carrito.setTotalAPagar(totalPagar);
        carrito.setTotalArticulos(totalArticulos);
    }
}