package GestorMedico;

import Enums.Especialidades;


/**
 *
 * @author Dario
 */
public interface IValidacionMedico {
    
    public abstract boolean esMedicoAutorizado(String cedula, Especialidades especialidad);
    
}
