/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.LoteDTO;
import Entidades.Lote;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link Lote} y objetos DTO {@link LoteDTO}.
 *
 * Contiene métodos estáticos para transformar lotes entre la capa de dominio y
 * la capa de transferencia de datos.
 *
 * @author Jorge
 */
public class LoteMapper {

    /**
     * Convierte un objeto de dominio {@link Lote} a un objeto DTO
     * {@link LoteDTO}.
     *
     * @param lote Objeto de dominio que se desea convertir.
     * @return Objeto DTO correspondiente o {@code null} si el objeto es nulo.
     */
    public static LoteDTO toDTO(Lote lote) {
        if (lote == null) {
            return null;
        }
        LoteDTO dto = new LoteDTO();
        dto.setCodigoLote(lote.getCodigoLote());
        dto.setObservacionGeneral(lote.getObservacionGeneral());
        dto.setProveedor(lote.getProveedor());
        dto.setDetalles(lote.getDetalles().stream().map(l -> DetalleLoteMapper.toDTO(l)).toList());
        return dto;
    }

    /**
     * Convierte un objeto DTO {@link LoteDTO} a un objeto de dominio
     * {@link Lote}.
     *
     * @param dto Objeto DTO que se desea convertir.
     * @return Objeto de dominio correspondiente o {@code null} si el objeto es
     * nulo.
     */
    public static Lote toEntity(LoteDTO dto) {
        if (dto == null) {
            return null;
        }
        Lote lote = new Lote();
        lote.setCodigoLote(dto.getCodigoLote());
        lote.setObservacionGeneral(dto.getObservacionGeneral());
        lote.setProveedor(dto.getProveedor());
        lote.setDetalles(dto.getDetalles().stream().map(d -> DetalleLoteMapper.toEntity(d)).toList());
        return lote;
    }
}
