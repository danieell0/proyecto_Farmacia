/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Medicamento;
import Entidades.Producto;
import Enums.Especialidades;
import Enums.Medida;
import Interfaces.IProductoDAO;
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
    private MongoCollection<Producto> coleccionProductos;

    public ProductoDAO() {
        //obtenemos la coleccion de productos de mongo 
        this.coleccionProductos = ManejadorConexiones.obtenerColeccionProductos();
    }

    @Override
    public List<Producto> obtenerProductos() {
        //regresa todos los productos de la coleccion
        List<Producto> productos=coleccionProductos.find().into(new ArrayList<>());
        productos.forEach(p-> System.out.println(p.toString()));
        return productos;
    }

    @Override
    public List<Producto> obtenerProductosPorNombre(String nombre) {
        //regresa los productos filtrados por nombre 
        return coleccionProductos .find(regex("nombre", nombre,"i")).into(new ArrayList<>());
    }

    @Override
    public List<Producto> obtenerProductoPorClave(Long clave) {
        //regresa los productos filtrados por clave
        return coleccionProductos.find(and(gt("stock",0),eq("idProducto",clave))).into(new ArrayList<>());
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        //regresa el producto con ese id
        return coleccionProductos.find(and(eq("idProducto",id))).first();
    }
    @Override
    public Boolean DisminuirStock(Long idProducto, int nuevoStock) {

    return coleccionProductos.updateOne(
            eq("idProducto", idProducto),
            set("stock", nuevoStock)
    ).getModifiedCount() > 0;
}
}
