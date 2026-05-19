/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Jorge
 */
public class DetalleSolicitud {
    private Producto producto;
    private Integer cantidadSolicitada;

    public DetalleSolicitud() {
    }

    public DetalleSolicitud(Producto producto, Integer cantSolicitada) {
        this.producto = producto;
        this.cantidadSolicitada = cantSolicitada;
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
    
    public Integer cantidadSolicitada() {
        return cantidadSolicitada;
    }

    public void cantidadSolicitada(Integer cantSolicitada) {
        this.cantidadSolicitada = cantSolicitada;
    }

    @Override
    public String toString() {
        return "DetalleSolicitud{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + '}';
    }
    
}
