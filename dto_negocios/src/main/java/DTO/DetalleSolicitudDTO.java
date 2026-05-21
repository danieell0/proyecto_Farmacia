/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Jorge
 */
public class DetalleSolicitudDTO {
    private ProductoDTO producto;
    private Integer cantidadSolicitada;

    public DetalleSolicitudDTO() {
    }

    public DetalleSolicitudDTO(ProductoDTO producto, Integer cantidadSolicitada) {
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    @Override
    public String toString() {
        return "DetalleSolicitudDTO{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + '}';
    }
    
}
