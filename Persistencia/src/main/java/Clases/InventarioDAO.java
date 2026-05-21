/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Lote;
import Entidades.MovimientoEntrada;
import Entidades.MovimientoSalida;
import Entidades.Producto;
import Entidades.Solicitud;
import EntidadesMongo.LoteMongo;
import EntidadesMongo.MovimientoEntradaMongo;
import EntidadesMongo.MovimientoMongo;
import EntidadesMongo.MovimientoSalidaMongo;
import EntidadesMongo.SolicitudMongo;
import Enums.EstadoSolicitud;
import Excepciones.PersistenciaException;
import Interfaces.IInventarioDAO;
import MapperMongo.LoteMapperMongo;
import MapperMongo.MovimientoMapperMongo;
import MapperMongo.SolicitudMapperMongo;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class InventarioDAO implements IInventarioDAO {

    private MongoCollection<SolicitudMongo> coleccionSolicitudes;
    private MongoCollection<MovimientoMongo> coleccionMovimientos;
    private MongoCollection<LoteMongo> coleccionLotes;

    public InventarioDAO() {
        coleccionSolicitudes= ManejadorConexiones.obtenerColeccionSolicitudes();
        coleccionMovimientos= ManejadorConexiones.obtenerColeccionMovimientos();
        coleccionLotes= ManejadorConexiones.obtenerColeccionLotes();
    }
    private static final Logger logger= Logger.getLogger(InventarioDAO.class.getSimpleName());

    @Override
    public Solicitud buscarSolicitud(String claveSolicitud) throws PersistenciaException {
        try {
            SolicitudMongo solicitudMongo= coleccionSolicitudes.find(and(eq("codigoSolicitud",claveSolicitud),eq("estado",EstadoSolicitud.PENDIENTE))).first();
            if (solicitudMongo != null) {
                return SolicitudMapperMongo.entityToDomain(solicitudMongo);
            }
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al buscar la solicitud",e);
            throw new PersistenciaException("Error al buscar la solicitud",e);
        }
    }

    @Override
    public Boolean crearMovimientoEntrada(MovimientoEntrada movimiento) throws PersistenciaException {
        try {
            MovimientoEntradaMongo mongo= (MovimientoEntradaMongo) MovimientoMapperMongo.domainToEntity(movimiento);
            return coleccionMovimientos.insertOne(mongo).wasAcknowledged();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al crear el movimiento entrada",e);
            throw new PersistenciaException("Error al crear el movimiento entrada",e);
        }
    }

    @Override
    public Boolean crearMovimientoSalida(MovimientoSalida movimiento) throws PersistenciaException {
        try {
            MovimientoSalidaMongo mongo= (MovimientoSalidaMongo) MovimientoMapperMongo.domainToEntity(movimiento);
            return coleccionMovimientos.insertOne(mongo).wasAcknowledged();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al crear el movimiento salida",e);
            throw new PersistenciaException("Error al crear el movimiento salida",e);
        }
    }

    @Override
    public Boolean actualizarEstadoSolicitud(String codigoSolicitud) throws PersistenciaException {
        try {
            return coleccionSolicitudes.updateOne(eq("codigoSolicitud",codigoSolicitud),set("estado",EstadoSolicitud.COMPLETADA)).getModifiedCount() > 0;
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al actualizar el estado de la solicitud",e);
            throw new PersistenciaException("Error al actualizar el estado de la solicitud",e);
        }
    }

    @Override
    public Boolean guardarLote(Lote lote) throws PersistenciaException {
        try {
            LoteMongo mongo= LoteMapperMongo.domainToEntity(lote);
            return coleccionLotes.insertOne(mongo).wasAcknowledged();
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al guardar el lote",e);
            throw new PersistenciaException("Error al guardar el lote",e);
        }
    }

    @Override
    public Lote obtenerLote(String codigoLote) throws PersistenciaException {
        try {
            LoteMongo mongo= coleccionLotes.find(eq("codigoLote",codigoLote)).first();
            if (mongo != null) {
                return LoteMapperMongo.entityToDomain(mongo);
            }
            return null;
        } catch (Exception e) {
            logger.log(Level.SEVERE,"Error al obtener el lote",e);
            throw new PersistenciaException("Error al obtener el lote",e);
        }
    }

    @Override
    public String generarIdMovimiento() throws PersistenciaException {
        try {
            Long numMovimientos = ManejadorConexiones.obtenerColeccionMovimientos().countDocuments();
            Long siguienteNumero = numMovimientos + 1;
            return "MOV-" + String.format("%03d", siguienteNumero);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al generar el id del movimiento");
            throw new PersistenciaException("Error al generar el id del movimiento");
        }
    }
}
