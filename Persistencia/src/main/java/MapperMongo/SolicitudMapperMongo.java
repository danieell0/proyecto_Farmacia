/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Solicitud;
import EntidadesMongo.SolicitudMongo;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link Solicitud} y entidades Mongo {@link SolicitudMongo}.
 *
 * Contiene métodos estáticos para transformar solicitudes entre la capa de
 * dominio y la capa de persistencia.
 *
 * @author Jorge
 */
public class SolicitudMapperMongo {

    /**
     * Convierte una entidad Mongo {@link SolicitudMongo} a un objeto de dominio
     * {@link Solicitud}.
     *
     * @param mongo Entidad Mongo que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si la entidad es
     * nula.
     */
    public static Solicitud entityToDomain(SolicitudMongo mongo) {
        if (mongo == null) {
            return null;
        }
        Solicitud solicitud = new Solicitud();
        solicitud.setCodigoSolicitud(mongo.getCodigoSolicitud());
        solicitud.setIdEmpleado(mongo.getIdEmpleado());
        solicitud.setFechaHoraSolicitud(mongo.getFechaHoraSolicitud());
        solicitud.setEstado(mongo.getEstado());
        solicitud.setDetalles(mongo.getDetalles().stream().map(d -> DetalleSolicitudMapperMongo.entityToDomain(d)).toList());
        return solicitud;
    }

    /**
     * Convierte un objeto de dominio {@link Solicitud} a una entidad Mongo
     * {@link SolicitudMongo}.
     *
     * @param solicitud Objeto de dominio que se desea convertir.
     * @return Entidad Mongo correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static SolicitudMongo domainToEntity(Solicitud solicitud) {
        if (solicitud == null) {
            return null;
        }
        SolicitudMongo mongo = new SolicitudMongo();
        mongo.setCodigoSolicitud(solicitud.getCodigoSolicitud());
        mongo.setIdEmpleado(solicitud.getIdEmpleado());
        mongo.setFechaHoraSolicitud(solicitud.getFechaHoraSolicitud());
        mongo.setEstado(solicitud.getEstado());
        mongo.setDetalles(solicitud.getDetalles().stream().map(d -> DetalleSolicitudMapperMongo.domainToEntity(d)).toList());
        return mongo;
    }
}
