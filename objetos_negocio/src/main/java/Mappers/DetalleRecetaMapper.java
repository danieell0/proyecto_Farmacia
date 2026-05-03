package Mappers;

import DTO.DetalleRecetaDTO;
import Entidades.DetalleReceta;

/**
 *
 * @author Dario
 */
public class DetalleRecetaMapper {
    
    public static DetalleRecetaDTO adaptarADTO(DetalleReceta detalleReceta) {
        if (detalleReceta == null) {
            return null;
        }
        DetalleRecetaDTO detalleRecetadto = new DetalleRecetaDTO();
        detalleRecetadto.setIdMedicamento(detalleReceta.getIdMedicamento());
        detalleRecetadto.setCantidadRecetada(detalleReceta.getCantidadRecetada());
        detalleRecetadto.setCantidadSurtida(detalleReceta.getCantidadSurtida());
        return detalleRecetadto;
    }
}
