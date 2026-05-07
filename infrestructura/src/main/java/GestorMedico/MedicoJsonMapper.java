package GestorMedico;

import DTO.Especialidad;
import DTO.MedicoDTO;

/**
 *
 * @author Dario
 */
public class MedicoJsonMapper {
    
    public static boolean interpretarRespuesta(String json) {
        if (json == null) {
            return false;   
        }
        return json.toLowerCase().contains("true");
    }
    
    public static MedicoDTO jsonAMedicoDTO(String json) {
        if (json == null || json.equals("null")) return null;

        String limpia = json.replace("{", "").replace("}", "").replace("\"", "");
        String[] pares = limpia.split(",");

        String cedula = "", nombre = "", especialidadString = ""; 
        Boolean permisos = false;

        for (String par : pares) {
            String[] datos = par.split(":");
            String llave = datos[0].trim();
            String valor = datos[1].trim();

            switch (llave) {
                case "cedula": cedula = valor; break;
                case "nombre": nombre = valor; break;
                case "especialidad": especialidadString = valor; break;
                case "permisos": permisos = Boolean.valueOf(valor); break;
            }
        }
        Especialidad especialidad = null;
        try {
            especialidad = Especialidad.valueOf(especialidadString.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Especialidad no reconocida: " + especialidadString);
        }
        
        return new MedicoDTO(cedula, nombre, especialidad, permisos);
    }
    
}
