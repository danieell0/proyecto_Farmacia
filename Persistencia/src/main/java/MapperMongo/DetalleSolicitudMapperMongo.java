/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.DetalleSolicitud;
import EntidadesMongo.DetalleSolicitudMongo;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link DetalleSolicitud} y entidades Mongo {@link DetalleSolicitudMongo}.
 *
 * Contiene métodos estáticos para transformar detalles de solicitud entre la
 * capa de dominio y la capa de persistencia.
 *
 * @author Jorge
 */
public class DetalleSolicitudMapperMongo {

    /**
     * Convierte una entidad Mongo {@link DetalleSolicitudMongo} a un objeto de
     * dominio {@link DetalleSolicitud}.
     *
     * @param mongo Entidad Mongo que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si la entidad es
     * nula.
     */
    public static DetalleSolicitud entityToDomain(DetalleSolicitudMongo mongo) {
        if (mongo == null) {
            return null;
        }
        DetalleSolicitud detalle = new DetalleSolicitud();
        detalle.setProducto(ProductoMapperMongo.entityToDomain(mongo.getProducto()));
        detalle.setCantidadSolicitada(mongo.getCantidadSolicitada());
        return detalle;
    }

    /**
     * Convierte un objeto de dominio {@link DetalleSolicitud} a una entidad
     * Mongo {@link DetalleSolicitudMongo}.
     *
     * @param detalle Objeto de dominio que se desea convertir.
     * @return Entidad Mongo correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static DetalleSolicitudMongo domainToEntity(DetalleSolicitud detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleSolicitudMongo mongo = new DetalleSolicitudMongo();
        mongo.setProducto(ProductoMapperMongo.domainToEntity(detalle.getProducto()));
        mongo.setCantidadSolicitada(detalle.getCantidadSolicitada());
        return mongo;
    }
}
