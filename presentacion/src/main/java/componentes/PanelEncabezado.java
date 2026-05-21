/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import DTO.EmpleadoDTO;
import DTO.SesionActualDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import pantallas.control.Coordinador;
import pantallas.control.ControlNavegacion;

/**
 *
 * @author Jorge
 */
public class PanelEncabezado extends JPanel {

    public PanelEncabezado() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(0, 70));
        SesionActualDTO usuarioActivo = Coordinador.getCoordinador().getEmpleadoLogueado();
        String saludo = "Atendiendo";
        if (usuarioActivo != null) {
            saludo = "Atendiendo: "
                    + usuarioActivo.getNombreCompleto()
                    + " ("
                    + usuarioActivo.getRol()
                    + ")";
        }
        JLabel titulo = new JLabel(saludo);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(new EmptyBorder(0, 25, 0, 0));
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(new Color(239, 83, 80));
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrarSesion.addActionListener(e -> {
            
            java.awt.Window ventanaPrincipal = SwingUtilities.getWindowAncestor(this);

            
            int respuesta = JOptionPane.showConfirmDialog(
                    ventanaPrincipal,
                    "¿Deseas cerrar sesión?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                // se borra la sesion
                Coordinador.getCoordinador().cerrarSesion();
                
                //se cierra el menu
                if (ventanaPrincipal != null) {
                    ventanaPrincipal.dispose(); 
                }
                
                // se abre el login 
                ControlNavegacion.getControlNavegacion().abrirLogin();
            }
        });
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.add(btnCerrarSesion);
        add(titulo, BorderLayout.WEST);
        add(right, BorderLayout.EAST);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gp = new GradientPaint(
                0,
                0,
                new Color(0, 121, 107),
                getWidth(),
                0,
                new Color(0, 150, 136)
        );
        g2.setPaint(gp);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
}
