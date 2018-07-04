package it.polimi.ing.sw.ui.gui.toolCardsActrionFrames;

import it.polimi.ing.sw.ui.gui.DiceGUI;
import it.polimi.ing.sw.ui.gui.GUI;
import it.polimi.ing.sw.util.ModelColorToAWTColor;

import java.rmi.RemoteException;

public class ToolCard1ActionForm extends javax.swing.JWindow {

    private int numFaceUp;
    private DiceGUI dice;
    private int operation = -1;
    private GUI gui;

    public ToolCard1ActionForm(DiceGUI dice, GUI gui) {
        this.gui = gui;
        initComponents();
        this.dice = dice;

        diceLabel.setText(String.valueOf(numFaceUp));

        diceLabel.setBackground(ModelColorToAWTColor.toColor(dice.getBox().getColor()));
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plusLabel = new javax.swing.JLabel();
        minusLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JToggleButton();
        cancelButton = new javax.swing.JToggleButton();
        diceLabel = new javax.swing.JLabel();

        plusLabel.setBackground(new java.awt.Color(0, 0, 0));
        plusLabel.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        plusLabel.setForeground(new java.awt.Color(0, 0, 0));
        plusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        plusLabel.setText("+");
        plusLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        plusLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                plusLabelMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                plusLabelMouseExited(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                plusLabelMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                plusLabelMouseReleased(evt);
            }
        });

        minusLabel.setBackground(new java.awt.Color(0, 0, 0));
        minusLabel.setFont(new java.awt.Font("Elephant", 1, 18)); // NOI18N
        minusLabel.setForeground(new java.awt.Color(0, 0, 0));
        minusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        minusLabel.setText("-");
        minusLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        minusLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                minusLabelMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                minusLabelMouseExited(evt);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                minusLabelMousePressed(evt);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                minusLabelMouseReleased(evt);
            }
        });

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    okButtonActionPerformed(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });

        cancelButton.setText("cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        diceLabel.setBackground(new java.awt.Color(255, 255, 255));
        diceLabel.setFont(new java.awt.Font("Elephant", 3, 24)); // NOI18N
        diceLabel.setForeground(new java.awt.Color(0, 0, 0));
        diceLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diceLabel.setText("5");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addComponent(cancelButton))
                                        .addComponent(minusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(diceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(okButton)
                                                .addGap(31, 31, 31))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(plusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(plusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(minusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(okButton)
                                                        .addComponent(cancelButton))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(diceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void minusLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusLabelMouseEntered
        minusLabel.setForeground(new java.awt.Color(222, 222, 222));
    }//GEN-LAST:event_minusLabelMouseEntered

    private void minusLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusLabelMouseExited
        minusLabel.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_minusLabelMouseExited

    private void plusLabelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusLabelMouseEntered
        plusLabel.setForeground(new java.awt.Color(222, 222, 222));
    }//GEN-LAST:event_plusLabelMouseEntered

    private void plusLabelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusLabelMouseExited
        plusLabel.setForeground(new java.awt.Color(0, 0, 0));
    }//GEN-LAST:event_plusLabelMouseExited

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_okButtonActionPerformed
        if (-1 != operation) {
            gui.performCallToolCard1(Integer.valueOf(dice.getName()), operation);
            setVisible(false);
        }
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void plusLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusLabelMousePressed
        plusLabel.setForeground(new java.awt.Color(255, 0, 0));

        if (((dice.getBox().getDice().getNumFacciaUp() - 1) == numFaceUp || numFaceUp == dice.getBox().getDice().getNumFacciaUp()) && numFaceUp < 6) {
            operation = 1;
            numFaceUp++;
            diceLabel.setText(String.valueOf(numFaceUp));
        }

    }//GEN-LAST:event_plusLabelMousePressed

    private void plusLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusLabelMouseReleased
        plusLabel.setForeground(new java.awt.Color(222, 222, 222));
    }//GEN-LAST:event_plusLabelMouseReleased

    private void minusLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusLabelMousePressed
        minusLabel.setForeground(new java.awt.Color(255, 0, 0));

        if (((dice.getBox().getDice().getNumFacciaUp() + 1) == numFaceUp || numFaceUp == dice.getBox().getDice().getNumFacciaUp()) && numFaceUp > 1) {
            operation = 0;
            numFaceUp--;
            diceLabel.setText(String.valueOf(numFaceUp));
        }

    }//GEN-LAST:event_minusLabelMousePressed

    private void minusLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusLabelMouseReleased
        minusLabel.setForeground(new java.awt.Color(222, 222, 222));
    }//GEN-LAST:event_minusLabelMouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton cancelButton;
    private javax.swing.JLabel diceLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minusLabel;
    private javax.swing.JToggleButton okButton;
    private javax.swing.JLabel plusLabel;
    // End of variables declaration//GEN-END:variables
}
