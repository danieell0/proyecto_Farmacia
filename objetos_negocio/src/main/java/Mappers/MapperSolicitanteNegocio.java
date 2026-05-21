/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.SolicitanteDTO;
import Entidades.Solicitante;

/**
 *
 * @author Benjamin
 */
public class MapperSolicitanteNegocio {
    public static Solicitante aEntidad(SolicitanteDTO dto) {
        if (dto == null) return null;
        
        Solicitante entidad = new Solicitante();
        entidad.setIdSolicitante(dto.getIdSolicitante());
        entidad.setNombre(dto.getNombre());
        entidad.setApellidoPaterno(dto.getApellidoPaterno());
        entidad.setApellidoMaterno(dto.getApellidoMaterno());
        entidad.setTelefono(dto.getTelefono());
        entidad.setRolPuesto(dto.getRolPuesto());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        // Nota: Si el DTO de creación no trae notas aún, quedará nulo, es correcto.
        
        return entidad;
    }

    public static SolicitanteDTO aDTO(Solicitante entidad) {
        if (entidad == null) return null;
        
        SolicitanteDTO dto = new SolicitanteDTO();
        dto.setIdSolicitante(entidad.getIdSolicitante());
        dto.setNombre(entidad.getNombre());
        dto.setApellidoPaterno(entidad.getApellidoPaterno());
        dto.setApellidoMaterno(entidad.getApellidoMaterno());
        dto.setTelefono(entidad.getTelefono());
        dto.setRolPuesto(entidad.getRolPuesto());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        
        return dto;
    }
}
