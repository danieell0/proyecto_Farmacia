package com.mycompany.dto_negocios;

/**
 * DTO que representa un producto.
 * @author Dario
 */
public class ProductoDTO {
    private Long id;
    private String nombre;
    private Double precio;

    /**
     * Contructor vacio.
     */
    public ProductoDTO() {
        
    }

    /**
     * Constructor con todos los atributos del producto.
     * @param id Id del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     */
    public ProductoDTO(Long id, String nombre, Double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    /** @return Obtiene el Id del producto. */
    public Long getId() {
        return id;
    }

    /** @param id El Id a asignar al producto. */
    public void setId(Long id) {
        this.id = id;
    }

    /** @return Obtiene el nombre del producto. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre  El nombre a asignar al producto. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return Obtiene el precio del producto. */
    public Double getPrecio() {
        return precio;
    }

    /** @param precio El precio a asignar al producto. */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
}
