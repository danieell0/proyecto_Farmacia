/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Data transfer object que representa a un objeto de cucenta de acceso dentro de el sistema
 * @author Benjamin
 */
public class CuentaAccesoDTO {
    
    private String IDEmpleado;
    private String contraseña;
    
    public CuentaAccesoDTO() {
    }

    public CuentaAccesoDTO(String IDEmpleado, String contraseña) {
        this.IDEmpleado = IDEmpleado;
        this.contraseña = contraseña;
    }

    public String getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(String IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}
