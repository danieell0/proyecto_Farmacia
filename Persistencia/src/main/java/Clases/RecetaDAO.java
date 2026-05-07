package Clases;

import Entidades.DetalleReceta;
import Entidades.Medicamento;
import Entidades.Receta;
import Enums.Especialidades;
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

        // --- RECETA 1: ACTIVA Y VÁLIDA ---
        Receta r1 = new Receta();
        r1.setFolio("101L");
        r1.setCedulaMedico("12345678");
        r1.setEstado(EstadoReceta.ACTIVA);
        r1.setFechaCaducidad(LocalDate.of(2026, 12, 31));
        
        List<DetalleReceta> detalles1 = new ArrayList<>();
        detalles1.add(new DetalleReceta(8L, 10, 0)); // Amoxicilina (ID 8)
        detalles1.add(new DetalleReceta(6L, 10, 0)); 
        r1.setDetalles(detalles1);
        recetas.add(r1);
        

        // --- RECETA 2: CADUCADA ---
        Receta r2 = new Receta();
        r2.setFolio("102L");
        r2.setCedulaMedico("87654321");
        r2.setEstado(EstadoReceta.CADUCADA);
        r2.setFechaCaducidad(LocalDate.of(2023, 01, 01)); // Ya venció
        
        List<DetalleReceta> detalles2 = new ArrayList<>();
        detalles2.add(new DetalleReceta(7L, 10, 0)); // Amoxicilina (ID 8)
        r2.setDetalles(detalles2);
        
        recetas.add(r2);

        // --- RECETA 3: SIN USOS (SURTIDA) ---
        Receta r3 = new Receta();
        r3.setFolio("103L");
        r3.setCedulaMedico("55556666");
        r3.setEstado(EstadoReceta.SURTIDA);
        r3.setFechaCaducidad(LocalDate.of(2026, 06, 15));
        
        List<DetalleReceta> detalles3 = new ArrayList<>();
        detalles3.add(new DetalleReceta(7L, 2, 0)); // Pide 2 Amoxicilinas
        r3.setDetalles(detalles3);
        
        recetas.add(r3);
        
        Receta r4 = new Receta();
        r4.setFolio("104L");
        r4.setCedulaMedico("55556666");
        r4.setEstado(EstadoReceta.ACTIVA);
        r4.setFechaCaducidad(LocalDate.of(2026, 12, 31));
        List<DetalleReceta> detalles4 = new ArrayList<>();
        detalles4.add(new DetalleReceta(7L, 10, 0));
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
