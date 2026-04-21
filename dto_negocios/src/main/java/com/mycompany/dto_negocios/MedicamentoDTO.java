package com.mycompany.dto_negocios;

import com.mycompany.dto_negocios.enums.Medida;
import java.time.LocalDate;

/**
 * DTO que representa un medicamento.
 * @author Dario
 */
public class MedicamentoDTO extends ProductoDTO{
    private String marca;
    private Medida medida;
    private Double dosis;
    private String presentacion;
    private boolean esControlado;
//    private String lote;
//    private LocalDate caducidad;

    /**
     * Contructor vacio.
     */
    public MedicamentoDTO() {
        
    }

    /**
     * Contructor con todos los atributos de medicamento.
     * @param id Id del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto. 
     * @param stock Stock del producto.
     * @param marca Marca del medicamento.
     * @param medida Medida del medicamento.
     * @param dosis Dosis del medicamento.
     * @param presentacion Presentacion del medicamento.
     * @param esControlado Restriccion del medicamento.
     */
    public MedicamentoDTO(Long id, String nombre, Double precio, Integer stock, String marca, Medida medida, Double dosis, String presentacion, boolean esControlado) {
        super(id, nombre, precio, stock);
        this.marca = marca;
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlado = esControlado;
    }

    /** @return Obtiene la marca del medicamento. */
    public String getMarca() {
        return marca;
    }

    /** @param marca La marca a asignar al medicamento. */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /** @return Obtiene la medida del medicamento. */
    public Medida getMedida() {
        return medida;
    }

    /** @param medida La medida a asignar al medicamento. */
    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    /** @return Obtiene la dosis del medicamento. */
    public Double getDosis() {
        return dosis;
    }

    /** @param dosis La dosis a asignar al medicamento. */
    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    /** @return Obtiene la presentacion del medicamento. */
    public String getPresentacion() {
        return presentacion;
    }

    /** @param presentacion La presentacion a asignar al medicamento. */
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    /** @return Obtiene la restriccion del medicamento. */
    public boolean isEsControlado() {
        return esControlado;
    }

    /** @param esControlado La restriccion a asignar al medicamento. */
    public void setEsControlado(boolean esControlado) {
        this.esControlado = esControlado;
    }
    
}
