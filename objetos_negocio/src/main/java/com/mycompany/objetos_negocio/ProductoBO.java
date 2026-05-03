/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;


import Clases.ProductoDAO;
import DTO.ProductoDTO;
import Entidades.Producto;
import IBO.IProductoBO;
import Interfaces.IProductoDAO;
import Mappers.ProductoMapper;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ProductoBO implements IProductoBO {

    private IProductoDAO productoDAO;
    private ProductoMapper mapper = new ProductoMapper();

    public ProductoBO() {
        this.productoDAO = new ProductoDAO();
    }

    @Override
    public List<ProductoDTO> obtenerProductos() {
        List<Producto> productos=productoDAO.obtenerProductos();
        return productos.stream().map(p-> mapper.toDTO(p)).toList();
    }

    @Override
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) {
        List<Producto> productoN=productoDAO.obtenerProductosPorNombre(nombre);
        return productoN.stream().map(p->mapper.toDTO(p)).toList();
    }

}
