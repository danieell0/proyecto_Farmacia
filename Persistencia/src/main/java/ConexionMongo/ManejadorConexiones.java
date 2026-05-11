package ConexionMongo;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.bson.codecs.configuration.CodecProvider;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author Dario
 */
public class ManejadorConexiones {
    
    public static final String cadenaConexion = "mongodb://localhost:27017";
    public static final String baseDatos = "Farmacia";
    
    public static MongoClient crearConexion(){
        MongoClient mongoClient = MongoClients.create(cadenaConexion);
        return mongoClient;
    }
    
    public static CodecRegistry obtenerCodecs(){
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
        CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
        return pojoCodecRegistry;
    }
}