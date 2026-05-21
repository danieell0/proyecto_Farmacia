package pantallas;

import DTO.CarritoDTO;
import DTO.ClienteDTO;
import DTO.DetalleCarritoDTO;
import DTO.ProductoDTO;
import componentes.PanelEncabezado;
import componentes.panelMenuLateralEmpleado;
import interfaces.ICoordinador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import pantallas.control.Coordinador;
import pantallas.control.controlNavegacion;
import interfaces.IControlNavegacion;

/**
 *
 * @author Dario
 */
public class menuPuntosFrame extends JFrame {
private DefaultListModel<String> modeloCarrito;
    private JList<String> listaCarrito;
    private JLabel lblTotal;
    private JLabel lblInformacionCliente;
    private JButton btnCanjear;
    private double total = 0;
    private JPanel grid;

    private ICoordinador coordinador;
    private IControlNavegacion navegacion;

    public void setCoordinador(ICoordinador coordinador) {
        this.coordinador = coordinador;
    }

    public void setNavegacion(IControlNavegacion navegacion) {
        this.navegacion = navegacion;
    }

    public menuPuntosFrame() {
        setTitle("Farmacia - Productos de Puntos");
        setSize(1450, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 243, 245));
        
        Coordinador.getCoordinador().setMenuPuntosFrame(this);
        
