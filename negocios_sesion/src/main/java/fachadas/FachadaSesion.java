/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fachadas;

import controlador.ControlSesion;
import dtos.EmpleadoDTO;
import dtos.LoginDTO;
import interfaces.IFachadaSesion;

/**
 *
 * @author Benjamin
 */
public class FachadaSesion implements IFachadaSesion{
    
    private ControlSesion controlSesion;
    
    public FachadaSesion() {
        // Al instanciar la fachada, preparamos el motor interno
        this.controlSesion = new ControlSesion();
    }
    
    @Override
    public EmpleadoDTO verificarCredenciales(LoginDTO login) {
        // La fachada intercepta la petición de la presentacion 
        // y se la delega a la clase que realmente sabe cómo hacerlo.
        return controlSesion.validarLogin(login);
    }
}
