/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import java.time.LocalDateTime;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Clase que representa un movimiento de entrada almacenado en MongoDB.
 *
 * Un movimiento de entrada Mongo almacena la información relacionada con el
 * lote registrado dentro del movimiento.
 *
 * @author Jorge
 */
@BsonDiscriminator
public class MovimientoEntradaMongo extends MovimientoMongo {

    private LoteMongo lote;

    /**
     * Constructor por defecto de la clase MovimientoEntradaMongo.
     */
    public MovimientoEntradaMongo() {
    }

    /**
     * Constructor que inicializa todos los atributos de un movimiento de
     * entrada Mongo.
     *
     * @param lote Lote asociado al movimiento.
     * @param idMovimiento Identificador del movimiento.
     * @param fechaHora Fecha y hora en que se realizó el movimiento.
     * @param idEmpleado Identificador del empleado responsable.
     * @param codigoSolicitud Código de la solicitud asociada.
     */
    public MovimientoEntradaMongo(LoteMongo lote, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.lote = lote;
    }

    /**
     * Obtiene el lote asociado al movimiento de entrada.
     *
     * @return Lote del movimiento.
     */
    public LoteMongo getLote() {
        return lote;
    }

    /**
     * Establece el lote asociado al movimiento de entrada.
     *
     * @param lote Lote del movimiento.
     */
    public void setLote(LoteMongo lote) {
        this.lote = lote;
    }

    /**
     * Devuelve una representación en cadena del objeto MovimientoEntradaMongo.
     *
     * @return Cadena con la información del movimiento de entrada.
     */
    @Override
    public String toString() {
        return "MovimientoEntradaMongo{" + "lote=" + lote + '}';
    }

}
