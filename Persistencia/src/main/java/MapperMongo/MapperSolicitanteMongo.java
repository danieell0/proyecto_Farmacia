/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Solicitante;
import EntidadesMongo.SolicitanteMongo;

/**
 *
 * @author Benjamin
 */
public class MapperSolicitanteMongo {
    
    public static Solicitante aEntidad(SolicitanteMongo entidadMongo) {
        if (entidadMongo == null) return null;
        
        Solicitante entidad = new Solicitante();
        entidad.setIdSolicitante(entidadMongo.getIdSolicitante());
        entidad.setNombre(entidadMongo.getNombre());
        entidad.setApellidoPaterno(entidadMongo.getApellidoPaterno());
        entidad.setApellidoMaterno(entidadMongo.getApellidoMaterno());
        entidad.setTelefono(entidadMongo.getTelefono());
        entidad.setRolPuesto(entidadMongo.getRolPuesto());
        entidad.setFechaNacimiento(entidadMongo.getFechaNacimiento());
        entidad.setNotasEntrevista(entidadMongo.getNotasEntrevista());
        
        return entidad;
    }

    public static SolicitanteMongo aEntidadMongo(Solicitante entidad) {
        if (entidad == null) return null;
        
        SolicitanteMongo mongo = new SolicitanteMongo();
        // si el id es null el mongo le va a asignar uno nuevo automaticamente
        mongo.setIdSolicitante(entidad.getIdSolicitante()); 
        mongo.setNombre(entidad.getNombre());
        mongo.setApellidoPaterno(entidad.getApellidoPaterno());
        mongo.setApellidoMaterno(entidad.getApellidoMaterno());
        mongo.setTelefono(entidad.getTelefono());
        mongo.setRolPuesto(entidad.getRolPuesto());
        mongo.setFechaNacimiento(entidad.getFechaNacimiento());
        mongo.setNotasEntrevista(entidad.getNotasEntrevista());
        
        return mongo;
    }
}
