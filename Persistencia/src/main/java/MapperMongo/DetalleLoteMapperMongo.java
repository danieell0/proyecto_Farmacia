/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.DetalleLote;
import EntidadesMongo.DetalleLoteMongo;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link DetalleLote} y entidades Mongo {@link DetalleLoteMongo}.
 *
 * Contiene métodos estáticos para transformar detalles de lote entre la capa de
 * dominio y la capa de persistencia.
 *
 * @author Jorge
 */
public class DetalleLoteMapperMongo {

    /**
     * Convierte una entidad Mongo {@link DetalleLoteMongo} a un objeto de
     * dominio {@link DetalleLote}.
     *
     * @param mongo Entidad Mongo que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si la entidad es
     * nula.
     */
    public static DetalleLote entityToDomain(DetalleLoteMongo mongo) {
        if (mongo == null) {
            return null;
        }
        DetalleLote detalle = new DetalleLote();
        detalle.setProducto(ProductoMapperMongo.entityToDomain(mongo.getProducto()));
        detalle.setCantidadSolicitada(mongo.getCantidadSolicitada());
        detalle.setCantidadRecibida(mongo.getCantidadRecibida());
        detalle.setObservacion(mongo.getObservacion());
        detalle.setCantidadAnterior(mongo.getCantidadAnterior());
        detalle.setCantidadNueva(mongo.getCantidadNueva());
        return detalle;
    }

    /**
     * Convierte un objeto de dominio {@link DetalleLote} a una entidad Mongo
     * {@link DetalleLoteMongo}.
     *
     * @param detalle Objeto de dominio que se desea convertir.
     * @return Entidad Mongo correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static DetalleLoteMongo domainToEntity(DetalleLote detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleLoteMongo mongo = new DetalleLoteMongo();
        mongo.setProducto(ProductoMapperMongo.domainToEntity(detalle.getProducto()));
        mongo.setCantidadRecibida(detalle.getCantidadRecibida());
        mongo.setCantidadSolicitada(detalle.getCantidadSolicitada());
        mongo.setObservacion(detalle.getObservacion());
        mongo.setCantidadAnterior(detalle.getCantidadAnterior());
        mongo.setCantidadNueva(detalle.getCantidadNueva());
        return mongo;
    }

}
