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
            dto.setIdProducto(m.getIdProducto());
            dto.setNombre(m.getNombre());
            dto.setMarca(m.getMarca());
            dto.setTipo(m.getTipo());
            dto.setPrecio(m.getPrecio());
            dto.setImagen(m.getImagen());
            dto.setMedida(m.getMedida());
            dto.setDosis(m.getDosis());
            dto.setStock(m.getStock());
            dto.setPresentacion(m.getPresentacion());
            dto.setEsControlado(m.getEsControlada());
            dto.setEspecialidades(m.getEspecialidades());
            return dto;
        }
        ProductoDTO dto = new ProductoDTO();
        dto.setIdProducto(producto.getIdProducto());
        dto.setMarca(producto.getMarca());
        dto.setTipo(producto.getTipo());
        dto.setNombre(producto.getNombre());
        dto.setPrecio(producto.getPrecio());
        dto.setImagen(producto.getImagen());
        dto.setStock(producto.getStock()); 
        return dto;
    }
    
    public Producto toEntity(ProductoDTO dto){
        if(dto==null){
            return null;
        }
        if(dto instanceof MedicamentoDTO m){
            Medicamento entity=new Medicamento();
            entity.setIdProducto(m.getIdProducto());
            entity.setTipo(m.getTipo());
            entity.setNombre(m.getNombre());
            entity.setPrecio(m.getPrecio());
            entity.setImagen(m.getImagen());
            entity.setMarca(m.getMarca());
            entity.setMedida(m.getMedida());
            entity.setDosis(m.getDosis());
            entity.setStock(m.getStock());
            entity.setPresentacion(m.getPresentacion());
            entity.setEsControlada(m.getEsControlado());
            entity.setEspecialidades(m.getEspecialidades());
            return entity;
        }
        Producto entity=new Producto();
        entity.setIdProducto(dto.getIdProducto());
        entity.setMarca(dto.getMarca());
        entity.setTipo(dto.getTipo());
        entity.setNombre(dto.getNombre());
        entity.setPrecio(dto.getPrecio());
        entity.setImagen(dto.getImagen());
        entity.setStock(dto.getStock()); 
        return entity;
    }

}
