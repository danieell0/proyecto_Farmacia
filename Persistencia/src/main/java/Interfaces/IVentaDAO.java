/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Venta;
import java.util.List;

/**
 *
 * @author Jorge
 */
public interface IVentaDAO {

    public boolean agregarVenta(Venta venta);
    
    public List<Venta> obtenerVentas();

    public Venta obtenerVentaPorId(Long idVenta);

    public List<Venta> obtenerVentasPorCliente(Long idCliente);

}
