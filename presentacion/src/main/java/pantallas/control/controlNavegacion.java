package pantallas.control;

import javax.swing.JDialog;

import javax.swing.JFrame;
import pantallas.VentaFrame;
import pantallas.menuFrame;
import pantallas.validarRecetaDlg;
import presentacion.InicioSesionFrm;

/**
 * Clase que se encarga de la nevagacion entre pantallas.
 * @author Dario
 */
public class controlNavegacion {
    
    private static controlNavegacion ControlNavegacion;
    private JFrame frameActual;
    private Coordinador coordinador;
    
    public void setCoordinador(Coordinador coordinador) {
        this.coordinador = coordinador;
    }
    
    public void abrirMenuFrame(){
        cambiarPantalla(new menuFrame());
    }
    
    public void abrirVentaFrame(){
        cambiarPantalla(new VentaFrame());
    }
    
    public void abrirRecetaDialog(){
        validarRecetaDlg dlg = new validarRecetaDlg(frameActual, true, coordinador);
        abrirDialog(dlg);
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
    
    public void abrirDialog(JDialog nuevoDialog){
        nuevoDialog.setVisible(true);
    }
    
    /**
     * Crea e instancia el diálogo de inicio de sesión de forma modal.
     */
    public void abrirLogin() {
        // Pasamos del frame actual para que el modal se bloquee correctamente osea se complete el jdialog de iniciar sesion 
        // y se posicione al centro de la ventana que el usuario tiene abierta.
        InicioSesionFrm loginDlg = new InicioSesionFrm(frameActual, pantallas.control.Coordinador.getCoordinador());
        abrirDialog(loginDlg);
    }
    
}
