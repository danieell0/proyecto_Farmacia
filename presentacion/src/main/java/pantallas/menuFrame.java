/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import pantallas.control.controlNavegacion;

/**
 *
 * @author Jorge
 */
public class menuFrame extends JFrame {

    private JTextField txtBuscar;
    private JPanel panelProductos;
    private DefaultListModel<String> modeloCarrito;
    private JList<String> listaCarrito;
    private JLabel lblTotal;
    private double total = 0;
    
    private JButton btnUsuario;

    public menuFrame() {
        setTitle("Sistema Farmacia");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //le agregamos border layout para acomodar el nuevo boton
        JPanel panelSuperior = new JPanel(new BorderLayout(10,10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        txtBuscar = new JTextField("Buscar medicamento");
        panelSuperior.add(txtBuscar, BorderLayout.CENTER);

        //nueva configuracion del boton de inicio de sesion
        btnUsuario = new JButton("Iniciar Sesion");
        btnUsuario.addActionListener(e -> {
            controlNavegacion navegador = new controlNavegacion();
            //le pasamos this para que el popup se centre en el frame y lo boquee
            navegador.solicitarLogin(this);
        });
        panelSuperior.add(btnUsuario, BorderLayout.EAST);
        
        add(panelSuperior, BorderLayout.NORTH);

        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollProductos = new JScrollPane(panelProductos);

        add(scrollProductos, BorderLayout.CENTER);

        JPanel panelCarrito = new JPanel();
        panelCarrito.setPreferredSize(new Dimension(250, 0));
        panelCarrito.setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Carrito");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        panelCarrito.add(lblTitulo, BorderLayout.NORTH);

        modeloCarrito = new DefaultListModel<>();
        listaCarrito = new JList<>(modeloCarrito);

        panelCarrito.add(new JScrollPane(listaCarrito), BorderLayout.CENTER);

        JPanel panelPago = new JPanel();
        panelPago.setLayout(new GridLayout(3, 1, 5, 5));

        lblTotal = new JLabel("Total: $0.00");
        JButton btnPagar = new JButton("Pagar");

        btnPagar.addActionListener(e -> {
            pagar();
        });

        panelPago.add(lblTitulo);
        panelPago.add(btnPagar);

        panelCarrito.add(panelPago, BorderLayout.SOUTH);
        add(panelCarrito, BorderLayout.EAST);
        cargarProductos();

    }

    private void cargarProductos() {
        agregarProducto("Paracetamol", 50);
        agregarProducto("Ibuprofeno", 80);
        agregarProducto("Omeprazol", 120);
        agregarProducto("Loratadina", 70);
        agregarProducto("Jarabe", 100);
        agregarProducto("Alcohol", 45);
        agregarProducto("Vitamina C", 60);
    }

    private void agregarProducto(String nombre, double precio) {
        JPanel carta = new JPanel();
        carta.setLayout(new BorderLayout());
        carta.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel lblNombre = new JLabel(nombre);
        JLabel lblPrecio = new JLabel("$" + precio);

        JButton btnAgregar = new JButton("Agregar pa");
        btnAgregar.addActionListener(e -> {
            modeloCarrito.addElement(nombre + " - $" + precio);
            total += precio;
            lblTotal.setText("Total: $ " + total);
        });

        JPanel panelInfo = new JPanel(new GridLayout(2, 1));
        panelInfo.add(lblNombre);
        panelInfo.add(lblPrecio);
        carta.add(panelInfo, BorderLayout.CENTER);
        carta.add(btnAgregar, BorderLayout.SOUTH);
        panelProductos.add(carta);
    }

    private void pagar() {

        if (modeloCarrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito esta vacio");
            return;
        }
        JOptionPane.showMessageDialog(this, "Pago realizado: $ " + total);
        modeloCarrito.clear();
        total = 0;
        lblTotal.setText("Total: $0.00");
    }

}
