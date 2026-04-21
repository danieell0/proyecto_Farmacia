/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FCatalogo;

import ICatalogo.ICatalogo;
import com.mycompany.dto_negocios.MedicamentoDTO;
import com.mycompany.dto_negocios.ProductoDTO;
import com.mycompany.dto_negocios.enums.Medida;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class FCatalogo implements ICatalogo {

    private List<ProductoDTO> productos;

    public FCatalogo() {
        productos = new ArrayList();
        productos.add(new ProductoDTO(1L, "Agua Natural", 20.0, 50, "/imagenes/paracetamol.png"));
        productos.add(new ProductoDTO(2L, "Galletas María", 30.0, 40, "/imagenes/ibuprofeno.png"));
        productos.add(new ProductoDTO(3L, "Jugo de Naranja", 25.0, 35, "/imagenes/omeprazol.png"));
        productos.add(new ProductoDTO(4L, "Papel Higiénico", 60.0, 20, "/imagenes/paracetamol.png"));
        productos.add(new ProductoDTO(5L, "Shampoo", 75.0, 15, "/imagenes/ibuprofeno.png"));

        productos.add(new MedicamentoDTO(6L, "Paracetamol", 50.0, 30, "/imagenes/omeprazol.png", "Genérico", Medida.mg, 500.0, "Tabletas", false));
        productos.add(new MedicamentoDTO(7L, "Ibuprofeno", 80.0, 25, "/imagenes/paracetamol.png", "Pfizer", Medida.mg, 400.0, "Cápsulas", false));
        productos.add(new MedicamentoDTO(8L, "Amoxicilina", 120.0, 20, "/imagenes/ibuprofeno.png", "Sandoz", Medida.mg, 500.0, "Cápsulas", true));
        productos.add(new MedicamentoDTO(9L, "Aspirina", 45.0, 40, "/imagenes/omeprazol.png", "Bayer", Medida.mg, 100.0, "Tabletas", false));
        productos.add(new MedicamentoDTO(10L, "Loratadina", 90.0, 18, "/imagenes/omeprazol.png", "Genérico", Medida.mg, 10.0, "Tabletas", false));
    }

    @Override
    public List<ProductoDTO> obtenerProductos() {
        return productos.stream().filter(p-> p.getStock()!=null && p.getStock()>0).toList();
    }

    @Override
    public List<ProductoDTO> buscarProductosNombre(String nombre) {
       return productos.stream().filter(p -> p.getNombre() != null && p.getNombre().toLowerCase().contains(nombre.toLowerCase())).toList();
    }

    @Override
    public ProductoDTO obtenerProductoId(Long id) {
        return productos.stream().filter(p-> id!=null && p.getId()!=null&& p.getId()==id).findFirst().orElse(null);
    }

}
