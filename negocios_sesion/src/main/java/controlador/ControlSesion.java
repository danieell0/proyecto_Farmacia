/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import dtos.EmpleadoDTO;
import dtos.LoginDTO;
import excepciones.NegocioExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.EmpleadoBO;
/**
 *
 * @author Benjamin
 */
public class ControlSesion {
    
    private static final Logger LOGGER = Logger.getLogger(ControlSesion.class.getName());
    private EmpleadoBO empleadoBO;
    
    public ControlSesion() {
        // instancia el objeto de negocio que tiene el mock del dao
        this.empleadoBO = new EmpleadoBO();
    }
    
    public EmpleadoDTO validarLogin(LoginDTO credenciales) {
        
        // Validación de seguridad por si el DTO llega vacío
        if (credenciales == null || credenciales.getIdUsuarioTexto() == null) {
            LOGGER.log(Level.WARNING, "Se intentó procesar un login con credenciales nulas.");
            return null;
        }

        try {
            //se extraen los datos del dto
            Long idEmpleado = Long.parseLong(credenciales.getIdUsuarioTexto().trim());
            String password = credenciales.getPassword();
            
            EmpleadoDTO empleado = empleadoBO.validarLogin(idEmpleado, password);
            
            //se tira el log de que el login fue exitoso
            LOGGER.log(Level.INFO, "Login exitoso para el empleado con ID: {0}", idEmpleado);
            return empleado;
            
        } catch (NumberFormatException e) {
            // se tira el log de que uso algo no numerico
            LOGGER.log(Level.WARNING, "Intento de login fallido. El ID ingresado no es numérico: {0}", credenciales.getIdUsuarioTexto());
            return null;
            
        } catch (Exception e) {
            // pasa algo inesperado entonces se tira el log severo
            LOGGER.log(Level.SEVERE, "Error crítico al intentar validar el login", e);
            return null;
        } 
    }
    
}
