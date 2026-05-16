/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Clases;

import ConexionMongo.ManejadorConexiones;
import Entidades.CuentaAcceso;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class CuentaAccesoDAO {
    /*
    private List<CuentaAcceso> tablaCuentas;
    
    public CuentaAccesoDAO(){
        tablaCuentas = new ArrayList<>();
        
        tablaCuentas.add(new CuentaAcceso(123L, "admin"));
        tablaCuentas.add(new CuentaAcceso(456L, "caja"));
        tablaCuentas.add(new CuentaAcceso(789L, "caja2"));
    }
    
    
    public boolean validarCredenciales(Long idEmpleado, String contrasena) {
        for (CuentaAcceso cuenta : tablaCuentas) {
            if (cuenta.getIDEmpleado().equals(idEmpleado) && cuenta.getContrasena().equals(contrasena)) {
                return true;
            }
        }
        return false;
    }
    */
    
    //coleccion de cuentas mongo
    private MongoCollection<CuentaAcceso> coleccionCuentas;
    
    public CuentaAccesoDAO(){
        //obtiene la conexion desde el manejador
        this.coleccionCuentas = ManejadorConexiones.obtenerColeccionCuentas();
    }
    
    /**
     * Busca en la BD si existe una cuenta que coincida con el ID y la contraseña
     */
    public boolean validarCredenciales(String idEmpleado, String contrasena){
        //busca una cuenta que tenga ese id y esa contraseña 
        CuentaAcceso cuentaEncontrada = coleccionCuentas.find(
        and(
            eq("iDEmpleado", idEmpleado),
            eq("contrasena", contrasena)
        )
        ).first();
        
        //si no es null significa que si encontra la cuenta y regresa true
        return cuentaEncontrada != null;
    }
}
