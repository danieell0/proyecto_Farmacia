/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.SolicitudDTO;
import Entidades.Solicitud;

/**
 *
 * @author Jorge
 */
public class SolicitudMapper {
    public static SolicitudDTO toDTO(Solicitud solcitud){
        if(solcitud==null){
            return null;
        }
        SolicitudDTO dto=new SolicitudDTO();
        dto.setCodigoSolicitud(solcitud.getCodigoSolicitud());
        dto.setIdEmpleado(solcitud.getIdEmpleado());
        dto.setDetalles(solcitud.getDetalles().stream().map(s->DetalleSolicitudMapper.toDTO(s)).toList());
        dto.setFechaHoraSolicitud(solcitud.getFechaHoraSolicitud());
        dto.setEstado(solcitud.getEstado());
        return dto;
    }
    public static Solicitud toEntity(SolicitudDTO dto){
        if(dto==null){
            return null;
        }
        Solicitud solicitud=new Solicitud();
        solicitud.setCodigoSolicitud(dto.getCodigoSolicitud());
        solicitud.setIdEmpleado(dto.getIdEmpleado());
        solicitud.setDetalles(dto.getDetalles().stream().map(a->DetalleSolicitudMapper.toEntity(a)).toList());
        solicitud.setFechaHoraSolicitud(dto.getFechaHoraSolicitud());
        solicitud.setEstado(dto.getEstado());
        return solicitud;
    }
}
