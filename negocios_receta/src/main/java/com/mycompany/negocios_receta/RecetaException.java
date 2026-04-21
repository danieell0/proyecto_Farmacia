package com.mycompany.negocios_receta;

/**
 * Excepcion personalizada del subsitema de objetos de recetas.
 * @author Dario
 */
public class RecetaException extends Exception{
    
    /**
     * Excepcion sin un mensaje detallado.
     */
    public RecetaException() {
        
    }

     /**
     * Excepcion con mensaje personaliza del error.
     * @param message El mensaje de descripcion de error.
     */
    public RecetaException(String message) {
        super(message);
    }

    /**
     * Excepcion con mensaje y con causa, usualmente usado para mostrar errores con loggers.
     * @param message El mensaje de descripcion de error.
     * @param cause La causa mandada por la base de datos.
     */
    public RecetaException(String message, Throwable cause) {
        super(message, cause);
    }
}
