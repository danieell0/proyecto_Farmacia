/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bo;

import com.mycompany.dto_negocios.ProductoDTO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author munos
 */
public class VentaBO {
    private List<ProductoDTO> inventario;

    public VentaBO() {
        this.inventario = new ArrayList<>();
    }

    
    public ProductoDTO buscarProducto(String nombre) {
        for (ProductoDTO p : inventario) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

     
    public double calcularSubtotal(double precio, int cantidad) {
        return precio * cantidad;
    }
     
    public double calcularCambio(double total, double pago) throws Exception {
        if (pago < total) {
            throw new Exception("El pago es insuficiente.");
        }
        return pago - total;
    }

    public boolean finalizarVenta(List<ProductoDTO> productosVendidos) {
        if (productosVendidos == null || productosVendidos.isEmpty()) {
            return false;
        }
            return true;
    }
}

