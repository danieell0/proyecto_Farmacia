/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.EstadoSolicitud;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase DTO que representa una solicitud dentro del sistema.
 *
 * Una solicitud DTO almacena la información relacionada con el empleado que
 * realizó la solicitud, la fecha y hora de creación, el estado actual y los
 * detalles asociados.
 *
 * @author Jorge
 */
public class SolicitudDTO {

    private String codigoSolicitud;
    private LocalDateTime fechaHoraSolicitud;
    private String idEmpleado;
    private EstadoSolicitud estado;
    private List<DetalleSolicitudDTO> detalles;

    /**
     * Constructor por defecto de la clase SolicitudDTO.
     */
    public SolicitudDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos de una solicitud DTO.
     *
     * @param codigoSolicitud Código identificador de la solicitud.
     * @param fechaHoraSolicitud Fecha y hora en que se realizó la solicitud.
     * @param idEmpleado Identificador del empleado que realizó la solicitud.
     * @param estado Estado actual de la solicitud.
     * @param detalles Lista de detalles asociados a la solicitud.
     */
    public SolicitudDTO(String codigoSolicitud, LocalDateTime fechaHoraSolicitud, String idEmpleado, EstadoSolicitud estado, List<DetalleSolicitudDTO> detalles) {
        this.codigoSolicitud = codigoSolicitud;
        this.fechaHoraSolicitud = fechaHoraSolicitud;
        this.idEmpleado = idEmpleado;
        this.estado = estado;
        this.detalles = detalles;
    }

    /**
     * Obtiene el código identificador de la solicitud.
     *
     * @return Código de la solicitud.
     */
    public String getCodigoSolicitud() {
        return codigoSolicitud;
    }

    /**
     * Establece el código identificador de la solicitud.
     *
     * @param codigoSolicitud Código de la solicitud.
     */
    public void setCodigoSolicitud(String codigoSolicitud) {
        this.codigoSolicitud = codigoSolicitud;
    }

    /**
     * Obtiene la fecha y hora en que se realizó la solicitud.
     *
     * @return Fecha y hora de la solicitud.
     */
    public LocalDateTime getFechaHoraSolicitud() {
        return fechaHoraSolicitud;
    }

    /**
     * Establece la fecha y hora en que se realizó la solicitud.
     *
     * @param fechaHoraSolicitud Fecha y hora de la solicitud.
     */
    public void setFechaHoraSolicitud(LocalDateTime fechaHoraSolicitud) {
        this.fechaHoraSolicitud = fechaHoraSolicitud;
    }

    /**
     * Obtiene el identificador del empleado que realizó la solicitud.
     *
     * @return Identificador del empleado.
     */
    public String getIdEmpleado() {
        return idEmpleado;
    }

    /**
     * Establece el identificador del empleado que realizó la solicitud.
     *
     * @param idEmpleado Identificador del empleado.
     */
    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    /**
     * Obtiene el estado actual de la solicitud.
     *
     * @return Estado de la solicitud.
     */
    public EstadoSolicitud getEstado() {
        return estado;
    }

    /**
     * Establece el estado actual de la solicitud.
     *
     * @param estado Estado de la solicitud.
     */
    public void setEstado(EstadoSolicitud estado) {
        this.estado = estado;
    }

    /**
     * Obtiene la lista de detalles asociados a la solicitud.
     *
     * @return Lista de detalles de la solicitud.
     */
    public List<DetalleSolicitudDTO> getDetalles() {
        return detalles;
    }

    /**
     * Establece la lista de detalles asociados a la solicitud.
     *
     * @param detalles Lista de detalles de la solicitud.
     */
    public void setDetalles(List<DetalleSolicitudDTO> detalles) {
        this.detalles = detalles;
    }

    /**
     * Devuelve una representación en cadena del objeto SolicitudDTO.
     *
     * @return Cadena con la información de la solicitud.
     */
    @Override
    public String toString() {
        return "SolicitudDTO{" + "codigoSolicitud=" + codigoSolicitud + ", fechaHoraSolicitud=" + fechaHoraSolicitud + ", idEmpleado=" + idEmpleado + ", estado=" + estado + ", detalles=" + detalles + '}';
    }

}
