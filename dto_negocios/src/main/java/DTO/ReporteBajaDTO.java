/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.TipoBaja;
import java.time.LocalDateTime;

/**
 * Clase data transfer object para representar el registro de un reporte de una baja de un empleado en el sistema
 * @author Benjamin
 */
public class ReporteBajaDTO {
    
    private Long IDEmpleado;
    private TipoBaja tipoBaja;
    private String descripcion;
    private LocalDateTime fechaBaja;
    
}
