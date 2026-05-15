/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.CuentaAcceso;
import Entidades.DetalleReceta;
import Entidades.Empleado;
import Entidades.Medicamento;
import Entidades.Producto;
import Entidades.Receta;
import Enums.Especialidades;
import Enums.EstadoReceta;
import Enums.EstatusEmpleado;
import Enums.Medida;
import Enums.RolPuesto;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;
import java.util.Arrays;
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
        
        coleccionProductos.drop();
        
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
        
        
        MongoCollection<Receta> coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
        coleccionRecetas.insertOne(new Receta("101L", "MG01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 12, 31), Arrays.asList(new DetalleReceta(8L, 10, 0), new DetalleReceta(6L, 10, 0))));
        coleccionRecetas.insertOne(new Receta("102L", "CA01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 12, 31), Arrays.asList(new DetalleReceta(1L, 5, 0), new DetalleReceta(9L, 5, 0))));
        coleccionRecetas.insertOne(new Receta("103L", "PS03", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 6, 15), Arrays.asList(new DetalleReceta(4L, 2, 0))));
        coleccionRecetas.insertOne(new Receta("104L", "ON01", 0, EstadoReceta.CADUCADA, LocalDate.of(2023, 12, 31), Arrays.asList(new DetalleReceta(5L, 10, 0))));
        
        MongoCollection<Empleado> coleccionEmpleados = ManejadorConexiones.obtenerColeccionEmpleados();
        
        coleccionEmpleados.drop();
        
        coleccionEmpleados.insertOne(new Empleado(123L, "Juan", "Perez", "Gomez", "555-0001", 
                                     RolPuesto.LIDER, LocalDate.of(1990, 5, 20), EstatusEmpleado.ACTIVO));
        
        coleccionEmpleados.insertOne(new Empleado(456L, "Maria", "Lopez", "Diaz", "555-0002", 
                                     RolPuesto.CAJERO, LocalDate.of(1995, 8, 15), EstatusEmpleado.ACTIVO));
        
        coleccionEmpleados.insertOne(new Empleado(789L, "Carlos", "Ruiz", "Soto", "555-0003", 
                                     RolPuesto.CAJERO, LocalDate.of(1998, 2, 10), EstatusEmpleado.INACTIVO));
        
        
        MongoCollection<CuentaAcceso> coleccionCuentas = ManejadorConexiones.obtenerColeccionCuentas();
        
        coleccionCuentas.drop();
        
        coleccionCuentas.insertOne(new CuentaAcceso(123L, "admin"));
        coleccionCuentas.insertOne(new CuentaAcceso(456L, "caja"));
        coleccionCuentas.insertOne(new CuentaAcceso(789L, "caja2"));
        
    }

}
