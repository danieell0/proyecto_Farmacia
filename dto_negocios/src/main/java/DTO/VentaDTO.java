package DTO;

import Enums.TipoVenta;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que representa una venta.
 * @author munos
 */
public class VentaDTO {
    private String idVenta;
    private LocalDateTime fecha;
    private Double total;
    private String idEmpleado;
    private String idCliente;
    private TipoVenta tipo;
    private Double puntosGenerados ;
    private List<DetalleVentaDTO> detalles;

    public VentaDTO() {
        this.detalles = new ArrayList<>();
    }

    public String getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(String idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(String idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public List<DetalleVentaDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaDTO> detalles) {
        this.detalles = detalles;
    }

    public TipoVenta getTipo() {
        return tipo;
    }

    public void setTipo(TipoVenta tipo) {
        this.tipo = tipo;
    }

    public Double getPuntosGenerados() {
        return puntosGenerados;
    }

    public void setPuntosGenerados(Double puntosGenerados) {
        this.puntosGenerados = puntosGenerados;
    }
    
}