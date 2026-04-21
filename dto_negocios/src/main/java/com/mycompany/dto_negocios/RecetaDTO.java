package com.mycompany.dto_negocios;

import java.util.List;

/**
 * DTO que representa una receta.
 * @author Dario
 */
public class RecetaDTO {
    private Long folio;
    private String cedulaMedico;
    private List<DetalleRecetaDTO> detalles;

    /**
     * Contructor vacio.
     */
    public RecetaDTO() {
        
    }

    /**
     * Contructor con todos los atributos.
     * @param folio Folio de la receta.
     * @param cedulaMedico Cedula del medico que dio la receta.
     * @param detalles Lista de medicamentos recetados.
     */
    public RecetaDTO(Long folio, String cedulaMedico, List<DetalleRecetaDTO> detalles) {
        this.folio = folio;
        this.cedulaMedico = cedulaMedico;
        this.detalles = detalles;
    }
    
    /** @return Obtiene el folio de la receta. */
    public Long getFolio() {
        return folio;
    }

    /** @param folio El folio a asignar a la receta. */    
    public void setFolio(Long folio) {
        this.folio = folio;
    }

    /** @return Obtiene la cedula del medico puesto en la receta. */
    public String getCedulaMedico() {
        return cedulaMedico;
    }

    /** @param cedulaMedico La cedula del medico a asignar a la receta. */
    public void setCedulaMedico(String cedulaMedico) {
        this.cedulaMedico = cedulaMedico;
    }

    /** @return Obtiene la lista de medicamentos recetados. */
    public List<DetalleRecetaDTO> getDetalles() {
        return detalles;
    }

    /** @param detalles Los medicamentos a asignar a la receta. */
    public void setDetalles(List<DetalleRecetaDTO> detalles) {
        this.detalles = detalles;
    }
    
}
