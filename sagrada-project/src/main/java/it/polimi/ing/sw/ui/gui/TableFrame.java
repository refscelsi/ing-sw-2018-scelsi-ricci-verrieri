package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.App;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static it.polimi.ing.sw.util.Constants.NOT_A_DICE;
import static it.polimi.ing.sw.util.Constants.SAGRADA_ICO;

public class TableFrame extends javax.swing.JFrame {

    public static List<Boolean> isToolCard;
    private static Match match;
    private static List<CardField> toolCardList;
    private static List<CardField> objCardList;
    private static RoundTrack roundTrack;
    private static VetrataPanel player1;
    private static VetrataPanel player2;
    private static VetrataPanel player3;
    private static VetrataPanel player4;
    private static DiceFieldPanel diceFieldPanel;
    private static SumPlayerPanel sumPlayer1;
    private static SumPlayerPanel sumPlayer2;
    private static SumPlayerPanel sumPlayer3;
    private static SumPlayerPanel sumPlayer4;
    private static int dimXcard;
    private static int dimYcard;
    public static String idSelectedTc;

    private static GUI gui;

    TableFrame(Match match, GUI gui) {
        TableFrame.match = match;
        TableFrame.gui = gui;

        isToolCard = new ArrayList<>();
        setUpIsToolCard();

        toolCardList = new ArrayList<>();
        objCardList = new ArrayList<>();

        dimXcard = 173;
        dimYcard = 245;

        initComponents();
        setLocationRelativeTo(null);
        setIcons();
        setName("tableFrame");

        addVetratePlayers();
        addSumsPlayer();
        addRoundTrack();
        addDiceFieldPAnel();
    }

    public static void isNotToolCardAnymore(int i) {
        TableFrame.isToolCard.set(i, false);
        for (int j=0;j<3;j++){
            if (toolCardList.get(j).getId().equals(String.valueOf(i))){
                toolCardList.get(j).setBorder(null);
            }
        }
    }

    private void setUpIsToolCard() {
        for (int i = 0; i < 12; i++) {
            isToolCard.add(false);
        }
    }

    public static Match getMatch() {
        return match;
    }

    static void updateMatch(Match match) {

        if (!(null == match.getRoundTrack().getRoundTrack())) {
            roundTrack.setDraftPool(match.getRoundTrack().getRoundTrack());
        }

        if (!match.getPublicObjectives().isEmpty()) {
            List<String> ids = new ArrayList<>();
            ids.add(String.valueOf(match.getPublicObjectives().get(0).getId()));
            ids.add(String.valueOf(match.getPublicObjectives().get(1).getId()));
            ids.add(String.valueOf(match.getPublicObjectives().get(2).getId()));
            setOBJCards(ids);
        }
        if (!match.getToolCards().isEmpty()) {
            List<String> ids = new ArrayList<>();
            ids.add(String.valueOf(match.getToolCards().get(0).getId()));
            ids.add(String.valueOf(match.getToolCards().get(1).getId()));
            ids.add(String.valueOf(match.getToolCards().get(2).getId()));
            setToolCards(ids);
        }

        int counter = 0;
        for (Player player : match.getPlayers()) {
            switch (counter) {
                case 0:
                    updatePlayer1(player);
                    break;
                case 1:
                    updatePlayer2(player);
                    break;
                case 2:
                    updatePlayer3(player);
                    break;
                case 3:
                    updatePlayer4(player);
                    break;
            }
            counter++;
        }
        diceFieldPanel.setDices(match.getDraftPool().getDraftPool());
    }

    private static void updatePlayer1(Player player) {
        sumPlayer1.setPlayerName(player.getNickname());
        sumPlayer1.setSchemeName(String.valueOf(player.getScheme().getId()));
        sumPlayer1.setToken(player.getNumOfToken());
        sumPlayer1.setPrivateObjCard(player.getPrivateObjective());

        player1.fillScheme(player.getScheme());
        player1.setPlayerName(player.getNickname());
    }

    private static void updatePlayer2(Player player) {
        sumPlayer2.setPlayerName(player.getNickname());
        sumPlayer2.setSchemeName(String.valueOf(player.getScheme().getId()));
        sumPlayer2.setToken(player.getNumOfToken());
        sumPlayer2.setPrivateObjCard(player.getPrivateObjective());

        player2.fillScheme(player.getScheme());
        player2.setPlayerName(player.getNickname());
    }

    private static void updatePlayer3(Player player) {
        sumPlayer3.setPlayerName(player.getNickname());
        sumPlayer3.setSchemeName(String.valueOf(player.getScheme().getId()));
        sumPlayer3.setToken(player.getNumOfToken());
        sumPlayer3.setPrivateObjCard(player.getPrivateObjective());

        player3.fillScheme(player.getScheme());
        player3.setPlayerName(player.getNickname());
    }

    private static void updatePlayer4(Player player) {
        sumPlayer4.setPlayerName(player.getNickname());
        sumPlayer4.setSchemeName(String.valueOf(player.getScheme().getId()));
        sumPlayer4.setToken(player.getNumOfToken());
        sumPlayer4.setPrivateObjCard(player.getPrivateObjective());

        player4.fillScheme(player.getScheme());
        player4.setPlayerName(player.getNickname());
    }

