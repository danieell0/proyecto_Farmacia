/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dto_negocios;

import DTO.DetalleVentaDTO;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author munos
 */
public class CarritoDTO {
    private Long idEmpleado; 
    private LocalDate fecha;
    private List<DetalleVentaDTO> listaProductos; 
    private Double totalAPagar;
    private Integer totalArticulos;
    
    public CarritoDTO() {
    this.listaProductos = new ArrayList<>();
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public List<DetalleVentaDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<DetalleVentaDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Double getTotalAPagar() {
        return totalAPagar;
    }

    public void setTotalAPagar(Double totalAPagar) {
        this.totalAPagar = totalAPagar;
    }

    public Integer getTotalArticulos() {
        return totalArticulos;
    }

    public void setTotalArticulos(Integer totalArticulos) {
        this.totalArticulos = totalArticulos;
    }
}
