/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.DetalleLoteDTO;
import Entidades.DetalleLote;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link DetalleLote} y objetos DTO {@link DetalleLoteDTO}.
 *
 * Contiene métodos estáticos para transformar detalles de lote entre la capa de
 * dominio y la capa de transferencia de datos.
 *
 * @author Jorge
 */
public class DetalleLoteMapper {

    /**
     * Convierte un objeto de dominio {@link DetalleLote} a un objeto DTO
     * {@link DetalleLoteDTO}.
     *
     * @param detalle Objeto de dominio que se desea convertir.
     * @return Objeto DTO correspondiente o {@code null} si el objeto es nulo.
     */
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

    /**
     * Convierte un objeto DTO {@link DetalleLoteDTO} a un objeto de dominio
     * {@link DetalleLote}.
     *
     * @param dto Objeto DTO que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si el objeto es
     * nulo.
     */
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
