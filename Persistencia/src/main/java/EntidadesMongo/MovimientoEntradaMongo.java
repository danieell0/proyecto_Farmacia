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
public class MovimientoEntradaMongo extends MovimientoMongo{
    private LoteMongo lote;

    public MovimientoEntradaMongo() {
    }

    public MovimientoEntradaMongo(LoteMongo lote, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.lote = lote;
    }

    public LoteMongo getLote() {
        return lote;
    }

    public void setLote(LoteMongo lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "MovimientoEntradaMongo{" + "lote=" + lote + '}';
    }
    
}
