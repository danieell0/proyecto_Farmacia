package DTO;

import Enums.Especialidades;
import Enums.Medida;
import Enums.TipoProducto;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO que representa un medicamento.
 *
 * @author Dario
 */
public class MedicamentoDTO extends ProductoDTO {

    private Medida medida;
    private Double dosis;
    private String presentacion;
    private Boolean esControlado;
    private List<Especialidades> especialidades;

    /**
     * Contructor vacio.
     */
    public MedicamentoDTO() {

    }

    public MedicamentoDTO(Medida medida, Double dosis, String presentacion, Boolean esControlado, List<Especialidades> especialidades, String idProducto, String nombre, String marca, Double precio, Integer stock, String imagen, TipoProducto tipo) {
        super(idProducto, nombre, marca, precio, stock, imagen, tipo);
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlado = esControlado;
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

    public Boolean getEsControlado() {
        return esControlado;
    }

    public void setEsControlado(Boolean esControlado) {
        this.esControlado = esControlado;
    }

    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

    @Override
    public String toString() {
        return "MedicamentoDTO{" + "medida=" + medida + ", dosis=" + dosis + ", presentacion=" + presentacion + ", esControlado=" + esControlado + ", especialidades=" + especialidades + '}';
    }
    
}
