/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.RolPuesto;
import java.time.LocalDateTime;

/**
 *
 * @author Benjamin
 */
public class Contrato {
    private Empleado empleado;
    private Double salario;
    private RolPuesto rolPuesto;
    private LocalDateTime fecha;

    public Contrato() {}

    public Contrato(Empleado empleado, Double salario, RolPuesto rolPuesto, LocalDateTime fecha) {
        this.empleado = empleado;
        this.salario = salario;
        this.rolPuesto = rolPuesto;
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public RolPuesto getRolPuesto() {
        return rolPuesto;
    }

    public void setRolPuesto(RolPuesto rolPuesto) {
        this.rolPuesto = rolPuesto;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
    
    
}
