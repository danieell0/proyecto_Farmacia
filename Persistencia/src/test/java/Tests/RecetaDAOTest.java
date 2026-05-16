package Tests;

import Clases.RecetaDAO;
import ConexionMongo.ManejadorConexiones;
import Entidades.DetalleReceta;
import Entidades.Receta;
import Enums.EstadoReceta;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Dario
 */
public class RecetaDAOTest {
    private RecetaDAO recetaDAO;
    private MongoCollection<Receta> coleccion;
    private Receta recetaPrueba;

    @BeforeEach
    public void setUp() {
        
        this.coleccion = ManejadorConexiones.obtenerColeccionReceta();
        recetaDAO = new RecetaDAO(); 

        recetaPrueba = new Receta();
        recetaPrueba.setFolio("101P");
        recetaPrueba.setCedulaMedico("CA01");
        recetaPrueba.setEstado(EstadoReceta.ACTIVA);
        recetaPrueba.setFechaCaducidad(LocalDate.now().plusDays(5));
        
        recetaPrueba.setDetalles(new ArrayList<>()); 
        
        DetalleReceta detalle = new DetalleReceta();
        detalle.setIdMedicamento("P001");
        detalle.setCantidadRecetada(5);
        detalle.setCantidadSurtida(0);
        recetaPrueba.getDetalles().add(detalle);
        
        coleccion.insertOne(recetaPrueba);
    }

    @AfterEach
    public void tearDown() {
        if (coleccion != null) {
            coleccion.deleteOne(eq("folio", "101P"));
        }
    }

    @Test
    public void testObtenerRecetaPorFolio() {
        Receta resultado = recetaDAO.obtenerRecetaPorFolio("101P");
        assertNotNull(resultado);
        assertEquals("101P", resultado.getFolio());
        assertEquals("CA01", resultado.getCedulaMedico());
    }

    @Test
    public void testActualizarEstadoReceta() {
        recetaDAO.actualizarEstadoReceta(recetaPrueba);
        Receta resultado = coleccion.find(eq("folio", "101P")).first();
        assertNotNull(resultado);
        assertEquals(EstadoReceta.ACTIVA, resultado.getEstado());
    }

    @Test
    public void testRestarMedicamentos() {
        recetaDAO.restarMedicamentos(recetaPrueba, "P001", 2);
        Receta resultado = coleccion.find(eq("folio", "101P")).first();
        assertNotNull(resultado);
        int surtidoActual = resultado.getDetalles().get(0).getCantidadSurtida();
        assertEquals(2, surtidoActual);
    }

    @Test
    public void testSumarMedicamentos() {
        coleccion.updateOne(
            and(eq("folio", "101P"), eq("detalles.idMedicamento", "P001")),
            com.mongodb.client.model.Updates.set("detalles.$.cantidadSurtida", 3)
        );
        
        recetaDAO.sumarMedicamentos(recetaPrueba, "P001", 1);
        Receta resultado = coleccion.find(eq("folio", "101P")).first();
        assertNotNull(resultado);
        int surtidoFinal = resultado.getDetalles().get(0).getCantidadSurtida();
        assertEquals(2, surtidoFinal);
    }
}
