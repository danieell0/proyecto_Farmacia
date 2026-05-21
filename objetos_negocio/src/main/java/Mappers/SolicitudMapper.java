/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.SolicitudDTO;
import Entidades.Solicitud;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link Solicitud} y objetos DTO {@link SolicitudDTO}.
 *
 * Contiene métodos estáticos para transformar solicitudes entre la capa de
 * dominio y la capa de transferencia de datos.
 *
 * @author Jorge
 */
public class SolicitudMapper {

    /**
     * Convierte un objeto de dominio {@link Solicitud} a un objeto DTO
     * {@link SolicitudDTO}.
     *
     * También realiza la conversión de los detalles asociados a la solicitud.
     *
     * @param solcitud Objeto de dominio que se desea convertir.
     * @return Objeto DTO correspondiente o {@code null} si el objeto es nulo.
     */
    public static SolicitudDTO toDTO(Solicitud solcitud) {
        if (solcitud == null) {
            return null;
        }
        SolicitudDTO dto = new SolicitudDTO();
        dto.setCodigoSolicitud(solcitud.getCodigoSolicitud());
        dto.setIdEmpleado(solcitud.getIdEmpleado());
        dto.setDetalles(solcitud.getDetalles().stream().map(s -> DetalleSolicitudMapper.toDTO(s)).toList());
        dto.setFechaHoraSolicitud(solcitud.getFechaHoraSolicitud());
        dto.setEstado(solcitud.getEstado());
        return dto;
    }

    /**
     * Convierte un objeto DTO {@link SolicitudDTO} a un objeto de dominio
     * {@link Solicitud}.
     *
     * También realiza la conversión de los detalles asociados a la solicitud.
     *
     * @param dto Objeto DTO que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static Solicitud toEntity(SolicitudDTO dto) {
        if (dto == null) {
            return null;
        }
        Solicitud solicitud = new Solicitud();
        solicitud.setCodigoSolicitud(dto.getCodigoSolicitud());
        solicitud.setIdEmpleado(dto.getIdEmpleado());
        solicitud.setDetalles(dto.getDetalles().stream().map(a -> DetalleSolicitudMapper.toEntity(a)).toList());
        solicitud.setFechaHoraSolicitud(dto.getFechaHoraSolicitud());
        solicitud.setEstado(dto.getEstado());
        return solicitud;
    }
}
