/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.negocios_ventas;

/**
 *Excepcion personalizada del paquete de objetos de negocio.
 * @author munos
 */
public class VentaException extends Exception{
    
    /**
     * Excepcion sin un mensaje detallado.
     */
    public VentaException() {
        
    }

     /**
     * Excepcion con mensaje personaliza del error.
     * @param message El mensaje de descripcion de error.
     */
    public VentaException(String message) {
        super(message);
    }

    /**
     * Excepcion con mensaje y con causa, usualmente usado para mostrar errores con loggers.
     * @param message El mensaje de descripcion de error.
     * @param cause La causa mandada por la base de datos.
     */
    public VentaException(String message, Throwable cause) {
        super(message, cause);
    }
}