        add(new panelMenuLateralEmpleado(), BorderLayout.WEST);
        add(crearMainContent(), BorderLayout.CENTER);
    }

    private JPanel crearMainContent() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(245, 247, 250));
        main.add(new PanelEncabezado(), BorderLayout.NORTH);
        
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.add(crearProductosPuntos(), BorderLayout.CENTER);
        
        body.add(centro, BorderLayout.CENTER);
        body.add(crearCarritoModerno(), BorderLayout.EAST);
        main.add(body, BorderLayout.CENTER);
        return main;
    }

    private JPanel crearProductosPuntos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        
        JLabel titulo = new JLabel("Productos de puntos en stock");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titulo, BorderLayout.WEST);
        panel.add(header, BorderLayout.NORTH);
        
        grid = new JPanel(new GridLayout(0, 4, 20, 20));
        grid.setOpaque(false);
        
        List<ProductoDTO> lista = obtenerProductosPuntos();
        if (lista != null) {
            for (ProductoDTO p : lista) {
                grid.add(crearCard(p));
            }
        }
        
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setOpaque(false);
        contenedor.add(grid, BorderLayout.NORTH);
        
        JScrollPane scroll = new JScrollPane(contenedor);
        scroll.setBorder(null);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearCard(ProductoDTO p) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(230, 230, 230), 1, true),
                new EmptyBorder(20, 20, 20, 20)
        ));

        ImageIcon icon = new ImageIcon(getClass().getResource(p.getImagen()));
        Image img = icon.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        JLabel lblImg = new JLabel(new ImageIcon(img));
        lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nombreLbl = new JLabel(p.getNombre());
        nombreLbl.setFont(new Font("Segoe UI", Font.BOLD, 18));
        nombreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel precioLbl = new JLabel(p.getPrecio() + " Pts");
        precioLbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        precioLbl.setForeground(new Color(0, 150, 136));
        precioLbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setFocusPainted(false);
        btnAgregar.setBackground(new Color(0, 150, 136));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnAgregar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        btnAgregar.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(this, "¿Cuántas unidades deseas agregar?");
            if (input != null && !input.trim().isEmpty()) {
                try {
                    int cantidad = Integer.parseInt(input);
                    if (cantidad > 0) {
                        Coordinador.getCoordinador().agregarProductoAlCarrito(p, cantidad);
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Por favor, ingresa un número entero.");
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error al agregar: " + ex.getMessage());
                }
            }
        });

        card.add(lblImg);
        card.add(Box.createVerticalStrut(15));
        card.add(nombreLbl);
        card.add(Box.createVerticalStrut(5));
        card.add(precioLbl);
        card.add(Box.createVerticalStrut(15));
        card.add(btnAgregar);
        return card;
    }

    private JPanel crearCarritoModerno() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(320, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("Carrito");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        panel.add(titulo);
        panel.add(Box.createVerticalStrut(20));
        
        modeloCarrito = new DefaultListModel<>();
        listaCarrito = new JList<>(modeloCarrito);
        listaCarrito.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        
        JScrollPane scroll = new JScrollPane(listaCarrito);
        scroll.setPreferredSize(new Dimension(250, 400));
        panel.add(scroll);
        panel.add(Box.createVerticalStrut(20));
        
        JButton btnQuitar = new JButton("Quitar seleccionado");
        btnQuitar.setFocusPainted(false);
        btnQuitar.addActionListener(e -> {
            int index = listaCarrito.getSelectedIndex();
            if (index != -1) {
                CarritoDTO carrito = Coordinador.getCoordinador().obtenerCarritoActual();
                if (carrito != null && index < carrito.getListaProductos().size()) {
                    DetalleCarritoDTO detalle = carrito.getListaProductos().get(index);
                    Coordinador.getCoordinador().eliminarProductoDelCarrito(detalle.getProducto().getIdProducto(), detalle.getCantidad());
                }
            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto");
            }
        });

        panel.add(btnQuitar);
        panel.add(Box.createVerticalStrut(20));
        lblTotal = new JLabel("Total: 0 Pts");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(lblTotal);
        panel.add(Box.createVerticalStrut(5));
        ClienteDTO clienteActual = Coordinador.getCoordinador().getClienteActual();
        String nombreCli = (clienteActual != null) ? clienteActual.getNombre() : "No asignado";
        double puntosCli = (clienteActual != null) ? clienteActual.getPuntos() : 0.0;
        lblInformacionCliente = new JLabel("<html>Cliente: " + nombreCli + "<br>Disponibles: " + String.format("%.2f", puntosCli) + " Pts</html>");
        lblInformacionCliente.setFont(new Font("Segoe UI", Font.ITALIC, 14));
        lblInformacionCliente.setForeground(new Color(115, 128, 142));
        panel.add(lblInformacionCliente);
        
        panel.add(Box.createVerticalGlue());
        
        btnCanjear = new JButton("Canjear");
        btnCanjear.setPreferredSize(new Dimension(250, 50));
        btnCanjear.setMaximumSize(new Dimension(300, 50));
        btnCanjear.setBackground(new Color(0, 150, 136));
        btnCanjear.setForeground(Color.WHITE);
        btnCanjear.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnCanjear.setFocusPainted(false);
        btnCanjear.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnCanjear.addActionListener(e -> {
            if (modeloCarrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito de puntos está vacío");
                return;
            }
            controlNavegacion.getControlNavegacion().abrirVentaPuntosFrame();
        });
        
        panel.add(Box.createVerticalStrut(20));
        panel.add(btnCanjear);
        return panel;
    }

    private List<ProductoDTO> obtenerProductosPuntos() {
        return Coordinador.getCoordinador().ObtenerProductosProductosConcordantes(); 
    }

    public void actualizarTablaCarrito(CarritoDTO carrito) {
        modeloCarrito.clear();
        total = 0;
        for (DetalleCarritoDTO detalle : carrito.getListaProductos()) {
            String item = detalle.getProducto().getNombre()
                    + " x" + detalle.getCantidad()
                    + " - " + (detalle.getProducto().getPrecio() * detalle.getCantidad()) + " Pts";
            modeloCarrito.addElement(item);
            total += (detalle.getProducto().getPrecio() * detalle.getCantidad());
        }

        lblTotal.setText("Total: " + String.format("%.2f", total) + " Pts");
        
        ClienteDTO clienteActual = Coordinador.getCoordinador().getClienteActual();
        if (clienteActual != null) {
            double puntosDisponibles = clienteActual.getPuntos();
            lblInformacionCliente.setText("<html>Cliente: " + clienteActual.getNombre() + "<br>Disponibles: " + String.format("%.2f", puntosDisponibles) + " Pts</html>");
            
            if (total > puntosDisponibles) {
                lblTotal.setForeground(Color.RED);
                btnCanjear.setEnabled(false);
                btnCanjear.setBackground(Color.LIGHT_GRAY);
                lblTotal.setText("Total: " + String.format("%.2f", total) + " Pts (Insuficiente)");
            } else {
                lblTotal.setForeground(Color.BLACK);
                btnCanjear.setEnabled(true);
                btnCanjear.setBackground(new Color(0, 150, 136));
            }
        }
    }

    public void limpiarVenta() {
        modeloCarrito.clear();
        total = 0;
        lblTotal.setText("Total: 0 Pts");
        lblTotal.setForeground(Color.BLACK);
        btnCanjear.setEnabled(true);
        btnCanjear.setBackground(new Color(0, 150, 136));
    }
}
