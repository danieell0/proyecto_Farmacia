/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.CuentaAcceso;
import EntidadesMongo.CuentaAccesoMongo;

/**
 *
 * @author Benjamin
 */
public class CuentaAccesoMapperMongo {
    
    //convertir de mongo a java
    /**
     * Convierte una entidad que viene de MongoDB a una entidad limpia de Java.
     */
    public static CuentaAcceso aEntidadJava(CuentaAccesoMongo cuentaMongo) {
        // Validación de seguridad por si Mongo no encontró nada
        if (cuentaMongo == null) {
            return null;
        }
        
        // Extraemos los datos y construimos la entidad de Java
        return new CuentaAcceso(
                cuentaMongo.getIDEmpleado(),
                cuentaMongo.getContrasena()
        );
    }

    /**
     * Convierte una entidad de Java a una entidad de MongoDB lista para insertarse.
     */
    public static CuentaAccesoMongo aEntidadMongo(CuentaAcceso cuentaJava) {
        // Validación de seguridad
        if (cuentaJava == null) {
            return null;
        }
        
        // Armamos el objeto con las etiquetas @BsonProperty
        return new CuentaAccesoMongo(
                cuentaJava.getIDEmpleado(),
                cuentaJava.getContrasena()
        );
    }
    
}
