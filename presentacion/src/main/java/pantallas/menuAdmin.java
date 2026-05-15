/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import componentes.PanelEncabezado;
import componentes.panelMenuLateralAdmin;
import componentes.panelMenuLateralEmpleado;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author Jorge
 */
public class menuAdmin extends JFrame {
    public menuAdmin() {
        setTitle("Farmacia admin");
        setSize(1525, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        panelMenuLateralAdmin menu= new panelMenuLateralAdmin();
        menu.setBounds(0,0,110,900);
        add(menu);
        PanelEncabezado encabezado= new PanelEncabezado();
        encabezado.setBounds(110,0,1390,70);
        add(encabezado);

        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

                ImageIcon fondo = new ImageIcon(getClass().getResource("/icons/farmacia.png"));
                      

                g2.drawImage(fondo.getImage(),0,0,getWidth(),getHeight(),this);
                g2.setColor(new Color(255,255,255,170));
                g2.fillRect(0,0,getWidth(),getHeight());
            }
        };

        panelCentral.setBounds(110,70,1390,830);
        panelCentral.setLayout(null);
        add(panelCentral);
        JLabel lblTitulo1 = new JLabel("Bienvenido al");
        lblTitulo1.setBounds(50,70,600,60);
        lblTitulo1.setFont(new Font("Segoe UI",Font.BOLD,52));
        lblTitulo1.setForeground(new Color(0, 77, 64));
        panelCentral.add(lblTitulo1);
        JLabel lblTitulo2 = new JLabel("Panel de Administración");
        lblTitulo2.setBounds(50,140,900,60);
        lblTitulo2.setFont(new Font("Segoe UI",Font.BOLD,52));
        lblTitulo2.setForeground(new Color(0, 77, 64));

        panelCentral.add(lblTitulo2);

        JLabel lblTexto1 = new JLabel("Desde aquí podrás gestionar todos los módulos");
        lblTexto1.setBounds(50,260,700,35);
        lblTexto1.setFont(new Font("Segoe UI",Font.PLAIN,24));
        panelCentral.add(lblTexto1);
        JLabel lblTexto2 = new JLabel("del sistema de farmacia de manera rápida,");
        lblTexto2.setBounds(50,300,700,35);
        lblTexto2.setFont(new Font("Segoe UI",Font.PLAIN,24));
        panelCentral.add(lblTexto2);
        JLabel lblTexto3 = new JLabel("segura y eficiente.");
        lblTexto3.setBounds(50,340,700,35);
        lblTexto3.setFont(new Font("Segoe UI",Font.PLAIN,24));
        panelCentral.add(lblTexto3);
        JLabel lblFrase = new JLabel( "Que tengas un excelente día de trabajo");

        lblFrase.setBounds(50,430,700,40);
        lblFrase.setFont(new Font("Segoe UI",Font.BOLD,24));
        lblFrase.setForeground(new Color(0, 100, 80));

        panelCentral.add(lblFrase);
        
        JPanel panelTarjetas = new JPanel();
        panelTarjetas.setBounds(35,560,1300,220);
        panelTarjetas.setOpaque(false);
        panelTarjetas.setLayout(new GridLayout(1,5,20,20));
        panelCentral.add(panelTarjetas);

        panelTarjetas.add(crearTarjeta("Productos"));
        panelTarjetas.add(crearTarjeta("Entradas"));
        panelTarjetas.add(crearTarjeta("Ventas"));
        panelTarjetas.add(crearTarjeta("Inventario"));
        panelTarjetas.add(crearTarjeta("Reportes"));

        setVisible(true);
    }

    private JPanel crearTarjeta(String titulo) {
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setLayout(null);
        tarjeta.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230),1,true));
        JLabel lblTitulo = new JLabel(titulo,SwingConstants.CENTER);
        lblTitulo.setBounds(10,70,220,35);
        lblTitulo.setFont(new Font("Segoe UI",Font.BOLD,22));

        tarjeta.add(lblTitulo);


        JButton btn = new JButton("➜");
        btn.setBounds(90,145,55,40);
        btn.setFocusPainted(false);
        btn.setBackground(new Color(0, 150, 120));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI",Font.BOLD,18));
        tarjeta.add(btn);

        return tarjeta;
    }

}
