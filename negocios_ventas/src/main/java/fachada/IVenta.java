/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.VentaDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import Enums.Especialidades;
import exception.VentaException;
import java.util.List;

/**
 *
 * @author munos
 */
public interface IVenta {
    public void eliminarDelCarrito(Long idProducto);
    public void agregarAlCarrito(DetalleCarritoDTO detalle);
    public CarritoDTO obtenerCarritoActual();
    Double finalizarVenta(Double cantidadRecibida, Long idEmpleado, Long idCliente);   
    void setFolioRecetaActual(String folio);        
    public boolean verificarExistenciaReceta(String folio);
    public void cancelarVentaActual();
    public Boolean validarProductoParaVenta(ProductoDTO producto, Integer cantidad);
}
