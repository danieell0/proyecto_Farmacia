/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import dtos.EmpleadoDTO;
import dtos.LoginDTO;

/**
 *
 * @author Benjamin
 */
public interface IFachadaSesion {
    
    //verifica en la bd si las credenciales coinciden
    EmpleadoDTO verificarCredenciales(LoginDTO login);
}
