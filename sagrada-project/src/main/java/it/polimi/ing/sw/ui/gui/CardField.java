package it.polimi.ing.sw.ui.gui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.rmi.RemoteException;

public class CardField extends javax.swing.JPanel {

    private static Boolean used;
    private static final String IMAGE_PATH = "/img/";
    private static String FINAL_IMAGE_PATH;
    private int token;
    private int dimXcard;
    private int dimYcard;
    private Boolean isToolCard;
    private String id;
    private GUI gui;

    //cardType should be defined as "po/" or "tc/"
    public CardField(String cardName, String cardType, int dimXcard, int dimYcard) {
        this.id = cardName;
        this.dimXcard = dimXcard;
        this.dimYcard = dimYcard;
        initComponents();
        FINAL_IMAGE_PATH = IMAGE_PATH.concat(cardType);
        setIcons(FINAL_IMAGE_PATH.concat(cardName).concat(".png"));
        used = false;
    }

    public void setController(GUI gui) {
        this.gui = gui;
    }

    public void setUsed(boolean used) {
        this.used = used;
        if (!used) {
            cardFieldLabel.setBorder(null);
        }
    }

    public void setIsToolCard(boolean isToolCard) {
        this.isToolCard = isToolCard;
    }

    public Boolean getUsed() {
        return used;
    }

    public String getId() {
        return id;
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
        add(tokenLabel);
        tokenLabel.setBounds(150, 220, 20, 20);

        cardFieldLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/po/1.png"))); // NOI18N
        cardFieldLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));/*
        cardFieldLabel.setMinimumSize(new java.awt.Dimension(173, 245));
        cardFieldLabel.setPreferredSize(new java.awt.Dimension(173, 245));*/
        cardFieldLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                try {
                    cardFieldLabelMouseClicked(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

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
        if (!used) {
            cardFieldLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        }
    }//GEN-LAST:event_cardFieldLabelMouseEntered

    private void cardFieldLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cardFieldLabelMouseExited
        if (!used) {
            cardFieldLabel.setBorder(null);
        }
    }//GEN-LAST:event_cardFieldLabelMouseExited

    private void cardFieldLabelMouseClicked(java.awt.event.MouseEvent evt) throws RemoteException {//GEN-FIRST:event_cardFieldLabelMouseClicked
        if (gui.isPlaying()) {
            if (isToolCard) {
                if (!used) {//TODO next line
                    TableFrame.isNotToolCardAnymore(Integer.valueOf(TableFrame.idSelectedTc));
                    setUsed(true);
                    cardFieldLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 51, 51)));
                    TableFrame.idSelectedTc = id;
                    if (TableFrame.aToolCardIsUsed()) {
                        switch (TableFrame.idSelectedTc) {
                            case "7":
                                gui.useToolCard7();
                                break;
                            case "8":
                                gui.useToolCard8();
                                break;
                        }
                    }
                } else {
                    setUsed(false);
                    TableFrame.idSelectedTc = null;
                }
            }
        }
    }//GEN-LAST:event_cardFieldLabelMouseClicked

    private void setIcons(String name) {
        System.out.println(FINAL_IMAGE_PATH);
        System.out.println(name);
        ImageIcon icon = new ImageIcon(getClass().getResource(name));
        Image scaledImage = icon.getImage().getScaledInstance(dimXcard, dimYcard, Image.SCALE_DEFAULT);
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
/*
    public void addToken(int numberOfTokens) {
        switch (token) {
            case 0:
                if (numberOfTokens == 1) {
                    token = 1;

                }
                break;//TODO add exc
            default:
                if (numberOfTokens == 2) {
                    token += numberOfTokens;
                }//TODO add exc
        }
        tokenLabel.setText(String.valueOf(token));
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel cardFieldLabel;
    private javax.swing.JLabel tokenLabel;

    public void setTokens(int numOfTokens) {
        token=numOfTokens;
        tokenLabel.setText(String.valueOf(token));
    }
    // End of variables declaration//GEN-END:variables
}
