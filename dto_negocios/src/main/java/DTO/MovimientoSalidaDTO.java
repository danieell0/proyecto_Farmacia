/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Jorge
 */
public class MovimientoSalidaDTO extends MovimientoDTO {

    private ProductoDTO producto;
    private Integer cantidad;
    private String motivo;
    private String observacion;
    private Integer cantidadAnterior;
    private Integer cantidadNueva;

    public MovimientoSalidaDTO() {
    }

    public MovimientoSalidaDTO(ProductoDTO producto, Integer cantidad, String motivo, String observacion, Integer cantidadAnterior, Integer cantidadNueva, String idMovimiento, LocalDateTime fechaHora, String idEmpleado, String codigoSolicitud) {
        super(idMovimiento, fechaHora, idEmpleado, codigoSolicitud);
        this.producto = producto;
        this.cantidad = cantidad;
        this.motivo = motivo;
        this.observacion = observacion;
        this.cantidadAnterior = cantidadAnterior;
        this.cantidadNueva = cantidadNueva;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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

    @Override
    public String toString() {
        return "MovimientoSalidaDTO{" + "producto=" + producto + ", cantidad=" + cantidad + ", motivo=" + motivo + ", observacion=" + observacion + ", cantidadAnterior=" + cantidadAnterior + ", cantidadNueva=" + cantidadNueva + '}';
    }

}
