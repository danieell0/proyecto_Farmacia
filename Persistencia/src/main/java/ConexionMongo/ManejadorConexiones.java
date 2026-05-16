package ConexionMongo;

import Entidades.CuentaAcceso;
import Entidades.Empleado;
import Entidades.Producto;
import Entidades.Receta;
import Entidades.Venta;
import EntidadesMongo.ProductoMongo;
import EntidadesMongo.VentaMongo;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
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

    //esta es la url de la conexion de mongodb
    private static final String URL = "mongodb://localhost:27017";
    //este es el nombre de la base de datos del proyecto 
    private static final String NOMBRE_BASE_DATOS = "farmacia";

    //coleciones 
    private static final String COLECCION_PRODUCTOS = "productos";
    private static final String COLECCION_CLIENTES = "clientes";
    private static final String COLECCION_VENTAS = "ventas";
    private static final String COLECCION_EMPLEADOS = "empleados";
    private static final String COLECCION_CUENTAS = "cuentas_acceso";
    private static final String COLECCION_RECETAS = "recetas";
    private static final String COLECCION_FACTURAS = "facturas";

    //cliente mongo 
    private static MongoClient cliente;

    public ManejadorConexiones() {

    }

    public static MongoClient obtenerCliente() {
        //verificamos si el cliente mongo todavia no ha sido creado 
        if (cliente == null) {
            //con esto convertimos automaticamente los objetos java a documentos Bson y viceversa
            CodecProvider proveedorPojo = PojoCodecProvider.builder().automatic(true).build();
            //combinamos los codecs por defecto de mongo a los codecs personalisados para pojos
            CodecRegistry registroCodecs = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(proveedorPojo));
            //configuramos la conexion utilizando la url y el registro de los codecs presonalizados
            MongoClientSettings configuracion = MongoClientSettings.builder().applyConnectionString(new ConnectionString(URL)).codecRegistry(registroCodecs).build();
            //creamos la unica instancia del cliente de mongo 
            cliente = MongoClients.create(configuracion);
        }
        //regresamos todo configurado 
        return cliente;
    }

    public static MongoDatabase obtenerBaseDatos() {
        //regresa la base de datos configurada 
        return obtenerCliente().getDatabase(NOMBRE_BASE_DATOS);
    }
    
    public static MongoCollection<ProductoMongo> obtenerColeccionProductos(){
        //regresa la coleccion de productos de la base de datos 
        return obtenerBaseDatos().getCollection(COLECCION_PRODUCTOS, ProductoMongo.class);
    }
    
    public static MongoCollection<Receta> obtenerColeccionReceta(){
        return obtenerBaseDatos().getCollection(COLECCION_RECETAS,Receta.class);
    }
    public static MongoCollection<VentaMongo> obtenerColeccionVentas() {
        return obtenerBaseDatos().getCollection(COLECCION_VENTAS, VentaMongo.class);
    }
    
    public static MongoCollection<Empleado> obtenerColeccionEmpleados(){
        return obtenerBaseDatos().getCollection(COLECCION_EMPLEADOS, Empleado.class);
    }
    
    public static MongoCollection<CuentaAcceso> obtenerColeccionCuentas() {
       return obtenerBaseDatos().getCollection(COLECCION_CUENTAS, CuentaAcceso.class);
   }
}
