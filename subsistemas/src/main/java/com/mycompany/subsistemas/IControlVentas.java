package com.mycompany.subsistemas;

/**
 * Interfaz que define los metodos del subsistema de control ventas.
 * @author Dario
 */
public interface IControlVentas {
    
    //String cambiara a venta caundo se implemente completamente.
    public abstract String crearVenta()
            throws SubsistemaException;
    
    //String cambiara a detalleVenta caundo se implemente completamente.
    public abstract String detalleVenta()
            throws SubsistemaException;
    
    //String cambiara a ticket caundo se implemente completamente.
    public abstract String solicitarTicket()
            throws SubsistemaException;
}
