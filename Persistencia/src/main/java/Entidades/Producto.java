package Entidades;

import Enums.TipoProducto;

/**
 * Clase que representa un producto dentro del sistema.
 * 
 * Contiene la información básica relacionada
 * con un producto disponible en el catálogo.
 *
 * @author Jorge
 */
public class Producto {
    //Identificador único del producto
    private String idProducto;
    //Nombre del producto
    private String nombre;
    //Marca del producto
    private String marca;
    //Precio del producto
    private Double precio;
    //Ruta o nombre de la imagen del producto
    private String imagen;
    //Cantidad disponible en stock
    private Integer stock;
    //Tipo de producto
    private TipoProducto tipo;
    

    /**
     * Constructor vacío.
     */
    public Producto() {
    }

    /**
     * Constructor con parámetros.
     * 
     * @param idProducto Identificador del producto.
     * @param nombre Nombre del producto.
     * @param marca Marca del producto.
     * @param precio Precio del producto.
     * @param imagen Imagen del producto.
     * @param stock Cantidad disponible en stock.
     * @param tipo Tipo del producto.
     */
    public Producto(String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
        this.tipo = tipo;
    }

    /**
     * Obtiene el identificador del producto.
     * 
     * @return Identificador del producto.
     */
    public String getIdProducto() {
        return idProducto;
    }

    /**
     * Establece el identificador del producto.
     * 
     * @param idProducto Nuevo identificador del producto.
     */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /**
     * Obtiene el nombre del producto.
     * 
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * 
     * @param nombre Nuevo nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la marca del producto.
     * 
     * @return Marca del producto.
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Establece la marca del producto.
     * 
     * @param marca Nueva marca del producto.
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Obtiene el precio del producto.
     * 
     * @return Precio del producto.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * 
     * @param precio Nuevo precio del producto.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la imagen del producto.
     * 
     * @return Imagen o ruta de la imagen.
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Establece la imagen del producto.
     * 
     * @param imagen Nueva imagen o ruta de la imagen.
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * Obtiene la cantidad disponible en stock.
     * 
     * @return Stock del producto.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece la cantidad disponible en stock.
     * 
     * @param stock Nuevo stock del producto.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el tipo del producto.
     * 
     * @return Tipo del producto.
     */
    public TipoProducto getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo del producto.
     * 
     * @param tipo Nuevo tipo del producto.
     */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     * 
     * @return Cadena con los datos del producto.
     */
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", imagen=" + imagen + ", stock=" + stock + ", tipo=" + tipo + '}';
    }
   
}