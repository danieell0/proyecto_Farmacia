/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.Medida;

/**
 *
 * @author Jorge
 */
public class Medicamento extends Producto{
    private String marca;
    private Medida medida;
    private Double dosis;
    private String presentacion;
    private Boolean esControlada;

    public Medicamento() {
    }

    public Medicamento(String marca, Medida medida, Double dosis, String presentacion, Boolean esControlada) {
        this.marca = marca;
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlada = esControlada;
    }

    public Medicamento(String marca, Medida medida, Double dosis, String presentacion, Boolean esControlada, Long idProducto, String nombre, Double precio, String Imagen) {
        super(idProducto, nombre, precio, Imagen);
        this.marca = marca;
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlada = esControlada;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Boolean getEsControlada() {
        return esControlada;
    }

    public void setEsControlada(Boolean esControlada) {
        this.esControlada = esControlada;
    }
    
}
