package EntidadesMongo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author Dario
 */
@BsonDiscriminator
public class DetalleRecetaMongo {
    private String idMedicamento;
    private Integer cantidadRecetada;
    private Integer cantidadSurtida = 0;
    
    public DetalleRecetaMongo() {
        
    }

    /**
     * Contructor con todos los atributos.
     * @param idMedicamento Id del medicamento agregado a la receta.
     * @param cantidadPermitida Cantidad recetada del medicamento.
     * @param cantidadSurtida Cantidad surtida del producto (inicialmente es 0);
     */
    public DetalleRecetaMongo(String idMedicamento, Integer cantidadPermitida, Integer cantidadSurtida) {
        this.idMedicamento = idMedicamento;
        this.cantidadRecetada = cantidadPermitida;
    }
    
    /** @return Obtiene el id del medicamento relaccionado a la receta. */
    public String getIdMedicamento() {
        return idMedicamento;
    }

    /** @param idMedicamento El Id del medicamento a asignar a la receta. */
    public void setIdMedicamento(String idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    /** @return Obtiene la cantidad recetada del medicamento relaccionado a la receta. */
    public Integer getCantidadRecetada() {
        return cantidadRecetada;
    }

    /** @param cantidadRecetada La cantidad recetada del medicamento a asignar a la receta. */
    public void setCantidadRecetada(Integer cantidadRecetada) {
        this.cantidadRecetada = cantidadRecetada;
    }

    /** @return Obtiene la cantidad surtida del medicamento relaccionado a la receta. */
    public Integer getCantidadSurtida() {
        return cantidadSurtida;
    }
    
    /** @param cantidadSurtida La cantidad surtida del medicamento a asignar a la receta. */
    public void setCantidadSurtida(Integer cantidadSurtida) {
        this.cantidadSurtida = cantidadSurtida;
    }
}
