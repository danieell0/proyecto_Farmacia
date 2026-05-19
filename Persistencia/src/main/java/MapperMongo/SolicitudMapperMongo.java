/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Solicitud;
import EntidadesMongo.SolicitudMongo;

/**
 *
 * @author Jorge
 */
public class SolicitudMapperMongo {
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
