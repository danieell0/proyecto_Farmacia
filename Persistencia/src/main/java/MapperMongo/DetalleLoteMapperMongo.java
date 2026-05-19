/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.DetalleLote;
import EntidadesMongo.DetalleLoteMongo;

/**
 *
 * @author Jorge
 */
public class DetalleLoteMapperMongo {
    
    public static DetalleLote entityToDomain(DetalleLoteMongo mongo){
        if(mongo==null){
            return null;
        }
        DetalleLote detalle=new DetalleLote();
        detalle.setProducto(ProductoMapperMongo.entityToDomain(mongo.getProducto()));
        detalle.setCantidadSolicitada(mongo.getCantidadSolicitada());
        detalle.setCantidadRecibida(mongo.getCantidadRecibida());
        detalle.setObservacion(mongo.getObservacion());
        detalle.setCantidadAnterior(mongo.getCantidadAnterior());
        detalle.setCantidadNueva(mongo.getCantidadNueva());
        return detalle;
    }
    
    public static DetalleLoteMongo domainToEntity(DetalleLote detalle){
        if(detalle==null){
            return null;
        }
        DetalleLoteMongo mongo=new DetalleLoteMongo();
        mongo.setProducto(ProductoMapperMongo.domainToEntity(detalle.getProducto()));
        mongo.setCantidadRecibida(detalle.getCantidadRecibida());
        mongo.setCantidadSolicitada(detalle.getCantidadSolicitada());
        mongo.setObservacion(detalle.getObservacion());
        mongo.setCantidadAnterior(detalle.getCantidadAnterior());
        mongo.setCantidadNueva(detalle.getCantidadNueva());
        return mongo;
    }
    
}
