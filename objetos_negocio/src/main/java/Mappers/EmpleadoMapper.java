    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.EmpleadoDTO;
import Entidades.Empleado;

/**
 *
 * @author Benjamin
 */
public class EmpleadoMapper {
    /**
     * Convierte una Entidad de la base de datos (Empleado) a un DTO.
     * @param entidad El empleado proveniente de la capa de persistencia.
     * @return El EmpleadoDTO listo para viajar a la capa de presentación.
     */
    public EmpleadoDTO toDTO(Empleado entidad) {
        // Validación de seguridad básica
        if (entidad == null) {
            return null;
        }

        // Aquí resolvemos la diferencia de fechas (LocalDate a LocalDateTime)
        java.time.LocalDateTime fechaConvertida = null;
        if (entidad.getFechaNacimiento() != null) {
            // Le agregamos la hora (00:00:00) para que encaje en el DTO
            fechaConvertida = entidad.getFechaNacimiento().atStartOfDay(); 
        }

        // Empaquetamos los datos en el DTO usando tu constructor
        return new EmpleadoDTO(
            entidad.getIdEmpleado(),
            entidad.getNombre(),
            entidad.getApellidoPaterno(),
            entidad.getApellidoMaterno(),
            entidad.getTelefono(),
            entidad.getRolPuesto(),
            fechaConvertida,
            entidad.getEmpleadoEstatus()
        );
    }

    /**
     * Convierte un DTO de vuelta a una Entidad 
     */
    public Empleado toEntity(EmpleadoDTO dto) {
        if (dto == null) {
            return null;
        }

        java.time.LocalDate fechaConvertida = null;
        if (dto.getFechaNacimiento() != null) {
            // se le quita la hora para que encaje con la entidad
            fechaConvertida = dto.getFechaNacimiento().toLocalDate();
        }

        return new Empleado(
            dto.getID(), 
            dto.getNombre(),
            dto.getApellidoPaterno(),
            dto.getApellidoMaterno(),
            dto.getTelefono(),
            dto.getRolPuesto(),
            fechaConvertida,
            dto.getEstatus() 
        );
    }
    
}
