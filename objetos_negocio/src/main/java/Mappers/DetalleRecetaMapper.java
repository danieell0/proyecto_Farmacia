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
        detalleReceta.setIdMedicamento(detalleRecetadto.getIdMedicamento());
        detalleReceta.setCantidadRecetada(detalleRecetadto.getCantidadRecetada());
        detalleReceta.setCantidadSurtida(detalleRecetadto.getCantidadSurtida());
        detalleReceta.setProducto(ProductoMapper.toDTO(detalleRecetadto.getProducto()));
        return detalleRecetadto;
    }
}
