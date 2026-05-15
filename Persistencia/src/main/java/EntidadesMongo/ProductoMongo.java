/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author Dario
 */
@BsonDiscriminator
public class ProductoMongo {

    private Long idProducto;
    private String nombre;
    private Double precio;
    private String Imagen;
    private Integer stock;

    public ProductoMongo() {
    }

    public ProductoMongo(Long idProducto, String nombre, Double precio, String Imagen, Integer stock) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.Imagen = Imagen;
        this.stock = stock;
    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", precio=" + precio + ", Imagen=" + Imagen + ", stock=" + stock + '}';
    }

}
