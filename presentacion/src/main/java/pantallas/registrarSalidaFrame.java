/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pantallas;

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

/**
 *
 * @author Jorge
 */
public class registrarSalidaFrame extends JFrame {

    public registrarSalidaFrame() {
        setTitle("Registrar salida");
        setSize(1500, 675);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        getContentPane().setBackground(new Color(245,245,245));
        JPanel header = new JPanel();
        header.setBackground(new Color(0,70,200));
        header.setBounds(0,0,1500,65);
        header.setLayout(null);
        JLabel titulo = new JLabel("Registrar salida de medicamento");
        titulo.setForeground(Color.WHITE);
        titulo.setFont(new Font("Segoe UI",Font.BOLD,30));
        titulo.setBounds(25,12,700,35);
        header.add(titulo);
        add(header);
        
        JPanel panelBusqueda = new JPanel();
        panelBusqueda.setLayout(null);
        panelBusqueda.setBackground(Color.WHITE);
        panelBusqueda.setBorder(BorderFactory.createLineBorder(new Color(225,225,225)));
        panelBusqueda.setBounds(20,85,1440,100);
        add(panelBusqueda);

        JLabel lblMedicamento = new JLabel("Medicamento:");
        lblMedicamento.setFont(new Font("Segoe UI",Font.BOLD,18));
        lblMedicamento.setForeground(new Color(0,70,200));
        lblMedicamento.setBounds(30,35,180,25);
        panelBusqueda.add(lblMedicamento);

        JTextField txtBuscar =new JTextField();
        txtBuscar.setText("Buscar medicamento...");
        txtBuscar.setFont(new Font("Segoe UI",Font.PLAIN,18));
        txtBuscar.setBounds(220,25,1180,45);
        panelBusqueda.add(txtBuscar);
        
        JPanel panelInfo = new JPanel();
        panelInfo.setLayout(null);
        panelInfo.setBackground(Color.WHITE);
        panelInfo.setBorder(BorderFactory.createLineBorder(new Color(225,225,225)));
        panelInfo.setBounds(20,200,1440,150);
        add(panelInfo);

        Font fontTitulo = new Font("Segoe UI",Font.BOLD,18);
        Font fontInfo = new Font("Segoe UI",Font.PLAIN,18);
        JLabel lblCodigo = new JLabel("Código:");
        lblCodigo.setForeground(Color.BLACK);
        lblCodigo.setFont(fontTitulo);
        lblCodigo.setBounds(30,35,100,25);
        panelInfo.add(lblCodigo);
        
        JLabel txtCodigo = new JLabel("MED-0012");
        txtCodigo.setForeground(new Color(0,70,200));
        txtCodigo.setFont(fontTitulo);
        txtCodigo.setBounds(140,35,150,25);
        panelInfo.add(txtCodigo);
        
        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setForeground(Color.BLACK);
        lblNombre.setFont(fontTitulo);
        lblNombre.setBounds(320,35,100,25);
        panelInfo.add(lblNombre);

        JLabel txtNombre = new JLabel("Paracetamol");
        txtNombre.setForeground(new Color(0,70,200));
        txtNombre.setFont(fontTitulo);
        txtNombre.setBounds(430,35,200,25);
        panelInfo.add(txtNombre);

        JLabel lblDosis = new JLabel("Dosis:");
        lblDosis.setForeground(Color.BLACK);
        lblDosis.setFont(fontTitulo);
        lblDosis.setBounds(610,35,80,25);
        panelInfo.add(lblDosis);
        
        JLabel txtDosis = new JLabel("500");
        txtDosis.setForeground(new Color(0,70,200));
        txtDosis.setFont(fontTitulo);
        txtDosis.setBounds(690,35,80,25);
        panelInfo.add(txtDosis);
        
        JLabel lblMedida = new JLabel("Medida:");
        lblMedida.setForeground(Color.BLACK);
        lblMedida.setFont(fontTitulo);
        lblMedida.setBounds(780,35,100,25);
        panelInfo.add(lblMedida);

        JLabel txtMedida = new JLabel("mg");
        txtMedida.setForeground(new Color(0,70,200));
        txtMedida.setFont(fontTitulo);
        txtMedida.setBounds(890,35,50,25);
        panelInfo.add(txtMedida);
        
        JLabel lblUnidad = new JLabel("Unidad:");
        lblUnidad.setForeground(Color.BLACK);
        lblUnidad.setFont(fontTitulo);
        lblUnidad.setBounds(980,35,100,25);
        panelInfo.add(lblUnidad);

        JLabel txtUnidad = new JLabel("tableta");
        txtUnidad.setForeground(new Color(0,70,200));
        txtUnidad.setFont(fontTitulo);
        txtUnidad.setBounds(1090,35,120,25);
        panelInfo.add(txtUnidad);
        
        JLabel lblStock = new JLabel("Stock disponible:");
        lblStock.setForeground(Color.BLACK);
        lblStock.setFont(fontTitulo);
        lblStock.setBounds(1210,35,180,25);
        panelInfo.add(lblStock);

        JLabel txtStock = new JLabel("150");
        txtStock.setForeground(new Color(0,70,200));
        txtStock.setFont(fontTitulo);
        txtStock.setBounds(1390,35,60,25);
        panelInfo.add(txtStock);
        
        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setForeground(Color.BLACK);
        lblMarca.setFont(fontTitulo);
        lblMarca.setBounds(30,95,100,25);
        panelInfo.add(lblMarca);
        
        JLabel txtMarca = new JLabel("Genfar");
        txtMarca.setForeground(new Color(0,70,200));
        txtMarca.setFont(fontTitulo);
        txtMarca.setBounds(140,95,150,25);
        panelInfo.add(txtMarca);
        
        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(null);
        panelDatos.setBackground(Color.WHITE);
        panelDatos.setBorder(BorderFactory.createLineBorder(new Color(225,225,225)));
        panelDatos.setBounds(20,365,1440,210);
        add(panelDatos);
        
        JLabel lblCantidad = new JLabel("Cantidad a retirar:");
        lblCantidad.setForeground(Color.BLACK);
        lblCantidad.setFont(fontTitulo);
        lblCantidad.setBounds(30,45,220,25);
        panelDatos.add(lblCantidad);

        JSpinner spinnerCantidad =new JSpinner();
        spinnerCantidad.setBounds(280,35,330,50);
        spinnerCantidad.setFont(fontInfo);
        panelDatos.add(spinnerCantidad);
        
        JLabel lblMotivo = new JLabel("Motivo de salida:");
        lblMotivo.setForeground(Color.BLACK);
        lblMotivo.setFont(fontTitulo);
        lblMotivo.setBounds(30,130,220,25);
        panelDatos.add(lblMotivo);
        
        JComboBox<String> comboMotivo =new JComboBox<>();
        comboMotivo.addItem("Caducó");
        comboMotivo.setBounds(280,120,330,50);
        comboMotivo.setFont(fontInfo);
        panelDatos.add(comboMotivo);
        
        JLabel lblObservaciones =new JLabel("Observaciones:");
        lblObservaciones.setForeground(Color.BLACK);
        lblObservaciones.setFont(fontTitulo);
        lblObservaciones.setBounds(720,45,180,25);
        panelDatos.add(lblObservaciones);

        JTextArea txtObservaciones =new JTextArea();
        txtObservaciones.setText("El medicamento no se mantuvo en refrigeración");
        txtObservaciones.setFont(fontInfo);
        JScrollPane scroll =new JScrollPane(txtObservaciones);
        scroll.setBounds(910,30,480,130);
        panelDatos.add(scroll);
        
        JLabel lblFecha = new JLabel(LocalDate.now().toString());
        lblFecha.setForeground(new Color(0,70,200));
        lblFecha.setFont(new Font("Segoe UI",Font.BOLD,18));
        lblFecha.setBounds(40,610,250,25);
        add(lblFecha);
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.setBounds(830,595,180,50);
        btnSalir.setBackground(Color.BLACK);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setFont(new Font("Segoe UI",Font.BOLD,18));
        add(btnSalir);

        JButton btnCancelar =new JButton("Cancelar");
        btnCancelar.setBounds(1040,595,210,50);
        btnCancelar.setBackground(new Color(255,0,0));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Segoe UI",Font.BOLD,18));
        add(btnCancelar);

        JButton btnRegistrar =new JButton("Registrar salida");
        btnRegistrar.setBounds(1280,595,180,50);
        btnRegistrar.setBackground(new Color(0,70,200));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFont(new Font("Segoe UI",Font.BOLD,18));
        add(btnRegistrar);
        setVisible(true);
    }

}
