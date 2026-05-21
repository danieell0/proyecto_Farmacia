/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;
import Enums.TipoBaja;
/**
 *
 * @author Benjamin
 */
public class Baja {
    private Empleado empleado; 
    private TipoBaja tipoBaja;
    private String descripcion;
    private LocalDateTime fecha;

    public Baja() {}

    public Baja(Empleado empleado, TipoBaja tipoBaja, String descripcion, LocalDateTime fecha) {
        this.empleado = empleado;
        this.tipoBaja = tipoBaja;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoBaja getTipoBaja() {
        return tipoBaja;
    }

    public void setTipoBaja(TipoBaja tipoBaja) {
        this.tipoBaja = tipoBaja;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
    
}
