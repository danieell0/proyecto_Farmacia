/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Empleado;
import EntidadesMongo.EmpleadoMongo;

/**
 *
 * @author Benjamin
 */
public class EmpleadoMapperMongo {
    /**
     * Convierte una entidad que viene de MongoDB a una entidad limpia de Java.
     */
    public static Empleado aEntidadJava(EmpleadoMongo empleadoMongo) {
        // Validación de seguridad
        if (empleadoMongo == null) {
            return null;
        }

        // Extraemos los datos de Mongo y construimos la entidad de Java
        return new Empleado(
                empleadoMongo.getIdEmpleado(),
                empleadoMongo.getNombre(),
                empleadoMongo.getApellidoPaterno(),
                empleadoMongo.getApellidoMaterno(),
                empleadoMongo.getTelefono(),
                empleadoMongo.getRolPuesto(),
                empleadoMongo.getFechaNacimiento(),
                empleadoMongo.getEmpleadoEstatus()
        );
    }

    /**
     * Convierte una entidad de Java a una entidad de MongoDB lista para insertarse.
     */
    public static EmpleadoMongo aEntidadMongo(Empleado empleadoJava) {
        // Validación de seguridad
        if (empleadoJava == null) {
            return null;
        }

        // Armamos el objeto Mongo (el que tiene el @BsonDiscriminator)
        return new EmpleadoMongo(
                empleadoJava.getIdEmpleado(),
                empleadoJava.getNombre(),
                empleadoJava.getApellidoPaterno(),
                empleadoJava.getApellidoMaterno(),
                empleadoJava.getTelefono(),
                empleadoJava.getRolPuesto(),
                empleadoJava.getFechaNacimiento(),
                empleadoJava.getEmpleadoEstatus()
        );
    }
}
