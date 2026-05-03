package Mappers;

import DTO.RecetaDTO;
import Entidades.Receta;
import Enums.EstadoReceta;

/**
 * 
 * @author Dario
 */
public class RecetaMapper {
    public static RecetaDTO toDTO(Receta receta) {
        if (receta == null) {
            return null;
        }
        RecetaDTO recetadto = new RecetaDTO();
        recetadto.setFolio(receta.getFolio());
        recetadto.setCedulaMedico(receta.getCedulaMedico());
        recetadto.setUsos(receta.getUsos());
        recetadto.setEstado(receta.getEstado());
        recetadto.setFechaCaducidad(receta.getFechaCaducidad());
        recetadto.setDetalles(receta.getDetalles());
        return receta;
    }

    // Mapeo para Medicamento (Clase independiente)
    public static MedicamentoDTO toDTO(Medicamento entidad) {
        if (entidad == null) return null;
        
        MedicamentoDTO dto = new MedicamentoDTO();
        // Mapeamos los campos comunes manualmente si no hay herencia
        dto.setIdProducto(entidad.getIdProducto());
        dto.setNombre(entidad.getNombre());
        dto.setPrecio(entidad.getPrecio());
        
        // Atributos únicos de Medicamento
        dto.setEsControlada(entidad.getEsControlada());
        dto.setDosis(entidad.getDosis());
        return dto;
    }
}
