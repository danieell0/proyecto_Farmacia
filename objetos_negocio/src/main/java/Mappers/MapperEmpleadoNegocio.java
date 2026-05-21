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
public class MapperEmpleadoNegocio {
    public static Empleado aEntidad(EmpleadoDTO dto) {
        if (dto == null) return null;
        
        Empleado entidad = new Empleado();
        entidad.setIdEmpleado(dto.getIdEmpleado());
        entidad.setNombre(dto.getNombre());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setTelefono(dto.getTelefono());
        entidad.setRolPuesto(dto.getRolPuesto());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        entidad.setEmpleadoEstatus(dto.getEstatus());
        
        return entidad;
    }

    public static EmpleadoDTO aDTO(Empleado entidad) {
        if (entidad == null) return null;
        
        EmpleadoDTO dto = new EmpleadoDTO();
        dto.setIdEmpleado(entidad.getIdEmpleado());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setTelefono(entidad.getTelefono());
        dto.setRolPuesto(entidad.getRolPuesto());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        dto.setEstatus(entidad.getEmpleadoEstatus());
        
        return dto;
    }
}
