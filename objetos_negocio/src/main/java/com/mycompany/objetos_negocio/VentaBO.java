/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import Clases.ProductoDAO;
import Clases.VentaDAO;
import DTO.VentaDTO;
import Entidades.DetalleVenta;
import Entidades.Producto;
import Entidades.Venta;
import IBO.IVentaBO;
import Interfaces.IProductoDAO;
import Interfaces.IVentaDAO;
import Mappers.VentaMapper;
import java.util.ArrayList;
import java.util.List;

public class VentaBO implements IVentaBO {

    private static VentaBO instancia;
    private IVentaDAO ventaDAO;
    private VentaMapper mapperVenta;
    private IProductoDAO productoDAO;

    private VentaBO() {
        this.ventaDAO = new VentaDAO();
        this.mapperVenta = new VentaMapper();
        this.productoDAO = new ProductoDAO();
    }

    public static VentaBO getInstance() {
        if (instancia == null) {
            instancia = new VentaBO();
        }
        return instancia;
    }

    @Override
public boolean agregarVenta(VentaDTO ventaDTO) {

    try {

        if (ventaDTO == null) {
            return false;
        }

        Venta venta = mapperVenta.toEntity(ventaDTO);

        // VALIDAR DETALLES
        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            System.out.println("Detalles vacíos");
            return false;
        }

        // VALIDAR STOCK REAL EN BD
        for (DetalleVenta dv : venta.getDetalles()) {

            if (dv.getProducto() == null) {
                System.out.println("Producto null");
                return false;
            }

            Producto productoBD = productoDAO.obtenerProductoPorId(
                    dv.getProducto().getIdProducto()
            );

            if (productoBD == null) {
                System.out.println("Producto no encontrado");
                return false;
            }

            int stockActual = productoBD.getStock();

            if (stockActual < dv.getCantidad()) {
                System.out.println("Stock insuficiente");
                return false;
            }
        }

        // GUARDAR VENTA
        boolean ventaGuardada = ventaDAO.agregarVenta(venta);

        // SI SE GUARDÓ, DESCONTAR STOCK DEFINITIVO
        if (ventaGuardada) {

            for (DetalleVenta dv : venta.getDetalles()) {

                Producto productoBD = productoDAO.obtenerProductoPorId(
                        dv.getProducto().getIdProducto()
                );

                int stockActual = productoBD.getStock();

                int nuevoStock = stockActual - dv.getCantidad();

                productoDAO.DisminuirStock(
                        productoBD.getIdProducto(),
                        nuevoStock
                );
            }
        }

        return ventaGuardada;

    } catch (Exception e) {

        e.printStackTrace();

        return false;
    }
}
}