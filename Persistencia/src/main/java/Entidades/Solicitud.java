/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import Enums.EstadoSolicitud;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Solicitud {
    private String codigoSolicitud;
    private LocalDateTime fechaHoraSolicitud;
    private String idEmpleado;
    private EstadoSolicitud estado;
    private List<DetalleSolicitud> detalles;

    public Solicitud() {
    }

    public Solicitud(String codigoSolicitud, LocalDateTime fechaHoraSolicitud, String idEmpleado, EstadoSolicitud estado, List<DetalleSolicitud> detalles) {
        this.codigoSolicitud = codigoSolicitud;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
        this.detalles = detalles;
    }

    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    public LocalDateTime getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    public void setFechaHoraSolicitud(LocalDateTime fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public EstadoSolicitud getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    public List<DetalleSolicitud> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleSolicitud> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Solicitud{" + "codigoSolicitud=" + codigoSolicitud + ", fechaHoraSolicitud=" + fechaHoraSolicitud + ", idEmpleado=" + idEmpleado + ", estado=" + estado + ", detalles=" + detalles + '}';
    }
    
}
