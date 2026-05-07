/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Producto;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IProductoDAO {

    public List<Producto> obtenerProductos();
    
    public List<Producto> obtenerProductosPorNombre(String nombre);
     
    public Producto obtenerProductoPorId(Long id);
    
}
