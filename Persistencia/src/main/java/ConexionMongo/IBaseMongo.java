package ConexionMongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 *
 * @author Dario
 */
public interface IBaseMongo {
    public abstract MongoDatabase obtenerBaseDatos(MongoClient cliente);
    
    public abstract MongoCollection obtenerColeccion(MongoDatabase baseDatos);
}
