/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.List;

/**
 *
 * @author Jorge
 */
public class LoteDTO {
    private String codigoLote;
    private String proveedor;
    private String observacionGeneral;
    private List<DetalleLoteDTO> detalles;

    public LoteDTO() {
    }

    public LoteDTO(String codigoLote, String proveedor, String observacionGeneral, List<DetalleLoteDTO> detalles) {
        this.codigoLote = codigoLote;
        this.proveedor = proveedor;
        this.observacionGeneral = observacionGeneral;
        this.detalles = detalles;
    }

    public String getCodigoLote() {
        return codigoLote;
    }

    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public String getObservacionGeneral() {
        return observacionGeneral;
    }

    public void setObservacionGeneral(String observacionGeneral) {
        this.observacionGeneral = observacionGeneral;
    }

    public List<DetalleLoteDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleLoteDTO> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "LoteDTO{" + "codigoLote=" + codigoLote + ", proveedor=" + proveedor + ", observacionGeneral=" + observacionGeneral + ", detalles=" + detalles + '}';
    }
    
}
