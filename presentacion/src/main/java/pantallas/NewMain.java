/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pantallas;

import pantallas.control.Coordinador;
import pantallas.control.controlNavegacion;



/**
 *
 * @author Jorge
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //se supone que es buena practica el hilo ese?
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                //se abre el login primero para que no se pueda interactuar con el sistema sin tener sesion iniciada
                controlNavegacion.getcontrolNavegacion().abrirLogin();

                // 2. La ventanita se cerro. Revisamos el resultado:
                if (Coordinador.getCoordinador().getEmpleadoLogueado() == null) {
                    System.out.println("Acceso cancelado por el usuario. Apagando sistema...");
                    System.exit(0); // Mata el proceso de Java por completo
                }
                
            }
        });
    }
    
}
