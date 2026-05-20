/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.DetalleLoteDTO;
import Entidades.DetalleLote;

/**
 *
 * @author Jorge
 */
public class DetalleLoteMapper {

    public static DetalleLoteDTO toDTO(DetalleLote detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleLoteDTO dto = new DetalleLoteDTO();
        dto.setProducto(ProductoMapper.toDTO(detalle.getProducto()));
        dto.setObservacion(detalle.getObservacion());
        dto.setCantidadSolicitada(detalle.getCantidadSolicitada());
        dto.setCantidadRecibida(detalle.getCantidadRecibida());
        dto.setCantidadNueva(detalle.getCantidadNueva());
        dto.setCantidadAnterior(detalle.getCantidadAnterior());
        return dto;
    }

    public static DetalleLote toEntity(DetalleLoteDTO dto) {
        if (dto == null) {
            return null;
        }
        DetalleLote detalle = new DetalleLote();
        detalle.setProducto(ProductoMapper.toEntity(dto.getProducto()));
        detalle.setObservacion(dto.getObservacion());
        detalle.setCantidadSolicitada(dto.getCantidadSolicitada());
        detalle.setCantidadRecibida(dto.getCantidadRecibida());
        detalle.setCantidadNueva(dto.getCantidadNueva());
        detalle.setCantidadAnterior(dto.getCantidadAnterior());
        return detalle;
    }
}
