/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mappers;

import DTO.CanjeableDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import Entidades.Canjeable;
import Entidades.Medicamento;
import Entidades.Producto;

/**
 *
 * Clase encargada de realizar la conversión entre entidades del dominio
 * {@link Producto} y objetos DTO {@link ProductoDTO}.
 *
 * @author Jorge
 */
public class ProductoMapper {

    /**
     * Convierte una entidad {@link Producto} a un objeto {@link ProductoDTO}.
     * @param producto Entidad de producto a convertir.
     * @return Objeto DTO correspondiente o {@code null} si el producto es nulo.
     */
    public static ProductoDTO toDTO(Producto producto) {
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
        if (producto instanceof Canjeable c) {
            CanjeableDTO dto = new CanjeableDTO();
            dto.setIdProducto(c.getIdProducto());
            dto.setNombre(c.getNombre());
            dto.setMarca(c.getMarca());
            dto.setTipo(c.getTipo());
            dto.setPrecio(c.getPrecio());
            dto.setImagen(c.getImagen());
            dto.setStock(c.getStock());
            dto.setPuntos(c.getPuntos());
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

    /**
     * Convierte un objeto {@link ProductoDTO} a una entidad {@link Producto}.
     * @param dto DTO del producto a convertir.
     * @return Entidad correspondiente o {@code null} si el DTO es nulo.
     */
    public static Producto toEntity(ProductoDTO dto) {
        if (dto == null) {
            return null;
        }
        if (dto instanceof MedicamentoDTO m) {
            Medicamento entity = new Medicamento();
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
        if (dto instanceof CanjeableDTO c) {
            Canjeable entity = new Canjeable();
            entity.setIdProducto(c.getIdProducto());
            entity.setMarca(c.getMarca());
            entity.setTipo(c.getTipo());
            entity.setNombre(c.getNombre());
            entity.setPrecio(c.getPrecio());
            entity.setImagen(c.getImagen());
            entity.setStock(c.getStock());
            entity.setPuntos(c.getPuntos());
            return entity;
        }
        Producto entity = new Producto();
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
