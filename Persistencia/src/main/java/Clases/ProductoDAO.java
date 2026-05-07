/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Entidades.Medicamento;
import Entidades.Producto;
import Enums.Especialidades;
import Enums.Medida;
import Interfaces.IProductoDAO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class ProductoDAO implements IProductoDAO {

    private List<Producto> productos;

    public ProductoDAO() {
        productos = new ArrayList<>();
        productos.add(new Producto(1L, "Agua Natural", 20.0, "/imagenes/paracetamol.png"));
        productos.add(new Producto(2L, "Galletas María", 30.0, "/imagenes/ibuprofeno.png"));
        productos.add(new Producto(3L, "Jugo de Naranja", 25.0, "/imagenes/omeprazol.png"));
        productos.add(new Producto(4L, "Papel Higiénico", 60.0, "/imagenes/paracetamol.png"));
        productos.add(new Producto(5L, "Shampoo", 75.0, "/imagenes/ibuprofeno.png"));

        productos.add(new Medicamento("Genérico",Medida.mg,500.0,"Tabletas",true,List.of(Especialidades.MEDICOGENERAL),6L,"Paracetamol",50.0,"/imagenes/omeprazol.png"));
        productos.add(new Medicamento("Pfizer",Medida.mg,400.0,"Cápsulas",true,List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA),7L,"Ibuprofeno",80.0,"/imagenes/paracetamol.png"));
        productos.add(new Medicamento("Sandoz",Medida.mg,500.0,"Cápsulas",true,List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA),8L,"Amoxicilina",120.0,"/imagenes/ibuprofeno.png"));
        productos.add(new Medicamento("Bayer",Medida.mg,100.0,"Tabletas",false,List.of(Especialidades.CARDIOLOGIA),9L,"Aspirina",45.0,"/imagenes/omeprazol.png"));
        productos.add(new Medicamento("Genérico",Medida.mg,10.0,"Tabletas",false,List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA),10L,"Loratadina",90.0,"/imagenes/omeprazol.png"));

    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return new ArrayList<>(productos);
    }

    @Override
    public List<Producto> obtenerProductosPorNombre(String nombre) {
        return productos.stream().filter(p -> p.getNombre() != null && p.getNombre().toLowerCase().contains(nombre.toLowerCase())).toList();
    }
}
