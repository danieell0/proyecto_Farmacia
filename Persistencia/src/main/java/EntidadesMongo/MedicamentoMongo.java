/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EntidadesMongo;

import Enums.Especialidades;
import Enums.Medida;
import Enums.TipoProducto;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 *
 * @author Dario
 */
@BsonDiscriminator
public class MedicamentoMongo extends ProductoMongo {

    private Medida medida;
    private Double dosis;
    private String presentacion;
    private Boolean esControlada;
    private List<Especialidades> especialidades;

    public MedicamentoMongo() {
    }

    public MedicamentoMongo(Medida medida, Double dosis, String presentacion, Boolean esControlada, List<Especialidades> especialidades, String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        super(idProducto, nombre, marca, precio, imagen, stock, tipo);
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlada = esControlada;
        this.especialidades = especialidades;
    }

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public Double getDosis() {
        return dosis;
    }

    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public Boolean getEsControlada() {
        return esControlada;
    }

    public void setEsControlada(Boolean esControlada) {
        this.esControlada = esControlada;
    }

    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public String toString() {
        return "MedicamentoMongo{" + "medida=" + medida + ", dosis=" + dosis + ", presentacion=" + presentacion + ", esControlada=" + esControlada + ", especialidades=" + especialidades + '}';
    }
    
}
