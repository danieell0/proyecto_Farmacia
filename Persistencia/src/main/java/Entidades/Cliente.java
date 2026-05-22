package Entidades;

import java.time.LocalDate;

/**
 * Clase que representa la entidad Cliente.
 * @author Dario
 */
public class Cliente {
    private String idCliente;
    private String nombre;
    private String telefono;
    private Double puntos;
    private LocalDate fechaNacimiento;

    /**
     * Contructor vacio.
     */
    public Cliente() {
        
    }

    /**
     * Contructor del cliente con todos sus atributos.
     * @param idCliente ID del cliente.
     * @param nombre Nombre del cliente.
     * @param telefono Telefono del cliente.
     * @param puntos Puntos del cliente.
     * @param fechaNacimiento Fecha de nacimiento del cliente.
     */
    public Cliente(String idCliente, String nombre, String telefono, Double puntos, LocalDate fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.telefono = telefono;
        this.puntos = puntos;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    /**
     * Obtiene el identificador del cliente.
     * @return Identificador del cliente.
     */
    public String getIdCliente() {
        return idCliente;
    }

    /**
     * Establece el identificador del cliente.
     * @param idCliente Nuevo identificador del cliente.
     */
    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Obtiene el nombre del cliente.
     * @return Nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del cliente.
     * @param nombre Nuevo nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el teléfono del cliente.
     * @return Teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el teléfono del cliente.
     * @param telefono Nuevo teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene los puntos del cliente.
     * @return Puntos del cliente.
     */
    public double getPuntos() {
        return puntos;
    }

    /**
     * Establece los puntos del cliente.
     * @param puntos Nuevos puntos del cliente.
     */
    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    /**
     * Obtiene la fecha de nacimiento del cliente.
     * @return Fecha de nacimiento del cliente.
     */
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Establece la fecha de nacimiento del cliente.
     * @param fechaNacimiento Nueva fecha de nacimiento del cliente.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
