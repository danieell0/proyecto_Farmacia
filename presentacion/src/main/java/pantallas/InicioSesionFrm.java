/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTO.LoginDTO;
import interfaces.ICoordinador;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Benjamin
 */
public class InicioSesionFrm extends JDialog{
    // Agregamos el coordinador como atributo
    private ICoordinador coordinador;
    
    private JTextField txtId;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;

    // Recibe el Frame padre (la ventana principal actual) para centrarse sobre ella
    public InicioSesionFrm(Frame parent, ICoordinador coordinador) {
        // El 'true' al final es la clave: hace que el diálogo sea MODAL
        super(parent, "Iniciar Sesión", true); 
        this.coordinador = coordinador;
        inicializarComponentes();
        configurarVentana(parent);
    }

    private void inicializarComponentes() {
        // Panel principal con fondo blanco para simular tu diseño
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // 1. Icono de Usuario (Simulado con un JLabel centrado)
        // Aquí puedes cambiarlo por un new ImageIcon("ruta/a/tu/icono.png")
        JLabel lblIcono = new JLabel("👤", SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 50));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Campo de ID
        JLabel lblId = new JLabel("ID");
        lblId.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtId = new JTextField(15);
        txtId.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtId.getPreferredSize().height));

        // 3. Campo de Contraseña
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        txtContrasena = new JPasswordField(15);
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, txtContrasena.getPreferredSize().height));

        // 4. Botón Iniciar Sesión
        btnIniciarSesion = new JButton("Iniciar sesión");
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciarSesion.setBackground(new Color(40, 40, 40)); // Gris oscuro/negro
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFocusPainted(false);
        
        // Evento del botón (aquí conectaremos con tu lógica de negocios luego)
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        // Ensamblar el panel
        panelPrincipal.add(lblIcono);
        panelPrincipal.add(Box.createVerticalStrut(20)); // Espaciado
        panelPrincipal.add(lblId);
        panelPrincipal.add(txtId);
        panelPrincipal.add(Box.createVerticalStrut(15));
        panelPrincipal.add(lblContrasena);
        panelPrincipal.add(txtContrasena);
        panelPrincipal.add(Box.createVerticalStrut(25));
        panelPrincipal.add(btnIniciarSesion);

        // Agregar panel al JDialog
        this.add(panelPrincipal);
    }

    private void configurarVentana(Frame parent) {
        this.setSize(300, 350);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Esto hace que la ventanita aparezca exactamente en el centro de la ventana actual
        this.setLocationRelativeTo(parent); 
    }

    private void iniciarSesion() {
        String idUsuario = txtId.getText();
        String password = new String(txtContrasena.getPassword());

        // Validar que no estén vacíos antes de molestar al servidor/BD
        if (idUsuario.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa tu ID y contraseña.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        LoginDTO credenciales = new LoginDTO();
        credenciales.setIdUsuarioTexto(idUsuario);
        credenciales.setPassword(password);
        
        
        // se delega la logica al coordinador 
        boolean sesionValida = coordinador.validarInicioSesion(credenciales);
       
        if(sesionValida){
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(this, "ID o contraseña incorrectos.", "Error de Autenticacion", JOptionPane.ERROR_MESSAGE);
            txtContrasena.setText(""); //se limpia la contra para seguridad
        }
    }
}
