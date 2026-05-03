/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Entidades.Venta;
import Interfaces.IVentaDAO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author Jorge
 */
public class VentaDAO implements IVentaDAO {

    private Long contador = 1L;
    private List<Venta> listaMock;
    private static final Logger logger = Logger.getLogger(VentaDAO.class.getSimpleName());

    public VentaDAO() {
        this.listaMock = new ArrayList<>();
    }

    @Override
    public boolean agregarVenta(Venta venta) {
        if (venta != null) {
            venta.setIdVenta(contador);
            contador++;
            listaMock.add(venta);
            logger.info("Venta#" + venta.getIdVenta() + " registrada con éxito. Total: $" + venta.getTotal());
            return true;
        }
        return false;
    }

}
