package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.client.UiUpdate;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.util.Constants;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public class CLI implements UiUpdate {

    public Scanner scanner = new Scanner(System.in);
    public String inText;


    private View controller;


    public CLI(View controller) {
        this.controller = controller;
    }


    public View getController() {
        return controller;
    }


    /**
     * Login del Client sul Server.
     */
    public void login(String message) {
        try {
            System.out.println(message);
            inText = scanner.nextLine().toLowerCase();
            controller.login(inText);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * Scelta della rete
     */
    public void chooseNetwork(String message) {
        try {
            do {
                System.out.println(message);
                inText = scanner.nextLine();
            } while (!inText.toLowerCase().equals("s") && !inText.toLowerCase().equals("r"));
            controller.chooseNetwork(inText);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    /**
     * Scelta dello schema tra i 4 schemi disponibili da parte di un giocatore
     */
    public void chooseScheme(Match match, String nickname, String message) {
        try {
            ArrayList<Scheme> schemes = match.getPlayer(nickname).getSchemesToChoose();
            showSchemesToChoose(schemes);
            int num;
            do {
                System.out.println(message);
                num = scanner.nextInt();
            } while (num < 1 || num > 4);
            controller.setChosenScheme(schemes.get(num - 1).getId());
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * Visualizzazione dei 4 schemi tra cui scegliere
     */
    public void showSchemesToChoose(ArrayList<Scheme> schemes) {
        try {
            for (int i = 0; i < 4; i++) {
                System.out.println("Schema " + (i + 1) + " (con ID " + schemes.get(i).getId() + " e difficoltà " + schemes.get(i).getDifficulty() + ")");
                ShowScheme show = new ShowScheme(schemes.get(i));
                System.out.println("");
            }
        } catch (NullPointerException e) {
        } catch (IndexOutOfBoundsException e) {
        }
    }


    /**
     * Scelta dell'azione da parte del giocatore
     */
    public void chooseAction(Match match, String nickname) {
        try {
            int choice;
            Boolean ok = true;
            System.out.println("Digita: \n- 1 se vuoi posizionare un dado sul tuo schema; \n- 2 se vuoi utilizzare una carta utensile; \n- 3 se vuoi visualizzare le informazioni degli altri giocatori; \n- 4 se vuoi terminare il tuo turno; \n- 5 se vuoi uscire dalla partita.");

            do {
                choice = scanner.nextInt();

                switch (choice) {

                    case 5: {
                        System.out.println("Sei sicuro che vuoi uscire dalla partita? Digita 0 se sei sicuro.");
                        choice = scanner.nextInt();
                        if (choice==0) {
                            System.out.println("Uscendo dalla partita...");
                            controller.stopPlayer();
                        }
                        break;
                    }

                    case 1: {
                        handleUseDice(match, false);
                        break;
                    }

                    case 2: {
                        handleUseToolCard(match);   //TODO: metodi per le carte utensili
                        break;
                    }

                    case 3: {
                        printOtherPlayersInfo(match, nickname);
                        break;
                    }

                    case 4: {
                        endTurn();
                        break;
                    }

                    default: {
                        ok = false;
                        System.out.println("Scelta non valida");
                        break;
                    }
                }
            } while (!ok);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        } catch (InputMismatchException e) {
        }
    }

    /**
     * Scelta di un dado e della casella in cui piazzarlo
     */
    public void handleUseDice(Match match, boolean toolCard9) {
        try {
            int dice, row, col;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita l'indice del dado che vuoi posizionare, tra 1 e " + match.getDraftPool().getSize());
                dice = scanner.nextInt();
            } while ((dice < 1 || dice > match.getDraftPool().getSize()) && dice != 9);
            if (dice == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                do {
                    System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
                    row = scanner.nextInt();
                } while ((row < 1 || row > Constants.NUM_ROWS) && row != 9);
                if (row == 9)
                    chooseAction(controller.getMatch(), controller.getNickname());
                else {
                    do {
                        System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
                        col = scanner.nextInt();
                    } while ((col < 1 || col > Constants.NUM_COLS) && col != 9);
                    if (col == 9)
                        chooseAction(controller.getMatch(), controller.getNickname());
                    else {
                        if (toolCard9)
                            controller.useToolCard(9, dice - 1, -1, row - 1, col - 1, -1, -1);
                        else
                            controller.useDice(dice - 1, row - 1, col - 1);
                    }
                }
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Scelta della casella dove piazzare un dado già scelto
     */
    public void retryPlaceDice() {
        try {
            int row, col;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
                row = scanner.nextInt();
            } while ((row < 1 || row > Constants.NUM_ROWS) && row != 9);
            if (row == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                do {
                    System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
                    col = scanner.nextInt();
                } while ((col < 1 || col > Constants.NUM_COLS) && col != 9);
                if (col == 9)
                    chooseAction(controller.getMatch(), controller.getNickname());
                else {
                    controller.useDice(-1, row - 1, col - 1);
                }
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta T: utilizzare una toolcard
    /////////////////////////////////////////////////////////////////////////////////////////


    /**
     * Scelta della carta utensile da utilizzare
     */
    public void handleUseToolCard(Match match) {
        try {
            int num;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita il numero della carta utensile che vuoi utilizzare, tra 1 e 3");
                num = scanner.nextInt();
            } while ((num < 1 || num > 3) && num != 9);
            if (num == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                int id = match.getToolCards().get(num - 1).getId();
                useToolCard(id, match);
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard(int id, Match match) {
        switch (id) {
            case 1:
                useToolCard1(match);
                break;
            case 2:
            case 3:
            case 4:
                useToolCard23412(id);
                break;
            case 5:
                useToolCard5(match);
                break;
            case 6:
                useToolCard6(match);
                break;
            case 7:
            case 8:
                controller.useToolCard(id, -1, -1, -1, -1, -1, -1);
                break;
            case 9:
                useToolCard9(match);
                break;
            case 10:
                useToolCard10(match);
                break;
            case 11:
                useToolCard11(match);
                break;
            case 12:
                useToolCard12(match);
                break;
            default:
                break;
        }
    }


    public void useToolCard1(Match match) {
        try{
            int dice, operation;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize());
                dice = scanner.nextInt();
            } while ((dice < 1 || dice > match.getDraftPool().getSize()) && dice != 9);
            if (dice == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                do {
                    System.out.println("Digita 0 se vuoi aumentare il numero del dado di 1, 1 se vuoi diminuirlo");
                    operation = scanner.nextInt();
                } while ((operation != 0) && (operation != 1) && (operation != 9));
                if (operation == 9)
                    chooseAction(controller.getMatch(), controller.getNickname());
                else {
                    controller.useToolCard(1, dice - 1, operation, -1, -1, -1, -1);
                }
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard23412(int id) {
        try {
            int sourceRow, sourceCol, destRow, destCol;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita il numero della riga dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_ROWS);
                sourceRow = scanner.nextInt();
            } while ((sourceRow < 1 || sourceRow > Constants.NUM_ROWS) && (sourceRow != 9));
            if (sourceRow == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                do {
                    System.out.println("Digita il numero della colonna dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_COLS);
                    sourceCol = scanner.nextInt();
                } while ((sourceCol < 1 || sourceCol > Constants.NUM_COLS) && sourceCol != 9);
                if (sourceCol == 9)
                    chooseAction(controller.getMatch(), controller.getNickname());
                else {
                    do {
                        System.out.println("Digita il numero della riga dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_ROWS);
                        destRow = scanner.nextInt();
                    } while ((destRow < 1 || destRow > Constants.NUM_ROWS) && destRow != 9);
                    if (destRow == 9)
                        chooseAction(controller.getMatch(), controller.getNickname());
                    else {
                        do {
                            System.out.println("Digita il numero della colonna dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_COLS);
                            destCol = scanner.nextInt();
                        } while ((destCol < 1 || destCol > Constants.NUM_COLS) && destCol != 9);
                        if (destCol == 9)
                            chooseAction(controller.getMatch(), controller.getNickname());
                        else {
                            controller.useToolCard(id, -1, -1, sourceRow - 1, sourceCol - 1, destRow - 1, destCol - 1);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard5(Match match) {
        try {
            int dice, round, indexInRound;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
            if (!roundTrackIsFull) {
                System.out.println("Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round");
                chooseAction(match, controller.getNickname());
            } else {
                do {
                    System.out.println("Digita l'indice del dado della riserva che vuoi scambiare, tra 1 e " + match.getDraftPool().getSize());
                    dice = scanner.nextInt();
                } while ((dice < 1 || dice > match.getDraftPool().getSize()) && dice != 9);
                if (dice == 9)
                    chooseAction(controller.getMatch(), controller.getNickname());
                else {
                    do {
                        System.out.println("Digita il numero di round a cui appartiene il dado con cui vuoi scambiarlo, tra 1 e " + match.getRoundTrack().getRoundTrackSize());
                        round = scanner.nextInt();
                    } while ((round < 1 || round > match.getRoundTrack().getRoundTrackSize()) && round != 9);
                    if (round == 9)
                        chooseAction(controller.getMatch(), controller.getNickname());
                    else {
                        do {
                            System.out.println("Digita l'indice del dado nel round che hai scelto, tra 0 e " + (match.getRoundTrack().getNumberOfDices(round) - 1));
                            indexInRound = scanner.nextInt();
                        }
                        while ((indexInRound < 0 || indexInRound > match.getRoundTrack().getNumberOfDices(round) - 1) && indexInRound != 9);
                        if (indexInRound == 9)
                            chooseAction(controller.getMatch(), controller.getNickname());
                        else {
                            controller.useToolCard(5, dice - 1, -1, round, indexInRound, -1, -1);
                        }
                    }
                }
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard6(Match match) {
        try {
            int dice;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita l'indice del dado che vuoi tirare, tra 1 e " + match.getDraftPool().getSize());
                dice = scanner.nextInt();
            } while ((dice < 1 || dice > match.getDraftPool().getSize()) && dice != 9);
            if (dice == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                controller.useToolCard(6, dice - 1, -1, -1, -1, -1, -1);
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard9(Match match) {
        handleUseDice(match, true);
    }


    public void useToolCard10(Match match) {
        try {
            int dice;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize());
                dice = scanner.nextInt();
            } while ((dice < 1 || dice > match.getDraftPool().getSize()) && dice != 9);
            if (dice == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                controller.useToolCard(10, dice - 1, -1, -1, -1, -1, -1);
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard11(Match match) {
        try {
            int dice;
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            do {
                System.out.println("Digita l'indice del dado che vuoi riporre nel sacchetto, tra 1 e " + match.getDraftPool().getSize());
                dice = scanner.nextInt();
            } while ((dice < 1 || dice > match.getDraftPool().getSize()) && dice != 9);
            if (dice == 9)
                chooseAction(controller.getMatch(), controller.getNickname());
            else {
                controller.useToolCard(11, dice - 1, -1, -1, -1, -1, -1);
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    public void useToolCard12(Match match) {
        try {
            System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
            boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
            if (!roundTrackIsFull) {
                System.out.println("Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round");
                chooseAction(match, controller.getNickname());
            }
            else {
                useToolCard23412(12);
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta I: visualizzare le informazioni degli altri giocatori (nome, schema, segnalini favore)
    /////////////////////////////////////////////////////////////////////////////////////////

    public void printOtherPlayersInfo(Match match, String nickname) {
        try {
            ArrayList<Player> otherPlayers = match.getOtherPlayers(nickname);
            for (Player player : otherPlayers) {
                System.out.println(player.getNickname());
                System.out.println(player.getNumOfToken());
                ShowScheme scheme = new ShowScheme(player.getScheme());
                System.out.println("");
            }
            chooseAction(match, nickname);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta E: terminare il turno
    /////////////////////////////////////////////////////////////////////////////////////////


    public void endTurn() {
        controller.endTurn();
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi che invoca PlayerController su UiUpdate
    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metodo di aggiornamento del login
     */
    @Override
    public void onLogin(String message) {
        login(message);
    }

    /**
     * Errore di azione non valida
     */
    @Override
    public void onActionNotValid(String errorCode) {
        try {
            System.out.println(errorCode);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Metodo che richiede la scelta di rete
     */
    @Override
    public void onChooseNetwork (String message) {
            chooseNetwork(message);
    }

    /**
     * Metodo di richiesta di azione di gioco
     */
    @Override
    public void onTurnStart(Match match, String nickname) {
        chooseAction(match, nickname);
    }

    /**
     * Errore di infrazione delle regole di piazzamento
     */
    @Override
    public void onPlaceDiceNotValid(String message) {
        try {
            System.out.println(message);
            retryPlaceDice();
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * Aggiornamento qualunque del model
     */
    @Override
    public void onGameUpdate(Match match, String nickname) {
         try {
            ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());
            ShowPublicObjectives pub = new ShowPublicObjectives(match.getPublicObjectives());
            ShowPrivateObjectiveCard priv = new ShowPrivateObjectiveCard(match.getPlayer(nickname).getPrivateObjective());
            ShowToolCards tool = new ShowToolCards(match.getToolCards());
            System.out.println("Hai " + match.getPlayer(nickname).getNumOfToken() + " segnalini favore");
            ShowDraftPool draft = new ShowDraftPool(match.getDraftPool());
            ShowScheme scheme = new ShowScheme(match.getPlayer(nickname).getScheme());
         } catch (NullPointerException e) {
         } catch (NumberFormatException e) {
             System.out.println("Digita un carattere valido");
         } catch (IndexOutOfBoundsException e) {
         }

    }

    /**
     * Aggiornamento di fine partita
     */
    @Override
    public void onGameEnd(Match match) {
        try {
            //ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());   //TODO: mettere pedine su roundtrack
            for (int i = 0; i < match.getPlayers().size(); i++) {
                System.out.print(i + 1 + ") " + match.getRanking().get(i).getNickname() + " con ");
                System.out.println(match.getRanking().get(i).getScore() + " punti");
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }
    }

    /**
     * Scelta dello schema tra i 4
     */
    @Override
    public void onSchemeToChoose(Match match, String nickname, String message) {
        chooseScheme(match, nickname, message);
    }

    /**
     * Errore nell'utilizzo di una carta utensile
     */
    @Override
    public void onUseToolCardNotValid(int id, Match match, String e) {
        try {
            System.out.println(e);
            switch (id) {
                case 6:
                    onOtherInfoToolCard(6, match);    // perché tanto la 6 può lanciare una notValidException solo nel secondo step
                    break;
                case 11:
                    onOtherInfoToolCard(11, match);   // perché tanto la 11 può lanciare una notValidException solo nel secondo step
                    break;
                default:
                    useToolCard(id, match);
                    break;
            }
        } catch (NullPointerException e1) {
        } catch (IndexOutOfBoundsException e1) {
        }
    }

    /**
     * Richiesta di altre informazioni per finire di utilizzare una carta utensile
     */
    @Override
    public void onOtherInfoToolCard(int id, Match match) {
        try {
            switch (id) {
                case 4: {
                    useToolCard23412(4);
                }
                break;
                case 6: {
                    System.out.println("Ora digita la casella dove posizionare il dado");
                    retryPlaceDice();
                }
                break;
                case 11: {
                    int dice, row, col;
                    System.out.println("Digita 9 in qualsiasi momento per tornare al menù principale.");
                    do {
                        System.out.println("Digita il valore del nuovo dado, tra 1 e 6");
                        dice = scanner.nextInt();
                    } while ((dice < 1 || dice > 6) && dice != 9);
                    if (dice == 9)
                        chooseAction(controller.getMatch(), controller.getNickname());
                    else {
                        do {
                            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
                            row = scanner.nextInt();
                        } while ((row < 1 || row > Constants.NUM_ROWS) && row != 9);
                        if (row == 9)
                            chooseAction(controller.getMatch(), controller.getNickname());
                        else {
                            do {
                                System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
                                col = scanner.nextInt();
                            } while ((col < 1 || col > Constants.NUM_COLS) && col != 9);
                            if (col == 9)
                                chooseAction(controller.getMatch(), controller.getNickname());
                            else {
                                controller.useToolCard(11, -1, dice, row - 1, col - 1, -1, -1);
                            }
                        }
                    }
                }
                break;
                case 12: {
                    int choice;
                    do {
                        System.out.println("Primo dado mosso correttamente, digita 0 se non vuoi spostare più dadi o 1 se vuoi spostarne un altro");
                        choice = scanner.nextInt();
                    } while (choice != 0 && choice != 1);
                    if (choice == 0)
                        controller.useToolCard(12, -2, -1, -1, -1, -1, -1);
                    else
                        useToolCard23412(12);
                }
            }
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }

    }

    /**
     * Aggiornamento di avvenuta disconnessione
     */
    @Override
    public void onPlayerDisconnection(String message, String nickname) {
        System.out.println(message);
        int choice;
        if (nickname.equals(controller.getNickname())) {
            try {
                do {
                    choice = scanner.nextInt();
                } while (choice!=0);
            controller.reconnectPlayer();
            } catch (NullPointerException e) {
            } catch (NumberFormatException e) {
                System.out.println("Digita un carattere valido");
            } catch (IndexOutOfBoundsException e) {
            }
        }
    }

    /**
     * Notifica il successo di un'azione
     */
    @Override
    public void onSuccess(String message) {
        try {
            System.out.println(message);
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        } catch (IndexOutOfBoundsException e) {
        }
    }

}