/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Venta;
import EntidadesMongo.VentaMongo;
import java.util.ArrayList;

/**
 * Clase Mapper para convertir un producto a mongo.
 * @author munos
 */
public class VentaMapperMongo {
/**
     * Convierte un VentaMongo a una entidad Venta (Dominio).
     * @param mongo VentaMongo a convertir.
     * @return Venta.
     */
    public static Venta entityToDomain(VentaMongo mongo) {
        if (mongo == null) return null;
        
        Venta venta = new Venta();
        venta.setIdVenta(mongo.getIdVenta());
        venta.setFecha(mongo.getFecha());
        venta.setTotal(mongo.getTotal());
        venta.setIdEmpleado(mongo.getIdEmpleado());
        venta.setIdCliente(mongo.getIdCliente());
        
        if (mongo.getDetalles() != null) {
            venta.setDetalles(
                mongo.getDetalles().stream()
                     .map(d -> DetalleVentaMapperMongo.entityToDomain(d))
                     .toList()
            );
        }
        return venta;
    }

    /**
     * Convierte una entidad Venta (Dominio) a un VentaMongo.
     * @param venta Venta a convertir.
     * @return VentaMongo.
     */
    public static VentaMongo domainToEntity(Venta venta) {
        if (venta == null) return null;
        
        VentaMongo mongo = new VentaMongo();
        mongo.setIdVenta(venta.getIdVenta());
        mongo.setFecha(venta.getFecha());
        mongo.setTotal(venta.getTotal());
        mongo.setIdEmpleado(venta.getIdEmpleado());
        mongo.setIdCliente(venta.getIdCliente());
        
        if (venta.getDetalles() != null) {
            mongo.setDetalles(
                venta.getDetalles().stream()
                     .map(d -> DetalleVentaMapperMongo.domainToEntity(d))
                     .toList()
            );
        }
        return mongo;
    }
}