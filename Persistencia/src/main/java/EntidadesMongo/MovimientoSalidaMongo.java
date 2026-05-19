/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import java.time.LocalDateTime;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author Jorge
 */
@BsonDiscriminator
public class MovimientoSalidaMongo extends MovimientoMongo{
    private ProductoMongo producto;
    private Integer cantidad;
    private String motivo;
    private String observacion;
    private Integer cantidadAnterior;
    private Integer cantidadNueva;

    public MovimientoSalidaMongo() {
    }

    public MovimientoSalidaMongo(ProductoMongo producto, Integer cantidad, String motivo, String observacion, Integer cantidadAnterior, Integer cantidadNueva, String idMovimiento, LocalDateTime fechaHora, String idEmpleado) {
        super(idMovimiento, fechaHora, idEmpleado);
        this.producto = producto;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.observacion = observacion;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
    }

    public Integer getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(Integer cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public Integer getCantidadNueva() {
        return cantidadNueva;
    }

    public void setCantidadNueva(Integer cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "MovimientoSalidaMongo{" + "producto=" + producto + ", cantidad=" + cantidad + ", motivo=" + motivo + ", observacion=" + observacion + ", cantidadAnterior=" + cantidadAnterior + ", cantidadNueva=" + cantidadNueva + '}';
    }
    
}
