package pantallas.control;

import javax.swing.JFrame;
import pantallas.VentaFrame;
import pantallas.menuFrame;

/**
 * Clase que se encarga de la nevagacion entre pantallas.
 * @author Dario
 */
public class controlNavegacion {
    
    private static controlNavegacion ControlNavegacion;
    private JFrame frameActual;
    
    /**
     * Cierra la pantalla actual y abre no se como le pongas a la pantalla.
     */
    public void abrirMenuFrame(){
        cambiarPantalla(new menuFrame());
    }
    
    public void abrirVentaFrame(){
        cambiarPantalla(new VentaFrame());
    }
    
    public static controlNavegacion getcontrolNavegacion() {
        if (ControlNavegacion == null) {
            ControlNavegacion = new controlNavegacion();
        }
        return ControlNavegacion;
    }
    public void cambiarPantalla(JFrame nuevoFrame) {
        if (frameActual != null) {
            frameActual.dispose();
        }

        frameActual = nuevoFrame;

        nuevoFrame.setVisible(true);
        nuevoFrame.setLocationRelativeTo(null);
    }
    
}
