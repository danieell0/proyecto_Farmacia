package Catalogo;

import DTO.ProductoDTO;
import java.util.List;

/**
 * Interfaz que define las operaciones disponibles para el catálogo de
 * productos.
 *
 * Permite obtener y buscar productos mediante distintos criterios de búsqueda.
 *
 * @author Jorge
 */
public interface ICatalogo {

    /**
     * Obtiene todos los productos disponibles.
     *
     * @return Lista de productos en formato DTO.
     */
    public List<ProductoDTO> obtenerProductos();

    /**
     * Busca productos por nombre.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    public List<ProductoDTO> buscarProductosNombre(String nombre);

    /**
     * Busca productos mediante su código o clave.
     *
     * @param codigo Código del producto.
     * @return Lista de productos encontrados.
     */
    public List<ProductoDTO> buscarProductoPorCodigo(String codigo);

    public List<ProductoDTO> obtenerProductosConcordantes(String idCliente, Double puntos);
}
