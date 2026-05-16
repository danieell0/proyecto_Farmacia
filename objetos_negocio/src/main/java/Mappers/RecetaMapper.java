package Mappers;

import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import Entidades.DetalleReceta;
import Entidades.Receta;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase mapper que convierte una Receta a RecetaDTO.
 * @author Dario
 */
public class RecetaMapper {
    
    /**
     * Convierte una receta a DTO.
     * @param receta a convertir a DTO.
     * @return DTO.
     */
    public static RecetaDTO adaptarADTO(Receta receta) {
        if (receta == null) {
            return null;
        }
        RecetaDTO recetadto = new RecetaDTO();
        recetadto.setFolio(receta.getFolio());
        recetadto.setCedulaMedico(receta.getCedulaMedico());
        recetadto.setUsos(receta.getUsos());
        recetadto.setEstado(receta.getEstado());
        recetadto.setFechaCaducidad(receta.getFechaCaducidad());
        if (receta.getDetalles() != null) {
            List<DetalleRecetaDTO> listaDetallesDTO = new ArrayList<>();
            for (DetalleReceta detalle : receta.getDetalles()) {
                DetalleRecetaDTO detalleDTO = DetalleRecetaMapper.adaptarADTO(detalle);
                listaDetallesDTO.add(detalleDTO);
            }
            recetadto.setDetalles(listaDetallesDTO);
        }
        return recetadto;
    }

}
