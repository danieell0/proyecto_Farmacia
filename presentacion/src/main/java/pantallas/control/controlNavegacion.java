package pantallas.control;

import componentes.LoginDialog;
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
    
    /**
     * Abre el pop-up modal de inicio de sesión.
     * @param framePadre El JFrame desde donde se está llamando al login (usualmente 'this' o el frameActual).
     */
    public void solicitarLogin(JFrame framePadre){
        // 1. Instanciamos el diálogo pasándole la ventana padre
        LoginDialog popUpLogin = new LoginDialog(framePadre);
        
        // 2. Lo hacemos visible. 
        // IMPORTANTE: Como LoginDialog es modal, la ejecución del código 
        // se pausará en esta línea hasta que el usuario cierre el pop-up.
        popUpLogin.setVisible(true);
        
        // 3. (Opcional) Aquí el código continuará una vez que el diálogo se cierre.
        // Si tienes una lógica post-login (como actualizar el nombre del usuario en el frame actual),
        // podrías llamarla aquí.
    }
}
