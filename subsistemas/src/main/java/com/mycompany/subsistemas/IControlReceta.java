package com.mycompany.subsistemas;

/**
 * Interfaz que define los metodos del subsistema de control receta.
 * @author Dario
 */
public interface IControlReceta {
    
    //El metodo tendra como parametro Receta.
    public abstract String obtenerReceta()
            throws SubsistemaException;
    
    //El metodo tendra como parametro Receta.
    public abstract String validarUsosReceta()
            throws SubsistemaException;
    
    //El string cambiara a estado y tendra como parametro Receta.
    public abstract String estadoReceta()
            throws SubsistemaException;
}
