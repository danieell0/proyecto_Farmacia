package MapperMongo;

import Entidades.DetalleReceta;
import Entidades.Receta;
import EntidadesMongo.DetalleRecetaMongo;
import EntidadesMongo.RecetaMongo;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Mapper para convertir una receta a mongo.
 * @author Dario
 */
public class RecetaMapperMongo {
    
    /**
     * Convierte una RecetaMongo a una entidad Receta.
     * @param mongo RecetaMongo a convertir.
     * @return Receta.
     */
    public static Receta entityToDomain(RecetaMongo mongo) {
        if (mongo == null) {
            return null;
        }
        
        Receta receta = new Receta();
        receta.setFolio(mongo.getFolio());
        receta.setCedulaMedico(mongo.getCedulaMedico());
        receta.setUsos(mongo.getUsos());
        receta.setEstado(mongo.getEstado());
        receta.setFechaCaducidad(mongo.getFechaCaducidad());
        
        if (mongo.getDetalles() != null) {
            List<DetalleReceta> listaDominio = new ArrayList<>();
            for (DetalleRecetaMongo detalleMongo : mongo.getDetalles()) {
                listaDominio.add(DetalleRecetaMapperMongo.entityToDomain(detalleMongo));
            }
            receta.setDetalles(listaDominio);
        } else {
            receta.setDetalles(new ArrayList<>());
        }
        
        return receta;
    }
    
    /**
     * Convierte una entidad Cliente a un RecetaMongo.
     * @param receta receta a convertir.
     * @return RecetaMongo.
     */
    public static RecetaMongo domainToEntity(Receta receta) {
        if (receta == null) {
            return null;
        }
        
        RecetaMongo mongo = new RecetaMongo();
        mongo.setFolio(receta.getFolio());
        mongo.setCedulaMedico(receta.getCedulaMedico());
        mongo.setUsos(receta.getUsos());
        mongo.setEstado(receta.getEstado());
        mongo.setFechaCaducidad(receta.getFechaCaducidad());
        
        if (receta.getDetalles() != null) {
            List<DetalleRecetaMongo> listaMongo = new ArrayList<>();
            for (DetalleReceta detalleDominio : receta.getDetalles()) {
                listaMongo.add(DetalleRecetaMapperMongo.domainToEntity(detalleDominio));
            }
            mongo.setDetalles(listaMongo);
        } else {
            mongo.setDetalles(new ArrayList<>());
        }
        
        return mongo;
    }
}
