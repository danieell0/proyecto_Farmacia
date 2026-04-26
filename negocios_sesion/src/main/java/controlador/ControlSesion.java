/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import dtos.EmpleadoDTO;
import excepciones.NegocioExcepcion;
import objetosNegocio.EmpleadoBO;
/**
 *
 * @author Benjamin
 */
public class ControlSesion {
    
    private EmpleadoBO empleadoBO;
    
    public ControlSesion() {
        // instancia el objeto de negocio que tiene el mock del dao
        this.empleadoBO = new EmpleadoBO();
    }

    public EmpleadoDTO validarLogin(String idUsuarioTexto, String password) {
        try {
            // 1. convierte string que viene de pantalla a long para comparar id
            Long idEmpleado = Long.parseLong(idUsuarioTexto);
            
            // 2. se llama al metodo del bo para que busque las credenciales
            EmpleadoDTO empleado = empleadoBO.validarLogin(idEmpleado, password);
            
            // 3. si llega a esta linea significa que no lanzo excepcion y que el inicio de sesion fue valido
            System.out.println("Login exitoso. Bienvenido: " + empleado.getNombre());
            return empleado;
            
        } catch (Exception e) {
            // Se ejecuta si el usuario escribio letras
            System.err.println("Error: " + e.getMessage());
            return null;
            
        } 
    }
    
}
