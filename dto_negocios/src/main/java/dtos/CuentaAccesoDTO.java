/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dtos;

/**
 * Data transfer object que representa a un objeto de cucenta de acceso dentro de el sistema
 * @author Benjamin
 */
public class CuentaAccesoDTO {
    
    private Long IDEmpleado;
    private Long IDReporte;
    private String contraseña;
    
    public CuentaAccesoDTO() {
    }

    public CuentaAccesoDTO(Long IDEmpleado, Long IDReporte, String contraseña) {
        this.IDEmpleado = IDEmpleado;
        this.IDReporte = IDReporte;
        this.contraseña = contraseña;
    }

    public Long getIDEmpleado() {
        return IDEmpleado;
    }

    public void setIDEmpleado(Long IDEmpleado) {
        this.IDEmpleado = IDEmpleado;
    }

    public Long getIDReporte() {
        return IDReporte;
    }

    public void setIDReporte(Long IDReporte) {
        this.IDReporte = IDReporte;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
}
