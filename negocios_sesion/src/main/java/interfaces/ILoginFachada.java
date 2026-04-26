/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import excepciones.NegocioExcepcion;

/**
 * Interfaz que define las operaciones publicas del subsistema de inicio de sesion
 * @author Benjamin
 */
public interface ILoginFachada {
    
    /**
     * Procesa la solicitud de inicio de sesion de un empleado 
     * @param idEmpleado 
     * @param contraseña 
     * @return 
     * @throws NegocioExcepcion 
     */
    String iniciarSesion(Long idEmpleado, String contraseña) throws NegocioExcepcion;
    
}
