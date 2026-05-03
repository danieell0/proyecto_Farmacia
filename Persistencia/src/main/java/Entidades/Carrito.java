/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Carrito {
    private Long idEmpleado;
    private LocalDate fecha;
    private List<DetalleCarrito> listaProductos; 
    private Double totalAPagar;
    private Integer totalArticulos;

    public Carrito() {
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

    public List<DetalleCarrito> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<DetalleCarrito> listaProductos) {
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
