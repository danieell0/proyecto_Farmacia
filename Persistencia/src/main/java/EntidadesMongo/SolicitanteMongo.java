/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import Enums.RolPuesto;
import java.time.LocalDate;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;

/**
 *
 * @author Benjamin
 */
public class SolicitanteMongo {
    // @BsonId le dice a Mongo que este es el campo "_id" principal
    @BsonId
    private String idSolicitante;
    
    // @BsonProperty asegura que el nombre en la BD sea exactamente este
    @BsonProperty("nombre")
    private String nombre;
    
    @BsonProperty("apellidoPaterno")
    private String apellidoPaterno;
    
    @BsonProperty("apellidoMaterno")
    private String apellidoMaterno;
    
    @BsonProperty("telefono")
    private String telefono;
    
    @BsonProperty("rolPuesto")
    private RolPuesto rolPuesto;
    
    @BsonProperty("fechaNacimiento")
    private LocalDate fechaNacimiento;
    
    @BsonProperty("notasEntrevista")
    private String notasEntrevista;

    // Mongo SIEMPRE necesita un constructor vacío
    public SolicitanteMongo() {}

    public String getIdSolicitante() {
        return idSolicitante;
    }

    public void setIdSolicitante(String idSolicitante) {
        this.idSolicitante = idSolicitante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public RolPuesto getRolPuesto() {
        return rolPuesto;
    }

    public void setRolPuesto(RolPuesto rolPuesto) {
        this.rolPuesto = rolPuesto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNotasEntrevista() {
        return notasEntrevista;
    }

    public void setNotasEntrevista(String notasEntrevista) {
        this.notasEntrevista = notasEntrevista;
    }
    
    
}
