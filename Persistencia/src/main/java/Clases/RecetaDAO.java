package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Receta;
import Interfaces.IRecetaDAO;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.util.Arrays;
import org.bson.Document;

/**
 * Clase DAO donde estan todas las operaciones de recetas con la base de datos.
 * @author Dario
 */
public class RecetaDAO implements IRecetaDAO {

    private final MongoCollection<Receta> coleccionRecetas;

    /**
     * Contructor de la clase.
     */
    public RecetaDAO() {
        this.coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
    }

    /**
     * Obtiene la receta por el folio de esta.
     * @param folio Folio de la receta a encontrar.
     * @return La receta encontrada.
     */
    @Override
    public Receta obtenerRecetaPorFolio(String folio) {
        Receta receta = coleccionRecetas.find(eq("folio", folio)).first();
        return receta;
    }

    /**
     * Se cambia el estado de la receta dependiendo si esta se surtio
     * completamente o a pasado la fecha limite para utilizarla.
     * @param receta Receta que se modificara el estado.
     */
    @Override
    public void actualizarEstadoReceta(Receta receta) {
        java.util.Date fechaActual = new java.util.Date();
        coleccionRecetas.updateOne(
            eq("folio", receta.getFolio()), Arrays.asList(
                new Document("$set", new Document("estado", 
                    new Document("$cond", Arrays.asList(
                        new Document("$gt", Arrays.asList(fechaActual, "$fechaCaducidad")), "CADUCADO",
                        new Document("$cond", Arrays.asList(
                            new Document("$allElementsTrue", Arrays.asList(
                                new Document("$map", new Document()
                                    .append("input", "$detalles")
                                    .append("as", "detalle")
                                    .append("in", new Document("$gte", Arrays.asList(
                                        "$$detalle.cantidadSurtida", 
                                        "$$detalle.cantidadRecetada"
                                    )))
                                )
                            )),
                            "SURTIDA",
                            "ACTIVA"
                        ))
                    ))
                ))
            )
        );
    }

    /**
     * Resta la cantidad de medicamentos utilizables sumando la cantidad surtida a la receta.
     * @param receta Receta a la que se le sumara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le sumara la cantidad surtida.
     * @param cantidad Cantidad que se le sumara del medicamento a la cantidad surtida.
     */
    @Override
    public void restarMedicamentos(Receta receta, String idMedicamento, Integer cantidad) {
        coleccionRecetas.updateOne(
            and(eq("folio", receta.getFolio()), eq("detalles.idMedicamento", idMedicamento)),
            Updates.inc("detalles.$.cantidadSurtida", cantidad)
        );
    }

    /**
     * Suma la cantidad de medicamentos utilizables resatando la cantidad surtida a la receta.
     * @param receta Receta a la que se le restara la cantidad surtida.
     * @param idMedicamento ID del medicamento al que se le restara la cantidad surtida.
     * @param cantidad Cantidad que se le restara del medicamento a la cantidad surtida.
     */
    @Override
    public void sumarMedicamentos(Receta receta, String idMedicamento, Integer cantidad) {
        coleccionRecetas.updateOne(
            and(eq("folio", receta.getFolio()), eq("detalles.idMedicamento", idMedicamento)),
            Updates.inc("detalles.$.cantidadSurtida", -cantidad)
        );
    }

}
