/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.CuentaAcceso;

/**
 *
 * @author Benjamin
 */
public interface ICuentaAccesoDAO {
    public boolean validarCredenciales(String idEmpleado, String contrasena);
    
    public CuentaAcceso obtenerCuenta(String idEmpleado);
}
