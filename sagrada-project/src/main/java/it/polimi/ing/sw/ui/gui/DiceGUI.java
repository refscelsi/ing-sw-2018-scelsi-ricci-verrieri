package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

import static it.polimi.ing.sw.model.Color.*;

public class DiceGUI extends javax.swing.JPanel {

    private static final String IMAGE_PATH = "/img/dices/";

    private Box box;

    private int dimXdice;

    private int dimYdice;

    public DiceGUI(int dimXdice, int dimYdice) {
        this.dimXdice = dimXdice;
        this.dimYdice = dimYdice;
        initComponents();
        //updateIcon("1g");

        box = new Box();
    }

    public DiceGUI(Boolean isVoid, int dimXdice, int dimYdice) {
        this.dimXdice = dimXdice;
        this.dimYdice = dimYdice;
        initComponents();
        box = new Box();
        if (!isVoid)
            updateIcon("1g", false);
    }

    public void setDice(Dice dice) {
        box.placeDice(dice);
        updateDice();
    }

    public void setBox(Box box) {
        this.box = box;
        updateBox();
    }

    private void updateBox() {
        updateIcon(String.valueOf(box.getShade()), false);
        updateColor(box.getColor());
        diceLabel.repaint();
    }

    private void updateDice() {
        updateIcon(String.valueOf(box.getDice().getNumFacciaUp()), true);
        updateColor(box.getDice().getDiceColor());
        diceLabel.repaint();
    }

    private void updateColor(it.polimi.ing.sw.model.Color color) {
        if (RED == color) {
            diceLabel.setBackground(new java.awt.Color(255, 0, 51));
        }
        if (BLUE == color) {
            diceLabel.setBackground(new java.awt.Color(0, 102, 255));
        }
        if (GREEN == color) {
            diceLabel.setBackground(new java.awt.Color(4, 175, 98));
        }
        if (YELLOW == color) {
            diceLabel.setBackground(new java.awt.Color(255, 255, 51));
        }
        if (PURPLE == color) {
            diceLabel.setBackground(new java.awt.Color(204, 0, 255));
        }
        if (WHITE == color) {
            diceLabel.setBackground(new java.awt.Color(255, 255, 255));
        }
    }

    public Box getBox() {
        return box;
    }

    public static String getImagePath() {
        return IMAGE_PATH;
    }

    private void updateIcon(String name, Boolean isSetDice) {
        String PATH = new String();
        if (name != null) {
            if (name.equals("1g") || name.equals("2g") || name.equals("3g") || name.equals("4g") || name.equals("5g") || name.equals("6g") || isSetDice) {
                PATH = IMAGE_PATH + name + ".png";
            } else {
                int intName = Integer.valueOf(name);
                if (intName > 0 && intName < 7) {
                    PATH = IMAGE_PATH + name + "g.png";
                }

            }
            if (!PATH.isEmpty()) {
                ImageIcon icon;
                Image scaledImage;
                icon = new javax.swing.ImageIcon(getClass().getResource(PATH));
                scaledImage = icon.getImage().getScaledInstance(dimXdice, dimYdice, Image.SCALE_DEFAULT);
                icon.setImage(scaledImage);
                diceLabel.setIcon(icon);
                diceLabel.repaint();
            }
        }
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
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_diceLabelMouseEntered

    private void diceLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceLabelMouseExited
        diceLabel.setBorder(null);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_diceLabelMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel diceLabel;
    // End of variables declaration//GEN-END:variables
}