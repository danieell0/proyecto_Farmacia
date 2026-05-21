/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import componentes.PanelEncabezado;
import componentes.panelMenuLateralAdmin;
import componentes.panelMenuLateralEmpleado;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import pantallas_gestion_empleados.RegistroSolicitante;
import interfaces.IControlNavegacion;


/**
 *
 * @author Jorge
 */
public class menuAdmin extends JFrame {
    //instancia de la interfaz del control de navegacion
    private IControlNavegacion controlNav;
    
    //variables de clase para permitir navegar entre los menus de las tarjetas
    private CardLayout navegadorVistas;
    private JPanel panelContenedorTarjetas;
    
    public menuAdmin(IControlNavegacion controlNav) {
        this.controlNav = controlNav;
        
        setTitle("Farmacia admin");
        setSize(1208, 720);
        setMinimumSize(new java.awt.Dimension(1024, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //para hacerla redimensionable en pantallas de cualquier tamaño
        setLayout(new BorderLayout());
        //menu lateral en la izquierda
        panelMenuLateralAdmin menu = new panelMenuLateralAdmin(controlNav);
        add(menu, BorderLayout.WEST);
        //panel de encabezado
        PanelEncabezado encabezado = new PanelEncabezado();
        add(encabezado, BorderLayout.NORTH);
        
        //area de trabajo dinamica
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                ImageIcon fondo = new ImageIcon(getClass().getResource("/icons/farmacia.png"));
                g2.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);
                g2.setColor(new Color(255, 255, 255, 170));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        
        //se usa el gridlayout para apilar  columnas verticalmente sin pixeles absolutos
        panelCentral.setLayout(new GridBagLayout());
        add(panelCentral, BorderLayout.CENTER);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // alinea todo el texto a la izquierda
        gbc.insets = new Insets(5, 50, 5, 50); // margenes de espacio 
        
        // columna de titulos de bienvenida
        JLabel lblTitulo1 = new JLabel("Bienvenido al Panel de Administración");
        lblTitulo1.setFont(new Font("Segoe UI", Font.BOLD, 42)); 
        lblTitulo1.setForeground(new Color(0, 77, 64));
        panelCentral.add(lblTitulo1, gbc);

        // descripcion del frame 
        gbc.gridy++;
        gbc.insets = new Insets(20, 50, 2, 50); // Extra gap above paragraph
        JLabel lblTexto1 = new JLabel("<html>Desde aquí podrás gestionar todos los módulos del sistema de farmacia<br>"
                + "de manera rápida, segura y eficiente.</html>");
        lblTexto1.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        panelCentral.add(lblTexto1, gbc);

        // bienvenida
        gbc.gridy++;
        gbc.insets = new Insets(15, 50, 30, 50); // Extra gap below paragraph
        JLabel lblFrase = new JLabel("Que tengas un excelente día de trabajo");
        lblFrase.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblFrase.setForeground(new Color(0, 100, 80));
        panelCentral.add(lblFrase, gbc);
        
        // 
        gbc.gridy++;
        gbc.weightx = 1.0; 
        gbc.weighty = 1.0; 
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(10, 50, 40, 50); 

        navegadorVistas = new CardLayout();
        panelContenedorTarjetas = new JPanel(navegadorVistas);
        panelContenedorTarjetas.setOpaque(false);
        
        // 1. crea el menu principal (las 5 tarjetas)
        JPanel menuPrincipal = new JPanel(new GridLayout(1, 5, 20, 0));
        menuPrincipal.setOpaque(false);
        
        // logica del boton contratar (abre sub menu)
        
        JPanel tarjetaContratar = crearTarjeta("Contratar", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navegadorVistas.show(panelContenedorTarjetas, "SUB_MENU_CONTRATAR");
            }
        });
        
        menuPrincipal.add(tarjetaContratar);
        menuPrincipal.add(crearTarjeta("Actualizar", null));
        menuPrincipal.add(crearTarjeta("Reportes", null));
        menuPrincipal.add(crearTarjeta("Cuentas", null));
        menuPrincipal.add(crearTarjeta("Bajas", null));
        
        //crea el submenu de las dos opciones y el boton de volver
        JPanel subMenuContratar = new JPanel(new BorderLayout(20, 0));
        subMenuContratar.setOpaque(false);
        
        // el boton de volver a las 5 opciones originales
        JButton btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnVolver.setBackground(new Color(120, 120, 120));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.addActionListener(e -> navegadorVistas.show(panelContenedorTarjetas, "MENU_PRINCIPAL"));
        
        JPanel panelBotonVolver = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBotonVolver.setOpaque(false);
        panelBotonVolver.add(btnVolver);
        subMenuContratar.add(panelBotonVolver, BorderLayout.NORTH);
        
        // Se cambió a un GridLayout de 1 fila y 2 columnas
        JPanel subGridTarjetas = new JPanel(new GridLayout(1, 2, 40, 0)); 
        subGridTarjetas.setOpaque(false);
        
        // 1. Botón para Registrar Solicitante (Conectado al controlador)
        subGridTarjetas.add(crearTarjeta("Registrar Solicitante", new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Le delega la responsabilidad al jefe de navegación
                controlNav.abrirRegistroSolicitante(); 
            }
        }));
        
        // 2. Botón para Consultar Solicitantes (Aún sin acción, le pasas null)
        subGridTarjetas.add(crearTarjeta("Consultar Solicitantes", null));
        
        subMenuContratar.add(subGridTarjetas, BorderLayout.CENTER);
       
        // se añaden al contenedor de tarjetas
        panelContenedorTarjetas.add(menuPrincipal, "MENU_PRINCIPAL");
        panelContenedorTarjetas.add(subMenuContratar, "SUB_MENU_CONTRATAR");
        
        panelCentral.add(panelContenedorTarjetas, gbc);

        setVisible(true);
    }

    // metodo para crear una tarjeta
    private JPanel crearTarjeta(String titulo, ActionListener accion) {
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setLayout(new BorderLayout(0, 15)); 
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230), 1, true),
                BorderFactory.createEmptyBorder(25, 15, 25, 15) 
        ));

        JLabel lblTitulo = new JLabel("<html><body style='text-align: center;'>" + titulo + "</body></html>", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        tarjeta.add(lblTitulo, BorderLayout.CENTER);

        JButton btn = new JButton("➜");
        btn.setPreferredSize(new java.awt.Dimension(55, 40));
        btn.setFocusPainted(false);
        btn.setBackground(new Color(0, 150, 120));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
      
        if (accion != null) {
            btn.addActionListener(accion);
        }
        
        JPanel btnWrapper = new JPanel();
        btnWrapper.setOpaque(false);
        btnWrapper.add(btn);
        tarjeta.add(btnWrapper, BorderLayout.SOUTH);

        return tarjeta;
    }
        /*
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
*/
}
