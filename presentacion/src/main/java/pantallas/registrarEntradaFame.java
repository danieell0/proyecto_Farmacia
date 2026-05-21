/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

import Bo.NegocioException;
import DTO.DetalleLoteDTO;
import DTO.DetalleSolicitudDTO;
import DTO.LoteDTO;
import DTO.MedicamentoDTO;
import DTO.MovimientoEntradaDTO;
import DTO.ProductoDTO;
import DTO.SolicitudDTO;
import com.mysql.cj.CoreSession;
import interfaces.ICoordinador;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import pantallas.control.Coordinador;

/**
 *
 * @author Jorge
 */
public class registrarEntradaFame extends JFrame {

    private ICoordinador coordinador;
    private JTextField txtCodigoPedido;
    private JTextField txtCodigoLote;
    private JTextField txtProveedor;
    private JTextArea txtObs;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JButton btnRegistrar;

    public registrarEntradaFame() {
        this.coordinador = new Coordinador();
        setTitle("Registrar entrada");
        setSize(1350, 820);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(new Color(245, 245, 245));
        JPanel header = new JPanel();
        header.setBackground(new Color(0, 110, 60));
        header.setBounds(0, 0, 1350, 60);
        header.setLayout(null);
        JLabel tituloHeader = new JLabel("Registrar entrada de medicamentos");
        tituloHeader.setForeground(Color.WHITE);
        tituloHeader.setFont(new Font("Segoe UI", Font.BOLD, 26));
        tituloHeader.setBounds(30, 10, 700, 35);
        header.add(tituloHeader);
        add(header);

        JPanel panelPedido = new JPanel();
        panelPedido.setLayout(null);
        panelPedido.setBackground(Color.WHITE);
        panelPedido.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        panelPedido.setBounds(30, 85, 620, 200);
        add(panelPedido);

        JLabel lblPedido = new JLabel("Información del pedido");
        lblPedido.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblPedido.setForeground(new Color(0, 80, 40));
        lblPedido.setBounds(25, 15, 350, 35);
        panelPedido.add(lblPedido);

        JLabel lblCodigoPedido = new JLabel("Código de pedido:");
        lblCodigoPedido.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblCodigoPedido.setBounds(25, 75, 220, 30);
        panelPedido.add(lblCodigoPedido);

        txtCodigoPedido = new JTextField();
        txtCodigoPedido.setBounds(230, 70, 340, 40);
        txtCodigoPedido.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelPedido.add(txtCodigoPedido);
        JLabel lblCodigoLote = new JLabel("Código de lote:");
        lblCodigoLote.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblCodigoLote.setBounds(25, 130, 220, 30);
        panelPedido.add(lblCodigoLote);

        txtCodigoLote = new JTextField();
        txtCodigoLote.setBounds(230, 125, 340, 40);
        txtCodigoLote.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelPedido.add(txtCodigoLote);
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        panelInfo.setBounds(680, 85, 620, 200);
        add(panelInfo);

        JLabel lblInfo = new JLabel("Información adicional");
        lblInfo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblInfo.setForeground(new Color(0, 80, 40));
        lblInfo.setBounds(25, 15, 350, 35);
        panelInfo.add(lblInfo);

        JLabel lblFecha = new JLabel("Fecha:");
        lblFecha.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblFecha.setBounds(25, 75, 150, 30);
        panelInfo.add(lblFecha);

        JTextField txtFecha = new JTextField(LocalDate.now().toString());
        txtFecha.setBounds(210, 70, 340, 40);
        txtFecha.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        txtFecha.setEditable(false);
        panelInfo.add(txtFecha);

        JLabel lblProveedor = new JLabel("Proveedor:");
        lblProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        lblProveedor.setBounds(25, 130, 150, 30);
        panelInfo.add(lblProveedor);
        txtProveedor = new JTextField(" ");
        txtProveedor.setBounds(210, 125, 340, 40);
        txtProveedor.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panelInfo.add(txtProveedor);

        JPanel panelTabla = new JPanel();
        panelTabla.setLayout(null);
        panelTabla.setBackground(Color.WHITE);
        panelTabla.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        panelTabla.setBounds(30, 320, 1270, 330);
        add(panelTabla);

        JLabel lblDetalle = new JLabel("Detalle de medicamentos");
        lblDetalle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lblDetalle.setForeground(new Color(0, 80, 40));
        lblDetalle.setBounds(25, 15, 400, 35);
        panelTabla.add(lblDetalle);

        JButton btnAgregar = new JButton("+ Agregar medicamento");
        btnAgregar.setBounds(980, 15, 240, 40);
        btnAgregar.setBackground(Color.WHITE);
        btnAgregar.setForeground(new Color(0, 120, 60));
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 17));
        panelTabla.add(btnAgregar);

        String[] columnas = {"Código", "Medicamento", "Marca", "Presentación", "Cantidad pedida", "Cantidad recibida", "Observaciones"};

        Object[][] datos = {};

        tabla = new JTable(datos, columnas);
        tabla.setRowHeight(38);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 16));
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(25, 70, 1210, 220);
        panelTabla.add(scroll);

        JPanel panelObs = new JPanel();
        panelObs.setLayout(null);
        panelObs.setBackground(Color.WHITE);
        panelObs.setBorder(BorderFactory.createLineBorder(new Color(225, 225, 225)));
        panelObs.setBounds(30, 680, 520, 100);
        add(panelObs);

        JLabel lblObs = new JLabel("Observaciones generales:");
        lblObs.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblObs.setForeground(new Color(0, 80, 40));
        lblObs.setBounds(20, 10, 320, 25);
        panelObs.add(lblObs);

        txtObs = new JTextArea();
        txtObs.setText("Observaciones..");
        txtObs.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        JScrollPane scrollObs = new JScrollPane(txtObs);
        scrollObs.setBounds(20, 40, 470, 40);
        panelObs.add(scrollObs);

        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(670, 705, 160, 50);
        btnSalir.setBackground(new Color(45, 45, 45));
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(btnSalir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(870, 705, 190, 50);
        btnCancelar.setBackground(new Color(255, 50, 50));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(btnCancelar);

        btnRegistrar = new JButton("Registrar entrada");
        btnRegistrar.setBounds(1100, 705, 200, 50);
        btnRegistrar.setBackground(new Color(0, 120, 60));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        add(btnRegistrar);

        txtCodigoPedido.addActionListener(e -> {
            try {
                SolicitudDTO solicitud = coordinador.buscarSolicitud(txtCodigoPedido.getText());
                cargarDetallesSolicitud(solicitud);
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        txtCodigoLote.addActionListener(e -> {
            try {
                LoteDTO lote = coordinador.obtenerLote(txtCodigoLote.getText());
                if (lote != null) {
                    JOptionPane.showMessageDialog(this, "Este codigo ya fue utilizado");
                    txtCodigoLote.setText("");
                    txtCodigoLote.requestFocus();
                    return;
                }
            } catch (NegocioException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        btnAgregar.addActionListener(e -> {
            try {
                String codigo = JOptionPane.showInputDialog(this, "Ingrese el codigo del producto:");
                if (codigo == null || codigo.trim().isEmpty()) {
                    return;
                }
                ProductoDTO producto = coordinador.ObtenerProductoPorCodigo(codigo).getFirst();
                if (producto == null) {
                    JOptionPane.showMessageDialog(this, "No existe un producto con ese codigo");
                    return;
                }
                String presentacion = "";
                if (producto instanceof MedicamentoDTO medicamento) {
                    presentacion = medicamento.getPresentacion();
                }

                modeloTabla.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getMarca(),
                    presentacion,
                    0,
                    0,
                    ""
                });
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al agregar producto");
            }
        });

        btnRegistrar.addActionListener(e -> {
            try {
                MovimientoEntradaDTO movimiento= new MovimientoEntradaDTO();
                movimiento.setFechaHora(LocalDateTime.now());
                movimiento.setIdEmpleado("123");
                //movimiento.setIdEmpleado(coordinador.obtenerSesionActual().getIdEmpleado());
                LoteDTO lote = new LoteDTO();
                lote.setCodigoLote(txtCodigoLote.getText());
                lote.setProveedor(txtProveedor.getText());
                lote.setObservacionGeneral(txtObs.getText());
                List<DetalleLoteDTO> detalles= new ArrayList<>();
                for (int i = 0; i < modeloTabla.getRowCount(); i++) {
                    DetalleLoteDTO detalle = new DetalleLoteDTO();
                    ProductoDTO producto = coordinador.ObtenerProductoPorCodigo(modeloTabla.getValueAt(i, 0).toString()).getFirst();
                    detalle.setProducto(producto);
                    detalle.setCantidadSolicitada(Integer.parseInt(modeloTabla.getValueAt(i, 4).toString()));
                    detalle.setCantidadRecibida(Integer.parseInt(modeloTabla.getValueAt(i, 5).toString()));
                    detalle.setObservacion(modeloTabla.getValueAt(i, 6).toString());
                    detalles.add(detalle);
                }
                lote.setProveedor(txtProveedor.getText());
                lote.setObservacionGeneral(txtObs.getText());
                lote.setDetalles(detalles);
                movimiento.setLote(lote);
                Boolean registrado = coordinador.registrarMovimientoEntrada(movimiento);
                if (registrado) {
                    JOptionPane.showMessageDialog(this, "Movimiento registrado correctamente");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        setVisible(true);
    }

    private void cargarDetallesSolicitud(SolicitudDTO solicitud) {
        modeloTabla.setRowCount(0);
        for (DetalleSolicitudDTO detalle : solicitud.getDetalles()) {
            String presentacion = "";
            if (detalle.getProducto() instanceof MedicamentoDTO medicamento) {
                presentacion = medicamento.getPresentacion();
            }
            Object[] fila = {
                detalle.getProducto().getIdProducto(),
                detalle.getProducto().getNombre(),
                detalle.getProducto().getMarca(), presentacion,
                detalle.getCantidadSolicitada(),
                0,
                ""
            };
            modeloTabla.addRow(fila);
        }
    }

}
