package it.polimi.ing.sw.ui.gui;

import javax.swing.*;
import java.awt.*;

public class DiceGUI extends javax.swing.JPanel {

    private static final String IMAGE_PATH = "/img/dices/";

    public DiceGUI() {
        initComponents();

        ImageIcon icon;
        Image scaledImage;

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH + "1g.png"));
        scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        diceLabel.setIcon(icon);
        diceLabel.repaint();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diceLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(diceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(diceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diceLabel;
    // End of variables declaration//GEN-END:variables
}
