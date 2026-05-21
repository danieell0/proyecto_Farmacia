package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Receta;
import EntidadesMongo.RecetaMongo;
import Excepciones.PersistenciaException;
import Interfaces.IRecetaDAO;
import MapperMongo.RecetaMapperMongo;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase DAO donde estan todas las operaciones de recetas con la base de datos.
 * @author Dario
 */
public class RecetaDAO implements IRecetaDAO {

    private final MongoCollection<RecetaMongo> coleccionRecetas;
    private static final Logger logger = Logger.getLogger(RecetaDAO.class.getSimpleName());

    /**
     * Contructor de la clase.
     */
    public RecetaDAO() {
        this.coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
    }

    /**
     * Obtiene la receta por el folio de esta.
     * @param folio Folio de la receta a encontrar.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @return La receta encontrada.
     */
    @Override
    public Receta obtenerRecetaPorFolio(String folio) throws PersistenciaException {
        try {
            RecetaMongo receta = coleccionRecetas.find(eq("folio", folio)).first();
            return RecetaMapperMongo.entityToDomain(receta);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar la receta", e);
            throw new PersistenciaException("Error al buscar la receta", e);
        }
    }

    /**
     * Se cambia el estado de la receta dependiendo si esta se surtio
     * completamente o a pasado la fecha limite para utilizarla.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @param receta Receta que se modificara el estado.
     */
    @Override
    public void actualizarEstadoReceta(Receta receta) throws PersistenciaException {
        try {
            coleccionRecetas.updateOne(
                eq("folio", receta.getFolio()),
                Updates.set("estado", receta.getEstado())
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar actualizar el estado de la receta", e);
            throw new PersistenciaException("Error al buscar actualizar el estado de la receta", e);
        }
    }

    /**
     * Resta la cantidad de medicamentos utilizables sumando la cantidad surtida a la receta.
     * @param receta Receta a la que se le sumara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le sumara la cantidad surtida.
     * @param cantidad Cantidad que se le sumara del medicamento a la cantidad surtida.
     * @throws PersistenciaException Error al ejecutar la operacion.
     */
    @Override
    public void restarMedicamentos(Receta receta, String idMedicamento, Integer cantidad) throws PersistenciaException {
        try {
            coleccionRecetas.updateOne(
                and(eq("folio", receta.getFolio()), eq("detalles.idMedicamento", idMedicamento)),
                Updates.inc("detalles.$.cantidadSurtida", cantidad)
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al restar medicamentos de la receta", e);
            throw new PersistenciaException("Error al restar medicamentos de la receta", e);
        }
    }

    /**
     * Suma la cantidad de medicamentos utilizables resatando la cantidad surtida a la receta.
     * @param receta Receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad que se le restara del medicamento a la cantidad surtida.
     * @throws PersistenciaException Error al ejecutar la operacion.
     */
    @Override
    public void sumarMedicamentos(Receta receta, String idMedicamento, Integer cantidad) throws PersistenciaException {
        try {
            coleccionRecetas.updateOne(
                and(eq("folio", receta.getFolio()), eq("detalles.idMedicamento", idMedicamento)),
                Updates.inc("detalles.$.cantidadSurtida", -cantidad)
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al sumar medicamentos a la receta", e);
            throw new PersistenciaException("Error al sumar medicamentos a la receta", e);
        }
    }

}
