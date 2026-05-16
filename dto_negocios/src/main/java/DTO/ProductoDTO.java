package DTO;

import Enums.TipoProducto;

/**
 * DTO que representa un producto.
 *
 * Contiene la información necesaria para transferir datos de productos entre
 * las distintas capas del sistema.
 *
 * @author Dario
 */
public class ProductoDTO {
    //Identificador único del producto
    private String idProducto;
    //Nombre del producto
    private String nombre;
    //Marca del producto
    private String marca;
    //Precio del producto
    private Double precio;
    //Cantidad disponible en stock
    private Integer stock;
    //Ruta o nombre de la imagen del producto
    private String imagen;
    //Tipo de producto
    private TipoProducto tipo;

    /**
     * Constructor vacío.
     */
    public ProductoDTO() {

    }

    /**
     * Constructor con parámetros.
     *
     * @param idProducto Identificador del producto.
     * @param nombre Nombre del producto.
     * @param marca Marca del producto.
     * @param precio Precio del producto.
     * @param stock Cantidad disponible en stock.
     * @param imagen Imagen del producto.
     * @param tipo Tipo del producto.
     */
    public ProductoDTO(String idProducto, String nombre, String marca, Double precio, Integer stock, String imagen, TipoProducto tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
        this.imagen = imagen;
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
        return "ProductoDTO{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", stock=" + stock + ", imagen=" + imagen + ", tipo=" + tipo + '}';
    }

}
