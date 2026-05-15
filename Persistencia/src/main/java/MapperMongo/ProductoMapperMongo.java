package MapperMongo;

import Entidades.Medicamento;
import Entidades.Producto;
import EntidadesMongo.MedicamentoMongo;
import EntidadesMongo.ProductoMongo;
import java.util.ArrayList;
/**
 *
 * @author Dario
 */
public class ProductoMapperMongo {
    
    public static Producto entityToDomain(ProductoMongo mongo) {
            if (mongo == null) return null;

            // Si el objeto de la BD es un MedicamentoMongo, creamos un Medicamento de negocio
            if (mongo instanceof MedicamentoMongo medMongo) {
                Medicamento med = new Medicamento();
                med.setIdProducto(medMongo.getIdProducto());
                med.setNombre(medMongo.getNombre());
                med.setPrecio(medMongo.getPrecio());
                med.setImagen(medMongo.getImagen());
                med.setStock(medMongo.getStock());

                // Atributos específicos de Medicamento
                med.setMarca(medMongo.getMarca());
                med.setMedida(medMongo.getMedida());
                med.setDosis(medMongo.getDosis());
                med.setPresentacion(medMongo.getPresentacion());
                med.setEsControlada(medMongo.getEsControlada());
                // Aquí mapeas las especialidades de String a tu Enum si es necesario
                
                if (medMongo.getEspecialidades() != null) {
                    // Al ser ambos List<Especialidades>, pasas la lista directa o creas una nueva copia
                    med.setEspecialidades(new ArrayList<>(medMongo.getEspecialidades()));
                }

                return med;
            } 

            // Si es un producto normal
            Producto prod = new Producto();
            prod.setIdProducto(mongo.getIdProducto());
            prod.setNombre(mongo.getNombre());
            prod.setPrecio(mongo.getPrecio());
            prod.setImagen(mongo.getImagen());
            prod.setStock(mongo.getStock());
            return prod;
        }

        // Convierte de Dominio (Negocio) a Mongo (Persistencia) para cuando vayas a guardar
        public static ProductoMongo domainToEntity(Producto dominio) {
            if (dominio == null) return null;

            if (dominio instanceof Medicamento med) {
                MedicamentoMongo medMongo = new MedicamentoMongo();
                medMongo.setIdProducto(med.getIdProducto());
                medMongo.setNombre(med.getNombre());
                medMongo.setPrecio(med.getPrecio());
                medMongo.setImagen(med.getImagen());
                medMongo.setStock(med.getStock());

                medMongo.setMarca(med.getMarca());
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
            mongo.setIdProducto(dominio.getIdProducto());
            mongo.setNombre(dominio.getNombre());
            mongo.setPrecio(dominio.getPrecio());
            mongo.setImagen(dominio.getImagen());
            mongo.setStock(dominio.getStock());
            return mongo;
        }
}
