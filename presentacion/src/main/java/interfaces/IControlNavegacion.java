/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package interfaces;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Jorge
 */
public interface IControlNavegacion {
    public void setControlNeavegacion();
    public void abrirMenuFrame();
    public void abrirMenuPuntosFrame();
    public void abrirVentaFrame();
    public void abrirRecetaDialog();
    public void cambiarPantalla(JFrame nuevoFrame);
    public void abrirDialog(JDialog nuevoDialog);
    public void abrirLogin();
    public void abrirMenuAdmin();
    public void abrirRegistroSolicitante();
    public void abrirMenuMovimientos();
    public void abrirEnreadaMovimiento();
    public void abrirSalidaMovimiento();
}
