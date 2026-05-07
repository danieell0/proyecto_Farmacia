/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import DTO.DetalleVentaDTO;
import DTO.CarritoDTO;
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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import pantallas.control.Coordinador;
import pantallas.control.controlNavegacion;
import pantallas.menuFrame;

/**
 *
 * @author munos
 */
public class VentaFrame extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JLabel lblTotal;
    private JTextField txtPago;
    private JLabel lblCambio;
    
    private ICoordinador coordinador;
    private IControlNevagacion control;

    private final Color colorFondo = new Color(245, 245, 245);
    private final Color colorAzul = new Color(52, 152, 219);
    private final Color colorTexto = new Color(44, 62, 80);
    private final Color colorVerde = new Color(39, 174, 96);

    public VentaFrame() {
        setTitle("Sistema de Ventas Punto de Venta");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(colorFondo);

        String[] columnas = {"Producto", "Cantidad", "Precio", "Subtotal"};
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

        lblTotal = new JLabel("TOTAL A PAGAR: $0.00");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTotal.setForeground(colorTexto);

        JPanel filaPago = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 0));
        filaPago.setBackground(Color.WHITE);

        JLabel lblPagoPrompt = new JLabel("CANTIDAD RECIBIDA:");
        lblPagoPrompt.setFont(new Font("SansSerif", Font.BOLD, 14));

        txtPago = new JTextField(12);
        txtPago.setFont(new Font("SansSerif", Font.PLAIN, 18));
        txtPago.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, colorAzul));
        txtPago.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyReleased(java.awt.event.KeyEvent evt) {
                calcularCambio();
            }
        });

        filaPago.add(lblPagoPrompt);
        filaPago.add(txtPago);

        lblCambio = new JLabel("CAMBIO: $0.00");
        lblCambio.setFont(new Font("SansSerif", Font.BOLD, 20));
        lblCambio.setForeground(colorVerde);

        panelInformacion.add(lblTotal);
        panelInformacion.add(filaPago);
        panelInformacion.add(lblCambio);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 0));
        panelBotones.setBackground(colorFondo);
        panelBotones.setBorder(new EmptyBorder(15, 0, 0, 0));

        JButton btnCancelar = crearBotonEstilizado("CANCELAR VENTA", new Color(231, 76, 60));
        JButton btnFinalizar = crearBotonEstilizado("FINALIZAR COMPRA", colorAzul);

        btnCancelar.addActionListener(e -> {
            int confirmacion = JOptionPane.showConfirmDialog(this,
                    "¿Estas seguro de cancelar la venta? Se vaciara el carrito.",
                    "Cancelar Venta",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                coordinador.cancelarVenta(); // Esto debería limpiar el carrito y devolver stock
                if (control != null) {
                    control.abrirMenuFrame(); // Regresamos al catálogo
                }
            }
        });
        btnFinalizar.addActionListener(e -> finalizarCompra());

        panelBotones.add(btnCancelar);
        panelBotones.add(btnFinalizar);

        panelContenedorInferior.add(panelInformacion, BorderLayout.CENTER);
        panelContenedorInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelContenedorInferior, BorderLayout.SOUTH);
    }

    public void setCoordinador(Coordinador coordinador) {
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
     * Este método actualiza la JTable de la pantalla final de cobro.
     */
    public void actualizarTablaCarrito(CarritoDTO carrito) {
        ((javax.swing.table.DefaultTableModel) tabla.getModel()).setRowCount(0);
        
        double totalSeguro = 0.0;

        if (carrito != null && carrito.getListaProductos() != null) {
            for (DetalleCarritoDTO detalle : carrito.getListaProductos()) {
                
                double precio = detalle.getProducto().getPrecio();
                int cantidad = detalle.getCantidad();
                double subtotal = precio * cantidad;
                
                totalSeguro += subtotal; 

                ((javax.swing.table.DefaultTableModel) tabla.getModel()).addRow(new Object[]{
                    detalle.getProducto().getNombre(),
                    cantidad,
                    precio,
                    subtotal 
                });
            }
            
            lblTotal.setText("TOTAL A PAGAR: $" + String.format("%.2f", totalSeguro));
            
            calcularCambio();
        }
    }

    private void calcularCambio() {
        if (coordinador == null || coordinador.obtenerCarritoActual() == null) return;    

        try {
            String texto = txtPago.getText().trim();
            if (texto.isEmpty()) {
                lblCambio.setText("CAMBIO: $0.00");
                lblCambio.setForeground(colorVerde);
                return;
            }
            double pago = Double.parseDouble(texto);
            
            double totalReal = coordinador.obtenerCarritoActual().getTotalAPagar();
            
            double cambio = coordinador.procesarCalculoCambio(totalReal, pago);
            
            if (cambio == -1) { 
                lblCambio.setText("CAMBIO: $0.00 (Falta dinero)");
                lblCambio.setForeground(Color.RED);
            } else {
                lblCambio.setText("CAMBIO: $" + String.format("%.2f", cambio));
                lblCambio.setForeground(colorVerde);
            }
        } catch (NumberFormatException e) {
            lblCambio.setText("CAMBIO: Error");
        }
    }

    public void limpiarVenta() {
        modelo.setRowCount(0);
        lblTotal.setText("TOTAL A PAGAR: $0.00");
        lblCambio.setText("CAMBIO: $0.00");
        lblCambio.setForeground(colorVerde);
        txtPago.setText("");
        
    }

    /**
     * Procesa la finalización de la compra, registra la venta en el sistema
     * y redirige al usuario de vuelta al catálogo de productos.
     */
   private void finalizarCompra() {
    // 1. Validación de presentación: Tabla vacía
    if (modelo.getRowCount() == 0) {
        JOptionPane.showMessageDialog(this, "La lista de productos está vacía.", "Aviso", JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        // 2. Validación de presentación: Formato de texto
        String textoRecibido = txtPago.getText().trim();
        if (textoRecibido.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar la cantidad con la que paga el cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Double cantidadRecibida = Double.parseDouble(textoRecibido);

        if (this.coordinador != null) {
            // Pedimos el resultado al coordinador
            Double resultado = this.coordinador.ejecutarFinalizarCompra(cantidadRecibida, 1L, 1L);

            // 3. Evaluar la respuesta del subsistema
            if (resultado == null) {
                JOptionPane.showMessageDialog(this, "Error interno al procesar la venta.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (resultado == -1.0) {
                JOptionPane.showMessageDialog(this, "El carrito de compras está vacío.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else if (resultado == -2.0) {
                // AQUÍ SE CORRIGE TU ERROR DE LA CAPTURA:
                JOptionPane.showMessageDialog(this, "Dinero insuficiente para completar la venta.", "Pago Insuficiente", JOptionPane.WARNING_MESSAGE);
            } else {
                // 4. VENTA EXITOSA: Solo si el resultado no es un código de error
                JOptionPane.showMessageDialog(this, 
                        "Venta registrada con éxito.\nEntregar cambio: $" + String.format("%.2f", resultado), 
                        "Venta Exitosa", 
                        JOptionPane.INFORMATION_MESSAGE);

                // 5. Redirigir al menú (Catálogo)
                if (this.control != null) {
                    this.control.abrirMenuFrame();
                } else {
                    this.dispose(); 
                }
            }
        }

    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.", "Error de formato", JOptionPane.ERROR_MESSAGE);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Ocurrió un error inesperado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
   }
