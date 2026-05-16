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
import Mappers.EmpleadoMapper;
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
    private CuentaAccesoDAO cuentaDAO;
    private EmpleadoDAO empleadoDAO;
    
    // Asumo que tienes un mapper creado, si no, lo haremos manual abajo
    private EmpleadoMapper mapper; 
    
    public EmpleadoBO() {
        this.cuentaDAO = new CuentaAccesoDAO();
        this.empleadoDAO = new EmpleadoDAO();
        this.mapper = new EmpleadoMapper(); 
    }
    
    public EmpleadoDTO validarLogin(String idEmpleado, String contrasena) throws NegocioExcepcion {
        
        // Validaciones iniciales
        if (idEmpleado == null || idEmpleado.isEmpty() || idEmpleado.startsWith("-")) { 
            throw new NegocioExcepcion("El ID ingresado no es válido.");
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new NegocioExcepcion("El campo de contraseña no puede estar vacío.");
        }
        
        // Paso 1: Pedirle a la BD que valide la cuenta
        boolean credencialesCorrectas = cuentaDAO.validarCredenciales(idEmpleado, contrasena);
        
        if (!credencialesCorrectas) {
            throw new NegocioExcepcion("Credenciales incorrectas.");
        }
        
        // Paso 2: Si es correcta, pedirle a la BD la ENTIDAD pura del empleado
        Empleado entidadEmpleado = empleadoDAO.obtenerEmpleadoPorId(idEmpleado);
        
        if (entidadEmpleado == null) {
            throw new NegocioExcepcion("Error de integridad: La cuenta existe pero el empleado no.");
        }
        
        // Validamos estatus usando la entidad
        if (entidadEmpleado.getEmpleadoEstatus() != EstatusEmpleado.ACTIVO) {
            throw new NegocioExcepcion("Acceso denegado: El empleado está inactivo en el sistema.");
        }
        
        // Paso 3: Transformamos la Entidad de la BD a un DTO para la presentacion
        return mapper.toDTO(entidadEmpleado); 
    }
     
}
