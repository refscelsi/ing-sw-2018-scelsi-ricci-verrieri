package it.polimi.ing.sw.view;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class CardField extends javax.swing.JPanel {
    
    private static final String IMAGE_PATH="/img/po/";


    public CardField() {
        initComponents();
        setIcons("po01.png");//default icon
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardFieldLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 75, 63));

        cardFieldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/po/po01.png"))); // NOI18N
        cardFieldLabel.setMinimumSize(new java.awt.Dimension(173, 245));
        cardFieldLabel.setPreferredSize(new java.awt.Dimension(173, 245));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardFieldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cardFieldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents
    public void setIcons(String nameIcon){
        ImageIcon icon =new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH+nameIcon)) ;
        Image scaledImage = icon.getImage().getScaledInstance(173,242, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        cardFieldLabel.setIcon(icon );
        cardFieldLabel.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cardFieldLabel;
    // End of variables declaration//GEN-END:variables
}
