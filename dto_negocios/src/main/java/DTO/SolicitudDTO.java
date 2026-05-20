/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.EstadoSolicitud;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class SolicitudDTO {
    private String codigoSolicitud;
    private LocalDateTime fechaHoraSolicitud;
    private String idEmpleado;
    private EstadoSolicitud estado;
    private List<DetalleSolicitudDTO> detalles;

    public SolicitudDTO() {
    }

    public SolicitudDTO(String codigoSolicitud, LocalDateTime fechaHoraSolicitud, String idEmpleado, EstadoSolicitud estado, List<DetalleSolicitudDTO> detalles) {
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

    public List<DetalleSolicitudDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleSolicitudDTO> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "SolicitudDTO{" + "codigoSolicitud=" + codigoSolicitud + ", fechaHoraSolicitud=" + fechaHoraSolicitud + ", idEmpleado=" + idEmpleado + ", estado=" + estado + ", detalles=" + detalles + '}';
    }
    
}
