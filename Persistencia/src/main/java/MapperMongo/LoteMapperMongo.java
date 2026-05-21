/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Lote;
import EntidadesMongo.LoteMongo;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link Lote} y entidades Mongo {@link LoteMongo}.
 *
 * Contiene métodos estáticos para transformar lotes entre la capa de dominio y
 * la capa de persistencia.
 *
 * @author Jorge
 */
public class LoteMapperMongo {

    /**
     * Convierte una entidad Mongo {@link LoteMongo} a un objeto de dominio
     * {@link Lote}.
     *
     * @param mongo Entidad Mongo que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si la entidad es
     * nula.
     */
    public static Lote entityToDomain(LoteMongo mongo) {
        if (mongo == null) {
            return null;
        }
        Lote lote = new Lote();
        lote.setCodigoLote(mongo.getCodigoLote());
        lote.setProveedor(mongo.getProveedor());
        lote.setObservacionGeneral(mongo.getObservacionGeneral());
        lote.setDetalles(mongo.getDetalles().stream().map(d -> DetalleLoteMapperMongo.entityToDomain(d)).toList());
        return lote;
    }

    /**
     * Convierte un objeto de dominio {@link Lote} a una entidad Mongo
     * {@link LoteMongo}.
     *
     * @param lote Objeto de dominio que se desea convertir.
     * @return Entidad Mongo correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static LoteMongo domainToEntity(Lote lote) {
        if (lote == null) {
            return null;
        }
        LoteMongo mongo = new LoteMongo();
        mongo.setCodigoLote(lote.getCodigoLote());
        mongo.setProveedor(lote.getProveedor());
        mongo.setObservacionGeneral(lote.getObservacionGeneral());
        mongo.setDetalles(lote.getDetalles().stream().map(m -> DetalleLoteMapperMongo.domainToEntity(m)).toList());
        return mongo;
    }

}
