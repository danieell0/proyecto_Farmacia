package MapperMongo;

import Entidades.Medicamento;
import Entidades.Producto;
import EntidadesMongo.MedicamentoMongo;
import EntidadesMongo.ProductoMongo;
import java.util.ArrayList;

/**
 * Clase Mapper para convertir un producto a mongo.
 * @author Dario
 */
public class ProductoMapperMongo {
    
    /**
     * Convierte un ProductoMongo a una entidad Producto.
     * @param mongo ProductoMongo a convertir.
     * @return Producto.
     */
    public static Producto entityToDomain(ProductoMongo mongo) {
        if (mongo == null) return null;
        if (mongo instanceof MedicamentoMongo medMongo) {
            Medicamento med = new Medicamento();
            med.setIdProducto(medMongo.getIdProducto());
            med.setNombre(medMongo.getNombre());
            med.setPrecio(medMongo.getPrecio());
            med.setImagen(medMongo.getImagen());
            med.setStock(medMongo.getStock());
            med.setTipo(medMongo.getTipo());
            med.setMarca(medMongo.getMarca());
            //Medicamento
            med.setMedida(medMongo.getMedida());
            med.setDosis(medMongo.getDosis());
            med.setPresentacion(medMongo.getPresentacion());
            med.setEsControlada(medMongo.getEsControlada());               
            if (medMongo.getEspecialidades() != null) {
                med.setEspecialidades(new ArrayList<>(medMongo.getEspecialidades()));
            }
            return med;
        } 
        Producto prod = new Producto();
        prod.setIdProducto(mongo.getIdProducto());
        prod.setNombre(mongo.getNombre());
        prod.setMarca(mongo.getMarca());
        prod.setTipo(mongo.getTipo());
        prod.setPrecio(mongo.getPrecio());
        prod.setImagen(mongo.getImagen());
        prod.setStock(mongo.getStock());
         return prod;
    }

    /**
     * Convierte una entidad Producto a un ProductoMongo
     * @param producto producto a convertir.
     * @return ProductoMongo.
     */
    public static ProductoMongo domainToEntity(Producto producto) {
        if (producto == null) return null;
        if (producto instanceof Medicamento med) {
            MedicamentoMongo medMongo = new MedicamentoMongo();
            medMongo.setIdProducto(med.getIdProducto());
            medMongo.setNombre(med.getNombre());
            medMongo.setPrecio(med.getPrecio());
            medMongo.setImagen(med.getImagen());
            medMongo.setStock(med.getStock());
            medMongo.setMarca(med.getMarca());
            medMongo.setTipo(med.getTipo());
            //Medicamento
            medMongo.setMedida(med.getMedida());
            medMongo.setDosis(med.getDosis());
            medMongo.setPresentacion(med.getPresentacion());
            medMongo.setEsControlada(med.getEsControlada());
            if (med.getEspecialidades() != null) {
                medMongo.setEspecialidades(new ArrayList<>(med.getEspecialidades()));
            }
            return medMongo;
        }
        ProductoMongo mongo = new ProductoMongo();
        mongo.setIdProducto(producto.getIdProducto());
        mongo.setNombre(producto.getNombre());
        mongo.setMarca(producto.getMarca());
        mongo.setTipo(producto.getTipo());
        mongo.setPrecio(producto.getPrecio());
        mongo.setImagen(producto.getImagen());
        mongo.setStock(producto.getStock());
        return mongo;
    }
}
