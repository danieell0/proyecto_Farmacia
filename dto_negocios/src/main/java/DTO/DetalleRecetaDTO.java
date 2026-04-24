package DTO;

/**
 * DTO que representa la lista de productos de una receta.
 * @author Dario
 */
class DetalleRecetaDTO {
    private Long idMedicamento;
    private Integer cantidadRecetada;

    /**
     * Contructor vacio.
     */
    public DetalleRecetaDTO() {
        
    }

    /**
     * Contructor con todos los atributos.
     * @param idMedicamento Id del medicamento agregado a la receta.
     * @param cantidadPermitida Cantidad recetada del medicamento.
     */
    public DetalleRecetaDTO(Long idMedicamento, Integer cantidadPermitida) {
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
    
}
