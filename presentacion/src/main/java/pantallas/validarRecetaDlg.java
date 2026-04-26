package pantallas;

import javax.swing.JOptionPane;
import pantallas.control.Coordinador;

/**
 * JDialog donde se validara el folio de la receta.
 * @author Dario
 */
public class validarRecetaDlg extends javax.swing.JDialog {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(validarRecetaDlg.class.getName());
    private Coordinador coordinador;

    /**
     * Contructor del JDialog.
     */
    public validarRecetaDlg(java.awt.Frame parent, boolean modal, Coordinador coordinador) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(parent);
        this.coordinador = coordinador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlPrincipal = new javax.swing.JPanel();
        lbl1 = new javax.swing.JLabel();
        lbl2 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        btnIngresarReceta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlPrincipal.setBackground(new java.awt.Color(210, 240, 242));

        lbl1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lbl1.setText("Receta");

        lbl2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbl2.setText("Folio: ");

        txtFolio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N

        btnIngresarReceta.setBackground(new java.awt.Color(80, 139, 107));
        btnIngresarReceta.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        btnIngresarReceta.setForeground(new java.awt.Color(255, 255, 255));
        btnIngresarReceta.setText("Ingresar Receta");
        btnIngresarReceta.addActionListener(this::btnIngresarRecetaActionPerformed);

        javax.swing.GroupLayout pnlPrincipalLayout = new javax.swing.GroupLayout(pnlPrincipal);
        pnlPrincipal.setLayout(pnlPrincipalLayout);
        pnlPrincipalLayout.setHorizontalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(154, 154, 154)
                        .addComponent(lbl1))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(lbl2)
                        .addGap(18, 18, 18)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlPrincipalLayout.createSequentialGroup()
                        .addGap(119, 119, 119)
                        .addComponent(btnIngresarReceta)))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        pnlPrincipalLayout.setVerticalGroup(
            pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbl1)
                .addGap(45, 45, 45)
                .addGroup(pnlPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbl2)
                    .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(btnIngresarReceta)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarRecetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarRecetaActionPerformed
        ingresarReceta();
    }//GEN-LAST:event_btnIngresarRecetaActionPerformed

    public void ingresarReceta() {
        String folio = txtFolio.getText().trim();

        if (folio.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un folio válido.");
            return;
        }

        // Le enviamos el folio al coordinador para que lo guarde en la sesión
        coordinador.setFolioRecetaActual(folio);

        JOptionPane.showMessageDialog(this, "Folio vinculado correctamente.");

        // Cerramos el diálogo para que el Coordinador siga con la ejecución
        this.dispose();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresarReceta;
    private javax.swing.JLabel lbl1;
    private javax.swing.JLabel lbl2;
    private javax.swing.JPanel pnlPrincipal;
    private javax.swing.JTextField txtFolio;
    // End of variables declaration//GEN-END:variables
}
