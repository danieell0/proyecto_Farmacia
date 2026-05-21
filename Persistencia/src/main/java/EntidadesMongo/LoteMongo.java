/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import java.util.List;

/**
 * Clase que representa un lote almacenado en MongoDB.
 *
 * Un lote Mongo almacena información relacionada con el proveedor,
 * observaciones generales y los detalles de los productos incluidos en el lote.
 *
 * @author Jorge
 */
public class LoteMongo {

    private String codigoLote;
    private String proveedor;
    private String observacionGeneral;
    private List<DetalleLoteMongo> detalles;

    /**
     * Constructor por defecto de la clase LoteMongo.
     */
    public LoteMongo() {
    }

    /**
     * Constructor que inicializa todos los atributos de un lote Mongo.
     *
     * @param codigoLote Código identificador del lote.
     * @param proveedor Nombre del proveedor asociado al lote.
     * @param observacionGeneral Observaciones generales del lote.
     * @param detalles Lista de detalles asociados al lote.
     */
    public LoteMongo(String codigoLote, String proveedor, String observacionGeneral, List<DetalleLoteMongo> detalles) {
        this.codigoLote = codigoLote;
        this.proveedor = proveedor;
        this.observacionGeneral = observacionGeneral;
        this.detalles = detalles;
    }

    /**
     * Obtiene el código identificador del lote.
     *
     * @return Código del lote.
     */
    public String getCodigoLote() {
        return codigoLote;
    }

    /**
     * Establece el código identificador del lote.
     *
     * @param codigoLote Código del lote.
     */
    public void setCodigoLote(String codigoLote) {
        this.codigoLote = codigoLote;
    }

    /**
     * Obtiene el proveedor asociado al lote.
     *
     * @return Nombre del proveedor.
     */
    public String getProveedor() {
        return proveedor;
    }

    /**
     * Establece el proveedor asociado al lote.
     *
     * @param proveedor Nombre del proveedor.
     */
    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    /**
     * Obtiene las observaciones generales del lote.
     *
     * @return Observaciones generales del lote.
     */
    public String getObservacionGeneral() {
        return observacionGeneral;
    }

    /**
     * Establece las observaciones generales del lote.
     *
     * @param observacionGeneral Observaciones generales del lote.
     */
    public void setObservacionGeneral(String observacionGeneral) {
        this.observacionGeneral = observacionGeneral;
    }

    /**
     * Obtiene la lista de detalles asociados al lote.
     *
     * @return Lista de detalles del lote.
     */
    public List<DetalleLoteMongo> getDetalles() {
        return detalles;
    }

    /**
     * Establece la lista de detalles asociados al lote.
     *
     * @param detalles Lista de detalles del lote.
     */
    public void setDetalles(List<DetalleLoteMongo> detalles) {
        this.detalles = detalles;
    }

    /**
     * Devuelve una representación en cadena del objeto LoteMongo.
     *
     * @return Cadena con la información del lote.
     */
    @Override
    public String toString() {
        return "LoteMongo{" + "codigoLote=" + codigoLote + ", proveedor=" + proveedor + ", observacionGeneral=" + observacionGeneral + ", detalles=" + detalles + '}';
    }
}
