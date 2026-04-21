package com.mycompany.dto_negocios;

import com.mycompany.dto_negocios.enums.Estado;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO que representa una receta.
 * @author Dario
 */
public class RecetaDTO {
    private Long folio;
    private String cedulaMedico;
    private Integer usos;
    private Estado estado;
    private LocalDate fechaCaducidad;
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
     * @param usos Numero de usos de la receta.
     * @param estado Estado de la receta.
     * @param fechaCaducidad Fecha de caducidad de la receta.
     * @param detalles Lista de medicamentos recetados.
     */
    public RecetaDTO(Long folio, String cedulaMedico, Integer usos, Estado estado, LocalDate fechaCaducidad, List<DetalleRecetaDTO> detalles) {
        this.folio = folio;
        this.cedulaMedico = cedulaMedico;
        this.usos = usos;
        this.estado = estado;
        this.fechaCaducidad = fechaCaducidad;
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

    /** @return Obtiene el numero de usos receta. */
    public Integer getUsos() {
        return usos;
    }

    /** @param usos El numero de usos a asignar a la receta. */
    public void setUsos(Integer usos) {
        this.usos = usos;
    }

    /** @return Obtiene el estado de la receta. */
    public Estado getEstado() {
        return estado;
    }

    /** @param estado El estado a asignar a la receta. */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /** @return Obtiene la fecha de caducidad de la receta. */
    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    /** @param fechaCaducidad La fecha de caducidad a asignar a la receta. */
    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
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
