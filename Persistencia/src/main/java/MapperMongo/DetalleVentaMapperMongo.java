/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.DetalleVenta;
import EntidadesMongo.DetalleVentaMongo;

/**
 *
 * @author munos
 */
public class DetalleVentaMapperMongo {
    public static DetalleVenta entityToDomain(DetalleVentaMongo mongo) {
        if (mongo == null) return null;
        
        DetalleVenta detalle = new DetalleVenta();
        detalle.setIdDetalle(mongo.getIdDetalle());
        detalle.setIdVenta(mongo.getIdVenta());
        detalle.setCantidad(mongo.getCantidad());
        detalle.setPrecioUnitario(mongo.getPrecioUnitario());
        detalle.setSubtotal(mongo.getSubtotal());
        
        detalle.setProducto(ProductoMapperMongo.entityToDomain(mongo.getProducto()));
        
        return detalle;
    }

    public static DetalleVentaMongo domainToEntity(DetalleVenta detalle) {
        if (detalle == null) return null;
        
        DetalleVentaMongo mongo = new DetalleVentaMongo();
        mongo.setIdDetalle(detalle.getIdDetalle());
        mongo.setIdVenta(detalle.getIdVenta());
        mongo.setCantidad(detalle.getCantidad());
        mongo.setPrecioUnitario(detalle.getPrecioUnitario());
        mongo.setSubtotal(detalle.getSubtotal());
        
        mongo.setProducto(ProductoMapperMongo.domainToEntity(detalle.getProducto()));
        
        return mongo;
    }
}

