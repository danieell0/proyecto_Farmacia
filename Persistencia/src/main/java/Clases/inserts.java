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
import EntidadesMongo.MedicamentoMongo;
import EntidadesMongo.ProductoMongo;
import Enums.Especialidades;
import Enums.EstadoReceta;
import Enums.EstatusEmpleado;
import Enums.Medida;
import Enums.RolPuesto;
import com.mongodb.client.MongoCollection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
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
        MongoCollection<ProductoMongo> coleccionProductos = ManejadorConexiones.obtenerColeccionProductos();

        coleccionProductos.drop();

        coleccionProductos.insertOne(new ProductoMongo(1L, "Agua Natural", 20.0, "/imagenes/paracetamol.png", 25));
        coleccionProductos.insertOne(new ProductoMongo(2L, "Galletas Maria", 30.0, "/imagenes/ibuprofeno.png", 20));
        coleccionProductos.insertOne(new ProductoMongo(3L, "Jugo de Naranja", 25.0, "/imagenes/omeprazol.png", 10));
        coleccionProductos.insertOne(new ProductoMongo(4L, "Papel Higienico", 60.0, "/imagenes/paracetamol.png", 5));
        coleccionProductos.insertOne(new ProductoMongo(5L, "Shampoo", 75.0, "/imagenes/ibuprofeno.png", 50));

        coleccionProductos.insertOne(new MedicamentoMongo("Genérico", Medida.mg, 500.0, "Tabletas", true, List.of(Especialidades.MEDICOGENERAL), 6L, "Paracetamol", 50.0, "/imagenes/omeprazol.png", 10));
        coleccionProductos.insertOne(new MedicamentoMongo("Pfizer", Medida.mg, 400.0, "Cápsulas", true, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), 7L, "Ibuprofeno", 80.0, "/imagenes/paracetamol.png", 20));
        coleccionProductos.insertOne(new MedicamentoMongo("Sandoz", Medida.mg, 500.0, "Cápsulas", true, List.of(Especialidades.PEDIATRIA), 8L, "Amoxicilina", 120.0, "/imagenes/ibuprofeno.png", 5));
        coleccionProductos.insertOne(new MedicamentoMongo("Bayer", Medida.mg, 100.0, "Tabletas", false, List.of(Especialidades.CARDIOLOGIA), 9L, "Aspirina", 45.0, "/imagenes/omeprazol.png", 20));
        coleccionProductos.insertOne(new MedicamentoMongo("Genérico", Medida.mg, 10.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), 10L, "Loratadina", 90.0, "/imagenes/omeprazol.png", 10));

        MongoCollection<Receta> coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
        coleccionRecetas.drop();
        coleccionRecetas.insertOne(new Receta("101L", "MG01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 12, 31), Arrays.asList(new DetalleReceta(8L, 10, 0), new DetalleReceta(6L, 10, 0))));
        coleccionRecetas.insertOne(new Receta("102L", "CA01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 12, 31), Arrays.asList(new DetalleReceta(1L, 5, 0), new DetalleReceta(9L, 5, 0))));
        coleccionRecetas.insertOne(new Receta("103L", "PS03", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 6, 15), Arrays.asList(new DetalleReceta(4L, 2, 0))));
        coleccionRecetas.insertOne(new Receta("104L", "ON01", 0, EstadoReceta.CADUCADA, LocalDate.of(2023, 12, 31), Arrays.asList(new DetalleReceta(5L, 10, 0))));

        MongoCollection<Empleado> coleccionEmpleados = ManejadorConexiones.obtenerColeccionEmpleados();
        coleccionEmpleados.drop();
        coleccionEmpleados.insertOne(new Empleado(123L, "Juan", "Perez", "Gomez", "555-0001", RolPuesto.LIDER, LocalDate.of(1990, 5, 20), EstatusEmpleado.ACTIVO));
        coleccionEmpleados.insertOne(new Empleado(456L, "Maria", "Lopez", "Diaz", "555-0002", RolPuesto.CAJERO, LocalDate.of(1995, 8, 15), EstatusEmpleado.ACTIVO));
        coleccionEmpleados.insertOne(new Empleado(789L, "Carlos", "Ruiz", "Soto", "555-0003", RolPuesto.CAJERO, LocalDate.of(1998, 2, 10), EstatusEmpleado.INACTIVO));

        MongoCollection<CuentaAcceso> coleccionCuentas = ManejadorConexiones.obtenerColeccionCuentas();
        coleccionCuentas.drop();
        coleccionCuentas.insertOne(new CuentaAcceso(123L, "admin"));
        coleccionCuentas.insertOne(new CuentaAcceso(456L, "caja"));
        coleccionCuentas.insertOne(new CuentaAcceso(789L, "caja2"));

        try {
            //aqui creamos la conexion con la base de datos 
            //aqui cambien el usuario y contraseña no se les olvide
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    "root",
                    "Jorge"
            );

            // Creamos el statemen que se usa para ejecutar los comandos directos, el create o el delate el que sea
            Statement statement = conexion.createStatement();
            //si no existe la base de datos la crea 
            statement.execute("CREATE DATABASE IF NOT EXISTS medicos");
            //usamos la base de datos de los medicos
            statement.execute("USE medicos");

            //aqui creamos la tabla con sus especificaciones 
            statement.execute("""
                CREATE TABLE IF NOT EXISTS medicos (
                    cedula VARCHAR(20) PRIMARY KEY,
                    nombre VARCHAR(100),
                    especialidad VARCHAR(50),
                    permisos BOOLEAN
                )
            """);

            //limpiamos la tabla si es que tiene algo
            statement.execute("DELETE FROM medicos");

            // con esto le decimos que vamos a insertar algo pero todavia no le decimos que 
            PreparedStatement ps = conexion.prepareStatement(
                    "INSERT INTO medicos VALUES (?, ?, ?, ?)"
            );

            //aqui estan los insert y especificamos que va en cada campo y ejecutamos el insert
            ps.setString(1, "MG01");
            ps.setString(2, "Dr. Juan Perez");
            ps.setString(3, "MEDICOGENERAL");
            ps.setBoolean(4, true);
            ps.executeUpdate();

            ps.setString(1, "MG02");
            ps.setString(2, "Dra. Ana Gomez");
            ps.setString(3, "MEDICOGENERAL");
            ps.setBoolean(4, true);
            ps.executeUpdate();

            ps.setString(1, "MG03");
            ps.setString(2, "Dr. Luis Garcia");
            ps.setString(3, "MEDICOGENERAL");
            ps.setBoolean(4, false);
            ps.executeUpdate();

            System.out.println("Medicos insertados correctamente");
            //cerramos la conexion 
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
