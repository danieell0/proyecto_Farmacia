/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 * Clase que representa el detalle de un lote dentro del sistema de inventario.
 *
 * Un detalle de lote almacena información relacionada con el producto, las
 * cantidades solicitadas y recibidas, observaciones y los cambios realizados en
 * el stock.
 *
 * @author Jorge
 */
public class DetalleLote {

    private Producto producto;
    private Integer cantidadSolicitada;
    private Integer cantidadRecibida;
    private String observacion;
    private Integer cantidadAnterior;
    private Integer cantidadNueva;

    /**
     * Constructor por defecto de la clase DetalleLote.
     */
    public DetalleLote() {
    }

    /**
     * Constructor que inicializa todos los atributos del detalle de lote.
     *
     * @param producto Producto asociado al detalle del lote.
     * @param cantidadSolicitada Cantidad solicitada del producto.
     * @param cantidadRecibida Cantidad recibida del producto.
     * @param observacion Observaciones relacionadas con el detalle del lote.
     * @param cantidadAnterior Cantidad existente antes de la actualización.
     * @param cantidadNueva Cantidad resultante después de la actualización.
     */
    public DetalleLote(Producto producto, Integer cantidadSolicitada, Integer cantidadRecibida, String observacion, Integer cantidadAnterior, Integer cantidadNueva) {
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.cantidadRecibida = cantidadRecibida;
        this.observacion = observacion;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
    }

    /**
     * Obtiene la cantidad existente antes de la actualización del stock.
     *
     * @return Cantidad anterior del producto.
     */
    public Integer getCantidadAnterior() {
        return cantidadAnterior;
    }

    /**
     * Establece la cantidad existente antes de la actualización del stock.
     *
     * @param cantidadAnterior Cantidad anterior del producto.
     */
    public void setCantidadAnterior(Integer cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    /**
     * Obtiene la cantidad resultante después de la actualización del stock.
     *
     * @return Cantidad nueva del producto.
     */
    public Integer getCantidadNueva() {
        return cantidadNueva;
    }

    /**
     * Establece la cantidad resultante después de la actualización del stock.
     *
     * @param cantidadNueva Cantidad nueva del producto.
     */
    public void setCantidadNueva(Integer cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    }

    /**
     * Obtiene el producto asociado al detalle del lote.
     *
     * @return Producto del detalle.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado al detalle del lote.
     *
     * @param producto Producto del detalle.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad solicitada del producto.
     *
     * @return Cantidad solicitada.
     */
    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    /**
     * Establece la cantidad solicitada del producto.
     *
     * @param cantidadSolicitada Cantidad solicitada.
     */
    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    /**
     * Obtiene la cantidad recibida del producto.
     *
     * @return Cantidad recibida.
     */
    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    /**
     * Establece la cantidad recibida del producto.
     *
     * @param cantidadRecibida Cantidad recibida.
     */
    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    /**
     * Obtiene las observaciones relacionadas con el detalle del lote.
     *
     * @return Observaciones del detalle.
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * Establece las observaciones relacionadas con el detalle del lote.
     *
     * @param observacion Observaciones del detalle.
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * Devuelve una representación en cadena del objeto DetalleLote.
     *
     * @return Cadena con la información del detalle del lote.
     */
    @Override
    public String toString() {
        return "DetalleLote{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + ", cantidadRecibida=" + cantidadRecibida + ", observacion=" + observacion + '}';
    }

}
