/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTO.MedicamentoDTO;
import DTO.MovimientoSalidaDTO;
import DTO.ProductoDTO;
import interfaces.IControlNavegacion;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import pantallas.control.Coordinador;

/**
 *
 * @author Jorge
 */
public class registrarSalidaFrame extends JFrame {

    private JTextField txtBuscar;
    private JTextField txtMotivo;
    private JTextArea txtObservaciones;
    private JLabel txtCodigo;
    private JLabel txtNombre;
    private JLabel txtDosis;
    private JLabel txtMedida;
    private JLabel txtUnidad;
    private JLabel txtStock;
    private JLabel txtMarca;
    private JSpinner spinnerCantidad;
    private DefaultListModel<String> modeloLista;
    private JList<String> listaSugerencias;
    private JScrollPane scrollSugerencias;
    private Coordinador coordinador;
    private List<ProductoDTO> productosEncontrados;
    private ProductoDTO productoSeleccionado;
    private JButton btnRegistrar;
    private JButton btnCancelar;
    private IControlNavegacion controlNav;
    private JButton btnSalir;

    public registrarSalidaFrame(IControlNavegacion controlNav) {
        this.controlNav=controlNav;
        coordinador = new Coordinador();
        productosEncontrados = new ArrayList<>();
        setTitle("Registrar salida");
        setSize(1500, 720);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1200, 650));
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(new Color(245, 245, 245));
        panelPrincipal.setLayout(new BorderLayout(15, 15));
        JScrollPane scrollPrincipal = new JScrollPane(panelPrincipal);
        scrollPrincipal.setBorder(null);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        add(scrollPrincipal, BorderLayout.CENTER);

        JPanel header = new JPanel();
        header.setBackground(new Color(0, 70, 200));
        header.setPreferredSize(new Dimension(1500, 80));
        header.setLayout(new BorderLayout());
        JLabel titulo = new JLabel("Registrar salida de medicamento");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 34));
        titulo.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 15));
        header.add(titulo, BorderLayout.WEST);
        panelPrincipal.add(header, BorderLayout.NORTH);

        JPanel centro = new JPanel();
        centro.setBackground(new Color(245, 245, 245));
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelPrincipal.add(centro, BorderLayout.CENTER);

        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)), BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        panelBusqueda.setLayout(new BorderLayout(20, 10));
        JPanel filaBusqueda = new JPanel(new BorderLayout(20, 0));
        filaBusqueda.setOpaque(false);
        JLabel lblMedicamento = new JLabel("Medicamento:");
        lblMedicamento.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblMedicamento.setForeground(new Color(0, 70, 200));
        filaBusqueda.add(lblMedicamento, BorderLayout.WEST);
        txtBuscar = new JTextField();
        txtBuscar.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        filaBusqueda.add(txtBuscar, BorderLayout.CENTER);
        panelBusqueda.add(filaBusqueda, BorderLayout.NORTH);

        modeloLista = new DefaultListModel<>();
        listaSugerencias = new JList<>(modeloLista);
        listaSugerencias.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        scrollSugerencias = new JScrollPane(listaSugerencias);
        scrollSugerencias.setPreferredSize(new Dimension(100, 90));
        scrollSugerencias.setVisible(false);
        panelBusqueda.add(scrollSugerencias, BorderLayout.CENTER);
        centro.add(panelBusqueda);
        centro.add(Box.createVerticalStrut(20));

        JPanel panelInfo = new JPanel();
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)), BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        panelInfo.setLayout(new GridLayout(2, 6, 20, 25));
        Font fontTitulo = new Font("Segoe UI", Font.BOLD, 20);
        txtCodigo = crearLabelValor("-");
        txtNombre = crearLabelValor("-");
        txtDosis = crearLabelValor("-");
        txtMedida = crearLabelValor("-");
        txtUnidad = crearLabelValor("-");
        txtStock = crearLabelValor("0");
        txtMarca = crearLabelValor("-");
        panelInfo.add(crearPanelDato("Código:", txtCodigo, fontTitulo));
        panelInfo.add(crearPanelDato("Nombre:", txtNombre, fontTitulo));
        panelInfo.add(crearPanelDato("Dosis:", txtDosis, fontTitulo));
        panelInfo.add(crearPanelDato("Medida:", txtMedida, fontTitulo));
        panelInfo.add(crearPanelDato("Unidad:", txtUnidad, fontTitulo));
        panelInfo.add(crearPanelDato("Stock:", txtStock, fontTitulo));
        panelInfo.add(crearPanelDato("Marca:", txtMarca, fontTitulo));

        centro.add(panelInfo);
        centro.add(Box.createVerticalStrut(20));
        JPanel panelDatos = new JPanel();
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)), BorderFactory.createEmptyBorder(25, 25, 25, 25)));
        panelDatos.setLayout(new GridLayout(1, 2, 40, 20));
        centro.add(panelDatos);

        JPanel izquierda = new JPanel();
        izquierda.setOpaque(false);
        izquierda.setLayout(new GridLayout(4, 1, 10, 20));
        JLabel lblCantidad = new JLabel("Cantidad a retirar:");
        lblCantidad.setFont(fontTitulo);
        izquierda.add(lblCantidad);
        SpinnerNumberModel modeloSpinner = new SpinnerNumberModel(0, 0, 9999, 1);
        spinnerCantidad = new JSpinner(modeloSpinner);
        spinnerCantidad.setFont(fontTitulo);
        izquierda.add(spinnerCantidad);
        JLabel lblMotivo = new JLabel("Motivo de salida:");
        lblMotivo.setFont(fontTitulo);
        izquierda.add(lblMotivo);
        txtMotivo = new JTextField();
        txtMotivo.setFont(fontTitulo);
        izquierda.add(txtMotivo);
        panelDatos.add(izquierda);

        JPanel derecha = new JPanel();
        derecha.setOpaque(false);
        derecha.setLayout(new BorderLayout(10, 10));
        JLabel lblObservaciones = new JLabel("Observaciones:");
        lblObservaciones.setFont(fontTitulo);
        derecha.add(lblObservaciones, BorderLayout.NORTH);
        txtObservaciones = new JTextArea();
        txtObservaciones.setFont(fontTitulo);
        txtObservaciones.setLineWrap(true);
        txtObservaciones.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(txtObservaciones);
        derecha.add(scroll, BorderLayout.CENTER);
        panelDatos.add(derecha);

        JPanel footer = new JPanel();
        footer.setBackground(new Color(245, 245, 245));
        footer.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));
        footer.setLayout(new BorderLayout());
        JLabel lblFecha = new JLabel(LocalDate.now().toString());
        lblFecha.setForeground(new Color(0, 70, 200));
        lblFecha.setFont(new Font("Segoe UI", Font.BOLD, 20));
        footer.add(lblFecha, BorderLayout.WEST);

        JPanel panelBotones = new JPanel();
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        btnSalir = crearBoton("Salir", Color.BLACK);
        btnCancelar = crearBoton("Cancelar", new Color(255, 0, 0));
        btnRegistrar = crearBoton("Registrar salida", new Color(0, 70, 200));
        panelBotones.add(btnSalir);
        panelBotones.add(btnCancelar);
        panelBotones.add(btnRegistrar);
        footer.add(panelBotones, BorderLayout.EAST);
        panelPrincipal.add(footer, BorderLayout.SOUTH);
        configurarBusqueda();
        configurarSeleccionProducto();
        configurarBotonRegistrar();
        configuracionBotonCancelar();
        btnSalir.addActionListener(e->{
            controlNav.abrirMenuMovimientos();
        });
        setVisible(true);
    }

    private JLabel crearLabelValor(String texto) {
        JLabel label = new JLabel(texto);
        label.setForeground(new Color(0, 70, 200));
        label.setFont(new Font("Segoe UI", Font.BOLD, 20));
        return label;
    }

    private JPanel crearPanelDato(String titulo, JLabel valor, Font fuente) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BorderLayout(5, 5));
        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(fuente);
        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(valor, BorderLayout.CENTER);
        return panel;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setPreferredSize(new Dimension(200, 55));
        boton.setBackground(color);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        return boton;
    }

    private void configurarBusqueda() {
        txtBuscar.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscar();
            }

            private void buscar() {
                try {
                    String texto = txtBuscar.getText().trim();
                    modeloLista.clear();
                    productosEncontrados.clear();
                    if (texto.isEmpty()) {
                        scrollSugerencias.setVisible(false);
                        return;
                    }
                    List<ProductoDTO> productosNombre = coordinador.ObtenerProductosPorNombre(texto);
                    List<ProductoDTO> productosClave = coordinador.ObtenerProductoPorCodigo(texto);
                    productosEncontrados.addAll(productosNombre);
                    for (ProductoDTO producto : productosClave) {
                        boolean existe = false;
                        for (ProductoDTO existente : productosEncontrados) {
                            if (existente.getIdProducto().equals(producto.getIdProducto())) {
                                existe = true;
                                break;
                            }
                        }
                        if (!existe) {
                            productosEncontrados.add(producto);
                        }
                    }
                    boolean encontrado = false;
                    for (ProductoDTO producto : productosEncontrados) {
                        modeloLista.addElement(producto.getIdProducto() + " - " + producto.getNombre());
                        encontrado = true;
                    }
                    scrollSugerencias.setVisible(encontrado);
                    revalidate();
                    repaint();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }

    private void configurarSeleccionProducto() {
        listaSugerencias.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int indice = listaSugerencias.getSelectedIndex();
                if (indice == -1) {
                    return;
                }
                productoSeleccionado = productosEncontrados.get(indice);
                txtBuscar.setText(productoSeleccionado.getNombre());
                scrollSugerencias.setVisible(false);
                txtCodigo.setText(productoSeleccionado.getIdProducto());
                txtNombre.setText(productoSeleccionado.getNombre());
                txtMarca.setText(productoSeleccionado.getMarca());
                txtStock.setText(String.valueOf(productoSeleccionado.getStock()));
                if (productoSeleccionado instanceof MedicamentoDTO medicamento) {
                    txtDosis.setText(String.valueOf(medicamento.getDosis()));
                    txtMedida.setText(medicamento.getMedida().toString());
                    txtUnidad.setText(medicamento.getPresentacion());
                } else {
                    txtDosis.setText("-");
                    txtMedida.setText("-");
                    txtUnidad.setText("Producto");
                }
                SpinnerNumberModel modelo = new SpinnerNumberModel(1, 1, (int) productoSeleccionado.getStock(), 1);
                spinnerCantidad.setModel(modelo);
            }
        });
    }

    private void configurarBotonRegistrar() {
        btnRegistrar.addActionListener(e -> {
            try {
                if (productoSeleccionado == null) {
                    JOptionPane.showMessageDialog(this, "Debe seleccionar un producto");
                    return;
                }
                Integer cantidad = (Integer) spinnerCantidad.getValue();
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser mayor a cero");
                    return;
                }
                if (cantidad > productoSeleccionado.getStock()) {
                    JOptionPane.showMessageDialog(this, "No hay suficiente stock");
                    return;
                }
                if (txtMotivo.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Debe ingresar un motivo");
                    return;
                }
                MovimientoSalidaDTO movimiento = new MovimientoSalidaDTO();
                movimiento.setFechaHora(LocalDateTime.now());
                movimiento.setIdEmpleado("123");
                movimiento.setProducto(productoSeleccionado);
                movimiento.setCantidad(cantidad);
                movimiento.setMotivo(txtMotivo.getText().trim());
                movimiento.setObservacion(txtObservaciones.getText().trim());
                Integer stockAnterior = productoSeleccionado.getStock();
                Integer nuevoStock = stockAnterior - cantidad;
                movimiento.setCantidadAnterior(stockAnterior);
                movimiento.setCantidadNueva(nuevoStock);
                Boolean registrado = coordinador.registrarMovimientoSalida(movimiento);
                if (registrado) {
                    JOptionPane.showMessageDialog(this, "Salida registrada correctamente");
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo registrar la salida");
                }
                txtBuscar.setText("");
                txtCodigo.setText("");
                txtDosis.setText("");
                txtMarca.setText("");
                txtMedida.setText("");
                txtMotivo.setText("");
                txtNombre.setText("");
                txtObservaciones.setText("");
                txtStock.setText("");
                txtUnidad.setText("");
                spinnerCantidad.setValue(0);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }

    private void configuracionBotonCancelar() {
        btnCancelar.addActionListener(e -> {
            txtBuscar.setText("");
            txtCodigo.setText("");
            txtDosis.setText("");
            txtMarca.setText("");
            txtMedida.setText("");
            txtMotivo.setText("");
            txtNombre.setText("");
            txtObservaciones.setText("");
            txtStock.setText("");
            txtUnidad.setText("");
            spinnerCantidad.setValue(0);
        });
    }
}
