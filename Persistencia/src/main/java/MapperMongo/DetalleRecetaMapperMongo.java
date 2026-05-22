package MapperMongo;

import Entidades.DetalleReceta;
import EntidadesMongo.DetalleRecetaMongo;

/**
 * Clase Mapper para convertir un DetalleReceta a mongo.
 * @author Dario
 */
public class DetalleRecetaMapperMongo {
    
    /**
     * Convierte un DetalleRecetaMongo a una entidad DetalleReceta.
     * @param mongo DetalleRecetaMongo a convertir.
     * @return DetalleReceta.
     */
    public static DetalleReceta entityToDomain(DetalleRecetaMongo mongo) {
        if (mongo == null) {
            return null;
        }
        DetalleReceta detalle = new DetalleReceta();
        detalle.setIdMedicamento(mongo.getIdMedicamento());
        detalle.setCantidadRecetada(mongo.getCantidadRecetada());
        if (mongo.getCantidadSurtida() != null) {
            detalle.setCantidadSurtida(mongo.getCantidadSurtida());
        } else {
            detalle.setCantidadSurtida(0);
        }
        
        return detalle;
    }
    
    /**
     * Convierte una entidad DetalleReceta a un DetalleRecetaMongo.
     * @param detalle  detalleReceta a convertir.
     * @return DetalleRecetaMongo.
     */
    public static DetalleRecetaMongo domainToEntity(DetalleReceta detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleRecetaMongo mongo = new DetalleRecetaMongo();
        mongo.setIdMedicamento(detalle.getIdMedicamento());
        mongo.setCantidadRecetada(detalle.getCantidadRecetada());
        if (detalle.getCantidadSurtida() != null) {
            mongo.setCantidadSurtida(detalle.getCantidadSurtida());
        } else {
            mongo.setCantidadSurtida(0);
        }
        
        return mongo;
    }
}
