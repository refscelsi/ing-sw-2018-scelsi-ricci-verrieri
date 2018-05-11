package sagradaview;
public class Menu extends javax.swing.JFrame {

    public Menu() {
        initComponents();
        setLocationRelativeTo(null);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        settings = new javax.swing.JLabel();
        newmatch = new javax.swing.JLabel();
        loadmatch = new javax.swing.JLabel();
        multiplayer = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(480, 658));
        setPreferredSize(new java.awt.Dimension(480, 658));
        setResizable(false);

        p1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        p1.setLayout(null);

        settings.setBackground(new java.awt.Color(255, 102, 0));
        settings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settings.setOpaque(true);
        p1.add(settings);
        settings.setBounds(214, 523, 53, 53);

        newmatch.setBackground(new java.awt.Color(255, 102, 0));
        newmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newmatch.setOpaque(true);
        p1.add(newmatch);
        newmatch.setBounds(214, 210, 53, 53);

        loadmatch.setBackground(new java.awt.Color(255, 102, 0));
        loadmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadmatch.setOpaque(true);
        p1.add(loadmatch);
        loadmatch.setBounds(58, 367, 53, 53);

        multiplayer.setBackground(new java.awt.Color(255, 102, 0));
        multiplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        multiplayer.setOpaque(true);
        p1.add(multiplayer);
        multiplayer.setBounds(370, 367, 53, 53);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sagradaview/menu.jpg"))); // NOI18N
        p1.add(background);
        background.setBounds(0, 0, 480, 630);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(p1, javax.swing.GroupLayout.DEFAULT_SIZE, 630, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JLabel loadmatch;
    private javax.swing.JLabel multiplayer;
    private javax.swing.JLabel newmatch;
    private javax.swing.JPanel p1;
    private javax.swing.JLabel settings;
    // End of variables declaration//GEN-END:variables
}
