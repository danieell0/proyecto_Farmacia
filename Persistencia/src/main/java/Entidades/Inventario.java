/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge
 */
public class Inventario {
    private List<PiezaInventario> piesas;

    public Inventario() {
        this.piesas = new ArrayList<>();
    }

    public List<PiezaInventario> getPiesas() {
        return piesas;
    }

    public void setPiesas(List<PiezaInventario> piesas) {
        this.piesas = piesas;
    }
    
}
