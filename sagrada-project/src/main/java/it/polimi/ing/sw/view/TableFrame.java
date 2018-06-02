/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.polimi.ing.sw.view;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Match;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TableFrame extends javax.swing.JFrame {

    private Match match;

    public TableFrame(Match match) {
        this.match=match;
        initComponents();
        setLocationRelativeTo(null);
        setIcons();
        
        CardField cf = new CardField();
        tableFramePanel.add(cf);
        cf.setBounds(0,0,173, 245);
    }

    public void setIcons(){
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableFramePanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tornaMenu();
            }
        };
        addWindowListener(exitListener);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setResizable(false);

        tableFramePanel.setBackground(new java.awt.Color(116, 65, 65));
        tableFramePanel.setLayout(null);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sagrada.png"))); // NOI18N
        jLabel1.setAlignmentY(0.0F);
        tableFramePanel.add(jLabel1);
        jLabel1.setBounds(0, 0, 1024, 768);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableFramePanel, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void tornaMenu(){
        this.setVisible(false);
        App.menu.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel tableFramePanel;
    // End of variables declaration//GEN-END:variables
}
