/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import Entidades.Medicamento;
import Entidades.Producto;

/**
 *
 * @author Jorge
 */
public class ProductoMapper {
    
      public static ProductoDTO toDTO(Producto producto) {

        if (producto == null) {
            return null;
        }
        if (producto instanceof Medicamento m) {

            MedicamentoDTO dto = new MedicamentoDTO();
            dto.setIdProducto(m.getIdProducto());
            dto.setNombre(m.getNombre());
            dto.setPrecio(m.getPrecio());
            dto.setImagen(m.getImagen());

            dto.setMarca(m.getMarca());
            dto.setMedida(m.getMedida());
            dto.setDosis(m.getDosis());
            dto.setPresentacion(m.getPresentacion());
            dto.setEsControlada(m.getEsControlada());

            return dto;
        }
        
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setImagen(producto.getImagen());

        return dto;
    }
    
}
