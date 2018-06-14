package it.polimi.ing.sw.ui.gui;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TestDadini extends JFrame {
    
    private static final String IMAGE_PATH = "/img/dices/";
    
    public TestDadini() {
        initComponents();
        setIcons();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        d1 = new javax.swing.JLabel();
        d2 = new javax.swing.JLabel();
        d3 = new javax.swing.JLabel();
        d4 = new javax.swing.JLabel();
        d5 = new javax.swing.JLabel();
        d6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 255, 255));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);
        jPanel1.setLayout(null);

        d1.setBackground(new java.awt.Color(255, 255, 255));
        d1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dices/1.png"))); // NOI18N
        d1.setOpaque(true);
        jPanel1.add(d1);
        d1.setBounds(230, 200, 110, 110);

        d2.setBackground(new java.awt.Color(255, 255, 51));
        d2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dices/1.png"))); // NOI18N
        d2.setOpaque(true);
        jPanel1.add(d2);
        d2.setBounds(30, 200, 110, 110);

        d3.setBackground(new java.awt.Color(255, 0, 51));
        d3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dices/1.png"))); // NOI18N
        d3.setOpaque(true);
        jPanel1.add(d3);
        d3.setBounds(240, 30, 110, 110);

        d4.setBackground(new java.awt.Color(204, 0, 255));
        d4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dices/1.png"))); // NOI18N
        d4.setOpaque(true);
        jPanel1.add(d4);
        d4.setBounds(40, 30, 110, 110);

        d5.setBackground(new java.awt.Color(51, 255, 0));
        d5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dices/1.png"))); // NOI18N
        d5.setOpaque(true);
        jPanel1.add(d5);
        d5.setBounds(430, 30, 110, 110);

        d6.setBackground(new java.awt.Color(0, 102, 255));
        d6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/dices/1.png"))); // NOI18N
        d6.setOpaque(true);
        jPanel1.add(d6);
        d6.setBounds(420, 200, 110, 110);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 584, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setIcons(){
        ImageIcon icon ;
        Image scaledImage ;

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"1.png"));
        scaledImage = icon.getImage().getScaledInstance(110,110, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        d1.setIcon(icon);
        d1.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"2.png"));
        scaledImage = icon.getImage().getScaledInstance(110,110, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        d2.setIcon(icon);
        d2.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"3.png"));
        scaledImage = icon.getImage().getScaledInstance(110,110, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        d3.setIcon(icon);
        d3.repaint();

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"4.png"));
        scaledImage = icon.getImage().getScaledInstance(110,110, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        d4.setIcon(icon);
        d4.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"5.png"));
        scaledImage = icon.getImage().getScaledInstance(110,110, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        d5.setIcon(icon);
        d5.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+"6.png"));
        scaledImage = icon.getImage().getScaledInstance(110,110, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        d6.setIcon(icon);
        d6.repaint();

        this.setTitle("Sagrada Boardgame");
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
            java.util.logging.Logger.getLogger(TestDadini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TestDadini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TestDadini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TestDadini.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TestDadini().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel d1;
    private javax.swing.JLabel d2;
    private javax.swing.JLabel d3;
    private javax.swing.JLabel d4;
    private javax.swing.JLabel d5;
    private javax.swing.JLabel d6;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
