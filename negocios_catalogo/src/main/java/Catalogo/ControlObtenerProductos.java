/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Catalogo;

import fachada.ControlCariito;
import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import com.mycompany.objetos_negocio.ProductoBO;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ControlObtenerProductos {

    protected ProductoBO catalogoBO;
    protected ControlCariito carrito;

    protected ControlObtenerProductos() {
        this.catalogoBO = new ProductoBO();
    }

    protected List<ProductoDTO> obtenerProductos() {
        return catalogoBO.obtenerProductos();
    }

    protected List<ProductoDTO> obtenerProductoPorNombre(String nombre) {
        return catalogoBO.obtenerProductosPorNombre(nombre);
    }
    
}
