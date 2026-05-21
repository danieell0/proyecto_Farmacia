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
public class MovimientoEntradaDTO extends MovimientoDTO{
    private LoteDTO lote;

    public MovimientoEntradaDTO() {
    }

    public MovimientoEntradaDTO(LoteDTO lote, String idMovimiento, LocalDateTime fechaHora, String idEmpleado) {
        super(idMovimiento, fechaHora, idEmpleado);
        this.lote = lote;
    }
    
    public LoteDTO getLote() {
        return lote;
    }

    public void setLote(LoteDTO lote) {
        this.lote = lote;
    }

    @Override
    public String toString() {
        return "MovimientoEntradaDTO{" + "lote=" + lote + '}';
    }
    
}
