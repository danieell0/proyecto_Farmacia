package EntidadesMongo;

import Enums.Especialidades;
import Enums.Medida;
import Enums.TipoProducto;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonDiscriminator;

/**
 * Clase de medicamento que se persiste en la base de datos de mongo.
 * @author Dario
 */
@BsonDiscriminator
public class MedicamentoMongo extends ProductoMongo {
    private Medida medida;
    private Double dosis;
    private String presentacion;
    private Boolean esControlada;
    private List<Especialidades> especialidades;

    /**
     * Contructor vacio.
     */
    public MedicamentoMongo() {
    }

    /**
     * Constructor completo del medicamento
     * @param medida Unidad de medida del medicamento.
     * @param dosis Concentracion de la dosis por unidad.
     * @param presentacion Formato de presentacion del medicamento.
     * @param esControlada Si el producto es controlado.
     * @param especialidades Lista de especialidades del medicamento.
     * @param idProducto ID del producto.
     * @param nombre Nombre del producto.
     * @param marca Laboratorio fabricante o marca del artículo.
     * @param precio Precio del producto.
     * @param imagen Imagen del producto.
     * @param stock Stock disponibles en el inventario.
     * @param tipo Clasificación del producto.
     */
    public MedicamentoMongo(Medida medida, Double dosis, String presentacion, Boolean esControlada, List<Especialidades> especialidades, String idProducto, String nombre, String marca, Double precio, String imagen, Integer stock, TipoProducto tipo) {
        super(idProducto, nombre, marca, precio, imagen, stock, tipo);
        this.medida = medida;
        this.dosis = dosis;
        this.presentacion = presentacion;
        this.esControlada = esControlada;
        this.especialidades = especialidades;
    }

    /** @return Obtiene la unidad de medida del medicamento. */
    public Medida getMedida() {
        return medida;
    }

    /** @param medida La unidad de medida a asignar al medicamento. */
    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    /** @return Obtiene la dosis del medicamento. */
    public Double getDosis() {
        return dosis;
    }

    /** @param dosis La dosis a asignar al medicamento. */
    public void setDosis(Double dosis) {
        this.dosis = dosis;
    }

    /** @return Obtiene la presentacion del medicamento. */
    public String getPresentacion() {
        return presentacion;
    }

    /** @param presentacion La presentacion a asignar al medicamento. */
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    /** @return Obtiene si el medicamento es controlado o no. */
    public Boolean getEsControlada() {
        return esControlada;
    }

    /** @param esControlada El estado de controlado a asignar al medicamento. */
    public void setEsControlada(Boolean esControlada) {
        this.esControlada = esControlada;
    }

    /** @return Obtiene la lista de especialidades autorizadas. */
    public List<Especialidades> getEspecialidades() {
        return especialidades;
    }

    /** @param especialidades Las especialidades a asignar al medicamento. */
    public void setEspecialidades(List<Especialidades> especialidades) {
        this.especialidades = especialidades;
    }

    /** @return Representacion en cadena del medicamento. */
    @Override
    public String toString() {
        return "Medicamento{" + "medida=" + medida + ", dosis=" + dosis + ", presentacion=" + presentacion + ", esControlada=" + esControlada + ", especialidades=" + especialidades + '}';
    }
    
}
