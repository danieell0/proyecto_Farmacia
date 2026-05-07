/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import Clases.VentaDAO;
import DTO.VentaDTO;
import Entidades.DetalleVenta;
import Entidades.Producto;
import Entidades.Venta;
import IBO.IVentaBO;
import Interfaces.IVentaDAO;
import Mappers.VentaMapper;
import java.util.ArrayList;
import java.util.List;

public class VentaBO implements IVentaBO {

    private static VentaBO instancia;
    private IVentaDAO ventaDAO;
    private VentaMapper mapperVenta;

    private VentaBO() {
        this.ventaDAO = new VentaDAO();
        this.mapperVenta = new VentaMapper();
    }

    public static VentaBO getInstance() {
        if (instancia == null) {
            instancia = new VentaBO();
        }
        return instancia;
    }

    @Override
    public boolean agregarVenta(VentaDTO ventaDTO) {
        if (ventaDTO != null) {
            Venta venta = mapperVenta.toEntity(ventaDTO);

            // DESCUENTO REAL DE STOCK
            for (DetalleVenta dv : venta.getDetalles()) {
                int stockActual = dv.getProducto().getStock();
                dv.getProducto().setStock(stockActual - dv.getCantidad());
            }

            return ventaDAO.agregarVenta(venta);
        }
        return false;
    }
}
