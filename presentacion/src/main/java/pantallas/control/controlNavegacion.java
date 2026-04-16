package pantallas.control;

import javax.swing.JFrame;
import pantallas.menuFrame;

/**
 * Clase que se encarga de la nevagacion entre pantallas.
 * @author Dario
 */
public class controlNavegacion {
    private JFrame frameActual;
    
    /**
     * Cierra la pantalla actual y abre menuFrame.
     */
    public void abrirClientesForm(){
        cambiarPantalla(new menuFrame());
    }
    
    /**
     * Cierra la pantalla actual y abre no se como le pongas a la pantalla.
     */
    public void abrirFrame(){
        cambiarPantalla(new menuFrame());
    }
    
    /**
     * Realiza el cambio de pantallas, cierra el frame actual y centra el nuevo.
     * @param nuevo El nuevo JFrame a mostrar.
     */
    public void cambiarPantalla(JFrame nuevo){
        if (frameActual != null) {
            frameActual.dispose();
        }
        this.frameActual = nuevo;
        this.frameActual.setVisible(true);
        this.frameActual.setLocationRelativeTo(null);
    }
}
