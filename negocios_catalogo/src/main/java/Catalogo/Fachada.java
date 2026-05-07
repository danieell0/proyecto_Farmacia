package Catalogo;

import fachada.ControlCariito;
import DTO.DetalleVentaDTO;
import DTO.MedicamentoDTO;
import DTO.ProductoDTO;
import Enums.Medida;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Fachada implements ICatalogo {
    
    private ControlObtenerProductos control;

    public Fachada() {
        this.control = new ControlObtenerProductos();
    }

    @Override
    public List<ProductoDTO> obtenerProductos() {
        return control.obtenerProductos();
    }

    @Override
    public List<ProductoDTO> buscarProductosNombre(String nombre) {
        return control.obtenerProductoPorNombre(nombre);
    }

    @Override
    public List<ProductoDTO> buscarProductoPorCodigo(Long codigo) {
        return control.obtenerProductoPorCodigo(codigo);
    }

}
