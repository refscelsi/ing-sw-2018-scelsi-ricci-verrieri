package it.polimi.ing.sw.ui.gui;

import it.polimi.ing.sw.client.UiUpdate;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.ui.gui.toolCardsActrionFrames.ToolCard11ActionForm;
import it.polimi.ing.sw.ui.gui.toolCardsActrionFrames.ToolCard1ActionForm;

import javax.swing.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

public class GUI implements UiUpdate {

    public Scanner scanner = new Scanner(System.in);
    public String inText;

    private TableFrame tableFrame;
    private View controller;

    public GUI(View controller) {
        this.controller = controller;
    }

    private Match resetMatch;

    public View getController() throws RemoteException {
        if (controller == null)
            controller = new View();
        return controller;
    }

    public void chooseNetwork(String message) throws RemoteException {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ToolCard1ActionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ToolCard1ActionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ToolCard1ActionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ToolCard1ActionForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        NewPlayerForm newPlayerForm = new NewPlayerForm(getController());
        newPlayerForm.setVisible(true);
    }

    public void chooseScheme(Match match, String nickname, String message) throws RemoteException {
        ArrayList<Scheme> schemes = match.getPlayer(nickname).getSchemesToChoose();
        showSchemesToChoose(schemes);
    }

    public void showSchemesToChoose(ArrayList<Scheme> schemes) throws RemoteException {
        ChooseSchemeForm chooseSchemeForm = new ChooseSchemeForm(schemes, getController());
        chooseSchemeForm.setVisible(true);
    }

    public void retryPlaceDice() throws RemoteException {
//        JOptionPane.showMessageDialog(null,
//                "Invalid placing dice action, retry or do something different.",
//                "Placing dice error",
//                JOptionPane.ERROR_MESSAGE);
        onGameUpdate(resetMatch, controller.getNickname());
    }

    public void useToolCard(int id, Match match) throws RemoteException {
        switch (id) {
            case 5:
                useToolCard5(match);
                break;
            default:
                break;
        }
    }

    public void useToolCard1(DiceGUI dice) {
        ToolCard1ActionForm toolCard1ActionForm = new ToolCard1ActionForm(dice, this);
        toolCard1ActionForm.setVisible(true);
        TableFrame.isNotToolCardAnymore(1 - 1);
    }

    public void performCallToolCard1(int name, int operation) throws RemoteException {
        controller.useToolCard(1, name - 1, operation, -1, -1, -1, -1);
    }

    public void useToolCard6(int name) throws RemoteException {
        if (0 <= name && 9 > name) {
            //TODO handling empty cose
            controller.useToolCard(6, name, -1, -1, -1, -1, -1);
            TableFrame.isNotToolCardAnymore(6 - 1);
        }
    }

    public void useToolCard10(int name) throws RemoteException {
        if (0 <= name && 9 > name) {
            //TODO handling empty cose
            controller.useToolCard(10, name, -1, -1, -1, -1, -1);
            TableFrame.isNotToolCardAnymore(10 - 1);
        }
    }

    public void useToolCard11(int name) throws RemoteException {
        if (0 <= name && 9 > name) {
            //TODO handling empty cose
            controller.useToolCard(11, name, -1, -1, -1, -1, -1);
            TableFrame.isNotToolCardAnymore(11 - 1);
        }
    }

