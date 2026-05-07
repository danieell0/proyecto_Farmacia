package GestorMedico;

/**
 *
 * @author Dario
 */
public interface IValidacionMedico {
    
    public abstract boolean esMedicoAutorizado(String cedula, String especialidad);
    
}
