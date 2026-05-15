package DTO;

import Enums.TipoProducto;

/**
 * DTO que representa un producto.
 * @author Dario
 */
public class ProductoDTO {

    private String idProducto;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
    private String imagen;
    private TipoProducto tipo;

    /**
     * Contructor vacio.
     */
    public ProductoDTO() {
       
    }

    public ProductoDTO(String idProducto, String nombre, String marca, Double precio, Integer stock, String imagen, TipoProducto tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
        this.tipo = tipo;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ProductoDTO{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock + ", imagen=" + imagen + ", tipo=" + tipo + '}';
    }
    
}
