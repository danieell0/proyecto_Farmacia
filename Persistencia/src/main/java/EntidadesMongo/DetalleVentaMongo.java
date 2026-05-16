/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import EntidadesMongo.ProductoMongo;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author munos
 */
@BsonDiscriminator
public class DetalleVentaMongo {
    private String idDetalle;
    private String idVenta; 
    private ProductoMongo producto; 
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;

    public DetalleVentaMongo() {
    }

    public DetalleVentaMongo(String idDetalle, String idVenta, ProductoMongo producto, Integer cantidad, Double precioUnitario, Double subtotal) {
        this.idDetalle = idDetalle;
        this.idVenta = idVenta;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public String getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(String idDetalle) {
        this.idDetalle = idDetalle;
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public ProductoMongo getProducto() {
        return producto;
    }

    public void setProducto(ProductoMongo producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
}

