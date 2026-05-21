/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import java.time.LocalDateTime;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Clase que representa un movimiento de salida almacenado en MongoDB.
 *
 * Un movimiento de salida Mongo almacena la información relacionada con el
 * producto afectado, la cantidad modificada, el motivo, observaciones y los
 * cambios realizados en el stock.
 *
 * @author Jorge
 */
@BsonDiscriminator
public class MovimientoSalidaMongo extends MovimientoMongo {

    private ProductoMongo producto;
    private Integer cantidad;
    private String motivo;
    private String observacion;
    private Integer cantidadAnterior;
    private Integer cantidadNueva;

    /**
     * Constructor por defecto de la clase MovimientoSalidaMongo.
     */
    public MovimientoSalidaMongo() {
    }

    /**
     * Constructor que inicializa todos los atributos de un movimiento de salida
     * Mongo.
     *
     * @param producto Producto asociado al movimiento.
     * @param cantidad Cantidad afectada del producto.
     * @param motivo Motivo del movimiento de salida.
     * @param observacion Observaciones adicionales del movimiento.
     * @param cantidadAnterior Cantidad existente antes del movimiento.
     * @param cantidadNueva Cantidad restante después del movimiento.
     * @param idMovimiento Identificador del movimiento.
     * @param fechaHora Fecha y hora en que se realizó el movimiento.
     * @param idEmpleado Identificador del empleado responsable.
     * @param codigoSolicitud Código de la solicitud asociada.
     */
    public MovimientoSalidaMongo(ProductoMongo producto, Integer cantidad, String motivo, String observacion, Integer cantidadAnterior, Integer cantidadNueva, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.producto = producto;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.observacion = observacion;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
    }

    /**
     * Obtiene la cantidad existente antes de realizar el movimiento.
     *
     * @return Cantidad anterior del producto.
     */
    public Integer getCantidadAnterior() {
        return cantidadAnterior;
    }

    /**
     * Establece la cantidad existente antes de realizar el movimiento.
     *
     * @param cantidadAnterior Cantidad anterior del producto.
     */
    public void setCantidadAnterior(Integer cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    /**
     * Obtiene la cantidad restante después de realizar el movimiento.
     *
     * @return Cantidad nueva del producto.
     */
    public Integer getCantidadNueva() {
        return cantidadNueva;
    }

    /**
     * Establece la cantidad restante después de realizar el movimiento.
     *
     * @param cantidadNueva Cantidad nueva del producto.
     */
    public void setCantidadNueva(Integer cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    }

    /**
     * Obtiene el producto asociado al movimiento de salida.
     *
     * @return Producto del movimiento.
     */
    public ProductoMongo getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado al movimiento de salida.
     *
     * @param producto Producto del movimiento.
     */
    public void setProducto(ProductoMongo producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad afectada del producto.
     *
     * @return Cantidad del producto.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad afectada del producto.
     *
     * @param cantidad Cantidad del producto.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el motivo del movimiento de salida.
     *
     * @return Motivo del movimiento.
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Establece el motivo del movimiento de salida.
     *
     * @param motivo Motivo del movimiento.
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Obtiene las observaciones del movimiento de salida.
     *
     * @return Observaciones del movimiento.
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Establece las observaciones del movimiento de salida.
     *
     * @param observacion Observaciones del movimiento.
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Devuelve una representación en cadena del objeto MovimientoSalidaMongo.
     *
     * @return Cadena con la información del movimiento de salida.
     */
    @Override
    public String toString() {
        return "MovimientoSalidaMongo{" + "producto=" + producto + ", cantidad=" + cantidad + ", motivo=" + motivo + ", observacion=" + observacion + ", cantidadAnterior=" + cantidadAnterior + ", cantidadNueva=" + cantidadNueva + '}';
    }

}
