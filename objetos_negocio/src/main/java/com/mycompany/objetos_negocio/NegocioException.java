package com.mycompany.objetos_negocio;

/**
 * Excepcion personalizada del paquete de objetos de negocio.
 * @author Dario
 */
public class NegocioException extends Exception{

    /**
     * Excepcion sin un mensaje detallado.
     */
    public NegocioException() {
        
    }

     /**
     * Excepcion con mensaje personaliza del error.
     * @param message El mensaje de descripcion de error.
     */
    public NegocioException(String message) {
        super(message);
    }

    /**
     * Excepcion con mensaje y con causa, usualmente usado para mostrar errores con loggers.
     * @param message El mensaje de descripcion de error.
     * @param cause La causa mandada por la base de datos.
     */
    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
