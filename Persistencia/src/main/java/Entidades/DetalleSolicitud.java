/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 * Clase que representa el detalle de una solicitud dentro del sistema.
 *
 * Un detalle de solicitud almacena el producto solicitado y la cantidad
 * requerida de dicho producto.
 *
 * @author Jorge
 */
public class DetalleSolicitud {

    private Producto producto;
    private Integer cantidadSolicitada;

    /**
     * Constructor por defecto de la clase DetalleSolicitud.
     */
    public DetalleSolicitud() {
    }

    /**
     * Constructor que inicializa todos los atributos del detalle de solicitud.
     *
     * @param producto Producto asociado al detalle.
     * @param cantSolicitada Cantidad solicitada del producto.
     */
    public DetalleSolicitud(Producto producto, Integer cantSolicitada) {
        this.producto = producto;
        this.cantidadSolicitada = cantSolicitada;
    }

    /**
     * Obtiene el producto asociado al detalle de solicitud.
     *
     * @return Producto del detalle.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado al detalle de solicitud.
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
     * Obtiene la cantidad solicitada del producto.
     *
     * @return Cantidad solicitada.
     */
    public Integer cantidadSolicitada() {
        return cantidadSolicitada;
    }

    /**
     * Establece la cantidad solicitada del producto.
     *
     * @param cantSolicitada Cantidad solicitada.
     */
    public void cantidadSolicitada(Integer cantSolicitada) {
        this.cantidadSolicitada = cantSolicitada;
    }

    /**
     * Devuelve una representación en cadena del objeto DetalleSolicitud.
     *
     * @return Cadena con la información del detalle de solicitud.
     */
    @Override
    public String toString() {
        return "DetalleSolicitud{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + '}';
    }

}
