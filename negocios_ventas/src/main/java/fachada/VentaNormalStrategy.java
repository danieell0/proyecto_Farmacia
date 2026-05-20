package fachada;

import DTO.CarritoDTO;

/**
 * Strategy para la situacion de venta de normal.
 * @author Dario
 */
public class VentaNormalStrategy implements IVentaStrategy {
    
    private final ControlClientePuntos controlClientePuntos = new ControlClientePuntos();

    /**
     * Se encarga de finalizar la venta con las operaciones especiales de la venta de normal,
     * donde se hay un cashback de puntos con un porcentaje de la venta.
     * @param monto El monto de la venta.
     * @param idCliente ID del cliente que realiza la compra.
     * @param carrito Conjunto de los productos que comprara el cliente.
     * @return La feria de la venta.
     * @throws Exception 
     */
    @Override
    public Double finalizarVenta(Double monto, String idCliente, CarritoDTO carrito) throws Exception {
        Double total = carrito.getTotalAPagar();
        if (monto < total) {
            throw new Exception("Dinero insuficiente para completar el cobro en efectivo.");
        }
        Double puntosAAsignar = total * 0.10;
        controlClientePuntos.sumarPuntosClientes(idCliente, puntosAAsignar);
        return monto - total;
    }
}
