/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import Enums.EstatusEmpleado;
import Enums.RolPuesto;
import DTO.CuentaAccesoDTO;
import DTO.EmpleadoDTO;
import excepciones.NegocioExcepcion;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de business object que hace las reglas de negocio relacionada a un empleado
 * @author Benjamin
 */
public class EmpleadoBO {
    
    // Simulación de nuestras tablas en la base de datos
    private List<EmpleadoDTO> tablaEmpleados;
    private List<CuentaAccesoDTO> tablaCuentas;
    
    public EmpleadoBO() {
        tablaEmpleados = new ArrayList<>();
        tablaCuentas = new ArrayList<>();
        
        // --- 1. Llenamos la tabla de Empleados ---
        // Asumo que tus enums se llaman algo así como RolPuesto.LIDER y EstatusEmpleado.ACTIVO
        EmpleadoDTO emp1 = new EmpleadoDTO(123L, "Juan", "Perez", "Gomez", "555-0001", 
                                           RolPuesto.LIDER, LocalDateTime.of(1990, 5, 20, 0, 0), EstatusEmpleado.ACTIVO);
                                           
        EmpleadoDTO emp2 = new EmpleadoDTO(456L, "Maria", "Lopez", "Diaz", "555-0002", 
                                           RolPuesto.CAJERO, LocalDateTime.of(1995, 8, 15, 0, 0), EstatusEmpleado.ACTIVO);
                                           
        EmpleadoDTO emp3 = new EmpleadoDTO(789L, "Carlos", "Ruiz", "Soto", "555-0003", 
                                           RolPuesto.CAJERO, LocalDateTime.of(1998, 2, 10, 0, 0), EstatusEmpleado.DESACTIVO);
        
        tablaEmpleados.add(emp1);
        tablaEmpleados.add(emp2);
        tablaEmpleados.add(emp3);

        // --- 2. Llenamos la tabla de Cuentas de Acceso ---
        // Parámetros: (IDEmpleado, IDReporte, contraseña)
        tablaCuentas.add(new CuentaAccesoDTO(123L, 1L, "admin"));
        tablaCuentas.add(new CuentaAccesoDTO(456L, 2L, "caja"));
        tablaCuentas.add(new CuentaAccesoDTO(789L, 3L, "caja2"));
    }
    
    public EmpleadoDTO validarLogin(Long idEmpleado, String contrasena) throws NegocioExcepcion {
        
        // Validaciones iniciales
        if (idEmpleado == null || idEmpleado <= 0) { 
            throw new NegocioExcepcion("El ID ingresado no es válido.");
        }
        if (contrasena == null || contrasena.trim().isEmpty()) {
            throw new NegocioExcepcion("El campo de contraseña no puede estar vacío.");
        }
        
        // Paso 1: Verificar en la tabla de cuentas si las credenciales coinciden
        boolean credencialesCorrectas = false;
        for (CuentaAccesoDTO cuenta : tablaCuentas) {
            // Usamos tus getters exactos: getIDEmpleado() y getContraseña()
            if (cuenta.getIDEmpleado().equals(idEmpleado) && cuenta.getContraseña().equals(contrasena)) {
                credencialesCorrectas = true;
                break;
            }
        }
        
        if (!credencialesCorrectas) {
            throw new NegocioExcepcion("Credenciales incorrectas.");
        }
        
        // Paso 2: Si la contraseña es correcta, buscamos al empleado para ver su estatus y rol
        for (EmpleadoDTO empleado : tablaEmpleados) {
            if (empleado.getID().equals(idEmpleado)) {
                
                // Aprovechamos tu DTO para validar si el empleado sigue trabajando en la farmacia
                if (empleado.getEstatus() != EstatusEmpleado.ACTIVO) {
                    throw new NegocioExcepcion("Acceso denegado: El empleado está inactivo en el sistema.");
                }
                
                // Retornamos el rol (convertimos el Enum a String)
                return empleado;
            }
        }
        
        // Por si existe la cuenta pero por algún error de base de datos se borró al empleado
        throw new NegocioExcepcion("Error de integridad");
    }
     
}
