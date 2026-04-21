/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

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
        inventario.add(new ProductoDTO(1L, "Paracetamol del grande", 50.0, 100, "paracetamol.png"));
        inventario.add(new ProductoDTO(2L, "Jarabe", 120.0, 50, "jarabe.png"));
        inventario.add(new ProductoDTO(3L, "Ibuprofeno", 85.0, 80, "ibuprofeno.png"));
    }

    /**
     * Método que faltaba: Retorna la lista de productos disponibles.
     * Usado por la Fachada en el método buscarProducto.
     */
    public List<ProductoDTO> getInventario() {
        return this.inventario;
    }

    /**
     * Método que faltaba: Realiza el cálculo matemático del subtotal.
     */
    public double calcularSubtotal(double precio, int cantidad) {
        return precio * cantidad;
    }

    /**
     * Método que faltaba: Procesa la lógica final de la venta.
     * Aquí es donde en el futuro restarías stock o guardarías en la base de datos.
     */
    public boolean finalizarVenta(List<ProductoDTO> productosVendidos) {
        if (productosVendidos != null && !productosVendidos.isEmpty()) {
            return true;
        }
        return false;
    }
}