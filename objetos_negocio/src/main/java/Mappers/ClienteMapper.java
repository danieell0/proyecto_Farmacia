package Mappers;

import DTO.ClienteDTO;
import Entidades.Cliente;

/**
 *
 * @author Dario
 */
public class ClienteMapper {
    
    public static ClienteDTO toDTO(Cliente entidad) {
        if (entidad == null) {
            return null;
        }
        
        ClienteDTO dto = new ClienteDTO();
        dto.setIdCliente(entidad.getIdCliente());
        dto.setNombre(entidad.getNombre());
        dto.setTelefono(entidad.getTelefono());
        dto.setPuntos(entidad.getPuntos());
        dto.setFechaNacimiento(entidad.getFechaNacimiento());
        
        return dto;
    }

    public static Cliente toEntity(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Cliente entidad = new Cliente();
        entidad.setIdCliente(dto.getIdCliente());
        entidad.setNombre(dto.getNombre());
        entidad.setTelefono(dto.getTelefono());
        entidad.setPuntos(dto.getPuntos());
        entidad.setFechaNacimiento(dto.getFechaNacimiento());
        
        return entidad;
    }
}
