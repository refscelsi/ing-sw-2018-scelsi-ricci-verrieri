package it.polimi.ing.sw.ui.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CardField extends javax.swing.JPanel {

    private static Boolean used;
    private static final String IMAGE_PATH = "/img/";
    private static String FINAL_IMAGE_PATH;
    private int token;
    private int dimXcard;
    private int dimYcard;

    public CardField(int dimXcard, int dimYcard) {
        this.dimXcard=dimXcard;
        this.dimYcard=dimYcard;
        initComponents();
        FINAL_IMAGE_PATH = IMAGE_PATH.concat("po/");
        setIcons(FINAL_IMAGE_PATH.concat("po01.png"));//default icon
        used = true;
        token=0;
    }

    //cardType should be defined as "po/" or "tc/"
    public CardField(String cardName, String cardType,int dimXcard, int dimYcard) {
        this.dimXcard=dimXcard;
        this.dimYcard=dimYcard;
        initComponents();
        FINAL_IMAGE_PATH = IMAGE_PATH.concat(cardType);
        setIcons(FINAL_IMAGE_PATH.concat(cardName));
        used = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tokenLabel = new javax.swing.JLabel();
        cardFieldLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 75, 63));
        setLayout(null);

        tokenLabel.setFont(new java.awt.Font("Elephant", 3, 14)); // NOI18N
        tokenLabel.setForeground(new java.awt.Color(51, 51, 0));
        cardFieldLabel.add(tokenLabel);
        tokenLabel.setBounds(150, 220, 20, 20);

        cardFieldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/po/po01.png"))); // NOI18N
        cardFieldLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cardFieldLabel.setMinimumSize(new java.awt.Dimension(dimXcard, dimYcard));
        cardFieldLabel.setPreferredSize(new java.awt.Dimension(dimXcard, dimYcard));
        cardFieldLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cardFieldLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cardFieldLabelMouseExited(evt);
            }
        });
        add(cardFieldLabel);
        cardFieldLabel.setBounds(0, 0, dimXcard, dimYcard);
    }// </editor-fold>//GEN-END:initComponents

    private void cardFieldLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFieldLabelMouseEntered
        cardFieldLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
    }//GEN-LAST:event_cardFieldLabelMouseEntered

    private void cardFieldLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFieldLabelMouseExited
        cardFieldLabel.setBorder(null);
    }//GEN-LAST:event_cardFieldLabelMouseExited

    private void setIcons(String name) {
        System.out.println(FINAL_IMAGE_PATH);

        ImageIcon icon = new ImageIcon(getClass().getResource(name));
        Image scaledImage = icon.getImage().getScaledInstance(dimXcard, dimYcard /*242*/, Image.SCALE_DEFAULT);
        icon.setImage(scaledImage);
        cardFieldLabel.setIcon(icon);
        cardFieldLabel.repaint();
    }

    public void disableToolCard() {
        used = false;
        setIcons(IMAGE_PATH.concat("tc/").concat("disabled.png"));
    }

    public void disableObjCard() {
        used = false;
        setIcons(IMAGE_PATH.concat("po/").concat("disabled.png"));
    }

    public void addToken(int numberOfTokens) {
        switch (token) {
            case 0:
                if (numberOfTokens == 1) {
                    token = 1;

                }break;//TODO add exc
            default:
                if (numberOfTokens == 2) {
                    token += numberOfTokens;
                }//TODO add exc
        }
        tokenLabel.setText(String.valueOf(token));
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cardFieldLabel;
    private javax.swing.JLabel tokenLabel;
    // End of variables declaration//GEN-END:variables
}
