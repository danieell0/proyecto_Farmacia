/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componentes;

import excepciones.NegocioExcepcion;
import fachadas.LoginFachada;
import interfaces.ILoginFachada;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import objetosNegocio.EmpleadoBO;

/**
 * JDialog que sirve para el inicio de sesion 
 * @author Benjamin
 */
public class LoginDialog extends JDialog {

    private LoginPanel panelLogin;

    // El Frame 'parent' es la ventana principal desde donde se abrió el pop-up
    public LoginDialog(Frame parent) {
        // Llamamos al constructor de JDialog pasándole el padre y 'true' para hacerlo modal
        super(parent, "Iniciar Sesión", true); 
        
        inicializarDialogo();
    }

    private void inicializarDialogo() {
        // 1. Instanciamos nuestro panel reutilizable
        panelLogin = new LoginPanel();
        
        // 2. Lo agregamos al centro del Diálogo
        this.add(panelLogin, BorderLayout.CENTER);
        
        // 3. Configuramos la acción del botón de ese panel
        panelLogin.getBtnIniciarSesion().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarCredenciales();
            }
        });

        // 4. Configuraciones finales del pop-up
        this.pack(); // Ajusta el tamaño del diálogo al tamaño de su contenido (el panel)
        this.setResizable(false); // Evita que el usuario le cambie el tamaño
        this.setLocationRelativeTo(getParent()); // Lo centra perfectamente sobre la ventana principal
    }

    private void verificarCredenciales() {
        String idTexto = panelLogin.getIdEmpleado();
        
        String password = new String(panelLogin.getContrasena());

        try{
            //validacion rapida de formato
            if (idTexto.trim().isEmpty()){
                throw new NegocioExcepcion("Debe ingresar un ID para continuar");
            }
            
            Long id = Long.parseLong(idTexto.trim());
            
            
            //Adaptacion para el patron fachada
            ILoginFachada fachada = new LoginFachada();
            String rolObtenido = fachada.iniciarSesion(id, password);
            
            JOptionPane.showMessageDialog(this, "Bienvenido. Rol: " + rolObtenido);
            this.dispose();
            
        } catch (NumberFormatException ex){
            //en el caso de que el usuario escriba letras en el campo de ID
            JOptionPane.showMessageDialog(this, "El ID debe ser un numero entero valido", "Error de Formato", JOptionPane.WARNING_MESSAGE);
        } catch (NegocioExcepcion ex){
            //en caso de que la capa de negocio rechaza las credenciales
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Atencion", JOptionPane.WARNING_MESSAGE);
        }
    }
}
