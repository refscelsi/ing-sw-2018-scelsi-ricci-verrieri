package sagradaview;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Menu extends javax.swing.JFrame {

    private NewMatchForm nmf = new NewMatchForm();
    
    public Menu() {
        initComponents();
        setLocationRelativeTo(null);        
        setIcons();          
    }
    
    public void setIcons(){
        ImageIcon icon = new javax.swing.ImageIcon(getClass().getResource("/sagradaview/home.png"));
        Image scaledImage = icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        
        newmatch.setIcon(icon);
        newmatch.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/sagradaview/settings.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        settings.setIcon(icon);
        settings.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/sagradaview/globe.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        multiplayer.setIcon(icon);
        multiplayer.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/sagradaview/find.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        loadmatch.setIcon(icon);
        loadmatch.repaint();
        
        icon = new javax.swing.ImageIcon(getClass().getResource("/sagradaview/add.png"));
        scaledImage = icon.getImage().getScaledInstance(53,53, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        addmap.setIcon(icon);
        addmap.repaint();
        
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/sagradaview/sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        p1 = new javax.swing.JPanel();
        addmap = new javax.swing.JLabel();
        settings = new javax.swing.JLabel();
        newmatch = new javax.swing.JLabel();
        loadmatch = new javax.swing.JLabel();
        multiplayer = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImages(null);
        setMinimumSize(new java.awt.Dimension(480, 658));
        setPreferredSize(new java.awt.Dimension(480, 658));
        setResizable(false);

        p1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        p1.setLayout(null);

        addmap.setBackground(new java.awt.Color(255, 102, 0));
        addmap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addmap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addmapMouseClicked(evt);
            }
        });
        p1.add(addmap);
        addmap.setBounds(214, 210, 53, 53);

        settings.setBackground(new java.awt.Color(255, 102, 0));
        settings.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        settings.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settingsMouseClicked(evt);
            }
        });
        p1.add(settings);
        settings.setBounds(214, 523, 53, 53);

        newmatch.setBackground(new java.awt.Color(255, 102, 0));
        newmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newmatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newmatchMouseClicked(evt);
            }
        });
        p1.add(newmatch);
        newmatch.setBounds(191, 344, 100, 100);

        loadmatch.setBackground(new java.awt.Color(255, 102, 0));
        loadmatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loadmatch.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loadmatchMouseClicked(evt);
            }
        });
        p1.add(loadmatch);
        loadmatch.setBounds(58, 367, 53, 53);

        multiplayer.setBackground(new java.awt.Color(255, 102, 0));
        multiplayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        multiplayer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                multiplayerMouseClicked(evt);
            }
        });
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

    private void newmatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newmatchMouseClicked
        scompareMenu();
        nmf.setVisible(true);
    }//GEN-LAST:event_newmatchMouseClicked

    private void addmapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addmapMouseClicked
        scompareMenu();
    }//GEN-LAST:event_addmapMouseClicked

    private void loadmatchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loadmatchMouseClicked
        scompareMenu();
    }//GEN-LAST:event_loadmatchMouseClicked

    private void settingsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settingsMouseClicked
        scompareMenu();
    }//GEN-LAST:event_settingsMouseClicked

    private void multiplayerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_multiplayerMouseClicked
        scompareMenu();
    }//GEN-LAST:event_multiplayerMouseClicked

    public void scompareMenu(){
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
    private javax.swing.JLabel addmap;
    private javax.swing.JLabel background;
    private javax.swing.JLabel loadmatch;
    private javax.swing.JLabel multiplayer;
    private javax.swing.JLabel newmatch;
    private javax.swing.JPanel p1;
    private javax.swing.JLabel settings;
    // End of variables declaration//GEN-END:variables
}