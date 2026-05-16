package EntidadesMongo;

import Enums.TipoProducto;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Clase de producto que se persiste en la base de datos de mongo.
 * @author Dario
 */
@BsonDiscriminator
public class ProductoMongo {
    private String idProducto;
    private String nombre;
    private String marca;
    private Double precio;
    private String imagen;
    private Integer stock;
    private TipoProducto tipo;

    /**
     * Contructor vacio.
     */
    public ProductoMongo() {
        
    }

    /**
     * Constructor completo del producto.
     * @param idProducto ID del producto.
     * @param nombre Nombre del producto.
     * @param marca Laboratorio fabricante o marca del artículo.
     * @param precio Precio del producto.
     * @param imagen Imagen del producto.
     * @param stock Stock disponibles en el inventario.
     * @param tipo Clasificacion del producto.
     */
    public ProductoMongo(String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.imagen = imagen;
        this.stock = stock;
        this.tipo = tipo;
    }

    /** @return Obtiene el ID del producto. */
    public String getIdProducto() {
        return idProducto;
    }

    /** @param idProducto El ID a asignar al producto. */
    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    /** @return Obtiene el nombre del producto. */
    public String getNombre() {
        return nombre;
    }

    /** @param nombre El nombre a asignar al producto. */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /** @return Obtiene la marca del producto. */
    public String getMarca() {
        return marca;
    }

    /** @param marca La marca a asignar al producto. */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /** @return Obtiene el precio del producto. */
    public Double getPrecio() {
        return precio;
    }

    /** @param precio El precio a asignar al producto. */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /** @return Obtiene la ruta de la imagen del producto. */
    public String getImagen() {
        return imagen;
    }

    /** @param imagen La imagen a asignar al producto. */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /** @return Obtiene el stock disponible del producto. */
    public Integer getStock() {
        return stock;
    }

    /** @param stock El stock a asignar al producto. */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /** @return Obtiene el tipo de producto. */
    public TipoProducto getTipo() {
        return tipo;
    }

    /** @param tipo El tipo a asignar al producto. */
    public void setTipo(TipoProducto tipo) {
        this.tipo = tipo;
    }

    /** @return Representacion en cadena del producto. */
    @Override
    public String toString() {
        return "Producto{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", marca=" + marca + ", precio=" + precio + ", imagen=" + imagen + ", stock=" + stock + ", tipo=" + tipo + '}';
    }
}
