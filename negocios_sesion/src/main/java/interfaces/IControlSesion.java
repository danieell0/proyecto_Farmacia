/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EmpleadoDTO;

/**
 *
 * @author Benjamin
 */
public interface IControlSesion {
    
    //verifica en la bd si las credenciales coinciden
    EmpleadoDTO verificarCredenciales(String idUsuario, String password);
}
