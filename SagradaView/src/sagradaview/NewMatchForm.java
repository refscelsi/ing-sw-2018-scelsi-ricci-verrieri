package sagradaview;
public class NewMatchForm extends javax.swing.JFrame {
    private boolean controllo=true;

    public NewMatchForm() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        nameMatchTF = new javax.swing.JTextField();
        playerNameTF = new javax.swing.JTextField();
        nuumPlayerCB = new javax.swing.JComboBox<>();
        nameMatch = new javax.swing.JLabel();
        playerName = new javax.swing.JLabel();
        numplayers = new javax.swing.JLabel();
        annulla = new javax.swing.JButton();
        avviaMatch = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(204, 255, 255));

        jLabel1.setOpaque(true);

        nameMatchTF.setForeground(new java.awt.Color(153, 153, 153));
        nameMatchTF.setText("jTextField1");
        nameMatchTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameMatchTFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                nameMatchTFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                nameMatchTFMouseExited(evt);
            }
        });

        playerNameTF.setText("jTextField1");
        playerNameTF.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                playerNameTFMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerNameTFMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playerNameTFMouseExited(evt);
            }
        });

        nuumPlayerCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));

        nameMatch.setForeground(new java.awt.Color(51, 51, 51));
        nameMatch.setText("Nome partita");

        playerName.setForeground(new java.awt.Color(51, 51, 51));
        playerName.setText("jLabel3");

        numplayers.setForeground(new java.awt.Color(51, 51, 51));
        numplayers.setText("Numero giocatori");

        annulla.setText("Annulla");
        annulla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annullaActionPerformed(evt);
            }
        });

        avviaMatch.setText("Avvia partita");
        avviaMatch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                avviaMatchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(playerName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nameMatch, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(numplayers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameMatchTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(playerNameTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nuumPlayerCB, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(avviaMatch)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(annulla)))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameMatchTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameMatch))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playerNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(playerName))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nuumPlayerCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numplayers))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(annulla, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(avviaMatch, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
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

    private void avviaMatchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_avviaMatchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_avviaMatchActionPerformed

    private void annullaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annullaActionPerformed
        tornaMenu();
    }//GEN-LAST:event_annullaActionPerformed

    private void nameMatchTFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMatchTFMouseEntered
        if (controllo)
            nameMatchTF.setText("");
    }//GEN-LAST:event_nameMatchTFMouseEntered

    private void nameMatchTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMatchTFMouseClicked
        controllo=false;
    }//GEN-LAST:event_nameMatchTFMouseClicked

    private void nameMatchTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameMatchTFMouseExited
        if (controllo)
            nameMatchTF.setText("");
    }//GEN-LAST:event_nameMatchTFMouseExited

    private void playerNameTFMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerNameTFMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_playerNameTFMouseClicked

    private void playerNameTFMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerNameTFMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_playerNameTFMouseEntered

    private void playerNameTFMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerNameTFMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_playerNameTFMouseExited

    public void tornaMenu(){
        this.setVisible(false);
        
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(NewMatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewMatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewMatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewMatchForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new NewMatchForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton annulla;
    private javax.swing.JButton avviaMatch;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameMatch;
    private javax.swing.JTextField nameMatchTF;
    private javax.swing.JLabel numplayers;
    private javax.swing.JComboBox<String> nuumPlayerCB;
    private javax.swing.JLabel playerName;
    private javax.swing.JTextField playerNameTF;
    // End of variables declaration//GEN-END:variables
}
