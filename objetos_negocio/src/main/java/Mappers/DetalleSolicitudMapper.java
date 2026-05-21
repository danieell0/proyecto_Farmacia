/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.DetalleSolicitudDTO;
import Entidades.DetalleSolicitud;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link DetalleSolicitud} y objetos DTO {@link DetalleSolicitudDTO}.
 *
 * Contiene métodos estáticos para transformar detalles de solicitud entre la
 * capa de dominio y la capa de transferencia de datos.
 *
 * @author Jorge
 */
public class DetalleSolicitudMapper {

    /**
     * Convierte un objeto de dominio {@link DetalleSolicitud} a un objeto DTO
     * {@link DetalleSolicitudDTO}.
     *
     * @param detalle Objeto de dominio que se desea convertir.
     * @return Objeto DTO correspondiente o {@code null} si el objeto es nulo.
     */
    public static DetalleSolicitudDTO toDTO(DetalleSolicitud detalle) {
        if (detalle == null) {
            return null;
        }
        DetalleSolicitudDTO dto = new DetalleSolicitudDTO();
        dto.setCantidadSolicitada(detalle.getCantidadSolicitada());
        dto.setProducto(ProductoMapper.toDTO(detalle.getProducto()));
        return dto;
    }

    /**
     * Convierte un objeto DTO {@link DetalleSolicitudDTO} a un objeto de
     * dominio {@link DetalleSolicitud}.
     *
     * @param dto Objeto DTO que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static DetalleSolicitud toEntity(DetalleSolicitudDTO dto) {
        if (dto == null) {
            return null;
        }
        DetalleSolicitud detalle = new DetalleSolicitud();
        detalle.setCantidadSolicitada(dto.getCantidadSolicitada());
        detalle.setProducto(ProductoMapper.toEntity(dto.getProducto()));
        return detalle;
    }

}
