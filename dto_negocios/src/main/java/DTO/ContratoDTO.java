/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import Enums.RolPuesto;
import java.time.LocalDateTime;

/**
 *
 * @author Benjamin
 */
public class ContratoDTO {
    private EmpleadoDTO empleado; 
    private Double salario;
    private String idContrato;
    private RolPuesto rolPuesto;
    private LocalDateTime fecha;

    public ContratoDTO() {}
}
