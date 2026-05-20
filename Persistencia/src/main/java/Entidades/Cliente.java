package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Dario
 */
public class Cliente {
    private String idCliente;
    private String nombre;
    private String telefono;
    private Double puntos;
    private LocalDate fechaNacimiento;

    public Cliente() {
        
    }

    public Cliente(String idCliente, String nombre, String telefono, Double puntos, LocalDate fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.puntos = puntos;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getPuntos() {
        return puntos;
    }

    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
