package pantallas;

import DTO.DetalleVentaDTO;
import DTO.ProductoDTO;
import com.mycompany.dto_negocios.CarritoDTO;
import dtos.EmpleadoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
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

    public menuFrame() {
        //agregamos un titulo
        setTitle("Farmacia");
        //establecemos el tamaño del frame 
        setSize(1200, 700);
        //establecemos que se cierre el frame al darle a la x
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //agregamos un border layout
        setLayout(new BorderLayout());
        //le ponemos al frame un color
        getContentPane().setBackground(new Color(245, 245, 245));

        pantallas.control.Coordinador.getCoordinador().setMenuFrame(this);
        
        add(crearHeader(), BorderLayout.NORTH);
        add(crearContenido(), BorderLayout.CENTER);
        
    }
    
    private JPanel crearHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(180, 210, 210));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        // 1. Obtenemos al usuario activo desde tu Coordinador
        // (Asegúrate de importar DTO.EmpleadoDTO en esta clase)
        EmpleadoDTO usuarioActivo = pantallas.control.Coordinador.getCoordinador().getEmpleadoLogueado();
        
        String saludo = "Punto de Venta Farmacia";
        if (usuarioActivo != null) {
            saludo = "Atendiendo: " + usuarioActivo.getNombre() + " (" + usuarioActivo.getRolPuesto() + ")";
        }

        JLabel titulo = new JLabel(saludo);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        // 2. El botón ahora es para Cerrar Sesión
        JButton btnCerrarSesion = new JButton("Cerrar sesión");
        btnCerrarSesion.setFocusPainted(false);
        btnCerrarSesion.setBackground(new Color(220, 80, 80)); // Un tono rojizo
        btnCerrarSesion.setForeground(Color.WHITE);
        
        btnCerrarSesion.addActionListener(e -> {
            // Confirmación opcional
            int respuesta = JOptionPane.showConfirmDialog(this, 
                "¿Estás seguro de que deseas cerrar sesión?", 
                "Cerrar Sesión", JOptionPane.YES_NO_OPTION);
                
            if (respuesta == JOptionPane.YES_OPTION) {
                // Limpiamos el usuario del coordinador llamando al método que hicimos
                pantallas.control.Coordinador.getCoordinador().cerrarSesion();
                
                // Cerramos este menú
                this.dispose(); 
                
                // Opcional: Volvemos a levantar "El Muro" para el siguiente empleado
                pantallas.control.controlNavegacion.getcontrolNavegacion().abrirLogin();
                if (pantallas.control.Coordinador.getCoordinador().getEmpleadoLogueado() == null) {
                    System.exit(0);
                }
            }
        });

        panel.add(titulo, BorderLayout.WEST);
        panel.add(btnCerrarSesion, BorderLayout.EAST);

        return panel;
    }
    
    //panel de contenido
    public JPanel crearContenido() {
        //creo un panel y le asigno un border layout
        JPanel panel = new JPanel(new BorderLayout());
        //le pongo un margen
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));
        //agrego un color de background
        panel.setBackground(new Color(245, 245, 245));
        //agrego la barra buscadora 
        panel.add(crearBuscador(), BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setOpaque(false);
        panelCentro.add(crearProductos(), BorderLayout.CENTER);
        panelCentro.add(crearCarrito(), BorderLayout.EAST);

        panel.add(panelCentro, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearBuscador() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 0, 10, 0));
        panel.setOpaque(false);

        txtBuscar = new JTextField("Buscar Producto...");
        txtBuscar.setPreferredSize(new Dimension(300, 40));
        txtBuscar.setBorder(new CompoundBorder(
                new LineBorder(Color.LIGHT_GRAY, 1, true),
                new EmptyBorder(5, 10, 5, 10)
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
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.add(titulo, BorderLayout.WEST);

        panel.add(header, BorderLayout.NORTH);

        grid = new JPanel(new GridLayout(0, 4, 15, 15));
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
        card.setBorder(new CompoundBorder(new LineBorder(new Color(220, 220, 220), 1, true), new EmptyBorder(10, 10, 10, 10)));

        ImageIcon icon = new ImageIcon(getClass().getResource(p.getImagen()));
        Image img = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JLabel lblImg = new JLabel(new ImageIcon(img));
        lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nombreLbl = new JLabel(p.getNombre());
        nombreLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        String precio = String.valueOf(p.getPrecio());
        JLabel precioLbl = new JLabel("$" + precio);
        precioLbl.setForeground(new Color(0, 130, 0));
        precioLbl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(80, 140, 100));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnAgregar.addActionListener(e -> {
//            modeloCarrito.addElement(p.getNombre() + "- $" + p.getPrecio());
//            total += p.getPrecio();
//            lblTotal.setText("Total: $ " + String.format("%.2f", total));
            Coordinador.getCoordinador().agregarProductoAlCarrito(p, 1);
        });

        card.add(lblImg);
        card.add(Box.createVerticalStrut(10));
        card.add(nombreLbl);
        card.add(precioLbl);
        card.add(Box.createVerticalStrut(10));
        card.add(btnAgregar);

        return card;
    }

    private JPanel crearCarrito() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 0));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.WHITE);
        panel.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));

        JLabel titulo = new JLabel("Carrito");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));

        panel.add(titulo);
        panel.add(Box.createVerticalStrut(10));

        modeloCarrito = new DefaultListModel<>();
        listaCarrito = new JList<>(modeloCarrito);
        
        JScrollPane scroll = new JScrollPane(listaCarrito);
        scroll.setPreferredSize(new Dimension(200, 200));
        
        // Lo agregamos una sola vez
        panel.add(scroll);
        panel.add(Box.createVerticalStrut(10));

        lblTotal = new JLabel("Total: $0.00");
        panel.add(lblTotal);

        panel.add(Box.createVerticalStrut(10));

        JButton pagar = new JButton("Pagar");
        pagar.setBackground(new Color(80, 140, 100));
        pagar.setForeground(Color.WHITE);
        
        pagar.addActionListener(e -> {
            // validacion de carrito vacio
            if (modeloCarrito.isEmpty()) {
                JOptionPane.showMessageDialog(this, 
                    "El carrito está vacío. Agrega productos primero.", 
                    "Aviso", JOptionPane.WARNING_MESSAGE);
                return; // Cortamos la ejecución aquí
            }

            // Usamos tu Singleton de navegación para cambiar a la pantalla de ventas
            pantallas.control.controlNavegacion.getcontrolNavegacion().abrirVentaFrame();
        });

        panel.add(pagar);

        return panel;
    }

    private List<ProductoDTO> obtenerProductos() {
        return Coordinador.getCoordinador().ObtenerProductos();
    }

    private void filtrar() {
        String texto = txtBuscar.getText();

        List<ProductoDTO> lista;

        if (texto.isEmpty() || "Buscar Producto...".equals(texto)) {
            lista = Coordinador.getCoordinador().ObtenerProductos();
        } else {
            lista = Coordinador.getCoordinador().ObtenerProductosPorNombre(texto);
        }
        actualizarProductos(lista);
    }

    private void actualizarProductos(List<ProductoDTO> productos) {
        grid.removeAll();

        // Volver a agregar los productos filtrados
        for (ProductoDTO p : productos) {
            grid.add(crearCard(p));
        }

        // Refrescar la vista
        grid.revalidate();
        grid.repaint();
    }

    /**
    * Este método lo llama el Coordinador después de validar la receta.
    */
   public void actualizarTablaCarrito(CarritoDTO carrito) {
       modeloCarrito.clear(); // Limpiamos la JList visual
       total = 0;

       for (DetalleVentaDTO detalle : carrito.getListaProductos()) {
           // Creamos el texto para la lista: "Nombre xCantidad - $Subtotal"
           String item = detalle.getProducto().getNombre() + " x" + detalle.getCantidad() + 
                         " - $" + (detalle.getProducto().getPrecio() * detalle.getCantidad());

           modeloCarrito.addElement(item);
           total += (detalle.getProducto().getPrecio() * detalle.getCantidad());
       }

       // Actualizamos el label del total con formato de 2 decimales
       lblTotal.setText("Total: $ " + String.format("%.2f", total));
   }

   /**
    * Limpia la vista después de una venta exitosa.
    */
    public void limpiarVenta() {
       modeloCarrito.clear();
       total = 0;
       lblTotal.setText("Total: $0.00");
       txtBuscar.setText("Buscar Producto...");
   }
}
/*
    //Este es el header 
    private JPanel crearHeader() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(180, 210, 210));
        panel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel titulo = new JLabel("Pantalla Inicial");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));

        JButton btnIniciarSesion = new JButton("Iniciar sesion");
        btnIniciarSesion.setFocusPainted(false);
        
        // 2. Le agregamos el evento de clic
        btnIniciarSesion.addActionListener(e -> {
            // Mandamos a llamar al método que creamos en controlNavegacion
            pantallas.control.controlNavegacion.getcontrolNavegacion().abrirLogin();
        });

        panel.add(titulo, BorderLayout.WEST);
        
        // 3. Agregamos el nuevo botón a la derecha
        panel.add(btnIniciarSesion, BorderLayout.EAST);

        return panel;
    }
*/
