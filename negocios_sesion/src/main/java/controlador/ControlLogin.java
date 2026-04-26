/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import excepciones.NegocioExcepcion;
import objetosNegocio.EmpleadoBO;

/**
 * Clase de control que controla el flujo interno de inicio de sesion
 * @author Benjamin
 */
public class ControlLogin {
    
    public ControlLogin(){}
    
    /**
     * Procesa la logica para iniciar sesion coordinand con los objetos de negocio
     * @param idEmpleado
     * @param contraseña
     * @return
     * @throws NegocioExcepcion 
     */
    public String procesarInicioSesion(Long idEmpleado, String contraseña) throws NegocioExcepcion {
        
        // 1. Instanciamos el Objeto de Negocio 
        EmpleadoBO empleadoBO = new EmpleadoBO();
        
        // 2. Ejecutamos la validación real de las reglas de negocio.
        // Si el EmpleadoBO detecta un error (ID vacío, contraseña incorrecta), 
        // lanzará la NegocioExcepcion. Como este método también tiene el "throws",
        // la excepción "subirá" automáticamente por la Fachada hasta llegar a tu LoginDialog.
        String rol = empleadoBO.validarLogin(idEmpleado, contraseña);
        
        // 3. (Opcional a futuro) Aquí podrías agregar más lógica de orquestación si lo necesitas.
        // Por ejemplo: llamar a otro BO para registrar en una bitácora la hora de entrada,
        // o construir un objeto DTO completo en lugar de solo devolver un String.
        
        // 4. Retornamos el resultado a la Fachada
        return rol;
    }
    
}
