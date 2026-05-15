/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.RolPuesto;

/**
 *
 * @author Benjamin
 */
public class SesionActualDTO {
    private Long idEmpleado;
    private String nombreCompleto;
    private RolPuesto rol;
    
    public SesionActualDTO(Long idEmpleado, String nombreCompleto, RolPuesto rol) {
        this.idEmpleado = idEmpleado;
        this.nombreCompleto = nombreCompleto;
        this.rol = rol;
    }

    public Long getIdEmpleado() {
        return idEmpleado;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public RolPuesto getRol() {
        return rol;
    }
    
}
