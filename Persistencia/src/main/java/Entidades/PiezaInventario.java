/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

/**
 *
 * @author Jorge
 */
public class PiezaInventario {
    private String idInventario;
    private Producto producto; 
    private Integer stockActual; 

    public PiezaInventario() {
    }

    public PiezaInventario(String idInventario, Producto producto, Integer stockActual) {
        this.idInventario = idInventario;
        this.producto = producto;
        this.stockActual = stockActual;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }
    
}
