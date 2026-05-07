package pantallas;

import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import DTO.CarritoDTO;
import DTO.DetalleCarritoDTO;
import DTO.EmpleadoDTO;
import interfaces.IControlNevagacion;
import interfaces.ICoordinador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import pantallas.control.controlNavegacion;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pantallas.control.Coordinador;
//import pantallas.control.Coordinador;

/**
 *
 * @author Jorge
 */
public class menuFrame extends JFrame {

    private DefaultListModel<String> modeloCarrito;
    private JList<String> listaCarrito;
    private JLabel lblTotal;
    private double total = 0;
    private JTextField txtBuscar;
    private JPanel grid;

    private ICoordinador coordinador;
    private IControlNevagacion navegacion;

    public void setCoordinador(ICoordinador coordinador) {
        this.coordinador = coordinador;
    }

    public void setNavegacion() {
        this.navegacion = navegacion;
    }

    public menuFrame() {

        setTitle("Farmacia");
        setSize(1450, 850);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(240, 243, 245));
        Coordinador.getCoordinador().setMenuFrame(this);
        add(crearSidebar(), BorderLayout.WEST);
        add(crearMainContent(), BorderLayout.CENTER);
    }

    private JPanel crearSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setPreferredSize(new Dimension(110, 0));
        sidebar.setBackground(new Color(0, 121, 107));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.add(Box.createVerticalStrut(20));

        ImageIcon iconLogo = new ImageIcon(
                getClass().getResource("/icons/logo.png")
        );
        Image imgLogo = iconLogo.getImage().getScaledInstance(
                45,
                45,
                Image.SCALE_SMOOTH
        );

        JLabel logo = new JLabel(new ImageIcon(imgLogo));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        sidebar.add(logo);
        sidebar.add(Box.createVerticalStrut(50));

        String[] iconos = {
            "/icons/venta.png"
        };

        String[] tooltips = {
            "Ventas",
            "Historial",
            "Clientes",
            "Inventario",
            "Reportes",
            "Configuración"
        };

        for (int i = 0; i < iconos.length; i++) {
            ImageIcon icon = new ImageIcon(
                    getClass().getResource(iconos[i])
            );

            Image img = icon.getImage().getScaledInstance(28, 28, Image.SCALE_SMOOTH);

            JButton btn = new JButton(new ImageIcon(img));
            btn.setToolTipText(tooltips[i]);
            btn.setMaximumSize(new Dimension(60, 60));
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setContentAreaFilled(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);

            btn.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    btn.setOpaque(true);
                    btn.setBackground(new Color(0, 150, 136));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    btn.setOpaque(false);
                    btn.setBackground(null);
                }
            });
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(20));
        }
        return sidebar;
    }

    private JPanel crearMainContent() {
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(new Color(245, 247, 250));
        main.add(crearHeaderModerno(), BorderLayout.NORTH);
        JPanel body = new JPanel(new BorderLayout());
        body.setOpaque(false);
        body.setBorder(new EmptyBorder(20, 20, 20, 20));
        JPanel centro = new JPanel(new BorderLayout());
        centro.setOpaque(false);
        centro.add(crearBuscador(), BorderLayout.NORTH);
        centro.add(crearProductos(), BorderLayout.CENTER);
        body.add(centro, BorderLayout.CENTER);
        body.add(crearCarritoModerno(), BorderLayout.EAST);
        main.add(body, BorderLayout.CENTER);
        return main;
    }

    private JPanel crearHeaderModerno() {
        JPanel header = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0,
                        0,
                        new Color(0, 121, 107),
                        getWidth(),
                        0,
                        new Color(0, 150, 136)
                );
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        header.setPreferredSize(new Dimension(0, 70));

        EmpleadoDTO usuarioActivo = Coordinador.getCoordinador().getEmpleadoLogueado();
        String saludo = "Atendiendo";
        if (usuarioActivo != null) {
            saludo = "Atendiendo: " + usuarioActivo.getNombre() + " (" + usuarioActivo.getRolPuesto() + ")";
        }
        JLabel titulo = new JLabel(saludo);
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(new EmptyBorder(0, 25, 0, 0));
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setForeground(Color.WHITE);
        btnCerrarSesion.setBackground(new Color(239, 83, 80));
        btnCerrarSesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCerrarSesion.addActionListener(e -> {

            int respuesta = JOptionPane.showConfirmDialog(
                    this,
                    "¿Deseas cerrar sesión?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION);

            if (respuesta == JOptionPane.YES_OPTION) {
                Coordinador.getCoordinador().cerrarSesion();
                this.dispose();
                controlNavegacion.getcontrolNavegacion().abrirLogin();
            }
        });
        JPanel right = new JPanel();
        right.setOpaque(false);
        right.add(btnCerrarSesion);
        header.add(titulo, BorderLayout.WEST);
        header.add(right, BorderLayout.EAST);
        return header;
    }

    private JPanel crearBuscador() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.setBorder(new EmptyBorder(0, 0, 20, 20));
        txtBuscar = new JTextField();
        txtBuscar.setPreferredSize(new Dimension(300, 50));
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtBuscar.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 2, true),
                new EmptyBorder(10, 15, 10, 15)
        ));

        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filtrar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                filtrar();
            }
        });
        panel.add(txtBuscar, BorderLayout.CENTER);
        return panel;
    }

    private JPanel crearProductos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        JLabel titulo = new JLabel("Productos en stock");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titulo, BorderLayout.WEST);
        panel.add(header, BorderLayout.NORTH);
        grid = new JPanel(new GridLayout(0, 4, 20, 20));
        grid.setOpaque(false);
        List<ProductoDTO> lista = obtenerProductos();
        for (ProductoDTO p : lista) {

            grid.add(crearCard(p));
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
        JLabel precioLbl = new JLabel("$" + p.getPrecio());
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
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida");
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
                    Coordinador.getCoordinador().eliminarProductoDelCarrito(detalle.getProducto().getId(), detalle.getCantidad());
                }

            } else {
                JOptionPane.showMessageDialog(this, "Selecciona un producto"
                );
            }
        });

        panel.add(btnQuitar);
        panel.add(Box.createVerticalStrut(20));
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 24));
        panel.add(lblTotal);
        panel.add(Box.createVerticalGlue());
        JButton pagar = new JButton("Pagar");
        pagar.setPreferredSize(new Dimension(250, 50));
        pagar.setMaximumSize(new Dimension(300, 50));
        pagar.setBackground(new Color(0, 150, 136));
        pagar.setForeground(Color.WHITE);
        pagar.setFont(new Font("Segoe UI", Font.BOLD, 20));
        pagar.setFocusPainted(false);
        pagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        pagar.addActionListener(e -> {
            if (modeloCarrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío");
                return;
            }
            controlNavegacion.getcontrolNavegacion().abrirVentaFrame();
        });
        panel.add(Box.createVerticalStrut(20));
        panel.add(pagar);
        return panel;
    }

    private List<ProductoDTO> obtenerProductos() {
        return Coordinador.getCoordinador().ObtenerProductos();
    }

    private void filtrar() {
        String texto = txtBuscar.getText();
        List<ProductoDTO> lista;
        if (texto.isEmpty()) {
            lista = Coordinador.getCoordinador().ObtenerProductos();
        } else {
            lista = Coordinador.getCoordinador().ObtenerProductosPorNombre(texto);
        }
        actualizarProductos(lista);
    }

    private void actualizarProductos(List<ProductoDTO> productos) {
        grid.removeAll();
        for (ProductoDTO p : productos) {
            grid.add(crearCard(p));
        }
        grid.revalidate();
        grid.repaint();
    }

    public void actualizarTablaCarrito(CarritoDTO carrito) {
        modeloCarrito.clear();
        total = 0;
        for (DetalleCarritoDTO detalle
                : carrito.getListaProductos()) {
            String item
                    = detalle.getProducto().getNombre()
                    + " x"
                    + detalle.getCantidad()
                    + " - $"
                    + (detalle.getProducto().getPrecio()
                    * detalle.getCantidad());
            modeloCarrito.addElement(item);
            total += (detalle.getProducto().getPrecio()
                    * detalle.getCantidad());
        }

        lblTotal.setText(
                "Total: $ "
                + String.format("%.2f", total)
        );
    }

    public void limpiarVenta() {
        modeloCarrito.clear();
        total = 0;
        lblTotal.setText("Total: $0.00");
        txtBuscar.setText("");
    }
}
