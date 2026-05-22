package MapperMongo;

import Entidades.Cliente;
import EntidadesMongo.ClienteMongo;

/**
 * Clase Mapper para convertir un cliente a mongo.
 * @author Dario
 */
public class ClienteMapperMongo {
    
    /**
     * Convierte un ClienteMongo a una entidad Cliente.
     * @param mongo ClienteMongo a convertir.
     * @return Cliente.
     */
    public static Cliente entityToDomain(ClienteMongo mongo){
        if (mongo == null) {
            return null;
        }
        Cliente cliente = new Cliente();
        cliente.setIdCliente(mongo.getIdCliente());
        cliente.setNombre(mongo.getNombre());
        cliente.setTelefono(mongo.getTelefono());
        cliente.setPuntos(mongo.getPuntos());        
        cliente.setFechaNacimiento(mongo.getFechaNacimiento());
        
        return cliente;
    }
    
    /**
     * Convierte una entidad Cliente a un ClienteMongo.
     * @param cliente cliente a convertir.
     * @return ClienteMongo.
     */
    public static ClienteMongo domainToEntity(Cliente cliente) {
        if (cliente == null) {
            return null;
        }
        ClienteMongo mongo = new ClienteMongo();
        mongo.setIdCliente(cliente.getIdCliente());
        mongo.setNombre(cliente.getNombre());
        mongo.setTelefono(cliente.getTelefono());
        mongo.setPuntos(cliente.getPuntos());
        mongo.setFechaNacimiento(cliente.getFechaNacimiento());
        
        return mongo;
    }
}
