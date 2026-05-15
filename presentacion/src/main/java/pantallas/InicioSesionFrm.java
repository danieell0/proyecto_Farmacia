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
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
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
        // Usamos un GridLayout de 1 fila y 2 columnas para partir la ventana a la mitad exacta
        this.setLayout(new GridLayout(1, 2));

        // =========================================================
        // PANEL IZQUIERDO (Información y Branding)
        // =========================================================
        JPanel pnlIzquierdo = new JPanel(new BorderLayout());
        pnlIzquierdo.setBackground(new Color(80, 139, 107)); // Tu verde original
        
        // Título con formato HTML para centrarlo y separarlo en varias líneas
        JLabel lblTitulo = new JLabel("<html><div style='text-align: center; font-family: Segoe UI;'>"
                + "SISTEMA GESTOR<br><br>INTEGRAL<br><br>FARMACÉUTICO</div></html>", SwingConstants.CENTER);
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10)); // Margen superior
        
        // Espacio preparado para tu imagen de la farmacia
        // Para poner tu imagen real cambia este texto por: new ImageIcon(getClass().getResource("/imagenes/tu_farmacia.png"))
        //JLabel lblImagenFarmacia = new JLabel(new ImageIcon(getClass().getResource("/Downloads/Fondo login.jpg")), SwingConstants.CENTER);
        //lblImagenFarmacia.setPreferredSize(new Dimension(300, 180));
        //lblImagenFarmacia.setOpaque(true);
        // Simulando un ligero degradado o color de fondo para la imagen
        //lblImagenFarmacia.setBackground(new Color(65, 115, 88)); 
        
        pnlIzquierdo.add(lblTitulo, BorderLayout.NORTH);
        //pnlIzquierdo.add(lblImagenFarmacia, BorderLayout.SOUTH);

        // =========================================================
        // PANEL DERECHO (Formulario de Login)
        // =========================================================
        JPanel pnlDerecho = new JPanel(new GridBagLayout());
        pnlDerecho.setBackground(new Color(45, 95, 70)); // Verde más oscuro

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new Insets(10, 40, 5, 40); // Margen (arriba, izquierda, abajo, derecha)

        // 1. Etiqueta ID
        JLabel lblId = new JLabel("Ingrese ID Empleado");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        pnlDerecho.add(lblId, gbc);

        // 2. Campo de Texto ID
        gbc.gridy++;
        gbc.insets = new Insets(0, 40, 25, 40); // Más margen abajo para separar del siguiente
        txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(200, 35));
        txtId.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtId.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Quita bordes feos
        pnlDerecho.add(txtId, gbc);

        // 3. Etiqueta Contraseña
        gbc.gridy++;
        gbc.insets = new Insets(10, 40, 5, 40);
        JLabel lblContrasena = new JLabel("Ingrese Contraseña");
        lblContrasena.setForeground(Color.WHITE);
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        pnlDerecho.add(lblContrasena, gbc);

        // 4. Campo de Texto Contraseña
        gbc.gridy++;
        gbc.insets = new Insets(0, 40, 40, 40); // Mucho margen abajo para separar del botón
        txtContrasena = new JPasswordField();
        txtContrasena.setPreferredSize(new Dimension(200, 35));
        txtContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtContrasena.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        pnlDerecho.add(txtContrasena, gbc);

        // 5. Botón Iniciar Sesión
        gbc.gridy++;
        gbc.fill = GridBagConstraints.NONE; // Para que el botón no se estire a lo ancho
        gbc.anchor = GridBagConstraints.WEST; // Alineado a la izquierda como en la foto
        gbc.insets = new Insets(0, 40, 20, 40);
        
        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setPreferredSize(new Dimension(140, 40));
        btnIniciarSesion.setBackground(Color.WHITE); // Botón blanco
        btnIniciarSesion.setForeground(new Color(45, 95, 70)); // Letra verde oscura
        btnIniciarSesion.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnIniciarSesion.setFocusPainted(false);
        btnIniciarSesion.setBorder(BorderFactory.createEmptyBorder()); // Estilo plano/plástico
        btnIniciarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        pnlDerecho.add(btnIniciarSesion, gbc);

        // Añadimos las dos mitades al JDialog
        this.add(pnlIzquierdo);
        this.add(pnlDerecho);
    }
    /*
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
*/
    private void configurarVentana(Frame parent) {
        this.setSize(650, 400);
        this.setResizable(false);
        //para que no se quede corriendo el programa sin pantallas cuando se cierra el login
        this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        // escuchador del boton X 
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // fuerza el apagado de la maquina virtual y los hilos de mongo terminando el programa
                System.exit(0); 
            }
        });
        
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
