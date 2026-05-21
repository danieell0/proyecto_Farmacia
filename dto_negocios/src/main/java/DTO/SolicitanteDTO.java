/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.RolPuesto;
import java.time.LocalDate;

/**
 *
 * @author Benjamin
 */
public class SolicitanteDTO {
    private String idSolicitante;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private RolPuesto rolPuesto;
    private LocalDate fechaNacimiento;
    private String notasEntrevista;

    public SolicitanteDTO() {}

    public SolicitanteDTO(String idSolicitante, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, RolPuesto rolPuesto, LocalDate fechaNacimiento, String notasEntrevista) {
        this.idSolicitante = idSolicitante;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.rolPuesto = rolPuesto;
        this.fechaNacimiento = fechaNacimiento;
        this.notasEntrevista = notasEntrevista;
    }

    public String getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(String idSolicitante) {
        this.idSolicitante = idSolicitante;
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

    public String getNotasEntrevista() {
        return notasEntrevista;
    }

    public void setNotasEntrevista(String notasEntrevista) {
        this.notasEntrevista = notasEntrevista;
    }
    
    
    
}