    public void useToolCard2(FloatingDiceFrame floatingDiceFrame, int destX, int destY) throws RemoteException {
        controller.useToolCard(2, -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
        TableFrame.isNotToolCardAnymore(2 - 1);
    }

    public void useToolCard3(FloatingDiceFrame floatingDiceFrame, int destX, int destY) throws RemoteException {
        controller.useToolCard(3, -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
        TableFrame.isNotToolCardAnymore(3 - 1);
    }

    public void useToolCard4(FloatingDiceFrame floatingDiceFrame, int destX, int destY) throws RemoteException {
        controller.useToolCard(4, -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
        TableFrame.isNotToolCardAnymore(4 - 1);
    }

    public void useToolCard7() throws RemoteException {
        controller.useToolCard(7, -1, -1, -1, -1, -1, -1);
        TableFrame.isNotToolCardAnymore(7 - 1);
    }

    public void useToolCard8() throws RemoteException {
        controller.useToolCard(8, -1, -1, -1, -1, -1, -1);
        TableFrame.isNotToolCardAnymore(8 - 1);
    }

    public void useToolCard12(FloatingDiceFrame floatingDiceFrame, int destX, int destY) throws RemoteException {
        controller.useToolCard(12, -1, -1, floatingDiceFrame.getDiceX() - 1, floatingDiceFrame.getDiceY() - 1, destX - 1, destY - 1);
        TableFrame.isNotToolCardAnymore(12 - 1);
    }

    public void useToolCard11(int value, int destX, int destY) throws RemoteException {
        controller.useToolCard(11, -1, value, destX, destY, -1, -1);
        TableFrame.isNotToolCardAnymore(11 - 1);
    }

    public void useToolCard9(int name, int destX, int destY) throws RemoteException {

        resetMatch = controller.getMatch();

        if (0 <= name && 9 > name) {
            TableFrame.isNotToolCardAnymore(9 - 1);
            //TODO handling empty cose
            controller.useToolCard(9, name, -1, destX, destY, -1, -1);
        } else {
            onActionNotValid("wrong action");
        }
    }

    public void useDice(int name, int destX, int destY) throws RemoteException {
        resetMatch = controller.getMatch();
        //TODO handling empty cose
        controller.useDice(name, destX, destY);
        //onGameUpdate(controller.getMatch(), getNickname());
    }

    public void useToolCard5(Match match) throws RemoteException {
        int dice, round, indexInRound;
        boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
        if (!roundTrackIsFull) {
            System.out.println("Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round");
            //chooseAction(match, controller.getNickname());
        } else {
            do {
                System.out.println("Digita l'indice del dado della riserva che vuoi scambiare, tra 1 e " + match.getDraftPool().getSize());
                dice = scanner.nextInt();
            } while (dice < 1 || dice > match.getDraftPool().getSize());
            do {
                System.out.println("Digita il numero di round a cui appartiene il dado con cui vuoi scambiarlo, tra 1 e " + match.getRoundTrack().getRoundTrackSize());
                round = scanner.nextInt();
            } while (round < 1 || round > match.getRoundTrack().getRoundTrackSize());
            do {
                System.out.println("Digita l'indice del dado nel round che hai scelto, tra 0 e " + (match.getRoundTrack().getNumberOfDices(round) - 1));
                indexInRound = scanner.nextInt();
            } while (indexInRound < 0 || indexInRound > match.getRoundTrack().getNumberOfDices(round) - 1);
            controller.useToolCard(5, dice - 1, -1, round, indexInRound, -1, -1);
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta E: terminare il turno
    /////////////////////////////////////////////////////////////////////////////////////////

    public void endTurn() throws RemoteException {
        controller.endTurn();
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi che invoca PlayerController su UiUpdate
    /////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onLogin(String message) {
        //managed by gui
    }

    @Override
    public void onActionNotValid(String errorCode) {
        System.out.println(errorCode);

        JOptionPane.showMessageDialog(null,
                errorCode,
                "Not valid Action",
                JOptionPane.ERROR_MESSAGE);

        try {
            onGameUpdate(resetMatch, getNickname());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onChooseNetwork(String message) throws RemoteException {
        chooseNetwork(message);
    }

    @Override
    public void onTurnStart(Match match, String nickname) throws RemoteException {
        JOptionPane.showMessageDialog(null,
                "Turn Strted");
        onGameUpdate(controller.getMatch(), nickname);
    }

    @Override
    public void onPlaceDiceNotValid(NotValidException e) throws RemoteException {
        //TODO retet match
        System.out.println(e);
        retryPlaceDice();
    }

    @Override
    public void onGameUpdate(Match match, String nickname) throws RemoteException {
        if (tableFrame == null) {
            tableFrame = new TableFrame(match, this);
            tableFrame.setVisible(true);
        }

        TableFrame.updateMatch(match);
    }

    @Override
    public void onGameEnd(Match match) {
        tableFrame.setVisible(false);
        ScoreBoard scoreBoard = new ScoreBoard(match);
        scoreBoard.setVisible(true);
    }

    @Override
    public void onSchemeToChoose(Match match, String nickname, String message) throws RemoteException {
        chooseScheme(match, nickname, message);
    }

    @Override
    public void onUseToolCardNotValid(int id, Match match, String errorCode) throws RemoteException {
        System.out.println(errorCode);
        onGameUpdate(resetMatch, controller.getNickname());
        JOptionPane.showMessageDialog(null,
                errorCode,
                "Not valid Action",
                JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void onOtherInfoToolCard(int id, Match match) throws RemoteException {
        switch (id) {
            case 4: {
                //useToolCard23412( 4 );
                JOptionPane.showMessageDialog(null,
                        "Primo dado mosso correttamente, ora muovi il secondo");
            }
            break;
            case 6: {
                JOptionPane.showMessageDialog(null,
                        "Ora seleziona la casella dove posizionare il dado");
                retryPlaceDice();
            }
            break;
            case 11: {
                int value = 1;
                ToolCard11ActionForm toolCard11ActionForm = new ToolCard11ActionForm(value, this);
                toolCard11ActionForm.setVisible(true);
            }
            break;
            case 12: {
                int reply = JOptionPane.showConfirmDialog(null, "Primo dado mosso correttamente, clicca no se non vuoi spostare più dadi o sì se vuoi spostarne un altro", "ToolCard12", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    // sulla gui muove un dado
                } else {
                    controller.useToolCard(12, -1, -1, -1, -1, -1, -1);
                }
            }
        }
    }

    @Override
    public void onSuccess(String message) {
        if (null != message) {
            /*
            JOptionPane.showMessageDialog(null,
                    message);*/
        }
    }

    public String getNickname() {
        return controller.getNickname();
    }

    public boolean checkIfRoundTrackIsFull() {
        return controller.checkIfRoundTrackIsFull();
    }

    public boolean isPlaying() {
        return controller.isPlaying();
    }
}