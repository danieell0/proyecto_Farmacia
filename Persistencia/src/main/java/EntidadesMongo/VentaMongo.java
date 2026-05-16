/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import Entidades.DetalleVenta;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author munos
 */
@BsonDiscriminator
public class VentaMongo {
 private String idVenta;
    private LocalDateTime fecha;
    private Double total;
    private String idEmpleado;
    private String idCliente;
    private List<DetalleVentaMongo> detalles;

    public VentaMongo() {
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

    public List<DetalleVentaMongo> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleVentaMongo> detalles) {
        this.detalles = detalles;
    }
    
}