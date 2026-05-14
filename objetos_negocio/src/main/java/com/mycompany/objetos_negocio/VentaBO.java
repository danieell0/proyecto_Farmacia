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

        if (ventaDTO == null) {
            return false;
        }

        Venta venta = mapperVenta.toEntity(ventaDTO);

        if (venta.getDetalles() == null || venta.getDetalles().isEmpty()) {
            return false;
        }

        for (DetalleVenta dv : venta.getDetalles()) {

            if (dv.getProducto() == null) {
                return false;
            }

            if (dv.getCantidad() <= 0) {
                return false;
            }

            Producto producto = dv.getProducto();

            int stockActual = producto.getStock();

            if (stockActual < dv.getCantidad()) {
                return false;
            }
        }

        boolean ventaGuardada = ventaDAO.agregarVenta(venta);

        if (ventaGuardada) {

            for (DetalleVenta dv : venta.getDetalles()) {

                Producto producto = dv.getProducto();

                int stockActual = producto.getStock();

                int nuevoStock = stockActual - dv.getCantidad();

                productoDAO.DisminuirStock(
                        producto.getIdProducto(),
                        nuevoStock
                );
            }
        }

        return ventaGuardada;
    }
}
