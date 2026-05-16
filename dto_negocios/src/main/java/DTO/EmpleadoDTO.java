/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.EstatusEmpleado;
import Enums.RolPuesto;
import java.time.LocalDateTime;

/**
 * Clase data transfer object para representar un objeto de tipo empleado dentro del sistema
 * @author Benjamin
 */
public class EmpleadoDTO {
    private String ID;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private RolPuesto rolPuesto;
    private LocalDateTime fechaNacimiento;
    private EstatusEmpleado estatus;
    
    public EmpleadoDTO() {
    }

    public EmpleadoDTO(String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, RolPuesto rolPuesto, LocalDateTime fechaNacimiento, EstatusEmpleado estatus) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.rolPuesto = rolPuesto;
        this.fechaNacimiento = fechaNacimiento;
        this.estatus = estatus;
    }

    public EmpleadoDTO(String ID, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, RolPuesto rolPuesto, LocalDateTime fechaNacimiento, EstatusEmpleado estatus) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.rolPuesto = rolPuesto;
        this.fechaNacimiento = fechaNacimiento;
        this.estatus = estatus;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public RolPuesto getRolPuesto() {
        return rolPuesto;
    }

    public void setRolPuesto(RolPuesto rolPuesto) {
        this.rolPuesto = rolPuesto;
    }

    public LocalDateTime getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstatusEmpleado getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusEmpleado estatus) {
        this.estatus = estatus;
    }
    
}
