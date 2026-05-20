package pantallas;

import DTO.CanjeableDTO;
import DTO.CarritoDTO;
import DTO.ClienteDTO;
import DTO.DetalleCarritoDTO;
import interfaces.IControlNevagacion;
import interfaces.ICoordinador;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import pantallas.control.Coordinador;

/**
 *
 * @author Dario
 */
public class VentaPuntosFrame extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotalPuntos;
    private JLabel lblSaldoActual;
    private JLabel lblSaldoRestante;
    
    private double totalPuntosCanje = 0.0;
    private ICoordinador coordinador;
    private IControlNevagacion control;
    private ClienteDTO clienteActual;

    private final Color colorFondo = new Color(245, 245, 245);
    private final Color colorAzul = new Color(52, 152, 219);
    private final Color colorTexto = new Color(44, 62, 80);
    private final Color colorVerde = new Color(39, 174, 96);

    public VentaPuntosFrame() {
        setTitle("Sistema de Ventas - Confirmación de Canje por Puntos");
        setSize(900, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);
        this.clienteActual = Coordinador.getCoordinador().getClienteActual();
        String[] columnas = {"Producto", "Cantidad", "Costo Pts", "Subtotal Pts"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;  
            }
        };        
        tabla = new JTable(modelo);
        configurarEstiloTabla();
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 20));
        scroll.getViewport().setBackground(Color.WHITE);
        add(scroll, BorderLayout.CENTER);
        JPanel panelContenedorInferior = new JPanel(new BorderLayout());
        panelContenedorInferior.setBackground(colorFondo);
        panelContenedorInferior.setBorder(new EmptyBorder(10, 25, 25, 25));
        JPanel panelInformacion = new JPanel(new GridLayout(3, 1, 0, 10));
        panelInformacion.setBackground(Color.WHITE);
        panelInformacion.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(220, 220, 220), 1, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        lblTotalPuntos = new JLabel("TOTAL VALOR CANJE: 0.00 Pts");
        lblTotalPuntos.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTotalPuntos.setForeground(colorTexto);
        double saldoPts = (clienteActual != null) ? clienteActual.getPuntos() : 0.0;
        lblSaldoActual = new JLabel("SALDO ACTUAL CLIENTE: " + String.format("%.2f", saldoPts) + " Pts");
        lblSaldoActual.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblSaldoActual.setForeground(colorTexto);
        lblSaldoRestante = new JLabel("SALDO RESTANTE FINAL: 0.00 Pts");
        lblSaldoRestante.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblSaldoRestante.setForeground(colorVerde);

        panelInformacion.add(lblTotalPuntos);
        panelInformacion.add(lblSaldoActual);
        panelInformacion.add(lblSaldoRestante);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotones.setBackground(colorFondo);
        panelBotones.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnCancelar = crearBotonEstilizado("CANCELAR CANJE", new Color(231, 76, 60));
        JButton btnFinalizar = crearBotonEstilizado("CONFIRMAR CANJE", colorAzul);

        btnCancelar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estás seguro de cancelar el proceso de canje? Se vaciará el carrito.",
                    "Cancelar Canje",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                coordinador.cancelarVenta(); 
                if (control != null) {
                    control.abrirMenuPuntosFrame();
                }
            }
        });
        
        btnFinalizar.addActionListener(e -> finalizarCanje());

        panelBotones.add(btnCancelar);
        panelBotones.add(btnFinalizar);

        panelContenedorInferior.add(panelInformacion, BorderLayout.CENTER);
        panelContenedorInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelContenedorInferior, BorderLayout.SOUTH);
    }

    public void setCoordinador(ICoordinador coordinador) {
        this.coordinador = coordinador;
    }
    public void setControlNavegacion(IControlNevagacion control) {
        this.control = control;
    }

    private void configurarEstiloTabla() {
        tabla.setRowHeight(35);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 14));
        tabla.setSelectionBackground(new Color(235, 245, 255));
        tabla.setShowVerticalLines(false);
        tabla.setGridColor(new Color(240, 240, 240));
        
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(colorAzul);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setPreferredSize(new Dimension(0, 40));
    }

    private JButton crearBotonEstilizado(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setForeground(Color.WHITE);
        btn.setBackground(fondo);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(12, 25, 12, 25));
        return btn;
    }

    /**
     * Mismo método de refresco pero procesando el modelo de datos de Puntos de manera polimórfica
     */
    public void actualizarTablaCarrito(CarritoDTO carrito) {
        if (this.coordinador != null) {
            this.clienteActual = this.coordinador.getClienteActual();
            if (this.clienteActual != null) {
                lblSaldoActual.setText("SALDO ACTUAL CLIENTE: " + String.format("%.2f", this.clienteActual.getPuntos()) + " Pts");
            }
        }

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0);
        this.totalPuntosCanje = 0.0;

        if (carrito != null && carrito.getListaProductos() != null) {
            for (DetalleCarritoDTO detalle : carrito.getListaProductos()) {
                
                if (detalle == null || detalle.getProducto() == null) {
                    continue;
                }

                double puntosUnidad = 0.0;

                if (detalle.getProducto() instanceof CanjeableDTO canjeable) {
                    puntosUnidad = canjeable.getPuntos();
                } else {
                    puntosUnidad = detalle.getProducto().getPrecio(); 
                }
                if (puntosUnidad == 0.0) {
                    try {
                        puntosUnidad = Double.parseDouble(String.valueOf(detalle.getProducto().getPrecio()));
                    } catch (Exception e) {
                        puntosUnidad = 0.0;
                    }
                }

                int cantidad = detalle.getCantidad();
                double subtotalPuntos = puntosUnidad * cantidad;

                this.totalPuntosCanje += subtotalPuntos; 

                modeloTabla.addRow(new Object[]{
                    detalle.getProducto().getNombre(),
                    cantidad,
                    puntosUnidad,
                    subtotalPuntos 
                });
            }
            
            lblTotalPuntos.setText("TOTAL VALOR CANJE: " + String.format("%.2f", this.totalPuntosCanje) + " Pts");
            calcularRestaPuntos();
        }
    }
    
    /**
     * Reemplazo lógico a tu método calcularCambio(). Hace la resta automática instantánea.
     */
    private void calcularRestaPuntos() {
        double saldoCliente = (clienteActual != null) ? clienteActual.getPuntos() : 0.0;
        double saldoRestante = saldoCliente - totalPuntosCanje;

        if (saldoRestante < 0) {
            lblSaldoRestante.setText("SALDO RESTANTE FINAL: " + String.format("%.2f", saldoRestante) + " Pts (Insuficientes ❌)");
            lblSaldoRestante.setForeground(Color.RED);
        } else {
            lblSaldoRestante.setText("SALDO RESTANTE FINAL: " + String.format("%.2f", saldoRestante) + " Pts");
            lblSaldoRestante.setForeground(colorVerde);
        }
    }

    public void limpiarVenta() {
        modelo.setRowCount(0);
        lblTotalPuntos.setText("TOTAL VALOR CANJE: 0.00 Pts");
        lblSaldoRestante.setText("SALDO RESTANTE FINAL: 0.00 Pts");
        lblSaldoRestante.setForeground(colorVerde);
    }

    /**
     * Adaptación limpia de tu método finalizarCompra() enfocado en lógica de puntos
     */
    private void finalizarCanje() {
        if (modelo.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "La lista de productos está vacía.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        double saldoCliente = (clienteActual != null) ? clienteActual.getPuntos() : 0.0;
        if (saldoCliente < totalPuntosCanje) {
            JOptionPane.showMessageDialog(this, "El cliente no tiene puntos suficientes para este canje.", "Puntos Insuficientes", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirmacion = JOptionPane.showConfirmDialog(this, 
                "¿Desea procesar el canje?\nSe descontarán " + String.format("%.2f", totalPuntosCanje) + " puntos al cliente.", 
                "Confirmar Operación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirmacion != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (this.coordinador != null) {
                String idCajeroActivo = String.valueOf(this.coordinador.obtenerSesionActual().getIdEmpleado());
                String idCliente = (clienteActual != null) ? clienteActual.getIdCliente() : "1L";

                Double resultadoSaldoFinal = this.coordinador.ejecutarFinalizarCompra("PUNTOS", totalPuntosCanje, idCajeroActivo, idCliente);
                
                if (resultadoSaldoFinal == null) {
                    JOptionPane.showMessageDialog(this, "Error interno al procesar el canje.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (resultadoSaldoFinal == -1.0) {
                    JOptionPane.showMessageDialog(this, "El carrito de compras está vacío.", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, 
                            "Canje registrado con éxito.\nNuevo saldo del cliente: " + String.format("%.2f", resultadoSaldoFinal) + " Pts", 
                            "Canje Exitoso", 
                            JOptionPane.INFORMATION_MESSAGE);

                    this.coordinador.cancelarVenta();
                    if (this.control != null) {
                        this.control.abrirMenuFrame();
                    } else {
                        this.dispose(); 
                    }
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
