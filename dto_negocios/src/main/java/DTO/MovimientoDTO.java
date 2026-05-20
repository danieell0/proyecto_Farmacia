/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Jorge
 */
public class MovimientoDTO {
    private String idMovimiento;
    private LocalDateTime fechaHora;
    private String idEmpleado;

    public MovimientoDTO() {
    }

    public MovimientoDTO(String idMovimiento, LocalDateTime fechaHora, String idEmpleado) {
        this.idMovimiento = idMovimiento;
        this.fechaHora = fechaHora;
        this.idEmpleado = idEmpleado;
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

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    @Override
    public String toString() {
        return "MovimientoDTO{" + "idMovimiento=" + idMovimiento + ", fechaHora=" + fechaHora + ", idEmpleado=" + idEmpleado + '}';
    }
    
}
