package EntidadesMongo;

import Enums.TipoProducto;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author Dario
 */
@BsonDiscriminator
public class CanjeableMongo extends ProductoMongo {
    private Double puntos;

    public CanjeableMongo() {
        
    }

    public CanjeableMongo(Double puntos) {
        this.puntos = puntos;
    }

    public CanjeableMongo(Double puntos, String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        super(idProducto, nombre, marca, precio, imagen, stock, tipo);
        this.puntos = puntos;
    }

    public Double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "CanjeableMongo{" + "puntos=" + puntos + '}';
    }

}
