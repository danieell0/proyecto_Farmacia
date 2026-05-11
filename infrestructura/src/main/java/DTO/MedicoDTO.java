package DTO;

import Enums.Especialidades;


/**
 *
 * @author Dario
 */
public class MedicoDTO {
    private String cedula;
    private String nombre;
    private Especialidades especialidad;
    private Boolean permisos;

    public MedicoDTO() {
        
    }
    
    public MedicoDTO(String cedula, String nombre, Especialidades especialidad, Boolean permisos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.permisos = permisos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidades getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidades especialidad) {
        this.especialidad = especialidad;
    }

    public Boolean getPermisos() {
        return permisos;
    }

    public void setPermisos(Boolean permisos) {
        this.permisos = permisos;
    }
    
}
