/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.LoteDTO;
import Entidades.Lote;

/**
 *
 * @author Jorge
 */
public class LoteMapper {

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
