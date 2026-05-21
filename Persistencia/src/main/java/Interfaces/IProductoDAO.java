/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Producto;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz que define las operaciones de acceso a datos relacionadas con los
 * productos.
 *
 * Contiene los métodos necesarios para consultar y actualizar información de
 * productos en el sistema.
 *
 * @author Jorge
 */
public interface IProductoDAO {

    /**
     * Obtiene todos los productos registrados.
     *
     * @return Lista de productos.
     */
    public List<Producto> obtenerProductos();

    /**
     * Obtiene los productos que coincidan con el nombre proporcionado.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    public List<Producto> obtenerProductosPorNombre(String nombre);

    /**
     * Obtiene los productos filtrados por su clave.
     *
     * @param clave Clave del producto.
     * @return Lista de productos encontrados.
     */
    public List<Producto> obtenerProductoPorClave(String clave);

    /**
     * Obtiene un producto mediante su identificador.
     *
     * @param id Identificador del producto.
     * @return Producto encontrado o {@code null} si no existe.
     */
    public Producto obtenerProductoPorId(String id);

    /**
     * Actualiza el stock de un producto.
     *
     * @param idProducto Identificador del producto.
     * @param nuevoStock Nueva cantidad de stock.
     * @return {@code true} si el stock fue actualizado correctamente,
     * {@code false} en caso contrario.
     */
    
    /**
     * Obtiene los productos que el cliente puede canjear.
     * @param idCliente ID del cliente objeto del filtro.
     * @param puntos Puntos disponibles del cliente.
     * @throws PersistenciaException Error en la consulta.
     * @return Lista de productos concordantes.
     */
    public List<Producto> obtenerProductosConcordantes(String idCliente, Double puntos) throws PersistenciaException;
            
    public Boolean DisminuirStock(String idProducto, int nuevoStock);
    
    public Boolean aumentarStock(String idProducto, Integer nuevoStock);
}
