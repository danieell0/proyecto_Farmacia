package fachada;

import DTO.CarritoDTO;
import DTO.ClienteDTO;
import IBO.IVentaBO;
import com.mycompany.objetos_negocio.VentaBO;

/**
 * Strategy para la situacion de venta de puntos.
 * @author Dario
 */
public class VentaPuntosStrategy implements IVentaStrategy {
    
    private final IVentaBO ventaBO = VentaBO.getInstance();
    private final ControlClientePuntos controlClientePuntos = new ControlClientePuntos();

    /**
     * Se encarga de finalizar la venta con las operaciones especiales de la venta de puntos,
     * donde se resta los puntos gastados en la venta.
     * @param monto El monto de la venta.
     * @param idCliente ID del cliente que realiza la compra.
     * @param carrito Conjunto de los productos que comprara el cliente.
     * @return Puntos del cliente despues de la operacion.
     * @throws Exception 
     */
    @Override
    public Double finalizarVenta(Double monto, String idCliente, CarritoDTO carrito) throws Exception {
        Double puntosDisponibles = controlClientePuntos.obtenerPuntosClientes(idCliente);
        if (puntosDisponibles < monto) {
            throw new Exception("El cliente no cuenta con los puntos suficientes para realizar este canje.");
        }
        ClienteDTO clienteActualizado = controlClientePuntos.restarPuntosClientes(idCliente, monto);
        return clienteActualizado.getPuntos();
    }

    @Override
    public Boolean requiereConfirmarReceta() {
        return false;
    }
}