package DTO;

/**
 * DTO que representa la lista de productos de una receta.
 * @author Dario
 */
public class DetalleRecetaDTO {
    private Long idMedicamento;
    private Integer cantidadRecetada;
    private Integer cantidadSurtida = 0;

    /**
     * Contructor vacio.
     */
    public DetalleRecetaDTO() {
        
    }

    /**
     * Contructor con todos los atributos.
     * @param idMedicamento Id del medicamento agregado a la receta.
     * @param cantidadPermitida Cantidad recetada del medicamento.
     * @param cantidadSurtida Cantidad surtida del producto (inicialmente es 0);
     */
    public DetalleRecetaDTO(Long idMedicamento, Integer cantidadPermitida, Integer cantidadSurtida) {
        this.idMedicamento = idMedicamento;
        this.cantidadRecetada = cantidadPermitida;
    }
    
    /** @return Obtiene el id del medicamento relaccionado a la receta. */
    public Long getIdMedicamento() {
        return idMedicamento;
    }

    /** @param idMedicamento El Id del medicamento a asignar a la receta. */
    public void setIdMedicamento(Long idMedicamento) {
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
