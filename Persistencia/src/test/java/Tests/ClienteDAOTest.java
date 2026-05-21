package Tests;

import Clases.ClienteDAO;
import ConexionMongo.ManejadorConexiones;
import Entidades.Cliente;
import EntidadesMongo.ClienteMongo;
import Excepciones.PersistenciaException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase test de la DAO de cliente.
 * @author Dario
 */
public class ClienteDAOTest {
    private ClienteDAO clienteDAO;
    private MongoCollection<ClienteMongo> coleccion;
    private ClienteMongo clientePrueba; 

    @BeforeEach
    public void setUp() {
        this.coleccion = ManejadorConexiones.obtenerColeccionCliente();
        clienteDAO = new ClienteDAO();
        clientePrueba = new ClienteMongo();
        clientePrueba.setIdCliente("C101");
        clientePrueba.setNombre("Juan Perez");
        clientePrueba.setPuntos(150.0);
        coleccion.insertOne(clientePrueba);
    }

    @AfterEach
    public void tearDown() {
        if (coleccion != null) {
            coleccion.deleteOne(eq("idCliente", "C101"));
        }
    }

    @Test
    public void testObtenerCliente() throws PersistenciaException {
        Cliente resultado = clienteDAO.obtenerCliente("C101");
        assertNotNull(resultado);
        assertEquals("C101", resultado.getIdCliente());
    }

    @Test
    public void testObtenerPuntos() throws PersistenciaException {
        Double puntos = clienteDAO.obtenerPuntos("C101");
        assertNotNull(puntos);
        assertEquals(150.0, puntos);
    }

    @Test
    public void testActualizarPuntos() throws PersistenciaException {
        clienteDAO.actualizarPuntos("C101", 300.0);
        ClienteMongo resultado = coleccion.find(eq("idCliente", "C101")).first();
        assertNotNull(resultado);
        assertEquals(300.0, resultado.getPuntos());
    }
}