    public static Boolean isPlayerTurn(String nickname, char id) {
        switch (id) {
            case '1':
                if (sumPlayer1.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            case '2':
                if (sumPlayer2.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            case '3':
                if (sumPlayer3.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            case '4':
                if (sumPlayer4.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private static void addSumsPlayer() {
        sumPlayer1 = new SumPlayerPanel();
        backgroundTableFRameLabel.add(sumPlayer1);
        sumPlayer1.setBounds(10, 10, 85, 80);

        sumPlayer2 = new SumPlayerPanel();
        backgroundTableFRameLabel.add(sumPlayer2);
        sumPlayer2.setBounds(105, 10, 85, 80);

        sumPlayer3 = new SumPlayerPanel();
        backgroundTableFRameLabel.add(sumPlayer3);
        sumPlayer3.setBounds(830, 10, 85, 80);

        sumPlayer4 = new SumPlayerPanel();
        backgroundTableFRameLabel.add(sumPlayer4);
        sumPlayer4.setBounds(925, 10, 85, 80);
    }

    private static void addVetratePlayers() {
        player1 = new VetrataPanel(1, gui, 230, 290);
        backgroundTableFRameLabel.add(player1);
        player1.setBounds(780, 430, 230, 290);

        player2 = new VetrataPanel(2, gui, 230, 290);
        backgroundTableFRameLabel.add(player2);
        player2.setBounds(780, 100, 230, 290);

        player3 = new VetrataPanel(3, gui, 230, 290);
        backgroundTableFRameLabel.add(player3);
        player3.setBounds(10, 100, 230, 290);

        player4 = new VetrataPanel(4, gui, 230, 290);
        backgroundTableFRameLabel.add(player4);
        player4.setBounds(10, 430, 230, 290);
    }

    private static void addDiceFieldPAnel() {
        diceFieldPanel = new DiceFieldPanel(gui);
        backgroundTableFRameLabel.add(diceFieldPanel);
        diceFieldPanel.setBounds(250, 100, 520, 140);
    }

    private static void addRoundTrack() {
        roundTrack = new RoundTrack();
        backgroundTableFRameLabel.add(roundTrack);
        roundTrack.setBounds(207, 20, 610, 70);
    }

    public static boolean isPlayerScheme(String nickname, char id) {
        switch (id) {
            case '1':
                if (player1.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            case '2':
                if (player2.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            case '3':
                if (player3.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            case '4':
                if (player4.getPlayerName().equals(nickname)) {
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    private void setIcons() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(SAGRADA_ICO)));
        this.setTitle("Sagrada Boardgame");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableFramePanel = new javax.swing.JPanel();
        objCardPanel = new javax.swing.JPanel();
        toolCardPanel = new javax.swing.JPanel();
        endTurnButton = new javax.swing.JButton();
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
        toolCardPanel.setBounds(250, 250, 519, 245);

        endTurnButton.setText("End Turn");
        endTurnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    endTurnButtonActionPerformed(evt);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        tableFramePanel.add(endTurnButton);
        endTurnButton.setBounds(820, 730, 150, 25);

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

    private void endTurnButtonActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {//GEN-FIRST:event_endTurnButtonActionPerformed
        if (gui.isPlaying()){
            gui.endTurn();
        }
    }//GEN-LAST:event_endTurnButtonActionPerformed

    public void tornaMenu() {
        this.setVisible(false);
        App.menu.setVisible(true);
    }

    private static void setToolCards(List<String> id) {
        toolCardPanel.setLayout(null);
        for (int i = 0; i < 3; i++) {
            CardField schemeCard = new CardField(id.get(i), "tc/", dimXcard, dimYcard);
            schemeCard.setController(gui);
            toolCardPanel.add(schemeCard);
            toolCardList.add(schemeCard);
            schemeCard.setIsToolCard(true);
            schemeCard.setBounds(i * (173), 0, 173, 245);
        }
    }

    private static void setOBJCards(List<String> id) {
        objCardPanel.setLayout(null);
        for (int i = 0; i < 3; i++) {
            CardField schemeCard = new CardField(id.get(i), "po/", dimXcard, dimYcard);
            objCardPanel.add(schemeCard);
            objCardList.add(schemeCard);
            schemeCard.setBounds(i * (173), 0, 173, 245);
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

        Match match = new Match();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableFrame(match, gui).setVisible(true);
            }
        });
    }

    public static void updateDice(int idPlayer, Dice dice, int i, int j) {
        switch (idPlayer) {
            case 1:
                player1.setDice(i, j, dice);
                break;
            case 2:
                player2.setDice(i, j, dice);
                break;
            case 3:
                player3.setDice(i, j, dice);
                break;
            case 4:
                player4.setDice(i, j, dice);
                break;
        }
    }

    public static void setCurrentComponentName(String nameComponentEntered, Boolean isAdiceGui) {
        currentComponentName = nameComponentEntered;
        TableFrame.isAdiceGui = isAdiceGui;
    }

    public static String getCurrentComponentName() {
        if (isAdiceGui) {
            return currentComponentName;
        } else {
            return NOT_A_DICE;
        }
    }

    public static void setIsAdiceGui(Boolean isAdiceGui) {
        TableFrame.isAdiceGui = isAdiceGui;
    }

    public static Boolean aToolCardIsUsed() {
        for (CardField card : toolCardList) {
            if (card.getUsed())
                return true;
        }
        return false;
    }

    private static Boolean isAdiceGui;
    private static String currentComponentName;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel backgroundTableFRameLabel;
    private javax.swing.JButton endTurnButton;
    private static javax.swing.JPanel objCardPanel;
    private javax.swing.JPanel tableFramePanel;
    private static javax.swing.JPanel toolCardPanel;
    // End of variables declaration//GEN-END:variables
}
