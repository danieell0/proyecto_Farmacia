/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class MovimientoEntrada extends Movimiento{
    private Lote lote;

    public MovimientoEntrada(Lote lote, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.lote = lote;
    }

    public MovimientoEntrada() {
    }

    public Lote getLote() {
        return lote;
    }

    public void setLote(Lote lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "MovimientoEntrada{" + "lote=" + lote + '}';
    }
    
}
