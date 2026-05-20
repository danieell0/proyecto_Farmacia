/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import pantallas.control.Coordinador;
import pantallas.control.controlNavegacion;

/**
 *
 * @author Jorge
 */
public class panelMenuLateralEmpleado extends JPanel {

    public panelMenuLateralEmpleado() {

        setPreferredSize(new Dimension(110, 0));
        setBackground(new Color(0, 121, 107));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(Box.createVerticalStrut(20));

        ImageIcon iconLogo = new ImageIcon(
                getClass().getResource("/icons/logo.png")
        );
        Image imgLogo = iconLogo.getImage().getScaledInstance(
                45,
                45,
                Image.SCALE_SMOOTH
        );

        JLabel logo = new JLabel(new ImageIcon(imgLogo));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(logo);
        add(Box.createVerticalStrut(50));

        String[] iconos = {
            "/icons/venta.png",
            "/icons/puntos.png"
        };

        String[] tooltips = {
            "Ventas",
            "Puntos"
        };

        for (int i = 0; i < iconos.length; i++) {
            final int index = i;
            ImageIcon icon = new ImageIcon(getClass().getResource(iconos[index]));
            Image img = icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);

            JButton btn = new JButton(new ImageIcon(img));
            btn.setToolTipText(tooltips[i]);
            btn.setMaximumSize(new Dimension(60, 60));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setOpaque(true);
                    btn.setBackground(new Color(0, 150, 136));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setOpaque(false);
                    btn.setBackground(null);
                }
            });
            
            btn.addActionListener(e -> {
                // 1. Cerramos la ventana en la que está montada actualmente este menú
                Window ventana = SwingUtilities.getWindowAncestor(this);
                
                switch (tooltips[index]) {
//                  case "Ventas":
//                        // Utiliza tu control de navegación global
//                        controlNavegacion.getcontrolNavegacion().abrirMenuFrame(); 
//                        break;
                        
                    case "Puntos":
                    String idCliente = JOptionPane.showInputDialog(
                        this,
                        "Ingrese el ID o Cédula del Cliente:",
                        "Validar Cliente",
                        JOptionPane.QUESTION_MESSAGE
                    );
                    
                    String idLimpio = idCliente.trim();
                    
                    if (idLimpio.isEmpty() || "0".equals(idLimpio)) {
                        return;
                    }
                    if (!Coordinador.getCoordinador().setClientePorId(idCliente.trim())) {
                        JOptionPane.showMessageDialog(this, "Cliente no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    
                    if (ventana != null) {
                        ventana.dispose();
                    }
                    controlNavegacion.getcontrolNavegacion().abrirMenuPuntosFrame();
                    break;
                }
            });
            
            add(btn);
            add(Box.createVerticalStrut(20));
        }

    }

}
