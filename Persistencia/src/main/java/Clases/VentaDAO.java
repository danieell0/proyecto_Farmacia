/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.Venta;
import EntidadesMongo.VentaMongo;
import Interfaces.IVentaDAO;
import MapperMongo.VentaMapperMongo;
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

    // Colección de ventas de Mongo (ahora usa la entidad VentaMongo)
    private MongoCollection<VentaMongo> coleccionVentas;

    private static final Logger logger = Logger.getLogger(VentaDAO.class.getSimpleName());

    public VentaDAO() {
        // Obtenemos la colección de ventas de mongo
        this.coleccionVentas = ManejadorConexiones.obtenerColeccionVentas();
    }

    @Override
    public boolean agregarVenta(Venta venta) {
        if (venta != null) {
            // 1. Convertimos la Venta (Dominio) a VentaMongo usando el Mapper
            VentaMongo ventaMongo = VentaMapperMongo.domainToEntity(venta);
            
            // 2. Insertamos la entidad Mongo en la base de datos
            coleccionVentas.insertOne(ventaMongo);
            logger.info("Venta registrada con éxito. Total: $" + venta.getTotal());
            return true;
        }
        return false;
    }

    @Override
    public List<Venta> obtenerVentas() {
        // Obtenemos todas las VentaMongo de la colección
        List<VentaMongo> ventasMongo = coleccionVentas.find().into(new ArrayList<>());
        
        // Convertimos la lista de VentaMongo a una lista de Venta usando el Mapper
        return ventasMongo.stream().map(v -> VentaMapperMongo.entityToDomain(v)).toList();
    }

    @Override
    public Venta obtenerVentaPorId(String idVenta) {
        try {
            // Convertimos el String a Long, ya que en VentaMongo idVenta es Long
            Long idBuscado = Long.valueOf(idVenta);
            
            // Buscamos la VentaMongo
            VentaMongo vm = coleccionVentas.find(eq("idVenta", idBuscado)).first();
            
            // Si la encuentra, la convierte y la retorna
            if (vm != null) {
                return VentaMapperMongo.entityToDomain(vm);
            }
        } catch (NumberFormatException e) {
            logger.warning("El formato del ID de venta proporcionado no es un número válido: " + idVenta);
        }
        return null;
    }

    @Override
    public List<Venta> obtenerVentasPorCliente(String idCliente) {
        try {
            // Convertimos el String a Long para la búsqueda
            Long idBuscado = Long.valueOf(idCliente);
            
            // Obtenemos la lista filtrada de VentaMongo
            List<VentaMongo> ventasMongo = coleccionVentas
                    .find(eq("idCliente", idBuscado))
                    .into(new ArrayList<>());
            
            // Retornamos la lista mapeada a entidades Venta
            return ventasMongo.stream().map(v -> VentaMapperMongo.entityToDomain(v)).toList();
            
        } catch (NumberFormatException e) {
            logger.warning("El formato del ID del cliente proporcionado no es un número válido: " + idCliente);
            return new ArrayList<>(); // Retornamos lista vacía en caso de error
        }
    }
}