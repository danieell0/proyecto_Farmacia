/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.DetalleSolicitud;
import EntidadesMongo.DetalleSolicitudMongo;

/**
 *
 * @author Jorge
 */
public class DetalleSolicitudMapperMongo {
    public static DetalleSolicitud entityToDomain(DetalleSolicitudMongo mongo){
        if(mongo==null){
            return null;
        }
        DetalleSolicitud detalle=new DetalleSolicitud();
        detalle.setProducto(ProductoMapperMongo.entityToDomain(mongo.getProducto()));
        detalle.setCantidadSolicitada(mongo.getCantidadSolicitada());
        return detalle;
    }
    
    public static DetalleSolicitudMongo domainToEntity(DetalleSolicitud detalle){
        if(detalle==null){
            return null;
        }
        DetalleSolicitudMongo mongo=new DetalleSolicitudMongo();
        mongo.setProducto(ProductoMapperMongo.domainToEntity(detalle.getProducto()));
        mongo.setCantidadSolicitada(detalle.getCantidadSolicitada());
        return mongo;
    }
}
