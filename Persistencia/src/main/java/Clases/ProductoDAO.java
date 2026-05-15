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
 *
 * @author Jorge
 */
public class ProductoDAO implements IProductoDAO {

    //coleccion de productos de mongo
    private MongoCollection<ProductoMongo> coleccionProductos;

    public ProductoDAO() {
        //obtenemos la coleccion de productos de mongo 
        this.coleccionProductos = ManejadorConexiones.obtenerColeccionProductos();
    }

    @Override
    public List<Producto> obtenerProductos() {
        //regresa todos los productos de la coleccion
        List<ProductoMongo> productosMongo = coleccionProductos.find().into(new ArrayList<>());
        return productosMongo.stream().map(p->ProductoMapperMongo.entityToDomain(p)).toList();
    }

    @Override
    public List<Producto> obtenerProductosPorNombre(String nombre) {
        //regresa los productos filtrados por nombre 
        List<ProductoMongo> productosMongo = coleccionProductos.find(regex("nombre", nombre, "i")).into(new ArrayList<>());
        return productosMongo.stream().map(p->ProductoMapperMongo.entityToDomain(p)).toList();
    }

    @Override
    public List<Producto> obtenerProductoPorClave(Long clave) {
        //regresa los productos filtrados por clave
        List<ProductoMongo> productosMongo = coleccionProductos.find(and(gt("stock", 0), eq("idProducto", clave))).into(new ArrayList<>());
        return productosMongo.stream().map(p->ProductoMapperMongo.entityToDomain(p)).toList();
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        //regresa el producto con ese id
        ProductoMongo pm = coleccionProductos.find(and(eq("idProducto", id))).first();
        if (pm != null) {
            return ProductoMapperMongo.entityToDomain(pm);
        }
        return null;
    }
    @Override
    public Boolean DisminuirStock(Long idProducto, int nuevoStock) {

        return coleccionProductos.updateOne(
                eq("idProducto", idProducto),
                set("stock", nuevoStock)
        ).getModifiedCount() > 0;
    }
}
