/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import componentes.PanelEncabezado;
import componentes.panelMenuLateralAdmin;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 *
 * @author Jorge
 */
public class menuEntradasSalidas extends JFrame {

    public menuEntradasSalidas() {
        setTitle("Gestionar Inventario");
        setSize(1525, 900);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(245, 245, 245));
        panelMenuLateralAdmin menu = new panelMenuLateralAdmin();
        menu.setBounds(0, 0, 110, 900);
        add(menu);
        PanelEncabezado encabezado = new PanelEncabezado();
        encabezado.setBounds(110, 0, 1390, 70);
        add(encabezado);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(new Color(248, 250, 250));
        panelCentral.setBounds(110, 70, 1390, 830);
        add(panelCentral);

        JLabel lblPregunta = new JLabel("¿Qué deseas hacer?");
        lblPregunta.setBounds(350, 60, 700, 70);

        lblPregunta.setHorizontalAlignment(SwingConstants.CENTER);

        lblPregunta.setForeground(new Color(0, 77, 64));
        lblPregunta.setFont(new Font("Segoe UI", Font.BOLD, 52));

        panelCentral.add(lblPregunta);

        JLabel lblSubtitulo = new JLabel("Selecciona la acción que deseas realizar con el inventario");
        lblSubtitulo.setBounds(250, 140, 900, 40);
        lblSubtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblSubtitulo.setForeground(Color.GRAY);
        lblSubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 28));
        panelCentral.add(lblSubtitulo);

        JPanel tarjetaEntrada = crearTarjeta(
                "/icons/entrada.png",
                "Registrar entrada",
                "Registrar la entrada de nuevos",
                "medicamentos al inventario",
                new Color(0, 90, 70),
                new Color(0, 130, 100));
        tarjetaEntrada.setBounds(120, 250, 500, 500);
        panelCentral.add(tarjetaEntrada);

        JPanel tarjetaSalida = crearTarjeta(
                "/icons/salida.png",
                "Registrar salida",
                "Registrar la salida de",
                "medicamentos al inventario",
                new Color(30, 70, 200),
                new Color(50, 100, 230)
        );

        tarjetaSalida.setBounds(760, 250, 500, 500);
        panelCentral.add(tarjetaSalida);
        setVisible(true);
    }

    private JPanel crearTarjeta(String rutaIcono,String titulo,String texto1,String texto2,Color colorTitulo,Color colorBoton) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(null);
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(new LineBorder(new Color(235, 235, 235),2,true));
        JLabel lblIcono = new JLabel();
        lblIcono.setBounds(120,20,260,260);
        ImageIcon icon = new ImageIcon(getClass().getResource(rutaIcono));
        Image img = icon.getImage().getScaledInstance(220,220,Image.SCALE_SMOOTH);
        lblIcono.setIcon(new ImageIcon(img));
        lblIcono.setHorizontalAlignment(SwingConstants.CENTER);
        tarjeta.add(lblIcono);
        
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setBounds(70,255,360,40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setForeground(colorTitulo);
        lblTitulo.setFont(new Font("Segoe UI",Font.BOLD,34));
        tarjeta.add(lblTitulo);
        
        JPanel linea = new JPanel();
        linea.setBounds(190,320,120,4);
        linea.setBackground(colorBoton);
        tarjeta.add(linea);
        
        JLabel descripcion1 = new JLabel(texto1);
        descripcion1.setBounds(60,360,380,30);
        descripcion1.setHorizontalAlignment(SwingConstants.CENTER);
        descripcion1.setForeground(Color.GRAY);
        descripcion1.setFont(new Font("Segoe UI",Font.PLAIN,20));
        tarjeta.add(descripcion1);
        
        JLabel descripcion2 = new JLabel(texto2);
        descripcion2.setBounds(60,395,380,30);
        descripcion2.setHorizontalAlignment(SwingConstants.CENTER);
        descripcion2.setForeground(Color.GRAY);
        descripcion2.setFont(new Font("Segoe UI",Font.PLAIN,20));
        tarjeta.add(descripcion2);
        
        JButton btn = new JButton(titulo);
        btn.setBounds(110,440,280,50);
        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setBackground(colorBoton);
        btn.setFont(new Font("Segoe UI",Font.BOLD,22));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        tarjeta.add(btn);

        return tarjeta;
    }

}
