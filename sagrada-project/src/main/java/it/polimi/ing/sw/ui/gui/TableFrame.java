package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Match;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TableFrame extends javax.swing.JFrame {

    private static Match match;
    private static List<CardField> toolCardList, objCardList;
    private RoundTrack roundTrack;
    private VetrataPanel player1, player2, player3, player4;

    public TableFrame(/*Match match*/) {
        //this.match = match;
        initComponents();
        setLocationRelativeTo(null);
        setIcons();

        roundTrack = new RoundTrack();
        toolCardList = new ArrayList<>();
        objCardList = new ArrayList<>();

        player1 = new VetrataPanel();
        tableFramePanel.add(player1);
        player1.setBounds(780, 430, 230, 290);

        player2 = new VetrataPanel();
        tableFramePanel.add(player2);
        player2.setBounds(780, 100, 230, 290);

        player3 = new VetrataPanel();
        tableFramePanel.add(player3);
        player3.setBounds(10, 100, 230, 290);

        player4 = new VetrataPanel();
        tableFramePanel.add(player4);
        player4.setBounds(10, 430, 230, 290);

        addRoundTrack();

/*
        //TEST carta singola
        CardField cf = new CardField();
        tableFramePanel.add(cf);
        cf.setBounds(0, 0, 173, 245);
*/

        //TEST campi carte
        String ids1[] = {"disabled.png", "tc02.png", "tc03.png"};
        setToolCards(ids1);
        String ids2[] = {"po01.png", "po02.png", "po03.png"};
        setOBJCards(ids2);

        //TEST set dice
        Dice testDice = new Dice();
        testDice.setNumFacciaUp(5);
        testDice.setDiceColor(it.polimi.ing.sw.model.Color.RED);
        roundTrack.getDiceGUIList().get(4).setDice(testDice);

        //TEST disablecard
        //toolCardList.get(1).disableToolCard();
    }

    private void addRoundTrack() {
        roundTrack = new RoundTrack();
        tableFramePanel.add(roundTrack);
        roundTrack.setBounds(207, 20, 610, 70);
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

        tableFramePanel.setBackground(new java.awt.Color(102, 102, 102));
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

        backgroundTableFRameLabel.setBackground(new java.awt.Color(153, 153, 153));
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
        //toolCardPanel.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        for (int i = 0; i < 3; i++) {
            CardField schemeCard = new CardField(id[i], "tc/");
            toolCardPanel.add(schemeCard);
            toolCardList.add(schemeCard);
            schemeCard.setBounds(i * (173), 0, 173, 245);
            //schemeCard.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        }
    }

    public void setOBJCards(String[] id) {
        objCardPanel.setLayout(null);
        //objCardPanel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        for (int i = 0; i < 3; i++) {
            CardField schemeCard = new CardField(id[i], "po/");
            objCardPanel.add(schemeCard);
            objCardList.add(schemeCard);
            schemeCard.setBounds(i * (173), 0, 173, 245);
            //schemeCard.setBorder(new LineBorder(new java.awt.Color(0, 0, 0), 2, true));
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

        //final Match match = new Match();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableFrame(/*match*/).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundTableFRameLabel;
    private javax.swing.JPanel objCardPanel;
    private javax.swing.JPanel tableFramePanel;
    private javax.swing.JPanel toolCardPanel;
    // End of variables declaration//GEN-END:variables
}
