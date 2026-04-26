/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTO.VentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import exception.VentaException;
import java.time.LocalDate;

/**
 *
 * @author munos
 */
public class ControlFinalizarVenta {
    public ControlFinalizarVenta() {
    }

    public VentaDTO prepararVenta(CarritoDTO carrito, Long idEmpleado, Long idCliente) throws VentaException {
        if (carrito == null || carrito.getListaProductos() == null || carrito.getListaProductos().isEmpty()) {
            throw new VentaException("No se puede finalizar la venta porque el carrito está vacío.");
        }   
        if (carrito.getTotalAPagar() <= 0) {
            throw new VentaException("El total a pagar debe ser mayor a cero.");
        }
        VentaDTO nuevaVenta = new VentaDTO();
        nuevaVenta.setFecha(LocalDate.now()); 
        nuevaVenta.setTotal(carrito.getTotalAPagar());
        nuevaVenta.setDetalles(carrito.getListaProductos());    
        
        nuevaVenta.setIdEmpleado(idEmpleado); 
        nuevaVenta.setIdCliente(idCliente);           
        
        return nuevaVenta;
    }
}