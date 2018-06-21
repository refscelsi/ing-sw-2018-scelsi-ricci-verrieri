package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Dice;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static it.polimi.ing.sw.model.Color.*;
import static it.polimi.ing.sw.model.Color.PURPLE;
import static it.polimi.ing.sw.model.Color.WHITE;

public class DiceGUI extends javax.swing.JPanel {

    private static final String IMAGE_PATH = "/img/dices/";

    private Dice dice;

    public void setDice(Dice dice) {
        this.dice = dice;
        updateDice();
        dice.getDiceColor();
    }

    private void updateDice() {
        updateIcon(String.valueOf(dice.getNumFacciaUp()));
        updateColor( dice.getDiceColor());
        diceLabel.repaint();
    }

    private void updateColor(it.polimi.ing.sw.model.Color color) {
        if (RED ==color){
            diceLabel.setBackground(new java.awt.Color(255, 0, 51));
        }
        if (BLUE == color){
            diceLabel.setBackground(new java.awt.Color(0, 102, 255));
        }
        if (GREEN == color){
            diceLabel.setBackground(new java.awt.Color(51, 255, 0));
        }
        if (YELLOW == color){
            diceLabel.setBackground(new java.awt.Color(255, 255, 51));
        }
        if (PURPLE == color){
            diceLabel.setBackground(new java.awt.Color(204, 0, 255));
        }
        if (WHITE == color){
            diceLabel.setBackground(new java.awt.Color(255, 255, 255));
        }
    }

    private void updateIcon(String name) {
        ImageIcon icon;
        Image scaledImage;

        icon = new javax.swing.ImageIcon(getClass().getResource(IMAGE_PATH + name + ".png"));
        scaledImage = icon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        diceLabel.setIcon(icon);
        diceLabel.repaint();
    }

    public DiceGUI() {
        initComponents();
        updateIcon("1g");
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        diceLabel = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(50, 50));

        diceLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        diceLabel.setOpaque(true);
        diceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                diceLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                diceLabelMouseExited(evt);
            }
        });

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

    private void diceLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseEntered
        diceLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        setCursor(new Cursor( Cursor.HAND_CURSOR ));
    }//GEN-LAST:event_diceLabelMouseEntered

    private void diceLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseExited
        diceLabel.setBorder(null);
        setCursor(new Cursor( Cursor.DEFAULT_CURSOR ));
    }//GEN-LAST:event_diceLabelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diceLabel;
    // End of variables declaration//GEN-END:variables
}