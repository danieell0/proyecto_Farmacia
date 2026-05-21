/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package Tests;

import Clases.InventarioDAO;
import ConexionMongo.ManejadorConexiones;
import Entidades.Lote;
import Entidades.Medicamento;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;
import Entidades.Solicitud;
import EntidadesMongo.DetalleSolicitudMongo;
import EntidadesMongo.LoteMongo;
import EntidadesMongo.MedicamentoMongo;
import EntidadesMongo.MovimientoMongo;
import EntidadesMongo.SolicitudMongo;
import Enums.EstadoSolicitud;
import Excepciones.PersistenciaException;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.regex;
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
 * @author Jorge
 */
public class InventarioDAOTest {
    private InventarioDAO inventarioDAO;
    private MongoCollection<SolicitudMongo> coleccionSolicitudes;
    private MongoCollection<MovimientoMongo> coleccionMovimientos;
    private MongoCollection<LoteMongo> coleccionLotes;
    private SolicitudMongo solicitudPrueba;
    private LoteMongo lotePrueba;

    @BeforeEach
    public void setUp() {
        inventarioDAO = new InventarioDAO();
        coleccionSolicitudes= ManejadorConexiones.obtenerColeccionSolicitudes();
        coleccionMovimientos= ManejadorConexiones.obtenerColeccionMovimientos();
        coleccionLotes= ManejadorConexiones.obtenerColeccionLotes();
        solicitudPrueba = new SolicitudMongo();
        solicitudPrueba.setCodigoSolicitud("SOL001");
        solicitudPrueba.setFechaHoraSolicitud(LocalDateTime.now());
        solicitudPrueba.setIdEmpleado("123");
        solicitudPrueba.setEstado(EstadoSolicitud.PENDIENTE);
        List<DetalleSolicitudMongo> detallesSolicitud= new ArrayList<>();
        MedicamentoMongo medicamento= new MedicamentoMongo();
        medicamento.setIdProducto("M001");
        medicamento.setNombre("Paracetamol");
        medicamento.setStock(10);
        DetalleSolicitudMongo detalleSolicitud= new DetalleSolicitudMongo();
        detalleSolicitud.setProducto(medicamento);
        detalleSolicitud.setCantidadSolicitada(5);
        detallesSolicitud.add(detalleSolicitud);
        solicitudPrueba.setDetalles(detallesSolicitud);
        coleccionSolicitudes.insertOne(solicitudPrueba);
        lotePrueba = new LoteMongo();
        lotePrueba.setCodigoLote("L001");
        lotePrueba.setProveedor("Proveedor Test");
        lotePrueba.setObservacionGeneral("Observacion test");
        lotePrueba.setDetalles(new ArrayList<>());
        coleccionLotes.insertOne(lotePrueba);
    }

    @AfterEach
    public void tearDown() {
        if (coleccionSolicitudes != null) {
            coleccionSolicitudes.deleteOne(eq("codigoSolicitud","SOL001"));
        }
        if (coleccionLotes != null) {
            coleccionLotes.deleteMany(regex("codigoLote","L00"));
        }
        if (coleccionMovimientos != null) {
            coleccionMovimientos.deleteMany(regex("idMovimiento","MOV00"));
        }
    }

    @Test
    public void buscarSolicitudTest()throws PersistenciaException {

        // Ejecución
        Solicitud resultado= inventarioDAO.buscarSolicitud("SOL001");

        // Validación
        assertNotNull(resultado);
        assertEquals("SOL001",resultado.getCodigoSolicitud());
        assertEquals(EstadoSolicitud.PENDIENTE,resultado.getEstado());
    }

    @Test
    public void actualizarEstadoSolicitudTest()throws PersistenciaException {

        // Ejecución
        Boolean resultado= inventarioDAO.actualizarEstadoSolicitud("SOL001");

        // Validación
        assertTrue(resultado);
        SolicitudMongo solicitudActualizada= coleccionSolicitudes.find(eq("codigoSolicitud","SOL001")).first();
        assertNotNull(solicitudActualizada);
        assertEquals(EstadoSolicitud.COMPLETADA,solicitudActualizada.getEstado());
    }

    @Test
    public void obtenerLoteTest()throws PersistenciaException {

        // Ejecución
        Lote resultado= inventarioDAO.obtenerLote("L001");

        // Validación
        assertNotNull(resultado);
        assertEquals("L001",resultado.getCodigoLote());
        assertEquals("Proveedor Test",resultado.getProveedor());
    }

    @Test
    public void guardarLoteTest()throws PersistenciaException {
        // Setup
        Lote lote = new Lote();
        lote.setCodigoLote("L999");
        lote.setProveedor("Proveedor Nuevo");
        lote.setObservacionGeneral("Nuevo lote");
        lote.setDetalles(new ArrayList<>());

        // Ejecución
        Boolean resultado= inventarioDAO.guardarLote(lote);

        // Validación
        assertTrue(resultado);
        LoteMongo loteGuardado= coleccionLotes.find(eq("codigoLote","L999")).first();
        assertNotNull(loteGuardado);
        assertEquals("Proveedor Nuevo",loteGuardado.getProveedor());
    }

    @Test
    public void crearMovimientoEntradaTest()throws PersistenciaException {
        // Setup
        MovimientoEntrada movimiento= new MovimientoEntrada();
        movimiento.setIdMovimiento("MOV001");
        movimiento.setFechaHora(LocalDateTime.now());
        movimiento.setIdEmpleado("123");
        movimiento.setCodigoSolicitud("SOL001");
        Lote lote = new Lote();
        lote.setCodigoLote("L001");
        lote.setProveedor("Proveedor Test");
        lote.setObservacionGeneral("Observacion test");
        lote.setDetalles(new ArrayList<>());
        movimiento.setLote(lote);

        // Ejecución
        Boolean resultado= inventarioDAO.crearMovimientoEntrada(movimiento);

        // Validación
        assertTrue(resultado);
        MovimientoMongo movimientoMongo= coleccionMovimientos.find(eq("idMovimiento","MOV001")).first();
        assertNotNull(movimientoMongo);
    }

    @Test
    public void crearMovimientoSalidaTest()throws PersistenciaException {
        // Setup
        MovimientoSalida movimiento= new MovimientoSalida();
        movimiento.setIdMovimiento("MOV002");
        movimiento.setFechaHora(LocalDateTime.now());
        movimiento.setIdEmpleado("123");
        movimiento.setCantidad(5);
        movimiento.setMotivo("Prueba");
        movimiento.setObservacion("Salida test");
        movimiento.setCantidadAnterior(20);
        movimiento.setCantidadNueva(15);
        Medicamento medicamento= new Medicamento();
        medicamento.setIdProducto("M001");
        movimiento.setProducto(medicamento);

        // Ejecución
        Boolean resultado= inventarioDAO.crearMovimientoSalida(movimiento);

        // Validación
        assertTrue(resultado);
        MovimientoMongo movimientoMongo= coleccionMovimientos.find(eq("idMovimiento","MOV002")).first();
        assertNotNull(movimientoMongo);
    }

    @Test
    public void generarIdMovimientoTest()throws PersistenciaException {

        // Ejecución
        String resultado = inventarioDAO.generarIdMovimiento();

        // Validación
        assertNotNull(resultado);
        assertTrue(resultado.startsWith("MOV"));
    }
}
