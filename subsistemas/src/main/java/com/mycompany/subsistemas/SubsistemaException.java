package com.mycompany.subsistemas;

/**
 * Excepcion personalizada para la capa de subsistemas.
 * @author Dario
 */
public class SubsistemaException extends Exception {

    /**
     * Contructor vacio de la excepcion.
     */
    public SubsistemaException() {
        
    }

    /**
     * Excepcion con mensaje.
     * @param message El mensaje de descripcion de error.
     */
    public SubsistemaException(String message) {
        super(message);
    }

    /**
     * Excepcion con mensaje y la causa del error.
     * @param message El mensaje de descripcion de error.
     * @param cause La causa del error.
     */
    public SubsistemaException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
