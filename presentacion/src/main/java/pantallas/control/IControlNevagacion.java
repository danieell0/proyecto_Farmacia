/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pantallas.control;

import javax.swing.JDialog;
import javax.swing.JFrame;

/**
 *
 * @author Jorge
 */
public interface IControlNevagacion {

    public void setCoordinador(Coordinador coordinador);

    public void abrirMenuFrame();

    
    public void abrirVentaFrame();

    
    public void abrirRecetaDialog();

    
    public void cambiarPantalla(JFrame nuevoFrame);

    
    public void abrirDialog(JDialog nuevoDialog);
}
