 
package View;

import Controller.PrincipalController;
import javax.swing.JButton;

 
public class PrincipalFrame extends javax.swing.JFrame {
    private PrincipalController principalController;
     
    public PrincipalFrame() {
        initComponents();
    }
    public void setController(PrincipalController principalController){
        this.principalController=principalController;  
    }

    public JButton getBtnProveedores() {
        return btnProveedores;
    }

    public JButton getBtnAlimentos() {
        return btnAlimentos;
    }

    public JButton getBtnAnimales() {
        return btnAnimales;
    }

    public JButton getBtnCategorias() {
        return btnCategorias;
    }

    public JButton getBtnInventario() {
        return btnInventario;
    }

    public JButton getBtnSuministros() {
        return btnSuministros;
    }

    public JButton getBtnTransacciones() {
        return btnTransacciones;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnAnimales = new javax.swing.JButton();
        btnAlimentos = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        btnSuministros = new javax.swing.JButton();
        btnCategorias = new javax.swing.JButton();
        btnTransacciones = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnProveedores = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnAnimales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Animales.png"))); // NOI18N
        btnAnimales.setText("Animales");
        btnAnimales.setAutoscrolls(true);
        btnAnimales.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnAnimales.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnimales.setIconTextGap(1);
        btnAnimales.setInheritsPopupMenu(true);
        btnAnimales.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnAlimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Alimentos.png"))); // NOI18N
        btnAlimentos.setText("Alimentos");
        btnAlimentos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlimentos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/inventario.png"))); // NOI18N
        btnInventario.setText("Inventario");
        btnInventario.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnInventario.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });

        btnSuministros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/suministro.png"))); // NOI18N
        btnSuministros.setText("Suministros");
        btnSuministros.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSuministros.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSuministros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuministrosActionPerformed(evt);
            }
        });

        btnCategorias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/categorias.png"))); // NOI18N
        btnCategorias.setText("Categorías");
        btnCategorias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCategorias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCategoriasActionPerformed(evt);
            }
        });

        btnTransacciones.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/transaccion.png"))); // NOI18N
        btnTransacciones.setText("Transacciones");
        btnTransacciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnTransacciones.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnTransacciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransaccionesActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/LogoIcon.jpg"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/proveedores.png"))); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.setAutoscrolls(true);
        btnProveedores.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProveedores.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProveedores.setIconTextGap(1);
        btnProveedores.setInheritsPopupMenu(true);
        btnProveedores.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnAnimales, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36)
                                .addComponent(btnProveedores, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(btnAlimentos, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnSuministros, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(76, 76, 76))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnAlimentos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAnimales, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                        .addComponent(btnProveedores, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                    .addComponent(btnCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnSuministros, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTransacciones, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnInventarioActionPerformed

    private void btnSuministrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuministrosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSuministrosActionPerformed

    private void btnCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCategoriasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCategoriasActionPerformed

    private void btnTransaccionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransaccionesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTransaccionesActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlimentos;
    private javax.swing.JButton btnAnimales;
    private javax.swing.JButton btnCategorias;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnSuministros;
    private javax.swing.JButton btnTransacciones;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
