/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import Enums.Medida;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ProductoBO {

    private List<ProductoDTO> productos;

    public ProductoBO() {
        productos = new ArrayList();
        productos.add(new ProductoDTO(1L, "Agua Natural", 20.0, 50, "/imagenes/paracetamol.png"));
        productos.add(new ProductoDTO(2L, "Galletas María", 30.0, 40, "/imagenes/ibuprofeno.png"));
        productos.add(new ProductoDTO(3L, "Jugo de Naranja", 25.0, 35, "/imagenes/omeprazol.png"));
        productos.add(new ProductoDTO(4L, "Papel Higiénico", 60.0, 20, "/imagenes/paracetamol.png"));
        productos.add(new ProductoDTO(5L, "Shampoo", 75.0, 15, "/imagenes/ibuprofeno.png"));

        productos.add(new MedicamentoDTO(6L, "Paracetamol", 50.0, 30, "/imagenes/omeprazol.png", "Genérico", Medida.mg, 500.0, "Tabletas", true));
        productos.add(new MedicamentoDTO(7L, "Ibuprofeno", 80.0, 25, "/imagenes/paracetamol.png", "Pfizer", Medida.mg, 400.0, "Cápsulas", true));
        productos.add(new MedicamentoDTO(8L, "Amoxicilina", 120.0, 20, "/imagenes/ibuprofeno.png", "Sandoz", Medida.mg, 500.0, "Cápsulas", true));
        productos.add(new MedicamentoDTO(9L, "Aspirina", 45.0, 40, "/imagenes/omeprazol.png", "Bayer", Medida.mg, 100.0, "Tabletas", false));
        productos.add(new MedicamentoDTO(10L, "Loratadina", 90.0, 18, "/imagenes/omeprazol.png", "Genérico", Medida.mg, 10.0, "Tabletas", false));
    }

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }

}
