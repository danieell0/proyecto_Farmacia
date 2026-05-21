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
     * La búsqueda no distingue entre mayúsculas y minúsculas.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    public List<Producto> obtenerProductosPorNombre(String nombre);

    /**
     * Obtiene los productos filtrados por su clave siempre y cuando tengan
     * stock disponible.
     *
     * @param clave Clave del producto.
     * @return Lista de productos encontrados con stock mayor a cero.
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
     * Obtiene los productos que el cliente puede canjear de acuerdo con la
     * cantidad de puntos disponibles.
     *
     * @param idCliente Identificador del cliente.
     * @param puntos Cantidad de puntos disponibles del cliente.
     * @return Lista de productos concordantes.
     * @throws PersistenciaException Error al realizar la consulta en la base de
     * datos.
     */
    public List<Producto> obtenerProductosConcordantes(String idCliente, Double puntos) throws PersistenciaException;

    /**
     * Disminuye el stock de un producto actualizando la cantidad disponible en
     * la base de datos.
     *
     * @param idProducto Identificador del producto.
     * @param nuevoStock Nueva cantidad de stock del producto.
     * @return {@code true} si el stock fue actualizado correctamente,
     * {@code false} en caso contrario.
     */
    public Boolean DisminuirStock(String idProducto, int nuevoStock);

    /**
     * Aumenta el stock de un producto actualizando la cantidad disponible en la
     * base de datos.
     *
     * @param idProducto Identificador del producto.
     * @param nuevoStock Nueva cantidad de stock del producto.
     * @return {@code true} si el stock fue actualizado correctamente,
     * {@code false} en caso contrario.
     */
    public Boolean aumentarStock(String idProducto, Integer nuevoStock);
}
