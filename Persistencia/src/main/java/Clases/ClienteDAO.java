package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Cliente;
import Interfaces.IClienteDAO;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;

/**
 *
 * @author Dario
 */
public class ClienteDAO implements IClienteDAO {
    
    private final MongoCollection<Cliente> coleccionClientes;

    public ClienteDAO() {
        this.coleccionClientes = ManejadorConexiones.obtenerColeccionCliente();
    }

    @Override
    public Cliente obtenerCliente(String idCliente) {
        return coleccionClientes.find(eq("idCliente", idCliente)).first();
    }

    @Override
    public Double obtenerPuntos(String idCliente) {
        Cliente encontrado = coleccionClientes.find(eq("idCliente", idCliente)).first();
        if (encontrado == null) {
            return null;
        }
        return encontrado.getPuntos();
    }

    @Override
    public void actualizarPuntos(String idCliente, Double puntos) {
        coleccionClientes.updateOne(
            eq("idCliente", idCliente),
            Updates.set("puntos", puntos)
        );
    }
}
