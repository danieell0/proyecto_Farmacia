/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import Clases.CuentaAccesoDAO;
import Clases.EmpleadoDAO;
import Enums.EstatusEmpleado;
import Enums.RolPuesto;
import DTO.CuentaAccesoDTO;
import DTO.EmpleadoDTO;
import Entidades.Empleado;
import Interfaces.ICuentaAccesoDAO;
import Interfaces.IEmpleadoDAO;
import Mappers.MapperEmpleadoNegocio;
import excepciones.NegocioExcepcion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de business object que hace las reglas de negocio relacionada a un empleado
 * @author Benjamin
 */
public class EmpleadoBO {
    
    // Instanciamos los accesos a datos
    private ICuentaAccesoDAO cuentaDAO;
    private IEmpleadoDAO empleadoDAO;
    
    private MapperEmpleadoNegocio mapper; 
    
    public EmpleadoBO() {
        this.cuentaDAO = new CuentaAccesoDAO();
        this.empleadoDAO = new EmpleadoDAO();
        this.mapper = new MapperEmpleadoNegocio(); 
    }
    
    public EmpleadoDTO validarLogin(String idEmpleado, String contrasena) throws NegocioExcepcion {
        
        // validaciones basicas
        if (idEmpleado == null || idEmpleado.isEmpty() || idEmpleado.startsWith("-")) { 
            throw new NegocioExcepcion("El ID ingresado no es válido.");
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new NegocioExcepcion("El campo de contraseña no puede estar vacío.");
        }
        
        // se le pide a la bd que valide la cuenta
        boolean credencialesCorrectas = cuentaDAO.validarCredenciales(idEmpleado, contrasena);
        
        if (!credencialesCorrectas) {
            throw new NegocioExcepcion("Credenciales incorrectas.");
        }
        
        // si es correcta se le pide la entidad pura a la bd
        Empleado entidadEmpleado = empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
        
        if (entidadEmpleado == null) {
            throw new NegocioExcepcion("Error de integridad: La cuenta existe pero el empleado no.");
        }
       
        // se valida el estatus usando la entidad
        if (entidadEmpleado.getEmpleadoEstatus() != EstatusEmpleado.ACTIVO) {
            throw new NegocioExcepcion("Acceso denegado: El empleado está inactivo en el sistema.");
        }
        
        // se transforma a dto para enviarselo a presentacion
        return mapper.aDTO(entidadEmpleado); 
    }
     
}
