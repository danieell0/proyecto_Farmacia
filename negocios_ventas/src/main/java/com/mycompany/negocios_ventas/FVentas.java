/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios_ventas;

import com.mycompany.dto_negocios.ProductoDTO;
import com.mycompany.objetos_negocio.VentaBO;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author munos
 */
public class FVentas implements IVenta {

    private VentaBO ventaBO;

    /**
     * Constructor de la clase FVentas.
     */
    public FVentas() {
        this.ventaBO = new VentaBO();
    }

    /**
     * Busca un producto recorriendo el inventario uno por uno.
     * @param nombre El nombre del producto que el usuario ingresa.
     * @return El producto si el nombre coincide, null si no.
     */
    @Override
    public ProductoDTO buscarProducto(String nombre) {
        List<ProductoDTO> inventario = ventaBO.getInventario();
        for (int i = 0; i < inventario.size(); i++) {
            ProductoDTO productoActual = inventario.get(i);
            
            // Usando Objects.equals para seguir el estilo de FReceta
            if (productoActual.getNombre().equalsIgnoreCase(nombre)) {
                return productoActual;
            }
        }
        return null;
    }

    /**
     * Calcula el subtotal de la línea de producto.
     * @param precio Precio unitario del producto.
     * @param cantidad Cantidad de unidades.
     * @return El subtotal calculado.
     */
    @Override
    public double calcularSubtotal(double precio, int cantidad) {
        return ventaBO.calcularSubtotal(precio, cantidad);
    }

    /**
     * Calcula el cambio a devolver al cliente y valida que el pago sea suficiente.
     * @param total Monto total de la venta.
     * @param pago Monto recibido por el cliente.
     * @return El cambio calculado.
     * @throws VentaException Si el pago es menor al total.
     */
    @Override
    public double calcularCambio(double total, double pago) throws VentaException {
        if (pago < total) {
            throw new VentaException("El pago es insuficiente.");
        }
        return pago - total;
    }

    /**
     * Finaliza la transacción de la venta actualizando el inventario.
     * @param productosVendidos Lista de productos a procesar.
     * @return true si la venta se realizó con éxito, false si la lista está vacía.
     */
    @Override
    public boolean finalizarVenta(List<ProductoDTO> productosVendidos) {
        if (productosVendidos == null || productosVendidos.isEmpty()) {
            return false;
        }
        // Delega la persistencia o lógica de cierre a la BO
        return ventaBO.finalizarVenta(productosVendidos);
    }
}