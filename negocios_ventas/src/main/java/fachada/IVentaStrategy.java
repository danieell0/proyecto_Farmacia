package fachada;

import DTO.CarritoDTO;

/**
 * 
 * @author Dario
 */
public interface IVentaStrategy {
    
    /**
     * Interfaz de la venta strategy.
     * @param monto El monto de la venta.
     * @param idCliente ID del cliente que realiza la compra.
     * @param carrito Conjunto de los productos que comprara el cliente.
     * @return La feria de la venta o los puntos del cliente,
     * dependiendo el tipo de venta.
     * @throws Exception 
     */
    Double finalizarVenta(Double monto, String idCliente, CarritoDTO carrito) throws Exception;
    
    Boolean requiereConfirmarReceta();
    
}
