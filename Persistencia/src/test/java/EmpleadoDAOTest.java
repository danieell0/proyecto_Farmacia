/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import Clases.EmpleadoDAO;
import ConexionMongo.ManejadorConexiones;
import Entidades.Empleado;
import EntidadesMongo.EmpleadoMongo;
import Enums.EstatusEmpleado;
import Enums.RolPuesto;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;
import org.bson.conversions.Bson;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author Benjamin
 */
public class EmpleadoDAOTest {
    
    private EmpleadoDAO empleadoDAO;

    // Este método se ejecuta ANTES de cada prueba para tener un DAO fresco y una BD limpia
    @BeforeEach
    public void setUp() {
        MongoCollection<EmpleadoMongo> coleccion = ManejadorConexiones.obtenerColeccionEmpleados();
        
        // Limpiamos la colección para evitar basura de pruebas anteriores
        coleccion.drop();
        
        empleadoDAO = new EmpleadoDAO();
        
        // Preparamos un empleado de prueba usando la entidad de Mongo
        EmpleadoMongo empleadoPrueba = new EmpleadoMongo();
        empleadoPrueba.setIdEmpleado("123");
        empleadoPrueba.setNombre("Juan");
        empleadoPrueba.setApellidoPaterno("Perez");
        empleadoPrueba.setApellidoMaterno("Gomez");
        empleadoPrueba.setTelefono("555-0001");
        empleadoPrueba.setRolPuesto(RolPuesto.LIDER);
        empleadoPrueba.setFechaNacimiento(LocalDate.of(1990, 5, 20));
        empleadoPrueba.setEmpleadoEstatus(EstatusEmpleado.ACTIVO);
        
        // Lo insertamos directamente en la BD para que las pruebas tengan qué buscar
        coleccion.insertOne(empleadoPrueba);
    }

    @Test
    public void testObtenerEmpleadoPorId_EmpleadoExiste_RetornaEmpleado() {
        // Buscamos el ID exacto que insertamos en el setUp
        String idValido = "123";
        
        Empleado resultado = empleadoDAO.obtenerEmpleadoPorId(idValido);
        
        // Verificamos que el mapeo y la búsqueda fueron exitosos
        assertNotNull(resultado, "El empleado no debería ser nulo si existe en la BD.");
        assertEquals("123", resultado.getIdEmpleado(), "El ID del empleado debe coincidir.");
        assertEquals("Juan", resultado.getNombre(), "El nombre debe coincidir con el registro insertado.");
    }

    @Test
    public void testObtenerEmpleadoPorId_EmpleadoNoExiste_RetornaNull() {
        // Buscamos un ID que sabemos que no insertamos
        String idInvalido = "999"; 
        
        Empleado resultado = empleadoDAO.obtenerEmpleadoPorId(idInvalido);
        
        // Verificamos que retorne null controladamente en lugar de explotar
        assertNull(resultado, "Debería retornar null cuando el empleado no existe en la base de datos.");
    }

    @Test
    public void testObtenerEmpleadoPorId_NoLanzaExcepcion() {
        String idValido = "123"; 
        
        assertDoesNotThrow(() -> {
            empleadoDAO.obtenerEmpleadoPorId(idValido);
        }, "No debería lanzar ninguna excepción al buscar un empleado válido en la BD.");
    }
    
}
