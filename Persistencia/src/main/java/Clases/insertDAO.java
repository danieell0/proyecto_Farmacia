/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import EntidadesMongo.CanjeableMongo;
import EntidadesMongo.ClienteMongo;
import EntidadesMongo.CuentaAccesoMongo;
import EntidadesMongo.DetalleRecetaMongo;
import EntidadesMongo.DetalleSolicitudMongo;
import EntidadesMongo.EmpleadoMongo;
import EntidadesMongo.MedicamentoMongo;
import EntidadesMongo.ProductoMongo;
import EntidadesMongo.RecetaMongo;
import EntidadesMongo.SolicitudMongo;
import Enums.Especialidades;
import Enums.EstadoReceta;
import Enums.EstadoSolicitud;
import Enums.EstatusEmpleado;
import Enums.Medida;
import Enums.RolPuesto;
import Enums.TipoProducto;
import Excepciones.PersistenciaException;
import Interfaces.IInsertDAO;
import com.mongodb.client.MongoCollection;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class insertDAO implements IInsertDAO{

    public insertDAO() {
    }
    @Override
    public void cargarDatos(){
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

        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C001", "Telefono Xiaomi", "Xiaomi", 5000.0, "/imagenes/phone.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C002", "Smartwatch", "Generico", 1500.0, "/imagenes/smartwatch.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C003", "Balon", "Adidas", 1000.0, "/imagenes/balon.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C004", "Audifonos", "Generico", 2000.0, "/imagenes/audifonos.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C005", "Television", "Generico", 10000.0, "/imagenes/tele.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C006", "Mouse Inalambrico", "Generico", 1000.0, "/imagenes/mouse.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C007", "Raqueta", "Wilson", 2500.0, "/imagenes/raqueta.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C008", "Guante Beisbol", "Rawlings", 3000.0, "/imagenes/glove.png", 20, TipoProducto.PUNTOS));
        coleccionProductos.insertOne(new CanjeableMongo(0.0, "C009", "Cargador Telefono", "Generico", 2000.0, "/imagenes/cargador.png", 20, TipoProducto.PUNTOS));

        MongoCollection<RecetaMongo> coleccionRecetas = ManejadorConexiones.obtenerColeccionReceta();
        coleccionRecetas.drop();
        coleccionRecetas.insertOne(new RecetaMongo("101L", "MG01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 12, 31), Arrays.asList(new DetalleRecetaMongo("M001", 10, 0), new DetalleRecetaMongo("M007", 10, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("102L", "CA01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 12, 31), Arrays.asList(new DetalleRecetaMongo("M004", 5, 0), new DetalleRecetaMongo("M008", 5, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("103L", "PS03", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 6, 15), Arrays.asList(new DetalleRecetaMongo("MC002", 2, 0), new DetalleRecetaMongo("MC004", 1, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("104L", "ON01", 0, EstadoReceta.CADUCADA, LocalDate.of(2023, 12, 31), Arrays.asList(new DetalleRecetaMongo("MC001", 10, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("105L", "MG02", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 10, 15), Arrays.asList(new DetalleRecetaMongo("M005", 4, 0), new DetalleRecetaMongo("M009", 3, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("105L", "MG02", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 10, 15), Arrays.asList(new DetalleRecetaMongo("M005", 4, 0), new DetalleRecetaMongo("M009", 3, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("106L", "PD01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 8, 10), Arrays.asList(new DetalleRecetaMongo("M003", 2, 0), new DetalleRecetaMongo("M010", 1, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("107L", "PS03", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 9, 30), Arrays.asList(new DetalleRecetaMongo("MC002", 1, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("108L", "CA01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 5, 18), Arrays.asList(new DetalleRecetaMongo("M008", 4, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("109L", "ON01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 11, 11), Arrays.asList(new DetalleRecetaMongo("MC001", 1, 0))));
        coleccionRecetas.insertOne(new RecetaMongo("110L", "MG01", 0, EstadoReceta.ACTIVA, LocalDate.of(2026, 4, 14), Arrays.asList(new DetalleRecetaMongo("M001", 10, 0), new DetalleRecetaMongo("M002", 6, 0), new DetalleRecetaMongo("M006", 3, 0))));

        MongoCollection<EmpleadoMongo> coleccionEmpleados = ManejadorConexiones.obtenerColeccionEmpleados();
        coleccionEmpleados.drop();
        coleccionEmpleados.insertOne(new EmpleadoMongo("123", "Juan", "Perez", "Gomez", "555-0001", RolPuesto.LIDER, LocalDate.of(1990, 5, 20), EstatusEmpleado.ACTIVO));
        coleccionEmpleados.insertOne(new EmpleadoMongo("456", "Maria", "Lopez", "Diaz", "555-0002", RolPuesto.CAJERO, LocalDate.of(1995, 8, 15), EstatusEmpleado.ACTIVO));
        coleccionEmpleados.insertOne(new EmpleadoMongo("789", "Carlos", "Ruiz", "Soto", "555-0003", RolPuesto.CAJERO, LocalDate.of(1998, 2, 10), EstatusEmpleado.INACTIVO));

        MongoCollection<CuentaAccesoMongo> coleccionCuentas = ManejadorConexiones.obtenerColeccionCuentas();
        coleccionCuentas.drop();
        coleccionCuentas.insertOne(new CuentaAccesoMongo("123", "admin"));
        coleccionCuentas.insertOne(new CuentaAccesoMongo("456", "caja"));
        coleccionCuentas.insertOne(new CuentaAccesoMongo("789", "caja2"));

        MongoCollection<ClienteMongo> coleccionClientes = ManejadorConexiones.obtenerColeccionCliente();
        coleccionClientes.drop();
        coleccionClientes.insertOne(new ClienteMongo("0", "VentaSinCliente", "1", 0.0, LocalDate.of(2000, 1, 1)));
        coleccionClientes.insertOne(new ClienteMongo("1", "nombre", "6441000665", 10000.0, LocalDate.of(2000, 5, 2)));
        coleccionClientes.insertOne(new ClienteMongo("2", "pepe", "6442134730", 5000.0, LocalDate.of(2006, 6, 27)));
        coleccionClientes.insertOne(new ClienteMongo("3", "juanito", "6441000664", 2000.0, LocalDate.of(2000, 8, 2)));

        //estas son las solicitudes de entrada 
        MongoCollection<SolicitudMongo> coleccionSolicitudes = ManejadorConexiones.obtenerColeccionSolicitudes();
        coleccionSolicitudes.drop();

        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL001", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 500.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL), "M001", "Paracetamol", "Genérico", 500.0, "/imagenes/paracetamol.png", 10, TipoProducto.MEDICAMENTO), 20),
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 400.0, "Cápsulas", false, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), "M002", "Ibuprofeno", "Pfizer", 600.0, "/imagenes/ibuprofeno.png", 20, TipoProducto.MEDICAMENTO), 15))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL002", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new ProductoMongo("P001", "Agua Natural Ciel", "Coca Cola", 20.0, "/imagenes/aguaNaturalCiel.png", 25, TipoProducto.PRODUCTO), 10),
                new DetalleSolicitudMongo(new ProductoMongo("P002", "Galletas Marías", "Gamesa", 30.0, "/imagenes/galletasMarias.png", 20, TipoProducto.PRODUCTO), 12))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL003", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 2.0, "Tabletas", true, List.of(Especialidades.NEUROLOGIA), "MC001", "Clonazepam", "Roche", 2.0, "/imagenes/clonazepam.png", 6, TipoProducto.MEDICAMENTO), 5),
                new DetalleSolicitudMongo(new ProductoMongo("P003", "Jugo Del Valle Naranja", "Del Valle", 25.0, "/imagenes/jugoDelValle.png", 10, TipoProducto.PRODUCTO), 8))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL004", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 500.0, "Cápsulas", false, List.of(Especialidades.PEDIATRIA), "M003", "Amoxicilina", "Sandoz", 500.0, "/imagenes/amoxicilina.png", 5, TipoProducto.MEDICAMENTO), 10),
                new DetalleSolicitudMongo(new ProductoMongo("P004", "Papel Higiénico", "Pétalo", 60.0, "/imagenes/papelHigienico.png", 5, TipoProducto.PRODUCTO), 15))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL005", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new ProductoMongo("P005", "Shampoo Nutritivo", "Pantene", 75.0, "/imagenes/shampooPantene.png", 50, TipoProducto.PRODUCTO), 8),
                new DetalleSolicitudMongo(new ProductoMongo("P006", "Sabritas Original", "Sabritas", 22.0, "/imagenes/sabritasOriginal.png", 35, TipoProducto.PRODUCTO), 10))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL006", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 100.0, "Tabletas", false, List.of(Especialidades.CARDIOLOGIA), "M004", "Aspirina", "Bayer", 500.0, "/imagenes/aspirina.png", 20, TipoProducto.MEDICAMENTO), 14),
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 10.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL, Especialidades.PEDIATRIA), "M005", "Loratadina", "Genérico", 10.0, "/imagenes/loratadina.png", 10, TipoProducto.MEDICAMENTO), 16))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL007", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new ProductoMongo("P007", "Coca Cola 600ml", "Coca Cola", 18.0, "/imagenes/cocaCola600.png", 40, TipoProducto.PRODUCTO), 20),
                new DetalleSolicitudMongo(new ProductoMongo("P008", "Pan Blanco", "Bimbo", 48.0, "/imagenes/panBimbo.png", 15, TipoProducto.PRODUCTO), 18))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL008", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 20.0, "Cápsulas", false, List.of(Especialidades.GASTROENTEROLOGIA), "M006", "Omeprazol", "Sandoz", 20.0, "/imagenes/omeprazol.png", 14, TipoProducto.MEDICAMENTO), 7),
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 850.0, "Tabletas", false, List.of(Especialidades.MEDICOGENERAL), "M007", "Metformina", "Merck", 850.0, "/imagenes/metformina.png", 12, TipoProducto.MEDICAMENTO), 5))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL009", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new ProductoMongo("P009", "Leche Entera", "Lala", 32.0, "/imagenes/lecheLala.png", 18, TipoProducto.PRODUCTO), 10),
                new DetalleSolicitudMongo(new ProductoMongo("P010", "Atún en Agua", "Dolores", 27.0, "/imagenes/atunDolores.png", 22, TipoProducto.PRODUCTO), 12))));
        coleccionSolicitudes.insertOne(new SolicitudMongo("SOL010", LocalDateTime.now(), "123", EstadoSolicitud.PENDIENTE, Arrays.asList(
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.mg, 50.0, "Tabletas", false, List.of(Especialidades.CARDIOLOGIA), "M008", "Losartán", "Pfizer", 50.0, "/imagenes/losartan.png", 16, TipoProducto.MEDICAMENTO), 11),
                new DetalleSolicitudMongo(new MedicamentoMongo(Medida.ml, 5.0, "Jarabe", false, List.of(Especialidades.PEDIATRIA), "M010", "Ambroxol", "Boehringer", 30.0, "/imagenes/ambroxol.png", 9, TipoProducto.MEDICAMENTO), 9))));
    }

}
