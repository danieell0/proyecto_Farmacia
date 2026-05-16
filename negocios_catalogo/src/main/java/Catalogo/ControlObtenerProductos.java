/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Catalogo;

import fachada.ControlCariito;
import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import com.mycompany.objetos_negocio.ProductoBO;
import java.util.List;

/**
 * Clase de control encargada de gestionar
 * la obtención de productos desde la capa de negocio.
 * 
 * Se comunica con {@link ProductoBO} para realizar
 * consultas relacionadas con productos.
 * 
 * @author Jorge
 */
public class ControlObtenerProductos {

    //Objeto de negocio para gestionar productos
    protected ProductoBO catalogoBO;

    /**
     * Constructor de la clase ControlObtenerProductos.
     * 
     * Inicializa el objeto de negocio de productos.
     */
    protected ControlObtenerProductos() {
        this.catalogoBO = new ProductoBO();
    }

    /**
     * Obtiene todos los productos disponibles.
     * 
     * @return Lista de productos en formato DTO.
     */
    protected List<ProductoDTO> obtenerProductos() {
        return catalogoBO.obtenerProductos();
    }

    /**
     * Obtiene los productos que coincidan
     * con el nombre proporcionado.
     * 
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    protected List<ProductoDTO> obtenerProductoPorNombre(String nombre) {
        return catalogoBO.obtenerProductosPorNombre(nombre);
    }
    
    /**
     * Obtiene los productos filtrados por su código.
     * 
     * @param codigo Código o clave del producto.
     * @return Lista de productos encontrados.
     */
    protected  List<ProductoDTO> obtenerProductoPorCodigo(String codigo){
        return catalogoBO.obtenerProductoPorClave(codigo);
    }
    
}