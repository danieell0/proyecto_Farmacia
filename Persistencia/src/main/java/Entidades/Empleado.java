 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.EstatusEmpleado;
import Enums.RolPuesto;
import java.time.LocalDate;

/**
 *
 * @author Benjamin
 */ 
public class Empleado {
    private Long idEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private RolPuesto rolPuesto;
    private LocalDate fechaNacimiento;
    private EstatusEmpleado empleadoEstatus;

    public Empleado() {
    }

    public Empleado(Long idEmpleado, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, RolPuesto rolPuesto, LocalDate fechaNacimiento, EstatusEmpleado empleadoEstatus) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.rolPuesto = rolPuesto;
        this.fechaNacimiento = fechaNacimiento;
        this.empleadoEstatus = empleadoEstatus;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Long idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public EstatusEmpleado getEmpleadoEstatus() {
        return empleadoEstatus;
    }

    public void setEmpleadoEstatus(EstatusEmpleado empleadoEstatus) {
        this.empleadoEstatus = empleadoEstatus;
    }
    
    
    
}
