package Catalogo;

import fachada.ControlCariito;
import DTO.DetalleVentaDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import Enums.Medida;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase fachada encargada de proporcionar acceso a las operaciones relacionadas
 * con el catálogo de productos.
 *
 * Implementa la interfaz {@link ICatalogo} y delega las operaciones al
 * controlador {@link ControlObtenerProductos}.
 *
 * @author Jorge
 */
public class Fachada implements ICatalogo {

    //Controlador encargado de las operaciones de productos
    private ControlObtenerProductos control;

    /**
     * Constructor de la clase Fachada.
     *
     * Inicializa el controlador de productos.
     */
    public Fachada() {
        this.control = new ControlObtenerProductos();
    }

    /**
     * Obtiene todos los productos disponibles.
     *
     * @return Lista de productos en formato DTO.
     */
    @Override
    public List<ProductoDTO> obtenerProductos() {
        return control.obtenerProductos();
    }

    /**
     * Busca productos por nombre.
     *
     * @param nombre Nombre del producto a buscar.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<ProductoDTO> buscarProductosNombre(String nombre) {
        return control.obtenerProductoPorNombre(nombre);
    }

    /**
     * Busca productos mediante su código.
     *
     * @param codigo Código o clave del producto.
     * @return Lista de productos encontrados.
     */
    @Override
    public List<ProductoDTO> buscarProductoPorCodigo(String codigo) {
        return control.obtenerProductoPorCodigo(codigo);
    }
    
    @Override
    public List<ProductoDTO> obtenerProductosConcordantes(String idCliente, Double puntos) {
        return control.obtenerProductosConcordantes(idCliente, puntos);
    }

}
