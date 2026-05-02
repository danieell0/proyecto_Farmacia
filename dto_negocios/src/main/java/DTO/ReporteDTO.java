/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 * Data transfer object que representa a un objeto tipo reporte dentro del sistema
 * @author Benjamin
 */
public class ReporteDTO {
    
    private Long IDEmpleado;
    private String tituloReporte;
    private LocalDateTime fechaReporte;
    private String descripcion;

    public ReporteDTO() {
    }

    public ReporteDTO(Long IDEmpleado, String tituloReporte, LocalDateTime fechaReporte, String descripcion) {
        this.IDEmpleado = IDEmpleado;
        this.tituloReporte = tituloReporte;
        this.fechaReporte = fechaReporte;
        this.descripcion = descripcion;
    }

    public Long getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(Long IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getTituloReporte() {
        return tituloReporte;
    }

    public void setTituloReporte(String tituloReporte) {
        this.tituloReporte = tituloReporte;
    }

    public LocalDateTime getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(LocalDateTime fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    
    
}
