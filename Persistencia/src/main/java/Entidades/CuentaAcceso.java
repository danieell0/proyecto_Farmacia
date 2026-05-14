/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author Benjamin
 */
public class CuentaAcceso {
    
    
    @BsonProperty("IDEmpleado")
    private Long IDEmpleado;
    
    @BsonProperty("contrasena")
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
