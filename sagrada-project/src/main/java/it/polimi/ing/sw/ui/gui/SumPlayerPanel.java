package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class SumPlayerPanel extends javax.swing.JPanel {

    private int token;
    private String playerName;
    private String schemeName;
    private PrivateObjectiveCard privateObjective;

    public SumPlayerPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        privateObjButtonLabel = new javax.swing.JLabel();
        schemeNameLabel = new javax.swing.JLabel();
        namePlayerLabel = new javax.swing.JLabel();
        numTokenRemainingLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 102));
        setAlignmentX(0.0F);
        setAlignmentY(0.0F);
        setLayout(null);

        privateObjButtonLabel.setBackground(new java.awt.Color(204, 204, 204));
        privateObjButtonLabel.setFont(new java.awt.Font("Elephant", 2, 12)); // NOI18N
        privateObjButtonLabel.setForeground(new java.awt.Color(51, 51, 0));
        privateObjButtonLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        privateObjButtonLabel.setText("PO");
        privateObjButtonLabel.setOpaque(true);
        privateObjButtonLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                privateObjButtonLabelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                privateObjButtonLabelMouseExited(evt);
            }
        });
        add(privateObjButtonLabel);
        privateObjButtonLabel.setBounds(10, 50, 40, 20);

        schemeNameLabel.setFont(new java.awt.Font("Elephant", 2, 8)); // NOI18N
        schemeNameLabel.setForeground(new java.awt.Color(51, 51, 0));
        schemeNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        schemeNameLabel.setText("nomeSchema");
        add(schemeNameLabel);
        schemeNameLabel.setBounds(2, 30, 80, 11);

        namePlayerLabel.setFont(new java.awt.Font("Elephant", 2, 8)); // NOI18N
        namePlayerLabel.setForeground(new java.awt.Color(51, 51, 0));
        namePlayerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namePlayerLabel.setText("nome player");
        add(namePlayerLabel);
        namePlayerLabel.setBounds(0, 11, 80, 10);

        numTokenRemainingLabel.setFont(new java.awt.Font("Elephant", 3, 14)); // NOI18N
        numTokenRemainingLabel.setForeground(new java.awt.Color(51, 51, 0));
        numTokenRemainingLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numTokenRemainingLabel.setText("N");
        add(numTokenRemainingLabel);
        numTokenRemainingLabel.setBounds(50, 50, 20, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void privateObjButtonLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_privateObjButtonLabelMouseEntered
        privateObjButtonLabel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
    }//GEN-LAST:event_privateObjButtonLabelMouseEntered

    private void privateObjButtonLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_privateObjButtonLabelMouseExited
        privateObjButtonLabel.setBorder(null);
    }//GEN-LAST:event_privateObjButtonLabelMouseExited

    public void setToken(int token) {
        this.token = token;
        numTokenRemainingLabel.setText(String.valueOf(token));
    }

    public void setPlayerName(String playerName) {
        this.playerName=playerName;
        namePlayerLabel.setText(playerName);
    }
    public void setSchemeName(String schemeName) {
        this.schemeName=schemeName;
        schemeNameLabel.setText(schemeName);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel namePlayerLabel;
    private javax.swing.JLabel numTokenRemainingLabel;
    private javax.swing.JLabel privateObjButtonLabel;
    private javax.swing.JLabel schemeNameLabel;

    public void setPrivateObjCard( PrivateObjectiveCard privateObjective ) {
        this.privateObjective=privateObjective;
    }
	// End of variables declaration//GEN-END:variables
}
