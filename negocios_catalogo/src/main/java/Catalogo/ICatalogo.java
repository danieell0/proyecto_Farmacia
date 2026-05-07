package Catalogo;

import DTO.ProductoDTO;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface ICatalogo {

    public List<ProductoDTO> obtenerProductos();

    public List<ProductoDTO> buscarProductosNombre(String nombre);
    
    public List<ProductoDTO> buscarProductoPorCodigo(Long codigo);
        
}
