/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import excepciones.NegocioExcepcion;

/**
 * Clase de business object que hace las reglas de negocio relacionada a un empleado
 * @author Benjamin
 */
public class EmpleadoBO {
    
    //donde se instanciaria la clase de acceso a la base de datos 
    
    public EmpleadoBO(){
        //se inicializaria aqui la DAO 
    }
    
    public String validarLogin(Long idEmpleado, String contraseña) throws NegocioExcepcion{
        
        //validaciones 
        if (idEmpleado == null){ 
            throw new NegocioExcepcion("El campo de ID no puede estar vacio");
        }
        if (idEmpleado <= 0){
            throw new NegocioExcepcion("El campo de contraseña no puede estar vacio");
        }
        if (contraseña == null || contraseña.trim().isEmpty()){
            throw new NegocioExcepcion("El campo de contraseña no puede estar vacio");
        }
        
        //caso ficticio para mockear
        String rol = null;
        
        if (idEmpleado.equals(123L) && contraseña.equals("admin")){
            rol = "Lider";
        } else if (idEmpleado.equals(456L) && contraseña.equals("caja")){
            rol = "Cajero";
        }
        
        //validacion del resultado
        if (rol == null){
            throw new NegocioExcepcion("Credenciales incorrectas o el empleado");
        }
        
        return rol;
    }
     
}
