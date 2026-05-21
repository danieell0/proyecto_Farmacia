package pantallas_gestion_empleados;

import componentes.PanelEncabezado;
import componentes.panelMenuLateralAdmin;
import com.toedter.calendar.JDateChooser; // Requiere la librería JCalendar
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import interfaces.IControlNavegacion;
/**
 *
 * @author Benjamin
 */
public class RegistroSolicitante extends JFrame{
    private IControlNavegacion controlNav;
    
    // Componentes declarados como variables de clase para facilitar su acceso lógico posterior
    private JTextField txtNombre;
    private JTextField txtApellidoPaterno;
    private JTextField txtApellidoMaterno;
    private JTextField txtTelefono;
    private JComboBox<String> comboRol;
    private JDateChooser selectorFechaNacimiento;
    private JTextArea txtDescripcionEntrevista;
    private JButton btnGuardar;
    private JButton btnVolver;
    
    public RegistroSolicitante(IControlNavegacion controlNav) {
        this.controlNav = controlNav;
        
        setTitle("Farmacia Admin - Registro de Solicitante");
        setSize(1208, 720);
        setMinimumSize(new Dimension(1024, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 1. Distribución Principal de la Ventana
        setLayout(new BorderLayout());

        panelMenuLateralAdmin menu = new panelMenuLateralAdmin();
        add(menu, BorderLayout.WEST);

        PanelEncabezado encabezado = new PanelEncabezado();
        add(encabezado, BorderLayout.NORTH);

        // 2. Contenedor Principal del Formulario (Panel Central con Scroll por si la pantalla es chica)
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(new Color(245, 247, 248)); // Fondo gris claro sutil y moderno
        add(panelCentral, BorderLayout.CENTER);

        // Barra Superior Interna (Título del Formulario y Botón Volver)
        JPanel barraSuperiorInterna = new JPanel(new BorderLayout());
        barraSuperiorInterna.setOpaque(false);
        barraSuperiorInterna.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));

        JLabel lblTituloForm = new JLabel("Registro de Nuevo Solicitante");
        lblTituloForm.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTituloForm.setForeground(new Color(0, 77, 64));
        barraSuperiorInterna.add(lblTituloForm, BorderLayout.WEST);

        btnVolver = new JButton("◀ Volver al Menú");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBackground(new Color(120, 120, 120));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setPreferredSize(new Dimension(150, 35));
        
        JPanel containerVolver = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        containerVolver.setOpaque(false);
        containerVolver.add(btnVolver);
        barraSuperiorInterna.add(containerVolver, BorderLayout.EAST);

        btnVolver.addActionListener(e -> {
            controlNav.abrirMenuAdmin();
        });
        
        panelCentral.add(barraSuperiorInterna, BorderLayout.NORTH);

        // 3. Cuerpo del Formulario utilizando GridBagLayout para control exacto
        JPanel cuerpoFormulario = new JPanel(new GridBagLayout());
        cuerpoFormulario.setBackground(Color.WHITE);
        cuerpoFormulario.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 40, 30, 40),
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15); // Márgenes de separación interna entre celdas
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font fontLabels = new Font("Segoe UI", Font.BOLD, 14);
        Font fontInputs = new Font("Segoe UI", Font.PLAIN, 14);

        // --- FILA 0: Nombre y Apellido Paterno ---
        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.5;
        cuerpoFormulario.add(crearLabelForm("Nombre *", fontLabels), gbc);
        
        gbc.gridx = 1;
        cuerpoFormulario.add(crearLabelForm("Apellido Paterno *", fontLabels), gbc);

        // inputs fila 1
        gbc.gridx = 0; gbc.gridy = 1;
        txtNombre = new JTextField();
        txtNombre.setFont(fontInputs);
        txtNombre.setPreferredSize(new Dimension(200, 35));
        cuerpoFormulario.add(txtNombre, gbc);

        gbc.gridx = 1;
        txtApellidoPaterno = new JTextField();
        txtApellidoPaterno.setFont(fontInputs);
        txtApellidoPaterno.setPreferredSize(new Dimension(200, 35));
        cuerpoFormulario.add(txtApellidoPaterno, gbc);

        // --- FILA 2: Apellido Materno y Teléfono ---
        gbc.gridx = 0; gbc.gridy = 2;
        cuerpoFormulario.add(crearLabelForm("Apellido Materno", fontLabels), gbc);

        gbc.gridx = 1;
        cuerpoFormulario.add(crearLabelForm("Teléfono *", fontLabels), gbc);

        // inputs fila 3
        gbc.gridx = 0; gbc.gridy = 3;
        txtApellidoMaterno = new JTextField();
        txtApellidoMaterno.setFont(fontInputs);
        txtApellidoMaterno.setPreferredSize(new Dimension(200, 35));
        cuerpoFormulario.add(txtApellidoMaterno, gbc);

        gbc.gridx = 1;
        txtTelefono = new JTextField();
        txtTelefono.setFont(fontInputs);
        txtTelefono.setPreferredSize(new Dimension(200, 35));
        cuerpoFormulario.add(txtTelefono, gbc);

        // --- FILA 4: Rol/Puesto y Fecha de Nacimiento ---
        gbc.gridx = 0; gbc.gridy = 4;
        cuerpoFormulario.add(crearLabelForm("Rol / Puesto Solicitado *", fontLabels), gbc);

        gbc.gridx = 1;
        cuerpoFormulario.add(crearLabelForm("Fecha de Nacimiento *", fontLabels), gbc);

        // inputs fila 5
        gbc.gridx = 0; gbc.gridy = 5;
        String[] puestos = {"Seleccione un puesto...", "Farmacéutico", "Auxiliar de Farmacia", "Cajero", "Administrador de Inventario", "Intendencia"};
        comboRol = new JComboBox<>(puestos);
        comboRol.setFont(fontInputs);
        comboRol.setPreferredSize(new Dimension(200, 35));
        comboRol.setBackground(Color.WHITE);
        cuerpoFormulario.add(comboRol, gbc);

        gbc.gridx = 1;
        selectorFechaNacimiento = new JDateChooser();
        selectorFechaNacimiento.setFont(fontInputs);
        selectorFechaNacimiento.setPreferredSize(new Dimension(200, 35));
        // Configura el formato en español estándar
        selectorFechaNacimiento.setDateFormatString("dd/MM/yyyy"); 
        cuerpoFormulario.add(selectorFechaNacimiento, gbc);

        // --- FILA 6: Descripción de la Entrevista (Ocupa ambas columnas) ---
        gbc.gridx = 0; gbc.gridy = 6; gbc.gridwidth = 2; gbc.weightx = 1.0;
        cuerpoFormulario.add(crearLabelForm("Descripción de la Entrevista / Notas adicionales", fontLabels), gbc);

        // input fila 7
        gbc.gridx = 0; gbc.gridy = 7; gbc.weighty = 1.0; // Le da prioridad de crecimiento vertical
        gbc.fill = GridBagConstraints.BOTH;
        
        txtDescripcionEntrevista = new JTextArea();
        txtDescripcionEntrevista.setFont(fontInputs);
        txtDescripcionEntrevista.setLineWrap(true); // Hace que el texto salte de línea automáticamente
        txtDescripcionEntrevista.setWrapStyleWord(true);
        
        JScrollPane scrollTextArea = new JScrollPane(txtDescripcionEntrevista);
        scrollTextArea.setPreferredSize(new Dimension(400, 120));
        cuerpoFormulario.add(scrollTextArea, gbc);

        // --- FILA 8: Botón de Guardar / Acción Principal ---
        gbc.gridx = 0; gbc.gridy = 8; gbc.gridwidth = 2; gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 15, 10, 15);

        btnGuardar = new JButton("Guardar Solicitante");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setBackground(new Color(0, 150, 120));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setPreferredSize(new Dimension(220, 45));
        cuerpoFormulario.add(btnGuardar, gbc);

        // Agregamos el cuerpo con scroll por si la resolución vertical es pequeña
        JScrollPane scrollPrincipal = new JScrollPane(cuerpoFormulario);
        scrollPrincipal.setBorder(BorderFactory.createEmptyBorder());
        panelCentral.add(scrollPrincipal, BorderLayout.CENTER);

        setVisible(true);
    }

    // Método auxiliar para generar etiquetas de texto uniformes rápidamente
    private JLabel crearLabelForm(String texto, Font fuente) {
        JLabel label = new JLabel(texto);
        label.setFont(fuente);
        label.setForeground(new Color(50, 60, 70));
        return label;
    }
}
