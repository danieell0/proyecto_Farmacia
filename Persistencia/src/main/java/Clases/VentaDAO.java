/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Venta;
import Interfaces.IVentaDAO;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author munos
 */
public class VentaDAO implements IVentaDAO {

    private MongoCollection<Venta> coleccionVentas;

    private static final Logger logger = Logger.getLogger(VentaDAO.class.getSimpleName());

    public VentaDAO() {
        this.coleccionVentas = ManejadorConexiones.obtenerColeccionVentas();
    }

    @Override
    public boolean agregarVenta(Venta venta) {
        if (venta != null) {
            coleccionVentas.insertOne(venta);
            logger.info("Venta registrada con éxito. Total: $" + venta.getTotal());
            return true;
        }
        return false;
    }

    @Override
    public List<Venta> obtenerVentas() {
        return coleccionVentas.find().into(new ArrayList<>());
    }

    @Override
    public Venta obtenerVentaPorId(Long idVenta) {
        return coleccionVentas.find(eq("idVenta", idVenta)).first();
    }

    @Override
    public List<Venta> obtenerVentasPorCliente(Long idCliente) {
        return coleccionVentas
                .find(eq("idCliente", idCliente))
                .into(new ArrayList<>());
    }

}
