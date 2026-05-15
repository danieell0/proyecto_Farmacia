/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MapperMongo;

import Entidades.Medicamento;
import Entidades.Producto;
import Enums.Especialidades;
import Enums.Medida;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Dario
 */
public class ProductoMapperMongo {
//        public Producto fromDocument(Document doc) {
//        if (doc == null) return null;
//
//        String tipo = doc.getString("tipo");
//
//        if ("Medicamento".equalsIgnoreCase(tipo)) {
//
//            Medicamento medicamento = new Medicamento();
//
//            medicamento.setIdProducto(doc.getLong("idProducto"));
//            medicamento.setNombre(doc.getString("nombre"));
//            medicamento.setPrecio(doc.getDouble("precio"));
//            medicamento.setImagen(doc.getString("imagen"));
//            medicamento.setStock(doc.getInteger("stock"));
//            medicamento.setMarca(doc.getString("marca"));
//            medicamento.setMedida(Medida.valueOf(doc.getString("medida")));
//            medicamento.setDosis(doc.getDouble("dosis"));
//            medicamento.setPresentacion(doc.getString("presentacion"));
//
//            medicamento.setEsControlado(doc.getBoolean("escontrolado", false));
//
//            List<String> espStrings = doc.getList("especialidades", String.class);
//            if (espStrings != null) {
//                medicamento.setEspecialidades(
//                    espStrings.stream()
//                        .map(Especialidades::valueOf)
//                        .toList()
//                );
//            }
//
//            return medicamento;
//        }
//
//        Producto dto = new Producto();
//        dto.setIdProducto(doc.getLong("idProducto"));
//        dto.setNombre(doc.getString("nombre"));
//        dto.setPrecio(doc.getDouble("precio"));
//        dto.setImagen(doc.getString("imagen"));
//        dto.setStock(doc.getInteger("stock"));
//
//        return dto;
//    }
}
