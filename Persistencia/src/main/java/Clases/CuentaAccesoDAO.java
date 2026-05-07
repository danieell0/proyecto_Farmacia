/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import Entidades.CuentaAcceso;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class CuentaAccesoDAO {
    
    private List<CuentaAcceso> tablaCuentas;
    
    public CuentaAccesoDAO(){
        tablaCuentas = new ArrayList<>();
        
        tablaCuentas.add(new CuentaAcceso(123L, "admin"));
        tablaCuentas.add(new CuentaAcceso(456L, "caja"));
        tablaCuentas.add(new CuentaAcceso(789L, "caja2"));
    }
    
    /**
     * Busca en la BD si existe una cuenta que coincida con el ID y Contraseña.
     */
    public boolean validarCredenciales(Long idEmpleado, String contrasena) {
        for (CuentaAcceso cuenta : tablaCuentas) {
            if (cuenta.getIDEmpleado().equals(idEmpleado) && cuenta.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
}
