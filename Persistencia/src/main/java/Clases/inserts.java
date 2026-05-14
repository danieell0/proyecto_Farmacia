/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Medicamento;
import Entidades.Producto;
import Enums.Especialidades;
import Enums.Medida;
import com.mongodb.client.MongoCollection;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class inserts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoCollection<Producto> coleccionProductos = ManejadorConexiones.obtenerColeccionProductos();

        coleccionProductos.insertOne(new Producto(1L, "Agua Natural", 20.0, "/imagenes/paracetamol.png", 25));
        coleccionProductos.insertOne(new Producto(2L, "Galletas Maria", 30.0, "/imagenes/ibuprofeno.png", 20));
        coleccionProductos.insertOne(new Producto(3L, "Jugo de Naranja", 25.0, "/imagenes/omeprazol.png", 10));
        coleccionProductos.insertOne(new Producto(4L, "Papel Higienico", 60.0, "/imagenes/paracetamol.png", 5));
        coleccionProductos.insertOne(new Producto(5L, "Shampoo", 75.0, "/imagenes/ibuprofeno.png", 50));

        coleccionProductos.insertOne(new Medicamento("Genérico", Medida.mg, 500.0, "Tabletas", true, List.of(Especialidades.MEDICOGENERAL), 6L, "Paracetamol", 50.0, "/imagenes/omeprazol.png", 10));
        coleccionProductos.insertOne(new Medicamento("Pfizer", Medida.mg, 400.0, "Cápsulas", true, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), 7L, "Ibuprofeno", 80.0, "/imagenes/paracetamol.png", 20));
        coleccionProductos.insertOne(new Medicamento("Sandoz", Medida.mg, 500.0, "Cápsulas", true, List.of(Especialidades.PEDIATRIA), 8L, "Amoxicilina", 120.0, "/imagenes/ibuprofeno.png", 5));
        coleccionProductos.insertOne(new Medicamento("Bayer", Medida.mg, 100.0, "Tabletas", false, List.of(Especialidades.CARDIOLOGIA), 9L, "Aspirina", 45.0, "/imagenes/omeprazol.png", 20));
        coleccionProductos.insertOne(new Medicamento("Genérico", Medida.mg, 10.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), 10L, "Loratadina", 90.0, "/imagenes/omeprazol.png", 10));
    }

}
