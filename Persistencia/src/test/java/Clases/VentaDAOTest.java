/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.DetalleVenta;
import Entidades.Producto;
import Entidades.Venta;
import EntidadesMongo.VentaMongo;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author munos
 */
public class VentaDAOTest {
    
    private VentaDAO ventaDAO;

    // Este método se ejecuta ANTES de cada prueba para tener un DAO fresco
    @BeforeEach
    public void setUp() {
        MongoCollection<VentaMongo> coleccion = ManejadorConexiones.obtenerColeccionVentas();
        
        coleccion.drop();
        
        ventaDAO = new VentaDAO();
        
        Venta ventaPrueba = new Venta();
        ventaPrueba.setIdVenta("V001"); 
        ventaPrueba.setFecha(LocalDateTime.now());
        ventaPrueba.setTotal(30.0);
        ventaPrueba.setIdEmpleado("E001");
        ventaPrueba.setIdCliente("C001");
        
        Producto productoPrueba = new Producto();
        productoPrueba.setIdProducto("P001"); 
        productoPrueba.setNombre("Galletas Maria");
        productoPrueba.setPrecio(30.0);
        
        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdDetalle("D001");
        detalle.setIdVenta("V001");
        detalle.setProducto(productoPrueba);
        detalle.setCantidad(1);
        detalle.setPrecioUnitario(30.0);
        detalle.setSubtotal(30.0);
        
        List<DetalleVenta> detalles = new ArrayList<>();
        detalles.add(detalle);
        ventaPrueba.setDetalles(detalles);
        
        ventaDAO.agregarVenta(ventaPrueba);
    }

    @Test
    public void testAgregarVenta_VentaValida_RetornaTrue() {
        Venta nuevaVenta = new Venta();
        nuevaVenta.setIdVenta("V999"); 
        nuevaVenta.setFecha(LocalDateTime.now());
        nuevaVenta.setTotal(1500.0);
        nuevaVenta.setIdEmpleado("EMP001"); 
        nuevaVenta.setIdCliente("C001");   
        boolean resultado = ventaDAO.agregarVenta(nuevaVenta);
        assertTrue(resultado, "El método debería retornar true al agregar una venta válida.");
    }

    @Test
    public void testAgregarVenta_VentaNula_RetornaFalse() {
        Venta ventaNula = null;
        boolean resultado = ventaDAO.agregarVenta(ventaNula);
        assertFalse(resultado, "El método debería retornar false si la venta es nula.");
    }

    @Test
    public void testObtenerVentas_RetornaLista() {
        List<Venta> listaVentas = ventaDAO.obtenerVentas();

        assertNotNull(listaVentas, "La lista de ventas no debería ser nula.");
    }

    @Test
    public void testObtenerVentaPorId_IdInvalido_RetornaNull() {
        String idLetras = "ID_INVALIDO"; // Esto forzará el catch (NumberFormatException)
        Venta resultado = ventaDAO.obtenerVentaPorId(idLetras);
        assertNull(resultado, "Debería retornar null cuando el ID tiene un formato incorrecto (letras en vez de números).");
    }

    @Test
    public void testObtenerVentaPorId_IdValido() {
        String idValido = "1"; 
        Venta resultado = ventaDAO.obtenerVentaPorId(idValido);
        assertDoesNotThrow(() -> {
            ventaDAO.obtenerVentaPorId(idValido);
        }, "No debería lanzar ninguna excepción al buscar un ID numérico válido.");
    }

    @Test
    public void testObtenerVentasPorCliente_IdInvalido_RetornaListaVacia() {
        String idClienteInvalido = "ABC"; // Forzará el catch

        List<Venta> resultado = ventaDAO.obtenerVentasPorCliente(idClienteInvalido);

        assertNotNull(resultado, "Debería retornar una lista vacía, no un objeto nulo.");
        assertTrue(resultado.isEmpty(), "La lista debería estar vacía debido al error de formato del ID.");
    }
}