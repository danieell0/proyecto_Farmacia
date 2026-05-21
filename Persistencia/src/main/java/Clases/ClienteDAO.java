package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Cliente;
import EntidadesMongo.ClienteMongo;
import Excepciones.PersistenciaException;
import Interfaces.IClienteDAO;
import MapperMongo.ClienteMapperMongo;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase DAO donde estan todas las operaciones de clientes con la base de datos.
 * @author Dario
 */
public class ClienteDAO implements IClienteDAO {
    
    private final MongoCollection<ClienteMongo> coleccionClientes;
    private static final Logger logger = Logger.getLogger(ClienteDAO.class.getSimpleName());

    /**
     * Contructor de la clase.
     */
    public ClienteDAO() {
        this.coleccionClientes = ManejadorConexiones.obtenerColeccionCliente();
    }

    /**
     * Obtiene el cliente por su ID.
     * @param idCliente ID del cliente a buscar.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @return El cliente encontrado.
     */
    @Override
    public Cliente obtenerCliente(String idCliente) throws PersistenciaException {
        try {
            ClienteMongo cliente = coleccionClientes.find(eq("idCliente", idCliente)).first();
            return ClienteMapperMongo.entityToDomain(cliente);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al buscar el cliente", e);
            throw new PersistenciaException("Error al buscar el cliente", e);
        }
    }

    /**
     * Obtiene los puntos del cliente.
     * @param idCliente ID del cliente a consultar sus puntos.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @return Puntos del cliente consultado.
     */
    @Override
    public Double obtenerPuntos(String idCliente) throws PersistenciaException {
        try {
            ClienteMongo cliente = coleccionClientes.find(eq("idCliente", idCliente)).first();
            if (cliente == null) {
                return null;
            }
            return cliente.getPuntos();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al obtener los puntos del cliente", e);
            throw new PersistenciaException("Error al obtener los puntos del cliente", e);
        }
    }

    /**
     * Actualiza los puntos de un cliente.
     * @param idCliente ID del cliente al actualizar sus puntos.
     * @throws PersistenciaException Error al ejecutar la operacion.
     * @param puntos Puntos que se daran o quitaran al cliente.
     */
    @Override
    public void actualizarPuntos(String idCliente, Double puntos) throws PersistenciaException {
        try {
            coleccionClientes.updateOne(
                eq("idCliente", idCliente),
                Updates.set("puntos", puntos)
            );
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al actualizar los puntos del cliente", e);
            throw new PersistenciaException("Error al actualizar los puntos del cliente", e);
        }
    }
}
