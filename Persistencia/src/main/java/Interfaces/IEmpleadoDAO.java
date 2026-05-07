/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Interfaces;

import Entidades.Empleado;

/**
 * Clase de data transfer object que se encarga de recuperar la informacion de la 
 * base de datos y pasarsela a lo objetos de negocio a traves de mappers que 
 * convierten las entidades que esta clase dao regresa a dtos que los objetos de negocio
 * reciben
 * @author Benjamin
 */
public interface IEmpleadoDAO {
    
    /**
     * Busca un empleado en la base de datos por su ID.
     * @param idEmpleado El identificador único del empleado.
     * @return La entidad Empleado si se encuentra, null si no existe.
     */
    Empleado obtenerEmpleadoPorId(Long idEmpleado);
    
    
    
}
