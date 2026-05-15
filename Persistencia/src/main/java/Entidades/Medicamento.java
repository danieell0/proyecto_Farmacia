package Entidades;

import Enums.Especialidades;
import Enums.Medida;
import Enums.TipoProducto;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Medicamento extends Producto {

    private Medida medida;
    private Double dosis;
    private String presentacion;
    private Boolean esControlada;
    private List<Especialidades> especialidades;

    public Medicamento() {
    }

    public Medicamento(Medida medida, Double dosis, String presentacion, Boolean esControlada, List<Especialidades> especialidades, String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
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
        return "Medicamento{" + "medida=" + medida + ", dosis=" + dosis + ", presentacion=" + presentacion + ", esControlada=" + esControlada + ", especialidades=" + especialidades + '}';
    }
    
}
