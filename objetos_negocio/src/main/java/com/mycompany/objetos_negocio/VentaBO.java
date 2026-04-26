/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.objetos_negocio;

import DTO.VentaDTO;
import java.util.ArrayList;
import java.util.List;

public class VentaBO {

    private static VentaBO instancia; 
    private List<VentaDTO> listaMock; 
    private Long contador; 

    private VentaBO() {
        this.listaMock = new ArrayList<>();
        this.contador = 1L;
    }

    public static VentaBO getInstance() {
        if (instancia == null) {
            instancia = new VentaBO();
        }
        return instancia;
    }
    
    public boolean agregarVenta(VentaDTO nuevaVenta) {
        if (nuevaVenta != null) {
            nuevaVenta.setIdVenta(contador);
            contador++;         
            listaMock.add(nuevaVenta);
            System.out.println("Venta#" + nuevaVenta.getIdVenta() + " registrada con éxito. Total: $" + nuevaVenta.getTotal());
            return true;
        }
        return false;
    }
    }