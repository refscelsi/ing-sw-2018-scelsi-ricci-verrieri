package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ing.sw.util.Constants.SAGRADA_ICO;

public class ScoreBoard extends javax.swing.JFrame {

    private List<JLabel> players;
    private List<JLabel> points;

    public ScoreBoard(Match match) {
        initComponents();
        setIcons();

        setLocationRelativeTo(null);

        setPlayersList();
        setPointsList();

        setPoints(match);
    }

    private void setPointsList() {
        points = new ArrayList<>();
        points.add(score1);
        points.add(score2);
        points.add(score3);
        points.add(score4);
        for (int i = 0; i < 4; i++) {
        }
    }

    private void setPlayersList() {
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
    }

    private void setIcons() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(SAGRADA_ICO)));
        this.setTitle("Sagrada Boardgame");
    }

    private void setPoints(Match match) {
        int counter = 0;
        for (Player player : match.getRanking()) {
            players.get(counter).setText(player.getNickname());
            points.get(counter).setText(String.valueOf(player.getScore()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        player1 = new javax.swing.JLabel();
        score1 = new javax.swing.JLabel();
        player2 = new javax.swing.JLabel();
        score2 = new javax.swing.JLabel();
        score3 = new javax.swing.JLabel();
        player3 = new javax.swing.JLabel();
        player4 = new javax.swing.JLabel();
        score4 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        player1.setFont(new java.awt.Font("Elephant", 2, 11)); // NOI18N
        player1.setForeground(new java.awt.Color(0, 0, 0));
        player1.setText("Player1");

        score1.setFont(new java.awt.Font("Elephant", 1, 11)); // NOI18N
        score1.setForeground(new java.awt.Color(0, 0, 0));
        score1.setText("X");

        player2.setFont(new java.awt.Font("Elephant", 2, 11)); // NOI18N
        player2.setForeground(new java.awt.Color(0, 0, 0));
        player2.setText("Player2");

        score2.setFont(new java.awt.Font("Elephant", 1, 11)); // NOI18N
        score2.setForeground(new java.awt.Color(0, 0, 0));
        score2.setText("X");

        score3.setFont(new java.awt.Font("Elephant", 1, 11)); // NOI18N
        score3.setForeground(new java.awt.Color(0, 0, 0));
        score3.setText("X");

        player3.setFont(new java.awt.Font("Elephant", 2, 11)); // NOI18N
        player3.setForeground(new java.awt.Color(0, 0, 0));
        player3.setText("Player3");

        player4.setFont(new java.awt.Font("Elephant", 2, 11)); // NOI18N
        player4.setForeground(new java.awt.Color(0, 0, 0));
        player4.setText("Player4");

        score4.setFont(new java.awt.Font("Elephant", 1, 11)); // NOI18N
        score4.setForeground(new java.awt.Color(0, 0, 0));
        score4.setText("X");

        title.setFont(new java.awt.Font("Elephant", 3, 24)); // NOI18N
        title.setForeground(new java.awt.Color(0, 0, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("SCORE BOARD");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(player2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(player1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(player3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(player4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                        .addComponent(score1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(36, 36, 36))
                                                .addGroup(layout.createSequentialGroup()
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(score3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addComponent(score2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addContainerGap()))
                                        .addComponent(score4, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(player1)
                                        .addComponent(score1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(player2)
                                        .addComponent(score2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(player3)
                                        .addComponent(score3))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(player4)
                                        .addComponent(score4))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel player1;
    private javax.swing.JLabel player2;
    private javax.swing.JLabel player3;
    private javax.swing.JLabel player4;
    private javax.swing.JLabel score1;
    private javax.swing.JLabel score2;
    private javax.swing.JLabel score3;
    private javax.swing.JLabel score4;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
