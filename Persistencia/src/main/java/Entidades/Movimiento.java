/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Jorge
 */
public class Movimiento {
    private String idMovimiento;
    private LocalDateTime fechaHora;
    private String idEmpleado;
    private String codigoSolicitud;
    
    public Movimiento() {
    }

    public Movimiento(String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        this.idMovimiento = idMovimiento;
        this.fechaHora = fechaHora;
        this.idEmpleado = idEmpleado;
        this.codigoSolicitud = codigoSolicitud;
    }

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public String getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(String idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fecha) {
        this.fechaHora = fecha;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "Movimiento{" + "idMovimiento=" + idMovimiento + ", fecha=" + fechaHora + ", idEmpleado=" + idEmpleado + '}';
    }
    
}
