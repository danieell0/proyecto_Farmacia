package DTO;

import Enums.Medida;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO que representa un producto.
 *
 * @author Dario
 */
public class ProductoDTO {

    private Long id;
    private String nombre;
    private Double precio;
    private Integer stock;
    private String imagen;

    /**
     * Contructor vacio.
     */
    public ProductoDTO() {
       
    }

    /**
     * Constructor con todos los atributos del producto.
     *
     * @param id Id del producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param stock Stock del producto.
     * @param imagen Imagen del producto
     */
    public ProductoDTO(Long id, String nombre, Double precio, Integer stock, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
    }

    public ProductoDTO(String nombre, Double precio, String imagen) {
        this.nombre = nombre;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return Obtiene el Id del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id El Id a asignar al producto.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return Obtiene el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre El nombre a asignar al producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return Obtiene el precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * @param precio El precio a asignar al producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * @return Obtiene el stock del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * @param stock El stock a asignar al producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

}
