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

    public ProductoDTO toDTO(Producto producto) {
        if (producto == null) {
            return null;
        }
        if (producto instanceof Medicamento m) {
            MedicamentoDTO dto = new MedicamentoDTO();
            dto.setId(m.getIdProducto());
            dto.setNombre(m.getNombre());
            dto.setPrecio(m.getPrecio());
            dto.setImagen(m.getImagen());
            dto.setMarca(m.getMarca());
            dto.setMedida(m.getMedida());
            dto.setDosis(m.getDosis());
            dto.setPresentacion(m.getPresentacion());
            dto.setEsControlado(m.getEsControlada());
            return dto;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getIdProducto());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setImagen(producto.getImagen());
        return dto;
    }
    
    public Producto toEntity(ProductoDTO dto){
        if(dto==null){
            return null;
        }
        if(dto instanceof MedicamentoDTO m){
            Medicamento entity=new Medicamento();
            entity.setIdProducto(m.getId());
            entity.setNombre(m.getNombre());
            entity.setPrecio(m.getPrecio());
            entity.setImagen(m.getImagen());
            entity.setMarca(m.getMarca());
            entity.setMedida(m.getMedida());
            entity.setDosis(m.getDosis());
            entity.setPresentacion(m.getPresentacion());
            entity.setEsControlada(m.isEsControlado());
            return entity;
        }
        Producto entity=new Producto();
        entity.setIdProducto(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setImagen(dto.getImagen());
        return entity;
    }

}
