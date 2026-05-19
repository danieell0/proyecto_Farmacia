/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import java.util.List;

/**
 *
 * @author Jorge
 */
public class LoteMongo {
    private String codigoLote;
    private String proveedor;
    private String observacionGeneral;
    private List<DetalleLoteMongo> detalles;

    public LoteMongo() {
    }

    public LoteMongo(String codigoLote, String proveedor, String observacionGeneral, List<DetalleLoteMongo> detalles) {
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

    public List<DetalleLoteMongo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleLoteMongo> detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "LoteMongo{" + "codigoLote=" + codigoLote + ", proveedor=" + proveedor + ", observacionGeneral=" + observacionGeneral + ", detalles=" + detalles + '}';
    }
    
}
