package com.mycompany.subsistemas;

/**
 * Interfaz que define los metodos del subsistema de control sesion.
 * @author Dario
 */
public interface IControlSesion {
    
    /**
     * Metodo para iniciar sesion del empleado.
     * @throws SubsistemaException La causa del error.
     */
    public abstract void iniciarSesion()
            throws SubsistemaException;
    
    /**
     * Metodo para verificar los permisios del usuario.
     * @throws SubsistemaException La causa del error.
     */
    public abstract void verificarPermisos()
            throws SubsistemaException;
    
    /**
     * Metodo para cerrar secion del empleado.
     * @throws SubsistemaException La causa del error.
     */
    public abstract void cerrarSesion()
            throws SubsistemaException;
}
