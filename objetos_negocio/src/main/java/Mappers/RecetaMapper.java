package Mappers;

import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import Entidades.DetalleReceta;
import Entidades.Receta;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar la conversión entre objetos de dominio
 * {@link Receta} y objetos DTO {@link RecetaDTO}.
 *
 * Contiene métodos estáticos para transformar recetas entre la capa de dominio
 * y la capa de transferencia de datos.
 *
 * @author Dario
 */
public class RecetaMapper {

    /**
     * Convierte un objeto de dominio {@link Receta} a un objeto DTO
     * {@link RecetaDTO}.
     *
     * También realiza la conversión de los detalles asociados a la receta.
     *
     * @param receta Objeto de dominio que se desea convertir.
     * @return Objeto DTO correspondiente o {@code null} si el objeto es nulo.
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
