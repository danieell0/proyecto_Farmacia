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

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                //se abre el login primero para que no se pueda interactuar con el sistema sin tener sesion iniciada
                controlNavegacion.getcontrolNavegacion().abrirLogin();

                //123
                //admin
                //ok
            }
        });

    }
    
}
