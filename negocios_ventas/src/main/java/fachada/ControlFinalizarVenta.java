/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachada;

import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.DetalleVentaDTO;
import DTO.VentaDTO;

import IBO.IVentaBO;
import Sesion.FachadaSesion;
import Sesion.IFachadaSesion;

import com.mycompany.objetos_negocio.VentaBO;

import exception.VentaException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author munos
 */
public class ControlFinalizarVenta {

    private IVentaBO ventaBO;
    

    protected ControlFinalizarVenta() {

        this.ventaBO = VentaBO.getInstance();
       
    }

    /**
     * Registra definitivamente la venta.
     *
     * @param ventaFinal Venta preparada.
     * @return true si se registró correctamente.
     * @throws VentaException Error al registrar.
     */
    protected boolean registrarVenta(
            VentaDTO ventaFinal
    ) throws VentaException {

        if (ventaFinal == null) {

            throw new VentaException(
                    "La venta no puede ser nula."
            );
        }

        try {

            return ventaBO.agregarVenta(ventaFinal);

        } catch (Exception e) {

            throw new VentaException(
                    "Error al registrar la venta: "
                    + e.getMessage()
            );
        }
    }

    /**
     * Convierte el carrito actual en una venta lista para guardar.
     *
     * @param carrito Carrito actual.
     * @param idEmpleado Empleado que realiza la venta.
     * @param idCliente Cliente asociado.
     * @return Venta preparada.
     * @throws VentaException Error de validación.
     */
    protected VentaDTO prepararVenta(
            CarritoDTO carrito,
            String idEmpleado,
            String idCliente
    ) throws VentaException {

        // VALIDAR CARRITO
        if (carrito == null
                || carrito.getListaProductos() == null
                || carrito.getListaProductos().isEmpty()) {

            throw new VentaException(
                    "No se puede finalizar la venta porque el carrito está vacío."
            );
        }

        // VALIDAR TOTAL
        if (carrito.getTotalAPagar() == null
                || carrito.getTotalAPagar() <= 0) {

            throw new VentaException(
                    "El total a pagar debe ser mayor a cero."
            );
        }

        // VALIDAR EMPLEADO
        if (idEmpleado == null) {

            throw new VentaException(
                    "El empleado es obligatorio."
            );
        }

        // VALIDAR CLIENTE
        if (idCliente == null) {

            throw new VentaException(
                    "El cliente es obligatorio."
            );
        }

        // CREAR VENTA
        VentaDTO nuevaVenta = new VentaDTO();

        nuevaVenta.setFecha(LocalDateTime.now());

        nuevaVenta.setTotal(carrito.getTotalAPagar());

        nuevaVenta.setIdEmpleado(idEmpleado);

        nuevaVenta.setIdCliente(idCliente);

        List<DetalleVentaDTO> detallesVenta
                = new ArrayList<>();

        // CONVERTIR DETALLES
        for (DetalleCarritoDTO itemCarrito
                : carrito.getListaProductos()) {

            DetalleVentaDTO itemVenta
                    = new DetalleVentaDTO();

            itemVenta.setProducto(
                    itemCarrito.getProducto()
            );

            itemVenta.setCantidad(
                    itemCarrito.getCantidad()
            );

            itemVenta.setPrecioUnitario(
                    itemCarrito.getPrecioUnitario()
            );

            itemVenta.setSubtotal(
                    itemCarrito.getSubtotal()
            );

            detallesVenta.add(itemVenta);
        }

        nuevaVenta.setDetalles(detallesVenta);

        return nuevaVenta;
    }
}