/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 * Clase DTO que representa el detalle de una solicitud dentro del sistema.
 *
 * Un detalle de solicitud DTO almacena la información relacionada con el
 * producto solicitado y la cantidad requerida.
 *
 * @author Jorge
 */
public class DetalleSolicitudDTO {

    private ProductoDTO producto;
    private Integer cantidadSolicitada;

    /**
     * Constructor por defecto de la clase DetalleSolicitudDTO.
     */
    public DetalleSolicitudDTO() {
    }

    /**
     * Constructor que inicializa todos los atributos del detalle de solicitud
     * DTO.
     *
     * @param producto Producto asociado al detalle.
     * @param cantidadSolicitada Cantidad solicitada del producto.
     */
    public DetalleSolicitudDTO(ProductoDTO producto, Integer cantidadSolicitada) {
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    /**
     * Obtiene el producto asociado al detalle de solicitud.
     *
     * @return Producto del detalle.
     */
    public ProductoDTO getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado al detalle de solicitud.
     *
     * @param producto Producto del detalle.
     */
    public void setProducto(ProductoDTO producto) {
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
     * Devuelve una representación en cadena del objeto DetalleSolicitudDTO.
     *
     * @return Cadena con la información del detalle de solicitud.
     */
    @Override
    public String toString() {
        return "DetalleSolicitudDTO{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + '}';
    }

}
