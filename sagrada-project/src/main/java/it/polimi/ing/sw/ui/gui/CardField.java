package it.polimi.ing.sw.ui.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CardField extends javax.swing.JPanel {

    private static final String IMAGE_PATH = "/img/";
    private static String FINAL_IMAGE_PATH;

    public CardField() {
        initComponents();
        FINAL_IMAGE_PATH = IMAGE_PATH.concat("po/").concat("po01.png");
        setIcons(FINAL_IMAGE_PATH);//default icon
    }

    //cardType should be defined as "po/" or "tc/"
    public CardField(String cardName, String cardType) {
        initComponents();
        FINAL_IMAGE_PATH = IMAGE_PATH.concat(cardType).concat(cardName);
        setIcons(FINAL_IMAGE_PATH);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cardFieldLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 75, 63));

        cardFieldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/po/po01.png"))); // NOI18N
        cardFieldLabel.setMinimumSize(new java.awt.Dimension(173, 245));
        cardFieldLabel.setPreferredSize(new java.awt.Dimension(173, 245));
        cardFieldLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cardFieldLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cardFieldLabelMouseExited(evt);
            }
        });

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

    private void cardFieldLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFieldLabelMouseEntered
        cardFieldLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
    }//GEN-LAST:event_cardFieldLabelMouseEntered

    private void cardFieldLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFieldLabelMouseExited
        cardFieldLabel.setBorder(null);
    }//GEN-LAST:event_cardFieldLabelMouseExited

    public void setIcons(String name) {
        System.out.println(FINAL_IMAGE_PATH);

        ImageIcon icon = new ImageIcon(getClass().getResource(name));
        Image scaledImage = icon.getImage().getScaledInstance(173, 242, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        cardFieldLabel.setIcon(icon);
        cardFieldLabel.repaint();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cardFieldLabel;
    // End of variables declaration//GEN-END:variables
}
