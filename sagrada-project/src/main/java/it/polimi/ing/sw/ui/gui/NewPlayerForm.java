package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.client.View;

import java.awt.*;
import java.rmi.RemoteException;

import static it.polimi.ing.sw.util.Constants.*;

public class NewPlayerForm extends javax.swing.JFrame {

    private Boolean serverModified;
    private Boolean nameModified;
    private View controller;
    private static String network;

    public NewPlayerForm(View controller) throws RemoteException {
        this.controller = controller;
        initComponents();
        serverModified = false;
        nameModified = false;
        setIcons();
        setLocationRelativeTo(null);
        rmiRadioButton.setSelected(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new javax.swing.JPanel();
        serverIpLabel = new javax.swing.JLabel();
        serverIpTextField = new javax.swing.JTextField();
        playerNameTextField = new javax.swing.JTextField();
        playerNameLabel = new javax.swing.JLabel();
        connectButton = new javax.swing.JButton();
        socketRadioButton = new javax.swing.JRadioButton();
        rmiRadioButton = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        serverIpLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        serverIpLabel.setText("Server IP");

        serverIpTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        serverIpTextField.setText(SERVER_ADDRESS);
        serverIpTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                serverIpTextFieldMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                serverIpTextFieldMouseExited(evt);
            }
        });
        serverIpTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                serverIpTextFieldKeyTyped(evt);
            }
        });

        playerNameTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        playerNameTextField.setText("Player");
        playerNameTextField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                playerNameTextFieldMouseEntered(evt);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                playerNameTextFieldMouseExited(evt);
            }
        });
        playerNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                playerNameTextFieldKeyTyped(evt);
            }
        });

        playerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playerNameLabel.setText("Player Name");

        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        socketRadioButton.setText("Socket");
        socketRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                socketRadioButtonActionPerformed(evt);
            }
        });

        rmiRadioButton.setText("RMI");
        rmiRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rmiRadioButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Connection type");

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(playerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                                                        .addComponent(playerNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGap(60, 60, 60)))
                                                        .addComponent(serverIpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(panelLayout.createSequentialGroup()
                                                                .addComponent(serverIpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(59, 59, 59)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(panelLayout.createSequentialGroup()
                                                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(socketRadioButton))
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(78, 78, 78))
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelLayout.createSequentialGroup()
                                        .addGap(22, 22, 22)
                                        .addComponent(rmiRadioButton)
                                        .addContainerGap(123, Short.MAX_VALUE)))
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(serverIpLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(serverIpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(playerNameLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addGap(5, 5, 5)
                                .addComponent(socketRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(connectButton)
                                .addContainerGap())
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelLayout.createSequentialGroup()
                                        .addContainerGap(169, Short.MAX_VALUE)
                                        .addComponent(rmiRadioButton)
                                        .addGap(44, 44, 44)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void serverIpTextFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serverIpTextFieldMouseEntered
        if (!serverModified) {
            serverIpTextField.setText(null);
        }
    }//GEN-LAST:event_serverIpTextFieldMouseEntered

    private void serverIpTextFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_serverIpTextFieldMouseExited
        if (!serverModified) {
            serverIpTextField.setText(SERVER_ADDRESS);
        }
    }//GEN-LAST:event_serverIpTextFieldMouseExited

    private void playerNameTextFieldMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerNameTextFieldMouseEntered
        if (!nameModified) {
            playerNameTextField.setText(null);
        }
    }//GEN-LAST:event_playerNameTextFieldMouseEntered

    private void playerNameTextFieldMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playerNameTextFieldMouseExited
        if (!nameModified) {
            playerNameTextField.setText(PLAYER_STANDARD_NAME);
        }
    }//GEN-LAST:event_playerNameTextFieldMouseExited

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectButtonActionPerformed
        if (socketRadioButton.isSelected()) {
            network = SOCKET_CODE;
        } else {
            network = RMI_CODE;
        }
        controller.chooseNetwork(network);

        controller.login(playerNameTextField.getText());
        setVisible(false);
    }//GEN-LAST:event_connectButtonActionPerformed

    private void rmiRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rmiRadioButtonActionPerformed
        socketRadioButton.setSelected(false);
    }//GEN-LAST:event_rmiRadioButtonActionPerformed

    private void socketRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_socketRadioButtonActionPerformed
        rmiRadioButton.setSelected(false);
    }//GEN-LAST:event_socketRadioButtonActionPerformed

    private void playerNameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_playerNameTextFieldKeyTyped
        nameModified = true;
    }//GEN-LAST:event_playerNameTextFieldKeyTyped

    private void serverIpTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_serverIpTextFieldKeyTyped
        serverModified = true;
    }//GEN-LAST:event_serverIpTextFieldKeyTyped

    private void setIcons() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(SAGRADA_ICO)));
        this.setTitle(DEFAULT_TITLE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel;
    private javax.swing.JLabel playerNameLabel;
    private javax.swing.JTextField playerNameTextField;
    private javax.swing.JRadioButton rmiRadioButton;
    private javax.swing.JLabel serverIpLabel;
    private javax.swing.JTextField serverIpTextField;
    private javax.swing.JRadioButton socketRadioButton;
    // End of variables declaration//GEN-END:variables
}
