package Entidades;

import Enums.Especialidades;
import Enums.Medida;
import Enums.TipoProducto;
import java.util.List;

/**
 * Clase que representa un medicamento dentro del sistema.
 *
 * Hereda de {@link Producto} e incluye información específica relacionada con
 * medicamentos.
 *
 * @author Jorge
 */
public class Medicamento extends Producto {

    //Unidad de medida del medicamento
    private Medida medida;

    //Cantidad de dosis del medicamento
    private Double dosis;

    //Presentación del medicamento
    private String presentacion;

    //Indica si el medicamento es controlado
    private Boolean esControlada;

    //Lista de especialidades médicas relacionadas
    private List<Especialidades> especialidades;

    /**
     * Constructor vacío.
     */
    public Medicamento() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param medida Unidad de medida del medicamento.
     * @param dosis Dosis del medicamento.
     * @param presentacion Presentación del medicamento.
     * @param esControlada Indica si el medicamento es controlado.
     * @param especialidades Especialidades relacionadas con el medicamento.
     * @param idProducto Identificador del producto.
     * @param nombre Nombre del medicamento.
     * @param marca Marca del medicamento.
     * @param precio Precio del medicamento.
     * @param imagen Imagen del medicamento.
     * @param stock Cantidad disponible en stock.
     * @param tipo Tipo de producto.
     */
    public Medicamento(Medida medida, Double dosis, String presentacion, Boolean esControlada, List<Especialidades> especialidades, String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        super(idProducto, nombre, marca, precio, imagen, stock, tipo);
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlada = esControlada;
        this.especialidades = especialidades;
    }

    /**
     * Obtiene la medida del medicamento.
     *
     * @return Medida del medicamento.
     */
    public Medida getMedida() {
        return medida;
    }

    /**
     * Establece la medida del medicamento.
     *
     * @param medida Nueva medida del medicamento.
     */
    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    /**
     * Obtiene la dosis del medicamento.
     *
     * @return Dosis del medicamento.
     */
    public Double getDosis() {
        return dosis;
    }

    /**
     * Establece la dosis del medicamento.
     *
     * @param dosis Nueva dosis del medicamento.
     */
    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    /**
     * Obtiene la presentación del medicamento.
     *
     * @return Presentación del medicamento.
     */
    public String getPresentacion() {
        return presentacion;
    }

    /**
     * Establece la presentación del medicamento.
     *
     * @param presentacion Nueva presentación del medicamento.
     */
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    /**
     * Indica si el medicamento es controlado.
     *
     * @return {@code true} si es controlado, {@code false} en caso contrario.
     */
    public Boolean getEsControlada() {
        return esControlada;
    }

    /**
     * Establece si el medicamento es controlado.
     *
     * @param esControlada Nuevo estado de control del medicamento.
     */
    public void setEsControlada(Boolean esControlada) {
        this.esControlada = esControlada;
    }

    /**
     * Obtiene las especialidades relacionadas con el medicamento.
     *
     * @return Lista de especialidades.
     */
    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    /**
     * Establece las especialidades relacionadas con el medicamento.
     *
     * @param especialidades Nueva lista de especialidades.
     */
    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

    /**
     * Devuelve una representación en cadena del objeto.
     *
     * @return Cadena con los datos del medicamento.
     */
    @Override
    public String toString() {
        return "Medicamento{" + "medida=" + medida + ", dosis=" + dosis + ", presentacion=" + presentacion + ", esControlada=" + esControlada + ", especialidades=" + especialidades + '}';
    }

}
