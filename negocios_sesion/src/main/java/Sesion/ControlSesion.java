/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sesion;


import DTO.CuentaAccesoDTO;
import DTO.EmpleadoDTO;
import DTO.SesionActualDTO;

import excepciones.NegocioExcepcion;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.EmpleadoBO;
/**
 *
 * @author Benjamin
 */
public class ControlSesion{

    private static final Logger LOGGER = Logger.getLogger(ControlSesion.class.getName());
    protected EmpleadoBO empleadoBO;

    protected ControlSesion() {
        // instancia el objeto de negocio que tiene el mock del dao
        this.empleadoBO = new EmpleadoBO();
    }

    protected SesionActualDTO validarLogin(CuentaAccesoDTO credenciales) throws Exception{

        // Validación de seguridad por si el DTO llega vacío
        if (credenciales == null || credenciales.getIDEmpleado() == null) {
            LOGGER.log(Level.WARNING, "Se intentó procesar un login con credenciales nulas.");
            return null;
        }
        try {
            //se extraen los datos del dto
            String idEmpleado = credenciales.getIDEmpleado();
            String password = credenciales.getContraseña();
            EmpleadoDTO empleado = empleadoBO.validarLogin(idEmpleado, password);
            
            if(empleado == null){
                LOGGER.warning("Inicio de sesion fallido para ID: " + idEmpleado);
                return null;
            }
            

            //concatenar el nombre para el dto de la sesion actual
            String nombreCompleto = empleado.getNombre() + " " + empleado.getApellidoPaterno();

            // se crea el dto que es bueno para mantener la sesion sin exponer la contraseña
            SesionActualDTO sesionSegura = new SesionActualDTO(
                    empleado.getIdEmpleado(),
                    nombreCompleto,
                    empleado.getRolPuesto());


                //se tira el log de que el login fue exitoso
            LOGGER.log(Level.INFO, "Login exitoso para el empleado con ID: {0}", idEmpleado);
            return sesionSegura;
            

        } catch (NegocioExcepcion ex) {
            // se tira el log de que uso algo no numerico
            LOGGER.log(Level.SEVERE, "Error del sistema al validar el login", ex);
            return null;
        }
    }    

}   
