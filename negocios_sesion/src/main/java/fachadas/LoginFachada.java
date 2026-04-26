/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import controlador.ControlLogin;
import excepciones.NegocioExcepcion;
import interfaces.ILoginFachada;

/**
 * Implementacion de la fachada para el subsistema de inicio de sesion
 * @author Benjamin
 */
public class LoginFachada implements ILoginFachada{
    
    /**
     * Metodo para iniciar sesion que es implementado obligatoriamente por la interfaz 
     * @param idEmpleado ID del empleado que quiere iniciar sesion
     * @param contraseña Contraseña 
     * @return 
     * @throws NegocioExcepcion 
     */
    @Override
    public String iniciarSesion(Long idEmpleado, String contraseña) throws NegocioExcepcion{
        
        //se instancia a el controlador
        ControlLogin controlador = new ControlLogin();
        //la fachada solo pasa el mensaje
        return controlador.procesarInicioSesion(idEmpleado, contraseña);
    }
}
