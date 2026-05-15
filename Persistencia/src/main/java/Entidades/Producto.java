package Entidades;

import Enums.TipoProducto;

/**
 *
 * @author Jorge
 */
public class Producto {

    private String idProducto;
    private String nombre;
    private String marca;
    private Double precio;
    private String imagen;
    private Integer stock;
    private TipoProducto tipo;
    

    public Producto() {
    }

    public Producto(String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public TipoProducto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", imagen=" + imagen + ", stock=" + stock + ", tipo=" + tipo + '}';
    }
   
}