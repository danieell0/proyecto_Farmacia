/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.CuentaAcceso;
import Entidades.DetalleReceta;
import Entidades.Empleado;
import Entidades.Receta;
import EntidadesMongo.MedicamentoMongo;
import EntidadesMongo.ProductoMongo;
import Enums.Especialidades;
import Enums.EstadoReceta;
import Enums.EstatusEmpleado;
import Enums.Medida;
import Enums.RolPuesto;
import Enums.TipoProducto;
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

        coleccionProductos.insertOne(new ProductoMongo("P001", "Agua Natural Ciel", "Coca Cola", 20.0, "/imagenes/aguaNaturalCiel.png", 25, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P002", "Galletas Marías", "Gamesa", 30.0, "/imagenes/galletasMarias.png", 20, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P003", "Jugo Del Valle Naranja", "Del Valle", 25.0, "/imagenes/jugoDelValle.png", 10, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P004", "Papel Higiénico", "Pétalo", 60.0, "/imagenes/papelHigienico.png", 5, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P005", "Shampoo Nutritivo", "Pantene", 75.0, "/imagenes/shampooPantene.png", 50, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P006", "Sabritas Original", "Sabritas", 22.0, "/imagenes/sabritasOriginal.png", 35, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P007", "Coca Cola 600ml", "Coca Cola", 18.0, "/imagenes/cocaCola600.png", 40, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P008", "Pan Blanco", "Bimbo", 48.0, "/imagenes/panBimbo.png", 15, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P009", "Leche Entera", "Lala", 32.0, "/imagenes/lecheLala.png", 18, TipoProducto.PRODUCTO));
        coleccionProductos.insertOne(new ProductoMongo("P010", "Atún en Agua", "Dolores", 27.0, "/imagenes/atunDolores.png", 22, TipoProducto.PRODUCTO));

        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 500.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL), "M001", "Paracetamol", "Genérico", 500.0, "/imagenes/paracetamol.png", 10, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 400.0, "Cápsulas", false, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), "M002", "Ibuprofeno", "Pfizer", 600.0, "/imagenes/ibuprofeno.png", 20, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 500.0, "Cápsulas", false, List.of(Especialidades.PEDIATRIA), "M003", "Amoxicilina", "Sandoz", 500.0, "/imagenes/amoxicilina.png", 5, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 100.0, "Tabletas", false, List.of(Especialidades.CARDIOLOGIA), "M004", "Aspirina", "Bayer", 500.0, "/imagenes/aspirina.png", 20, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 10.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), "M005", "Loratadina", "Genérico", 10.0, "/imagenes/loratadina.png", 10, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 20.0, "Cápsulas", false, List.of(Especialidades.GASTROENTEROLOGIA), "M006", "Omeprazol", "Sandoz", 20.0, "/imagenes/omeprazol.png", 14, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 850.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL), "M007", "Metformina", "Merck", 850.0, "/imagenes/metformina.png", 12, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 50.0, "Tabletas", false, List.of(Especialidades.CARDIOLOGIA), "M008", "Losartán", "Pfizer", 50.0, "/imagenes/losartan.png", 16, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 500.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL), "M009", "Naproxeno", "Genérico", 250.0, "/imagenes/naproxeno.png", 18, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.ml, 5.0, "Jarabe", false, List.of(Especialidades.PEDIATRIA), "M010", "Ambroxol", "Boehringer", 30.0, "/imagenes/ambroxol.png", 9, TipoProducto.MEDICAMENTO));

        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 2.0, "Tabletas", true, List.of(Especialidades.NEUROLOGIA), "MC001", "Clonazepam", "Roche", 2.0, "/imagenes/clonazepam.png", 6, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 10.0, "Tabletas", true, List.of(Especialidades.PSIQUIATRIA), "MC002", "Diazepam", "Pfizer", 150.0, "/imagenes/diazepam.png", 8, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 10.0, "Tabletas", true, List.of(Especialidades.NEUROLOGIA), "MC003", "Ritalin", "Novartis", 320.0, "/imagenes/ritalin.png", 4, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 5.0, "Tabletas", true, List.of(Especialidades.PSIQUIATRIA), "MC004", "Alprazolam", "Pfizer", 0.5, "/imagenes/alprazolam.png", 7, TipoProducto.MEDICAMENTO));
        coleccionProductos.insertOne(new MedicamentoMongo(Medida.mg, 30.0, "Cápsulas", true, List.of(Especialidades.NEUROLOGIA), "MC005", "Vyvanse", "Takeda", 450.0, "/imagenes/vyvanse.png", 3, TipoProducto.MEDICAMENTO));

        MongoCollection<Receta> coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
        coleccionRecetas.drop();
        coleccionRecetas.insertOne(new Receta("101L","MG01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 12, 31),Arrays.asList(new DetalleReceta("M001", 10, 0),new DetalleReceta("M007", 10, 0))));
        coleccionRecetas.insertOne(new Receta("101L","MG01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 12, 31),Arrays.asList(new DetalleReceta("M001", 10, 0),new DetalleReceta("M007", 10, 0))));
        coleccionRecetas.insertOne(new Receta("102L","CA01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 12, 31),Arrays.asList(new DetalleReceta("M004", 5, 0),new DetalleReceta("M008", 5, 0))));
        coleccionRecetas.insertOne(new Receta("103L","PS03",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 6, 15),Arrays.asList(new DetalleReceta("MC002", 2, 0),new DetalleReceta("MC004", 1, 0))));
        coleccionRecetas.insertOne(new Receta("104L","ON01",0,EstadoReceta.CADUCADA,LocalDate.of(2023, 12, 31),Arrays.asList(new DetalleReceta("MC001", 10, 0))));
        coleccionRecetas.insertOne(new Receta("105L","MG02",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 10, 15),Arrays.asList(new DetalleReceta("M005", 4, 0),new DetalleReceta("M009", 3, 0))));
        coleccionRecetas.insertOne(new Receta("106L","PD01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 8, 10),Arrays.asList(new DetalleReceta("M003", 2, 0),new DetalleReceta("M010", 1, 0))));
        coleccionRecetas.insertOne(new Receta("107L","PS03",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 9, 30),Arrays.asList(new DetalleReceta("MC002", 1, 0))));
        coleccionRecetas.insertOne(new Receta("108L","CA01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 5, 18),Arrays.asList(new DetalleReceta("M008", 4, 0))));
        coleccionRecetas.insertOne(new Receta("109L","ON01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 11, 11),Arrays.asList(new DetalleReceta("MC001", 1, 0))));
        coleccionRecetas.insertOne(new Receta("110L","MG01",0,EstadoReceta.ACTIVA,LocalDate.of(2026, 4, 14),Arrays.asList(new DetalleReceta("M001", 10, 0),new DetalleReceta("M002", 6, 0),new DetalleReceta("M006", 3, 0))));

        MongoCollection<Empleado> coleccionEmpleados = ManejadorConexiones.obtenerColeccionEmpleados();
        coleccionEmpleados.drop();
        coleccionEmpleados.insertOne(new Empleado("123", "Juan", "Perez", "Gomez", "555-0001", RolPuesto.LIDER, LocalDate.of(1990, 5, 20), EstatusEmpleado.ACTIVO));
        coleccionEmpleados.insertOne(new Empleado("456", "Maria", "Lopez", "Diaz", "555-0002", RolPuesto.CAJERO, LocalDate.of(1995, 8, 15), EstatusEmpleado.ACTIVO));
        coleccionEmpleados.insertOne(new Empleado("789", "Carlos", "Ruiz", "Soto", "555-0003", RolPuesto.CAJERO, LocalDate.of(1998, 2, 10), EstatusEmpleado.INACTIVO));

        MongoCollection<CuentaAcceso> coleccionCuentas = ManejadorConexiones.obtenerColeccionCuentas();
        coleccionCuentas.drop();
        coleccionCuentas.insertOne(new CuentaAcceso("123", "admin"));
        coleccionCuentas.insertOne(new CuentaAcceso("456", "caja"));
        coleccionCuentas.insertOne(new CuentaAcceso("789", "caja2"));

        try {
            //aqui creamos la conexion con la base de datos 
            //aqui cambien el usuario y contraseña no se les olvide
            Connection conexion = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/",
                    "root",
                    "Gasparin08"
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
            //General
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

            // CARDIOLOGIA
            ps.setString(1, "CA01");
            ps.setString(2, "Dr. Roberto Sanchez");
            ps.setString(3, "CARDIOLOGIA");
            ps.setBoolean(4, true);
            ps.executeUpdate();

            // PSIQUIATRIA
            ps.setString(1, "PS03");
            ps.setString(2, "Dra. Fernanda Lopez");
            ps.setString(3, "PSIQUIATRIA");
            ps.setBoolean(4, true);
            ps.executeUpdate();

            // ONCOLOGIA
            ps.setString(1, "ON01");
            ps.setString(2, "Dr. Miguel Torres");
            ps.setString(3, "ONCOLOGIA");
            ps.setBoolean(4, true);
            ps.executeUpdate();

            // PEDIATRIA
            ps.setString(1, "PD01");
            ps.setString(2, "Dra. Sofia Martinez");
            ps.setString(3, "PEDIATRIA");
            ps.setBoolean(4, true);
            ps.executeUpdate();

            System.out.println("Medicos insertados correctamente");
            //cerramos la conexion 
            conexion.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
