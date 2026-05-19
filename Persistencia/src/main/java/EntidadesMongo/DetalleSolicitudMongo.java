/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

/**
 *
 * @author Jorge
 */
public class DetalleSolicitudMongo {
    private ProductoMongo producto;
    private Integer cantidadSolicitada;

    public DetalleSolicitudMongo() {
    }

    public DetalleSolicitudMongo(ProductoMongo producto, Integer cantidadSolicitada) {
        this.producto = producto;
        this.cantidadSolicitada = cantidadSolicitada;
    }

    public ProductoMongo getProducto() {
        return producto;
    }

    public void setProducto(ProductoMongo producto) {
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
        return "DetalleSolicitudMongo{" + "producto=" + producto + ", cantidadSolicitada=" + cantidadSolicitada + '}';
    }
    
}
