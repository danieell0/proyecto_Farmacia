/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 * Clase DTO que representa un movimiento de entrada dentro del sistema.
 *
 * Un movimiento de entrada DTO almacena la información relacionada con el lote
 * registrado dentro del movimiento.
 *
 * @author Jorge
 */
public class MovimientoEntradaDTO extends MovimientoDTO {

    private LoteDTO lote;

    /**
     * Constructor por defecto de la clase MovimientoEntradaDTO.
     */
    public MovimientoEntradaDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos de un movimiento de
     * entrada DTO.
     *
     * @param lote Lote asociado al movimiento.
     * @param idMovimiento Identificador del movimiento.
     * @param fechaHora Fecha y hora en que se realizó el movimiento.
     * @param idEmpleado Identificador del empleado responsable.
     * @param codigoSolicitud Código de la solicitud asociada.
     */
    public MovimientoEntradaDTO(LoteDTO lote, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.lote = lote;
    }

    /**
     * Obtiene el lote asociado al movimiento de entrada.
     *
     * @return Lote del movimiento.
     */
    public LoteDTO getLote() {
        return lote;
    }

    /**
     * Establece el lote asociado al movimiento de entrada.
     *
     * @param lote Lote del movimiento.
     */
    public void setLote(LoteDTO lote) {
        this.lote = lote;
    }

    /**
     * Devuelve una representación en cadena del objeto MovimientoEntradaDTO.
     *
     * @return Cadena con la información del movimiento de entrada.
     */
    @Override
    public String toString() {
        return "MovimientoEntradaDTO{" + "lote=" + lote + '}';
    }

}
