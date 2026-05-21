/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pantallas;

import interfaces.IControlNavegacion;
import pantallas.control.controlNavegacion;

/**
 *
 * @author Jorge
 */
public class admin {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // 1. Despertamos al "Jefe de Navegación" usando el Patrón Singleton
        controlNavegacion controlador = controlNavegacion.getControlNavegacion();
        
        // 2. Le damos la orden de arrancar el sistema abriendo la primera pantalla
        controlador.abrirMenuAdmin();
    }
    
}
