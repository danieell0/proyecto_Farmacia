/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Benjamin
 */
public class CuentaAcceso {
    
    private Long IDEmpleado;
    private String contrasena;

    public CuentaAcceso() {
    }

    public CuentaAcceso(Long IDEmpleado, String contrasena) {
        this.IDEmpleado = IDEmpleado;
        this.contrasena = contrasena;
    }

    public Long getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(Long IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    
}
