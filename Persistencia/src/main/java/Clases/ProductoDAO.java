package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Producto;
import EntidadesMongo.ProductoMongo;
import Interfaces.IProductoDAO;
import MapperMongo.ProductoMapperMongo;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.regex;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase DAO encargada de realizar las operaciones relacionadas con los
 * productos en la base de datos MongoDB.
 *
 * Implementa la interfaz {@link IProductoDAO}.
 *
 * @author Jorge
 */
public class ProductoDAO implements IProductoDAO {

    //coleccion de productos de mongo
    private MongoCollection<ProductoMongo> coleccionProductos;

    /**
     * Constructor de la clase ProductoDAO.
     *
     * Inicializa la colección de productos obteniendo la conexión desde el
     * manejador de conexiones.
     */
    public ProductoDAO() {
        //obtenemos la coleccion de productos de mongo 
        this.coleccionProductos = ManejadorConexiones.obtenerColeccionProductos();
    }

    /**
     * Obtiene todos los productos almacenados en la colección.
     *
     * Convierte cada entidad {@link ProductoMongo} al objeto de dominio
     * {@link Producto}.
     *
     * @return Lista de todos los productos registrados.
     */
    @Override
    public List<Producto> obtenerProductos() {
        //regresa todos los productos de la coleccion
        List<ProductoMongo> productosMongo = coleccionProductos.find().into(new ArrayList<>());
       
        return productosMongo.stream().map(p -> ProductoMapperMongo.entityToDomain(p)).toList();
    }

    /**
     * Obtiene los productos cuyo nombre coincida parcial o totalmente con el
     * nombre proporcionado.
     *
     * La búsqueda no distingue entre mayúsculas y minúsculas.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<Producto> obtenerProductosPorNombre(String nombre) {
        //regresa los productos filtrados por nombre 
        List<ProductoMongo> productosMongo = coleccionProductos.find(regex("nombre", nombre, "i")).into(new ArrayList<>());
        return productosMongo.stream().map(p -> ProductoMapperMongo.entityToDomain(p)).toList();
    }

    /**
     * Obtiene los productos filtrados por su clave siempre y cuando tengan
     * stock disponible.
     *
     * @param clave Clave o identificador del producto.
     * @return Lista de productos encontrados con stock mayor a cero.
     */
    @Override
    public List<Producto> obtenerProductoPorClave(String clave) {
        //regresa los productos filtrados por clave
        List<ProductoMongo> productosMongo = coleccionProductos.find(and(gt("stock", 0), eq("idProducto", clave))).into(new ArrayList<>());
        return productosMongo.stream().map(p -> ProductoMapperMongo.entityToDomain(p)).toList();
    }

    /**
     * Obtiene un producto mediante su identificador.
     *
     * @param id Identificador del producto.
     * @return El producto encontrado o {@code null} si no existe.
     */
    @Override
    public Producto obtenerProductoPorId(String id) {
        //regresa el producto con ese id
        ProductoMongo pm = coleccionProductos.find(and(eq("idProducto", id))).first();
        if (pm != null) {
            return ProductoMapperMongo.entityToDomain(pm);
        }
        return null;
    }

    /**
     * Disminuye el stock de un producto actualizando la cantidad disponible en
     * la base de datos.
     *
     * @param idProducto Identificador del producto.
     * @param nuevoStock Nueva cantidad de stock del producto.
     * @return {@code true} si el stock fue actualizado correctamente,
     * {@code false} en caso contrario.
     */
    @Override
    public Boolean DisminuirStock(String idProducto, int nuevoStock) {
        return coleccionProductos.updateOne(eq("idProducto", idProducto), set("stock", nuevoStock)).getModifiedCount() > 0;
    }
}
