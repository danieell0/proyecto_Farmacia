/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sesion;

import Sesion.ControlSesion;
import DTO.EmpleadoDTO;
import DTO.CuentaAccesoDTO;
import DTO.SesionActualDTO;

/**
 *
 * @author Benjamin
 */
public class FachadaSesion implements IFachadaSesion{
    
    private ControlSesion controlSesion;
    
    private SesionActualDTO sesionActiva;
    
    public FachadaSesion() {
        // Al instanciar la fachada, preparamos el motor interno
        this.controlSesion = new ControlSesion();
    }
    
    @Override
    public SesionActualDTO verificarCredenciales(CuentaAccesoDTO login) throws Exception {
        // 1. fachada llama a control para validar 
        SesionActualDTO sesionValidada = controlSesion.validarLogin(login);
        
        // 2. si el login se valida entonces se almacena en la sesion activa
        if (sesionValidada != null) {
            this.sesionActiva = sesionValidada;
        }
        
        // 3. se regresa la sesion al coordinador
        return sesionValidada;
    }
    
    @Override
    public SesionActualDTO obtenerSesionActual() {
        return this.sesionActiva;
    }

    @Override
    public void cerrarSesion() {
        this.sesionActiva = null; // Destruimos el DTO al salir
    }
    
    
}
