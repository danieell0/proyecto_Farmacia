package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Producto;
import EntidadesMongo.ProductoMongo;
import Enums.TipoProducto;
import Excepciones.PersistenciaException;
import Interfaces.IProductoDAO;
import MapperMongo.ProductoMapperMongo;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.lte;
import static com.mongodb.client.model.Filters.regex;
import com.mongodb.client.model.Sorts;
import static com.mongodb.client.model.Updates.set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;

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
    private static final Logger logger = Logger.getLogger(ProductoDAO.class.getSimpleName());

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
     * Convierte cada entidad {@link ProductoMongo} a su objeto de dominio
     * correspondiente {@link Producto}.
     *
     * @return Lista con todos los productos registrados.
     */
    @Override
    public List<Producto> obtenerProductos() {
        //regresa todos los productos de la coleccion
        List<ProductoMongo> productosMongo = coleccionProductos.find(
                in("tipo", TipoProducto.PRODUCTO, TipoProducto.MEDICAMENTO)
        ).into(new ArrayList<>());
        return productosMongo.stream().map(ProductoMapperMongo::entityToDomain).toList();
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
        List<ProductoMongo> productosMongo = coleccionProductos.find(
                and(
                        regex("nombre", nombre, "i"),
                        in("tipo", TipoProducto.PRODUCTO, TipoProducto.MEDICAMENTO)
                )
        ).into(new ArrayList<>());
        return productosMongo.stream().map(ProductoMapperMongo::entityToDomain).toList();
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
        List<ProductoMongo> productosMongo = coleccionProductos.find(
                and(
                        gt("stock", 0),
                        eq("idProducto", clave),
                        in("tipo", TipoProducto.PRODUCTO, TipoProducto.MEDICAMENTO)
                )
        ).into(new ArrayList<>());
        return productosMongo.stream().map(ProductoMapperMongo::entityToDomain).toList();
    }

    /**
     * Obtiene un producto mediante su identificador.
     *
     * @param id Identificador del producto.
     * @return Producto encontrado o {@code null} si no existe.
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
     * Obtiene los productos que el cliente puede canjear de acuerdo con la
     * cantidad de puntos disponibles.
     *
     * Filtra únicamente productos de tipo puntos con stock disponible y cuyo
     * precio sea menor o igual a los puntos proporcionados.
     *
     * @param idCliente Identificador del cliente.
     * @param puntos Cantidad de puntos disponibles del cliente.
     * @return Lista de productos concordantes.
     * @throws PersistenciaException Error al realizar la consulta en la base de
     * datos.
     */
    @Override
    public List<Producto> obtenerProductosConcordantes(String idCliente, Double puntos) throws PersistenciaException {
        try {
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.match(
                            and(
                                    eq("tipo", TipoProducto.PUNTOS),
                                    gt("stock", 0),
                                    lte("precio", puntos)
                            )
                    ),
                    Aggregates.sort(Sorts.ascending("precio"))
            );
            return coleccionProductos.aggregate(pipeline, ProductoMongo.class)
                    .into(new ArrayList<>())
                    .stream()
                    .map(ProductoMapperMongo::entityToDomain)
                    .toList();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al ejecutar la agregacion de productos", e);
            throw new PersistenciaException("Error al obtener los productos concordantes desde la base de datos.", e);
        }
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

    /**
     * Aumenta el stock de un producto actualizando la cantidad disponible en la
     * base de datos.
     *
     * @param idProducto Identificador del producto.
     * @param nuevoStock Nueva cantidad de stock del producto.
     * @return {@code true} si el stock fue actualizado correctamente,
     * {@code false} en caso contrario.
     */
    @Override
    public Boolean aumentarStock(String idProducto, Integer nuevoStock) {
        return coleccionProductos.updateOne(eq("idProducto", idProducto), set("stock", nuevoStock)).getModifiedCount() > 0;
    }
}
