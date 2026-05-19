/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Lote;
import EntidadesMongo.LoteMongo;

/**
 *
 * @author Jorge
 */
public class LoteMapperMongo {
    
    public static Lote entityToDomain(LoteMongo mongo){
        if(mongo==null){
            return null;
        }
        Lote lote=new Lote();
        lote.setCodigoLote(mongo.getCodigoLote());
        lote.setProveedor(mongo.getProveedor());
        lote.setObservacionGeneral(mongo.getObservacionGeneral());
        lote.setDetalles(mongo.getDetalles().stream().map(d->DetalleLoteMapperMongo.entityToDomain(d)).toList());
        return lote;
    }
    
    public static LoteMongo domainToEntity(Lote lote){
        if(lote==null){
            return null;
        }
        LoteMongo mongo= new LoteMongo();
        mongo.setCodigoLote(lote.getCodigoLote());
        mongo.setProveedor(lote.getProveedor());
        mongo.setObservacionGeneral(lote.getObservacionGeneral());
        mongo.setDetalles(lote.getDetalles().stream().map(m->DetalleLoteMapperMongo.domainToEntity(m)).toList());
        return mongo;
    }
    
}
