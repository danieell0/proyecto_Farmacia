/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Sesion;

import DTO.EmpleadoDTO;
import DTO.CuentaAccesoDTO;
import DTO.SesionActualDTO;

/**
 *
 * @author Benjamin
 */
public interface IFachadaSesion {
    
    //verifica en la bd si las credenciales coinciden
    SesionActualDTO verificarCredenciales(CuentaAccesoDTO login) throws Exception;
    
    public SesionActualDTO obtenerSesionActual();
    
    public void cerrarSesion();
}
