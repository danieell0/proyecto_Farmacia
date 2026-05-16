/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Tests;

import Clases.ProductoDAO;
import ConexionMongo.ManejadorConexiones;
import Entidades.Producto;
import EntidadesMongo.ProductoMongo;
import Enums.TipoProducto;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Jorge
 */
public class ProductoDAOtest {
    // Instanciamos la dao
    private ProductoDAO productoDAO;

    @BeforeEach
    public void setUp() {
        // preparamos el ambiente
        productoDAO = new ProductoDAO();
    }

    @Test
    public void obtenerProductosTest() {
        // Ejecución
        List<Producto> productos = productoDAO.obtenerProductos();

        // Validación
        assertNotNull(productos);
        assertFalse(productos.isEmpty());

        // Deben existir 25 productos
        assertEquals(25, productos.size());

        productos.forEach(p -> {assertNotNull(p.getIdProducto());assertNotNull(p.getNombre());assertTrue(p.getStock() >= 0);});
    }

    @Test
    public void obtenerProductosPorNombreTest() {
        // Setup
        String nombre = "Paracetamol";

        // Ejecución
        List<Producto> productos= productoDAO.obtenerProductosPorNombre(nombre);

        // Validación
        assertNotNull(productos);
        assertFalse(productos.isEmpty());

        productos.forEach(p -> {assertTrue(p.getNombre().toLowerCase().contains(nombre.toLowerCase()));});
    }

    @Test
    public void obtenerProductosPorClaveTest() {
        // Setup
        String clave = "M001";

        // Ejecución
        List<Producto> productos= productoDAO.obtenerProductoPorClave(clave);

        // Validación
        assertNotNull(productos);
        assertFalse(productos.isEmpty());
        productos.forEach(producto -> {assertEquals(clave, producto.getIdProducto());assertTrue(producto.getStock() > 0);
        });
    }

    @Test
    public void obtenerProductoPorIdTest() {
        // Setup
        String id = "MC001";

        // Ejecución
        Producto producto= productoDAO.obtenerProductoPorId(id);

        // Validación
        assertNotNull(producto);
        assertEquals(id, producto.getIdProducto());
        assertEquals("Clonazepam", producto.getNombre());
    }

    @Test
    public void disminuirStockTest() {
        // Setup
        String idProducto = "P001";
        int nuevoStock = 5;

        // Ejecución
        Boolean resultado= productoDAO.DisminuirStock(idProducto, nuevoStock);

        // Validación
        assertTrue(resultado);
        Producto productoActualizado= productoDAO.obtenerProductoPorId(idProducto);
        assertEquals(nuevoStock, productoActualizado.getStock());
    }
}
