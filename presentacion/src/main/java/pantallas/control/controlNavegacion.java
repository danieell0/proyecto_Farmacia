/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas.control;

import interfaces.IControlNavegacion;
import javax.swing.JDialog;
import javax.swing.JFrame;
import pantallas.InicioSesionFrm;
import pantallas.VentaFrame;
import pantallas.VentaPuntosFrame;
import pantallas.menuAdmin;
import pantallas.menuFrame;
import pantallas.menuPuntosFrame;
import pantallas.validarRecetaDlg;
import pantallas_gestion_empleados.RegistroSolicitante;

/**
 *
 * @author Benjamin
 */
public class controlNavegacion implements IControlNavegacion{
    // 1. CORRECCIÓN: Nombre de variable estándar para Singleton
    private static controlNavegacion instancia; 
    
    private JFrame frameActual;
    
    // 2. CORRECCIÓN: Siempre usamos la instancia única del Coordinador
    private Coordinador coordinador = Coordinador.getCoordinador(); 
    
    // Constructor privado para obligar a usar el getControlNavegacion()
    private controlNavegacion() {}

    public static controlNavegacion getControlNavegacion() {
        if (instancia == null) {
            instancia = new controlNavegacion();
        }
        return instancia;
    }

    @Override
    public void cambiarPantalla(JFrame nuevoFrame) {
        if (frameActual != null) {
            frameActual.dispose(); // Libera la memoria de la ventana anterior
        }
        frameActual = nuevoFrame;
        nuevoFrame.setLocationRelativeTo(null); // Centra la ventana
        nuevoFrame.setVisible(true);
    }

    @Override
    public void abrirDialog(JDialog nuevoDialog) {
        nuevoDialog.setLocationRelativeTo(frameActual); // Centra el diálogo sobre la ventana actual
        nuevoDialog.setVisible(true);
    }

    // ========================================================
    // MÉTODOS DE NAVEGACIÓN (PANTALLAS NUEVAS - Inyección limpia)
    // ========================================================

    @Override
    public void abrirMenuAdmin() {
        cambiarPantalla(new menuAdmin(this));
    }

    @Override
    public void abrirRegistroSolicitante() {
        cambiarPantalla(new RegistroSolicitante(this));
    }

    // ========================================================
    // MÉTODOS DE NAVEGACIÓN (PANTALLAS ANTIGUAS)
    // Recomendación: A futuro, actualiza los constructores de estas 
    // clases para que reciban (this) igual que las nuevas.
    // ========================================================

    @Override
    public void abrirMenuFrame() {
        cambiarPantalla(new menuFrame());
    }
    
    @Override
    public void abrirMenuPuntosFrame() {
        menuPuntosFrame puntosFrame = new menuPuntosFrame();
        puntosFrame.setCoordinador(this.coordinador);
        cambiarPantalla(puntosFrame);
    }
    
    @Override
    public void abrirVentaFrame() {
        VentaFrame nuevaVenta = new VentaFrame();
        nuevaVenta.setControlNavegacion(this); 
        nuevaVenta.setCoordinador(this.coordinador);
        this.coordinador.setVentaFrame(nuevaVenta);

        // Cargar datos actuales del carrito
        DTO.CarritoDTO carritoGuardado = this.coordinador.obtenerCarritoActual();
        if (carritoGuardado != null) {
            nuevaVenta.actualizarTablaCarrito(carritoGuardado);
        }

        cambiarPantalla(nuevaVenta);
    }
    
    // Asegúrate de que este método esté en tu interfaz IControlNavegacion
    public void abrirVentaPuntosFrame() {
        VentaPuntosFrame nuevaVenta = new VentaPuntosFrame();
        nuevaVenta.setControlNavegacion(this); 
        nuevaVenta.setCoordinador(this.coordinador);
        this.coordinador.setVentaPuntosFrame(nuevaVenta);

        DTO.CarritoDTO carritoGuardado = this.coordinador.obtenerCarritoActual();
        if (carritoGuardado != null) {
            nuevaVenta.actualizarTablaCarrito(carritoGuardado);
        }

        cambiarPantalla(nuevaVenta);
    }
    
    @Override
    public void abrirRecetaDialog() {
        validarRecetaDlg dlg = new validarRecetaDlg(frameActual, true, this.coordinador);
        abrirDialog(dlg);
    }
    
    // Asegúrate de que este método esté en tu interfaz IControlNavegacion
    public void abrirLogin() {
        InicioSesionFrm loginDlg = new InicioSesionFrm(frameActual, this.coordinador);
        abrirDialog(loginDlg);
    }

    @Override
    public void setControlNeavegacion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}