package com.mycompany.subsistemas;

import java.util.List;

/**
 * Interfaz que define los metodos del subsistema de control catalogo.
 * @author Dario
 */
public interface IControlCatalogo {
    
    //El metodo tendra como parametro idProducto.
    public abstract boolean validarStock()
            throws SubsistemaException;
    
    //El String cambiar a Producto y tendra como parametro Producto.
    public abstract String buscarProducto()
            throws SubsistemaException;
    
    //El String cambiara a Producto.
    public abstract List<String> listarProductos (String filtro)
            throws SubsistemaException;
}
