/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import java.time.LocalDateTime;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Clase base que representa un movimiento almacenado en MongoDB.
 *
 * Esta clase contiene la información general de un movimiento, incluyendo el
 * identificador, la fecha y hora, el empleado responsable y la solicitud
 * asociada.
 *
 * @author Jorge
 */
@BsonDiscriminator
public class MovimientoMongo {

    private String idMovimiento;
    private LocalDateTime fechaHora;
    private String idEmpleado;
    private String codigoSolicitud;

    /**
     * Constructor por defecto de la clase MovimientoMongo.
     */
    public MovimientoMongo() {
    }

    /**
     * Constructor que inicializa todos los atributos de un movimiento Mongo.
     *
     * @param idMovimiento Identificador del movimiento.
     * @param fechaHora Fecha y hora en que se realizó el movimiento.
     * @param idEmpleado Identificador del empleado responsable.
     * @param codigoSolicitud Código de la solicitud asociada.
     */
    public MovimientoMongo(String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        this.idMovimiento = idMovimiento;
        this.fechaHora = fechaHora;
        this.idEmpleado = idEmpleado;
        this.codigoSolicitud = codigoSolicitud;
    }

    /**
     * Obtiene el código de la solicitud asociada al movimiento.
     *
     * @return Código de la solicitud.
     */
    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    /**
     * Establece el código de la solicitud asociada al movimiento.
     *
     * @param codigoSolicitud Código de la solicitud.
     */
    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    /**
     * Obtiene el identificador del movimiento.
     *
     * @return Identificador del movimiento.
     */
    public String getIdMovimiento() {
        return idMovimiento;
    }

    /**
     * Establece el identificador del movimiento.
     *
     * @param idMovimiento Identificador del movimiento.
     */
    public void setIdMovimiento(String idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    /**
     * Obtiene la fecha y hora en que se realizó el movimiento.
     *
     * @return Fecha y hora del movimiento.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Establece la fecha y hora en que se realizó el movimiento.
     *
     * @param fechaHora Fecha y hora del movimiento.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Obtiene el identificador del empleado responsable del movimiento.
     *
     * @return Identificador del empleado.
     */
    public String getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Establece el identificador del empleado responsable del movimiento.
     *
     * @param idEmpleado Identificador del empleado.
     */
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Devuelve una representación en cadena del objeto MovimientoMongo.
     *
     * @return Cadena con la información del movimiento.
     */
    @Override
    public String toString() {
        return "MovimientoMongo{" + "idMovimiento=" + idMovimiento + ", fechaHora=" + fechaHora + ", idEmpleado=" + idEmpleado + '}';
    }

}
