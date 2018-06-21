package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Match;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TableFrame extends javax.swing.JFrame {

    private static Match match;

    public TableFrame(Match match) {
        this.match = match;
        initComponents();
        setLocationRelativeTo(null);
        setIcons();

        addRoundTrack();

/*
        //TEST carta singola
        CardField cf = new CardField();
        tableFramePanel.add(cf);
        cf.setBounds(0, 0, 173, 245);
*/

        //TEST campi carte
        String ids1[] = {"tc01.png", "tc02.png", "tc03.png"};
        setToolCards(ids1);
        String ids2[] = {"po01.png", "po02.png", "po03.png"};
        setOBJCards(ids2);

        //TEST set dice
        Dice testDice = new Dice();
        testDice.setNumFacciaUp(5);
        testDice.setDiceColor(it.polimi.ing.sw.model.Color.RED);
        roundTrack.getDiceGUIList().get(4).setDice(testDice);
    }

    private void addRoundTrack() {
        roundTrack = new RoundTrack();
        tableFramePanel.add(roundTrack);
        roundTrack.setBounds(182, 20, 660, 70);
    }

    public void setIcons() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/sagrada.png")));
        this.setTitle("Sagrada Boardgame");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableFramePanel = new javax.swing.JPanel();
        objCardPanel = new javax.swing.JPanel();
        toolCardPanel = new javax.swing.JPanel();
        backgroundTableFRameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setResizable(false);

        tableFramePanel.setBackground(new java.awt.Color(116, 65, 65));
        tableFramePanel.setLayout(null);

        javax.swing.GroupLayout objCardPanelLayout = new javax.swing.GroupLayout(objCardPanel);
        objCardPanel.setLayout(objCardPanelLayout);
        objCardPanelLayout.setHorizontalGroup(
                objCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 519, Short.MAX_VALUE)
        );
        objCardPanelLayout.setVerticalGroup(
                objCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 245, Short.MAX_VALUE)
        );

        tableFramePanel.add(objCardPanel);
        objCardPanel.setBounds(250, 500, 519, 245);

        javax.swing.GroupLayout toolCardPanelLayout = new javax.swing.GroupLayout(toolCardPanel);
        toolCardPanel.setLayout(toolCardPanelLayout);
        toolCardPanelLayout.setHorizontalGroup(
                toolCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 519, Short.MAX_VALUE)
        );
        toolCardPanelLayout.setVerticalGroup(
                toolCardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 245, Short.MAX_VALUE)
        );

        tableFramePanel.add(toolCardPanel);
        toolCardPanel.setBounds(250, 230, 519, 245);

        backgroundTableFRameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        backgroundTableFRameLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sagrada.png"))); // NOI18N
        backgroundTableFRameLabel.setAlignmentY(0.0F);
        tableFramePanel.add(backgroundTableFRameLabel);
        backgroundTableFRameLabel.setBounds(0, 0, 1024, 768);

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

    public void tornaMenu() {
        this.setVisible(false);
        App.menu.setVisible(true);
    }

    public void setToolCards(String[] id) {
        toolCardPanel.setLayout(null);
        toolCardPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        for (int i = 0; i < 3; i++) {
            CardField schemeCard = new CardField(id[i], "tc/");
            toolCardPanel.add(schemeCard);
            schemeCard.setBounds(i * 173, 0, 173, 245);
            schemeCard.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        }
    }

    public void setOBJCards(String[] id) {
        objCardPanel.setLayout(null);
        objCardPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        for (int i = 0; i < 3; i++) {
            CardField schemeCard = new CardField(id[i], "po/");
            objCardPanel.add(schemeCard);
            schemeCard.setBounds(i * 173, 0, 173, 245);
            schemeCard.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        }
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            //java.util.logging.Logger.getLogger(AddScheme.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        /* Create and display the form */

        final Match match = new Match();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableFrame(match).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel backgroundTableFRameLabel;
    private JPanel objCardPanel;
    private JPanel tableFramePanel;
    private JPanel toolCardPanel;
    private RoundTrack roundTrack;
    // End of variables declaration//GEN-END:variables
}
