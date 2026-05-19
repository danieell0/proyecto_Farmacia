/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
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
    
    
    public DetalleLote() {
    }

    public DetalleLote(Producto producto, Integer cantidadSolicitada, Integer cantidadRecibida, String observacion, Integer cantidadAnterior, Integer cantidadNueva) {
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
        this.cantidadRecibida = cantidadRecibida;
        this.observacion = observacion;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
    }

    public Integer getCantidadAnterior() {
        return cantidadAnterior;
    }

    public void setCantidadAnterior(Integer cantidadAnterior) {
        this.cantidadAnterior = cantidadAnterior;
    }

    public Integer getCantidadNueva() {
        return cantidadNueva;
    }

    public void setCantidadNueva(Integer cantidadNueva) {
        this.cantidadNueva = cantidadNueva;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void setCantidadSolicitada(Integer cantidadSolicitada) {
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public Integer getCantidadRecibida() {
        return cantidadRecibida;
    }

    public void setCantidadRecibida(Integer cantidadRecibida) {
        this.cantidadRecibida = cantidadRecibida;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "DetalleLote{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + ", cantidadRecibida=" + cantidadRecibida + ", observacion=" + observacion + '}';
    }
    
}
