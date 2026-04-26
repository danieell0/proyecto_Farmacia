/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Formulario de inicio de sesion
 * @author Benjamin
 */
public class LoginPanel extends JPanel{
    
    private JTextField txtIdEmpleado;
    private JPasswordField txtContrasena;
    private JButton btnIniciarSesion;

    public LoginPanel() {
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        
        txtIdEmpleado = new JTextField(15);
        txtContrasena = new JPasswordField(15);
        btnIniciarSesion = new JButton("Iniciar sesión");
        
        // Configuraciones visuales básicas
        btnIniciarSesion.setBackground(Color.DARK_GRAY);
        btnIniciarSesion.setForeground(Color.WHITE);

        // Agregando componentes al panel (Ejemplo muy básico)
        this.add(new JLabel("ID:"));
        this.add(txtIdEmpleado);
        this.add(new JLabel("Contraseña:"));
        this.add(txtContrasena);
        this.add(btnIniciarSesion);
    }

    // Métodos "Getters" para que el Dialog o el Controlador puedan leer los datos
    public String getIdEmpleado() {
        return txtIdEmpleado.getText();
    }

    public char[] getContrasena() {
        return txtContrasena.getPassword();
    }

    public JButton getBtnIniciarSesion() {
        return btnIniciarSesion;
    }
    
}
