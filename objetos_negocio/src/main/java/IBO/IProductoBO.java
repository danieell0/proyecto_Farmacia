/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package IBO;

import DTO.ProductoDTO;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IProductoBO {
    public List<ProductoDTO> obtenerProductos();
    
    public List<ProductoDTO> obtenerProductosPorNombre(String nombre);
    
    public List<ProductoDTO> obtenerProductoPorClave(String clave);
    
}
