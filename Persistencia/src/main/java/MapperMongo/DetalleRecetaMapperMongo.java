package MapperMongo;

import Entidades.DetalleReceta;
import EntidadesMongo.DetalleRecetaMongo;

/**
 *
 * @author Dario
 */
public class DetalleRecetaMapperMongo {
    
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
