package com.mycompany.objetos_negocio;

import DTO.DetalleRecetaDTO;
import DTO.RecetaDTO;
import Enums.EstadoReceta;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase BO (base de datos temporal del sistema de las recetas).
 * @author Dario
 */
public class RecetaBO {
    
    private List<RecetaDTO> recetas;
    
    public RecetaBO() {
        this.recetas = new ArrayList<>();

        // --- RECETA 1: ACTIVA Y VÁLIDA ---
        RecetaDTO r1 = new RecetaDTO();
        r1.setFolio(101L);
        r1.setCedulaMedico("12345678");
        r1.setEstado(EstadoReceta.ACTIVA);
        r1.setFechaCaducidad(LocalDate.of(2026, 12, 31));
        
        List<DetalleRecetaDTO> detalles1 = new ArrayList<>();
        detalles1.add(new DetalleRecetaDTO(8L, 1, 0)); // Amoxicilina (ID 8)
        r1.setDetalles(detalles1);
        
        recetas.add(r1);

        // --- RECETA 2: CADUCADA ---
        RecetaDTO r2 = new RecetaDTO();
        r2.setFolio(102L);
        r2.setCedulaMedico("87654321");
        r2.setEstado(EstadoReceta.CADUCADA);
        r2.setFechaCaducidad(LocalDate.of(2023, 01, 01)); // Ya venció
        
        List<DetalleRecetaDTO> detalles2 = new ArrayList<>();
        detalles2.add(new DetalleRecetaDTO(8L, 1, 0)); // Amoxicilina (ID 8)
        r2.setDetalles(detalles2);
        
        recetas.add(r2);

        // --- RECETA 3: SIN USOS (SURTIDA) ---
        RecetaDTO r3 = new RecetaDTO();
        r3.setFolio(103L);
        r3.setCedulaMedico("55556666");
        r3.setEstado(EstadoReceta.SURTIDA);
        r3.setFechaCaducidad(LocalDate.of(2026, 06, 15));
        
        List<DetalleRecetaDTO> detalles3 = new ArrayList<>();
        detalles3.add(new DetalleRecetaDTO(8L, 2, 0)); // Pide 2 Amoxicilinas
        r3.setDetalles(detalles3);
        
        recetas.add(r3);
    }
    
    public List<RecetaDTO> getRecetas() {
        return recetas;
    }
    
    
}
