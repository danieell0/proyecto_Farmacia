/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author Benjamin
 */
public class CuentaAccesoMongo {
    @BsonProperty("IDEmpleado")
    private String IDEmpleado;
    
    @BsonProperty("contrasena")
    private String contrasena;

    public CuentaAccesoMongo() {
    }

    public CuentaAccesoMongo(String IDEmpleado, String contrasena) {
        this.IDEmpleado = IDEmpleado;
        this.contrasena = contrasena;
    }

    public String getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(String IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}
