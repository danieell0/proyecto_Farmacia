/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.List;

/**
 *
 * @author Jorge
 */
public class Lote {
    private String codigoLote;
    private String proveedor;
    private String observacionGeneral;
    private List<DetalleLote> detalles;

    public Lote() {
    }

    public Lote(String codigoLote, String proveedor, String observacionGeneral, List<DetalleLote> detalles) {
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

    public List<DetalleLote> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleLote> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Lote{" + "codigoLote=" + codigoLote + ", proveedor=" + proveedor + ", observacionGeneral=" + observacionGeneral + ", detalles=" + detalles + '}';
    }
    
}
