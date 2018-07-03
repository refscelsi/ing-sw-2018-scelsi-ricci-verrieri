package it.polimi.ing.sw.ui.gui.toolCardsActrionFrames;

import it.polimi.ing.sw.ui.gui.GUI;

import java.rmi.RemoteException;

public class ToolCard11ActionForm extends javax.swing.JFrame {

    private int numFaceUp;
    private int operation = -1;
    private GUI gui;

    public ToolCard11ActionForm(int numFaceUp, GUI gui) {
        this.gui = gui;
        initComponents();
        numberButton.setText(String.valueOf(numFaceUp));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        plusLabel = new javax.swing.JLabel();
        minusLabel = new javax.swing.JLabel();
        numberButton = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 102, 102));

        plusLabel.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
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

        minusLabel.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
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

        numberButton.setFont(new java.awt.Font("Elephant", 1, 24)); // NOI18N
        numberButton.setForeground(new java.awt.Color(0, 0, 0));
        numberButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        numberButton.setText("1");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(minusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cancelButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(numberButton, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                                        .addComponent(plusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(minusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                                                .addComponent(numberButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addComponent(plusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(cancelButton)
                                        .addComponent(okButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, Short.MAX_VALUE)
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
        setVisible(false);
        //gui.useToolCard11(numFaceUp);
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void plusLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusLabelMousePressed
        plusLabel.setForeground(new java.awt.Color(255, 0, 0));

        if (Integer.valueOf(numberButton.getText()) == 6) {
            numFaceUp = 1;
            numberButton.setText(String.valueOf(numFaceUp));
        }

    }//GEN-LAST:event_plusLabelMousePressed

    private void plusLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_plusLabelMouseReleased
        plusLabel.setForeground(new java.awt.Color(222, 222, 222));
    }//GEN-LAST:event_plusLabelMouseReleased

    private void minusLabelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusLabelMousePressed
        minusLabel.setForeground(new java.awt.Color(255, 0, 0));

        if (Integer.valueOf(numberButton.getText()) == 1) {
            numFaceUp = 6;
            numberButton.setText(String.valueOf(numFaceUp));
        }

    }//GEN-LAST:event_minusLabelMousePressed

    private void minusLabelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minusLabelMouseReleased
        minusLabel.setForeground(new java.awt.Color(222, 222, 222));
    }//GEN-LAST:event_minusLabelMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minusLabel;
    private javax.swing.JLabel numberButton;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel plusLabel;
    // End of variables declaration//GEN-END:variables
}
