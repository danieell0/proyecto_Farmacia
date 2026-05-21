/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Clase que representa un movimiento de entrada dentro del sistema de
 * inventario.
 *
 * Un movimiento de entrada registra la información relacionada con el lote
 * ingresado al inventario.
 *
 * @author Jorge
 */
public class MovimientoEntrada extends Movimiento {

    private Lote lote;

    /**
     * Constructor que inicializa todos los atributos de un movimiento de
     * entrada.
     *
     * @param lote Lote asociado al movimiento de entrada.
     * @param idMovimiento Identificador del movimiento.
     * @param fechaHora Fecha y hora en que se realizó el movimiento.
     * @param idEmpleado Identificador del empleado responsable.
     * @param codigoSolicitud Código de la solicitud asociada.
     */
    public MovimientoEntrada(Lote lote, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.lote = lote;
    }

    /**
     * Constructor por defecto de la clase MovimientoEntrada.
     */
    public MovimientoEntrada() {
    }

    /**
     * Obtiene el lote asociado al movimiento de entrada.
     *
     * @return Lote del movimiento.
     */
    public Lote getLote() {
        return lote;
    }

    /**
     * Establece el lote asociado al movimiento de entrada.
     *
     * @param lote Lote del movimiento.
     */
    public void setLote(Lote lote) {
        this.lote = lote;
    }

    /**
     * Devuelve una representación en cadena del objeto MovimientoEntrada.
     *
     * @return Cadena con la información del movimiento de entrada.
     */
    @Override
    public String toString() {
        return "MovimientoEntrada{" + "lote=" + lote + '}';
    }

}
