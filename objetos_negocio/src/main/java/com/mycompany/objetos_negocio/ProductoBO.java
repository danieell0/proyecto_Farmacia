/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import Bo.NegocioException;
import Clases.ProductoDAO;
import DTO.ProductoDTO;
import Entidades.Producto;
import Excepciones.PersistenciaException;
import IBO.IProductoBO;
import Interfaces.IProductoDAO;
import Mappers.ProductoMapper;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la lógica de negocio relacionada con los productos.
 *
 * Se encarga de comunicarse con la capa DAO y convertir las entidades del
 * dominio a objetos DTO mediante el uso de {@link ProductoMapper}.
 *
 * Implementa la interfaz {@link IProductoBO}.
 *
 * @author Jorge
 */
public class ProductoBO implements IProductoBO {

    //Objeto DAO para acceder a los datos de productos
    private IProductoDAO productoDAO;
    private static final Logger logger = Logger.getLogger(ProductoBO.class.getSimpleName());
    
    /**
     * Constructor de la clase ProductoBO.
     *
     * Inicializa el objeto DAO de productos.
     */
    public ProductoBO() {
        this.productoDAO = new ProductoDAO();
    }

    /**
     * Obtiene todos los productos registrados.
     *
     * Convierte cada entidad {@link Producto} a un objeto {@link ProductoDTO}.
     *
     * @return Lista de productos en formato DTO.
     */
    @Override
    public List<ProductoDTO> obtenerProductos() {
        List<Producto> productos = productoDAO.obtenerProductos();
        return productos.stream().map(p -> ProductoMapper.toDTO(p)).toList();
    }

    /**
     * Obtiene los productos que coincidan con el nombre proporcionado.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados en formato DTO.
     */
    @Override
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre) {
        List<Producto> productoN = productoDAO.obtenerProductosPorNombre(nombre);
        return productoN.stream().map(p -> ProductoMapper.toDTO(p)).toList();
    }

    /**
     * Obtiene los productos filtrados por su clave.
     *
     * @param clave Clave del producto.
     * @return Lista de productos encontrados en formato DTO.
     */
    @Override
    public List<ProductoDTO> obtenerProductoPorClave(String clave) {
        List<Producto> productoC = productoDAO.obtenerProductoPorClave(clave);
        return productoC.stream().map(p -> ProductoMapper.toDTO(p)).toList();
    }

    /**
     * Obtiene los productos que el cliente puede canjear.
     * @param idCliente ID del cliente objeto del filtro.
     * @param puntos Puntos disponibles del cliente.
     * @throws NegocioException La causa del error.
     * @return Lista de productos concordantes.
     */
    @Override
    public List<ProductoDTO> obtenerProductosConcordantes(String idCliente, Double puntos) throws NegocioException {
        try {
            List<Producto> productoCan = productoDAO.obtenerProductosConcordantes(idCliente, puntos);
            return productoCan.stream().map(p -> ProductoMapper.toDTO(p)).toList();
        } catch (PersistenciaException e) {
            logger.log(Level.SEVERE, "Error en negocio al obtener productos concordantes", e);
            throw new NegocioException("Error al procesar los productos elegibles para el cliente.", e);
        }
    }

    @Override
    public Boolean aumentar(String idProducto, Integer nuevoStock) throws NegocioException {
        return productoDAO.aumentarStock(idProducto, nuevoStock);
    }

    @Override
    public Boolean disminuir(String idProducto, Integer nuevoStock) throws NegocioException {
        return productoDAO.DisminuirStock(idProducto, nuevoStock);
    }

    @Override
    public ProductoDTO obtenerProducto(String idProducto) throws NegocioException {
        List<Producto> productos= productoDAO.obtenerProductoPorClave(idProducto);
        if (productos.isEmpty()) {
            return null;
        }
        return ProductoMapper.toDTO(productos.getFirst());
    }

}
