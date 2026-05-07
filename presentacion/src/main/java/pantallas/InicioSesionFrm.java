/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTO.CuentaAccesoDTO;
import interfaces.ICoordinador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagLayout;
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
        // Contenedor principal con BorderLayout
        JPanel pnlFondo = new JPanel(new BorderLayout());
        pnlFondo.setBackground(Color.WHITE);

        // --- HEADER (Rectángulo Verde Arriba) ---
        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(80, 139, 107)); // El verde de tu farmacia
        pnlHeader.setPreferredSize(new Dimension(0, 60));
        pnlHeader.setLayout(new GridBagLayout()); // Excelente para centrar cosas rápido

        JLabel lblTitulo = new JLabel("INICIO DE SESIÓN");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pnlHeader.add(lblTitulo); // Se centra automáticamente por el GridBagLayout

        // --- FORMULARIO (Centro) ---
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBackground(Color.WHITE);
        // Le damos un poco más de margen lateral para que no se vea apretado
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // 1. Icono de Usuario
        JLabel lblIcono = new JLabel("👤", SwingConstants.CENTER);
        lblIcono.setFont(new Font("Segoe UI", Font.PLAIN, 55));
        lblIcono.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 2. Campo de ID
        JLabel lblId = new JLabel("ID de Empleado");
        lblId.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblId.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtId = new JTextField();
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtId.setHorizontalAlignment(JTextField.CENTER);
        txtId.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        // 3. Campo de Contraseña
        JLabel lblContrasena = new JLabel("Contraseña");
        lblContrasena.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblContrasena.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        txtContrasena = new JPasswordField();
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtContrasena.setHorizontalAlignment(JTextField.CENTER);
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        // 4. Botón Iniciar Sesión
        btnIniciarSesion = new JButton("Entrar al sistema");
        btnIniciarSesion.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnIniciarSesion.setBackground(new Color(40, 40, 40)); 
        btnIniciarSesion.setForeground(Color.WHITE);
        btnIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setPreferredSize(new Dimension(150, 40));
        btnIniciarSesion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Manita al pasar el mouse
        
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        // Ensamblar el formulario
        panelPrincipal.add(lblIcono);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(lblId);
        panelPrincipal.add(Box.createVerticalStrut(5));
        panelPrincipal.add(txtId);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(lblContrasena);
        panelPrincipal.add(Box.createVerticalStrut(5));
        panelPrincipal.add(txtContrasena);
        panelPrincipal.add(Box.createVerticalStrut(30));
        panelPrincipal.add(btnIniciarSesion);

        // Unir todo al panel de fondo
        pnlFondo.add(pnlHeader, BorderLayout.NORTH);
        pnlFondo.add(panelPrincipal, BorderLayout.CENTER);

        // Agregar al JDialog
        this.add(pnlFondo);
    }

    private void configurarVentana(Frame parent) {
        this.setSize(350, 450);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        // Esto hace que la ventanita aparezca exactamente en el centro de la ventana actual
        this.setLocationRelativeTo(parent); 
    }

    private void iniciarSesion() {
        // 1. Obtenemos los textos CRUDOS primero
        String idTexto = txtId.getText();
        String password = new String(txtContrasena.getPassword());

        // 2. Validamos que no estén vacíos
        if (idTexto.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa tu ID y contraseña.", "Campos incompletos", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            // 3. AHORA SÍ convertimos a número (porque ya sabemos que no está vacío)
            Long idUsuario = Long.parseLong(idTexto.trim());
            
            // 4. Armamos el DTO
            CuentaAccesoDTO credenciales = new CuentaAccesoDTO();
            credenciales.setIDEmpleado(idUsuario);
            credenciales.setContraseña(password);
            
            // 5. Delegamos al coordinador 
            boolean sesionValida = coordinador.validarInicioSesion(credenciales);
           
            if(sesionValida){
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "ID o contraseña incorrectos.", "Error de Autenticacion", JOptionPane.ERROR_MESSAGE);
                txtContrasena.setText(""); // se limpia la contra para seguridad
            }
            
        } catch (NumberFormatException ex) {
            // 6. Atrapamos el error por si el usuario escribió letras en vez de números en el ID
            JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.", "Formato incorrecto", JOptionPane.WARNING_MESSAGE);
            txtId.setText("");
        }
    }
}
