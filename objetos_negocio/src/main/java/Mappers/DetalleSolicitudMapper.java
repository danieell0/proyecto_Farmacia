/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.DetalleSolicitudDTO;
import Entidades.DetalleSolicitud;

/**
 *
 * @author Jorge
 */
public class DetalleSolicitudMapper {
    
    public static DetalleSolicitudDTO toDTO(DetalleSolicitud detalle){
        if(detalle==null){
            return null;
        }
        DetalleSolicitudDTO dto=new DetalleSolicitudDTO();
        dto.setCantidadSolicitada(detalle.getCantidadSolicitada());
        dto.setProducto(ProductoMapper.toDTO(detalle.getProducto()));
        return dto;
    }
    public static DetalleSolicitud toEntity(DetalleSolicitudDTO dto){
        if(dto==null){
            return null;
        }
        DetalleSolicitud detalle=new DetalleSolicitud();
        detalle.setCantidadSolicitada(dto.getCantidadSolicitada());
        detalle.setProducto(ProductoMapper.toEntity(dto.getProducto()));
        return detalle;
    }
            
}
