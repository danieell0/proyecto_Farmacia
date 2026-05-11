package Clases;

import Entidades.DetalleReceta;
import Entidades.Medicamento;
import Entidades.Receta;
import Enums.EstadoReceta;
import Interfaces.IRecetaDAO;
import java.time.LocalDate;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dario
 */
public class RecetaDAO implements IRecetaDAO{
    
    private List<Receta> recetas;
    private List<Medicamento> medicamentos;
    
    public RecetaDAO() {
        this.recetas = new ArrayList<>();
        this.medicamentos = new ArrayList<>();

        // RECETA 1: FUNCIONA TODO
        Receta r1 = new Receta();
        r1.setFolio("101L");
        r1.setCedulaMedico("MG01"); // Dr. Juan Perez (ACTIVO)
        r1.setEstado(EstadoReceta.ACTIVA);
        r1.setFechaCaducidad(LocalDate.of(2026, 12, 31));

        List<DetalleReceta> detalles1 = new ArrayList<>();
        detalles1.add(new DetalleReceta(8L, 10, 0)); // Medicamento A
        detalles1.add(new DetalleReceta(6L, 10, 0)); // Medicamento B
        r1.setDetalles(detalles1);
        recetas.add(r1);
        

        // RECETA 2: SOLO FUNCIONA UNO (Especialidad cruzada)
        Receta r2 = new Receta();
        r2.setFolio("102L");
        r2.setCedulaMedico("CA01"); // Dr. Valentin Fuster (Cardiólogo)
        r2.setEstado(EstadoReceta.ACTIVA);
        r2.setFechaCaducidad(LocalDate.of(2026, 12, 31));

        List<DetalleReceta> detalles2 = new ArrayList<>();
        detalles2.add(new DetalleReceta(1L, 5, 0)); // Supongamos ID 1 = Cardio (PASA)
        detalles2.add(new DetalleReceta(9L, 5, 0)); // Supongamos ID 9 = Oncología (FALLA)
        r2.setDetalles(detalles2);
        recetas.add(r2);

        // RECETA 3: NO FUNCIONA (Médico con permisos en False)
        Receta r3 = new Receta();
        r3.setFolio("103L");
        r3.setCedulaMedico("PS03"); // Dr. Carl Jung (Tiene permiso 0 en MySQL)
        r3.setEstado(EstadoReceta.ACTIVA);
        r3.setFechaCaducidad(LocalDate.of(2026, 06, 15));

        List<DetalleReceta> detalles3 = new ArrayList<>();
        detalles3.add(new DetalleReceta(4L, 2, 0)); // Medicamento de Psiquiatría
        r3.setDetalles(detalles3);
        recetas.add(r3);
        
        // RECETA 4: NO FUNCIONA (Validación de Negocio: Caducada)
        Receta r4 = new Receta();
        r4.setFolio("104L");
        r4.setCedulaMedico("ON01"); // Dr. House (Válido y Activo)
        r4.setEstado(EstadoReceta.CADUCADA); // El estado bloquea la operación
        r4.setFechaCaducidad(LocalDate.of(2023, 12, 31)); // Fecha vencida

        List<DetalleReceta> detalles4 = new ArrayList<>();
        detalles4.add(new DetalleReceta(5L, 10, 0)); // Medicamento de Oncología
        r4.setDetalles(detalles4);
        recetas.add(r4);
    }
    
    public List<Receta> getRecetas() {
        return recetas;
    }

    @Override
    public Receta obtenerRecetaPorFolio(String folio) {
        for (int i = 0; i < recetas.size(); i++) {
            Receta receta = recetas.get(i);
            if (receta.getFolio().equals(folio)) {
                return receta;
            }
        }
        return null;
    }

    @Override
    public void actualizarEstadoReceta(Receta receta) {
        Receta recetaActualizada = obtenerRecetaPorFolio(receta.getFolio());
        if (recetaActualizada != null) {
            recetaActualizada.setEstado(receta.getEstado());
            recetaActualizada.setUsos(receta.getUsos());
        }
    }

    @Override
    public void restarMedicamentos(Receta receta, Long idMedicamento, Integer cantidad) {
        Receta recetaRestar = obtenerRecetaPorFolio(receta.getFolio());
        if (recetaRestar != null && recetaRestar.getDetalles() != null) {
            for (DetalleReceta detalle : recetaRestar.getDetalles()) {
                if (detalle.getIdMedicamento().equals(idMedicamento)) {
                    int nuevaCantidad = detalle.getCantidadSurtida() - cantidad;
                    if (nuevaCantidad < 0) {
                        detalle.setCantidadSurtida(0);
                    } else {
                        detalle.setCantidadSurtida(nuevaCantidad);
                    }
                    return;
                }
            }
        }
    }

    @Override
    public void sumarMedicamentos(Receta receta, Long idMedicamento, Integer cantidad) {
        Receta recetaSumar = obtenerRecetaPorFolio(receta.getFolio());
        if (recetaSumar != null && recetaSumar.getDetalles() != null) {
            for (DetalleReceta detalle : recetaSumar.getDetalles()) {
                if (detalle.getIdMedicamento().equals(idMedicamento)) {
                    detalle.setCantidadSurtida(detalle.getCantidadSurtida() + cantidad);
                    return;
                }
            }
        }
    }
    
}
