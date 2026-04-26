/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import DTO.VentaDTO;
import com.mycompany.objetos_negocio.VentaBO;
import exception.VentaException;

/**
 *
 * @author munos
 */
public class ControlRegisitrarVenta {
    private VentaBO ventaBO;

    public ControlRegisitrarVenta() {

        this.ventaBO = VentaBO.getInstance(); 
    }

    
    public boolean registrarVenta(VentaDTO ventaFinal) throws VentaException {
        
        if (ventaFinal == null) {
            throw new VentaException("La venta no puede ser nula al registrar.");
        }

        try {
            return ventaBO.agregarVenta(ventaFinal); 
            
        } catch (Exception e) {
            throw new VentaException("Error al registrar la venta en la base de datos: " + e.getMessage());
        }
    }
}