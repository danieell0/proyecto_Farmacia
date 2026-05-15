package Clases;

import ConexionMongo.IBaseMongo;
import ConexionMongo.ManejadorConexiones;
import Entidades.DetalleReceta;
import Entidades.Medicamento;
import Entidades.Receta;
import Enums.EstadoReceta;
import Interfaces.IRecetaDAO;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Dario
 */
public class RecetaDAO implements IRecetaDAO {

    private final MongoCollection<Receta> coleccionRecetas;

    public RecetaDAO() {
        this.coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
    }

    @Override
    public Receta obtenerRecetaPorFolio(String folio) {
        Receta receta = coleccionRecetas.find(eq("folio", folio)).first();
        return receta;
    }

    @Override
    public void actualizarEstadoReceta(Receta receta) {
        coleccionRecetas.updateOne(
            eq("folio", receta.getFolio()), 
            Arrays.asList(
                // Usamos un Document nativo para el operador $set de agregación
                new Document("$set", new Document("estado", 
                    new Document("$cond", Arrays.asList(
                        new Document("$gte", Arrays.asList("$usos", 10)), // Si usos >= 10
                        "AGOTADA",                                       // Entonces
                        "ACTIVA"                                         // Sino
                    ))
                ))
            )
        );
    }

    @Override
    public void restarMedicamentos(Receta receta, Long idMedicamento, Integer cantidad) {
        // Filtra por folio y el id del medicamento dentro del array
        coleccionRecetas.updateOne(
            and(eq("folio", receta.getFolio()), eq("detalles.idMedicamento", idMedicamento)),
            Updates.inc("detalles.$.cantidadSurtida", -cantidad)
        );
    }

    @Override
    public void sumarMedicamentos(Receta receta, Long idMedicamento, Integer cantidad) {
        // Filtra por folio y el id del medicamento dentro del array
        coleccionRecetas.updateOne(
            and(eq("folio", receta.getFolio()), eq("detalles.idMedicamento", idMedicamento)),
            Updates.inc("detalles.$.cantidadSurtida", cantidad)
        );
    }

}
