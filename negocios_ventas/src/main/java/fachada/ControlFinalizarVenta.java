/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.VentaDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import com.mycompany.objetos_negocio.VentaBO;
import exception.VentaException;
import java.time.LocalDate;

/**
 *
 * @author munos
 */
public class ControlFinalizarVenta {
protected VentaBO ventaBO;

    protected ControlFinalizarVenta() {

        this.ventaBO = VentaBO.getInstance(); 
    }

    protected boolean registrarVenta(VentaDTO ventaFinal) throws VentaException {        
        if (ventaFinal == null) {
            throw new VentaException("La venta no puede ser nula al registrar.");
        }
        try {
            return ventaBO.agregarVenta(ventaFinal); 
            
        } catch (Exception e) {
            throw new VentaException("Error al registrar la venta en la base de datos: " + e.getMessage());
        }
    }
    protected VentaDTO prepararVenta(CarritoDTO carrito, Long idEmpleado, Long idCliente) throws VentaException {
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
