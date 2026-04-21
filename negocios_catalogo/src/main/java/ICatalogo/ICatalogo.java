/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ICatalogo;

import com.mycompany.dto_negocios.ProductoDTO;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface ICatalogo {

    public List<ProductoDTO> obtenerProductos();

    public List<ProductoDTO> buscarProductosNombre(String nombre);

    public ProductoDTO obtenerProductoId(Long id);
    
}
