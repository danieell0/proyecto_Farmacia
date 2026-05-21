/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IBO;

import Bo.NegocioException;
import DTO.ProductoDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones de negocio relacionadas con los
 * productos.
 *
 * Contiene los métodos necesarios para obtener información de productos en
 * formato DTO.
 *
 * @author Jorge
 */
public interface IProductoBO {

    /**
     * Obtiene todos los productos disponibles.
     *
     * @return Lista de productos en formato DTO.
     */
    public List<ProductoDTO> obtenerProductos();

    /**
     * Obtiene los productos que coincidan con el nombre proporcionado.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados en formato DTO.
     */
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre);

    /**
     * Obtiene los productos filtrados por su clave.
     *
     * @param clave Clave del producto.
     * @return Lista de productos encontrados en formato DTO.
     */
    public List<ProductoDTO> obtenerProductoPorClave(String clave);

    public List<ProductoDTO> obtenerProductosConcordantes(String idCliente, Double puntos);

    public Boolean aumentar(String idProducto, Integer nuevoStock) throws NegocioException;

    public Boolean disminuir(String idProducto, Integer nuevoStock) throws NegocioException;

    public ProductoDTO obtenerProducto(String idProducto) throws NegocioException;

}
