package Entidades;

import Enums.TipoProducto;

/**
 * DTO que representa un producto canjeable.
 * @author Dario
 */
public class Canjeable extends Producto {
    private Double puntos;

    /**
     * Contructor vacio.
     */
    public Canjeable() {
        
    }

    /**
     * Constructor con todos los atributos del producto canejable.
     * @param puntos puntos de costro del producto.
     * @param idProducto Identificador del producto.
     * @param nombre Nombre del producto.
     * @param marca Marca del producto.
     * @param precio Precio del producto.
     * @param stock Cantidad disponible en stock.
     * @param imagen Imagen del producto.
     * @param tipo Tipo del producto.
     */
    public Canjeable(Double puntos, String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        super(idProducto, nombre, marca, precio, imagen, stock, tipo);
        this.puntos = puntos;
    }

    /** @return El precio en puntos del producto. */
    public Double getPuntos() {
        return puntos;
    }

    /** @param puntos El precio en puntos a asignar al producto. */
    public void setPuntos(Double puntos) {
        this.puntos = puntos;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     * @return Cadena con los datos del producto canjeable.
     */
    @Override
    public String toString() {
        return "Canjeable{" + "puntos=" + puntos + '}';
    }
    
}
