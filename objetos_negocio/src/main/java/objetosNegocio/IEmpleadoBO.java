/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package objetosNegocio;

import DTO.EmpleadoDTO;
import excepciones.NegocioExcepcion;

/**
 *
 * @author Benjamin
 */
public interface IEmpleadoBO {
    public EmpleadoDTO validarLogin(String idEmpleado, String contrasena) throws NegocioExcepcion;
    
    
}
