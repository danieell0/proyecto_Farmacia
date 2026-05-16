package Mappers;

import DTO.DetalleRecetaDTO;
import Entidades.DetalleReceta;

/**
 * Clase Mapper que adapta un DetalleReceta a un DetalleRecetaDTO.
 * @author Dario
 */
public class DetalleRecetaMapper {
    
    /**
     * Convierte un DetalleReceta a un DetalleRecetaDTO.
     * @param detalleReceta entidad a convertir a DTO.
     * @return DTO.
     */
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
