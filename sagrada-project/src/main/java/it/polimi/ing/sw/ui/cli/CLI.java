package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.client.UiUpdate;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import static it.polimi.ing.sw.util.Constants.RMI_CODE;
import static it.polimi.ing.sw.util.Constants.SOCKET_CODE;


public class CLI implements UiUpdate {

    public Scanner scanner = new Scanner(System.in);
    public String inText;


    private View controller;


    public CLI(View controller) {
        this.controller = controller;
    }

    /*public PlayerController getController() {
        if (controller == null)
            controller = new PlayerController(getUI());
        return controller;
    }*/

    public View getController() {
        return controller;
    }

    /*public UiUpdate getUI() {
        if (ui == null) {
            ui = new CLI();
        }
        return ui;
    }*/

    /**
     * Inizio come Client o Server.
     *
     * @param //args
     */
    /*public static void main(String[] args) {
        String serverAddress = Constants.SERVER_ADDRESS;
        int socketPort = Constants.SOCKET_PORT;
        int rmiPort = Constants.RMI_PORT;

        // Controllo se l'utente ha inserito un serverAddress, socketPort o rmiPort. Se li ha inseriti, li utilizzo, altrimenti uso quelli di default
        if (args.length != 0) {
            try {
                serverAddress = args[0];
                socketPort = Integer.parseInt(args[1]);
                rmiPort = Integer.parseInt(args[0]);
            } catch (Exception e) {    //TODO: gestire questa eccezione
                System.exit(0);
            }
        }
        if (Constants.START_AS_SERVER_IF_CLIENT_CONNECTION_FAILS) {
            System.out.print("Digita C per iniziare come Client o S per iniziare come Server (Default: Client)");
            inText = scanner.nextLine().toUpperCase();

            if (inText.equals("S")) {
                mainServer(socketPort, rmiPort);
            } else if (inText.equals("C")) {
                mainClient(serverAddress, socketPort, rmiPort);
            }
            // Default: Client
            else {
                System.out.println("Inizio come Client...");
                mainClient(serverAddress, socketPort, rmiPort);
            }
        } else {
            mainClient(serverAddress, socketPort, rmiPort);
        }
    }

    /**
     * Avvia Client (RMI o socket).
     *
     */
    /*public void mainClient(String serverAddress, int socketPort, int rmiPort) {
        mainClient(serverAddress, socketPort, rmiPort, null);
    }

    /**
     * Avvia Client (RMI o socket)
     * @param clientUI
     */

    /*public PlayerController mainClient(String serverAddress, int socketPort, int rmiPort, UiUpdate clientUI) {
        ui = clientUI;

        System.out.print("Digita R per connetterti tramite RMI o S per connetterti tramite socket (Default: RMI)");
        inText = scanner.nextLine().toUpperCase();

        if (inText.equals("S")) {
            inText = "socket";
        } else if (inText.equals("R")) {
            inText = "RMI";
        }
        // Default: RMI
        else {
            inText = "RMI";
            System.out.println("Connettendo con RMI...");
        }

        boolean success = false;
        int attempts = Constants.MAX_CONNECTION_ATTEMPTS;
        int sec = Constants.CONNECTION_RETRY_SECONDS * 1000;
        while (!success && attempts > 0) {
            try {
                attempts--;
                PlayerController controller = getController();
                controller.startClient(inText, serverAddress, socketPort, rmiPort);
                success = true;
            } catch (ClientException e) {
                if (attempts > 0) {
                    System.err.println(e.getMessage() + " (" + "Riprovo in " + sec / 1000 + " secondi" + ", " + attempts
                            + " tentativi rimanenti)");
                    try {
                        Thread.sleep(sec);
                    } catch (InterruptedException ie) {
                        // TODO gestire questa eccezione
                    }
                }
            }
        }

        if (success) {
            login();
            if (clientUI == null) {

            }
            else
                return getController();
        } else {
            if (Constants.START_AS_SERVER_IF_CLIENT_CONNECTION_FAILS) {
                System.err.println(
                        "\nImpossibile stabilire una connessione col server, il programma avvierà un server locale");

                mainServer(socketPort, rmiPort);
            } else {
                System.err.println("\nImpossibile stabilire una connessione col server, il programma terminerà a breve");
                System.exit(0);
            }
        }
        return null;
    }

    /**
     * Avvio Server (Client e Server).
     *
     * @param socketPort
     * @param rmiPort
     */
    /*public void mainServer(int socketPort, int rmiPort) {
        try {
            Server server = new Server();
            server.startServer(socketPort, rmiPort);

            System.out.print("\nServer in ascolto a: ");
            System.out.println("127.0.0.1" + " (RMI: " + rmiPort + ", socket: " + socketPort + ")");
            System.out.println();

        } catch (ServerException e) {
            System.err.println(e.getMessage());
            System.err.println("Errore di avvio del server locale");
            System.exit(0);
        }
    }

    /**
     * Login del Client sul Server.
     */
    public void login(String message) {
        System.out.println(message);
        inText = scanner.nextLine();
        controller.login(inText);
    }


    public void chooseNetwork(String message) {
        do {
            System.out.println(message);
            inText = scanner.nextLine();
        } while (!SOCKET_CODE.equals( inText.toLowerCase() ) && !RMI_CODE.equals( inText.toLowerCase() ));
        controller.chooseNetwork(inText);
    }


    /**
     * Scelta dello schema tra i 4 schemi disponibili da parte di un giocatore
     */
    public void chooseScheme(Match match, String nickname, String message) throws RemoteException {
        ArrayList<Scheme> schemes = match.getPlayer(nickname).getSchemesToChoose();
        showSchemesToChoose(schemes);
        int num;
        do {
            System.out.println(message);
            num = scanner.nextInt();

        } while (num < 1 || num > 4);
        //System.out.println("Ho scelto schema nella cli");
        controller.setChosenScheme(schemes.get(num - 1).getId());   //se per esempio qui c'è un errore, se lo gestisce il PlayerController*/
        //System.out.println("Ho scelto schema nella cli, torno subitooo");
    }


    public void showSchemesToChoose(ArrayList<Scheme> schemes) {
        for (int i = 0; i < 4; i++) {
            System.out.println("Schema " + (i + 1) + " (con ID " + schemes.get(i).getId() + "):");
            ShowScheme show = new ShowScheme(schemes.get(i));
            System.out.println("");
        }
    }


    /**
     * Scelta dell'azione da parte del giocatore
     */
    public void chooseAction(Match match, String nickname) throws RemoteException {
        int choice;
        Boolean ok = true;
        System.out.println("Digita: \n- 1 se vuoi posizionare un dado sul tuo schema; \n- 2 se vuoi utilizzare una carta utensile; \n- 3 se vuoi visualizzare le informazioni degli altri giocatori; \n- 4 se vuoi terminare il tuo turno; \n- 5 se vuoi uscire dalla partita.");

        do {
            choice = scanner.nextInt();

            switch (choice) {

                case 5: {
                    System.out.println("Sei sicuro che vuoi uscire dalla partita? Digita S per sì o N per no.");
                    if (scanner.nextLine().toLowerCase().equalsIgnoreCase("s")) {
                        // TODO: gestire terminazione corretta del programma!
                        System.out.println("Uscendo dalla partita...");
                        System.exit(0);
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
        //System.out.println("Ho scelto la lettera nella cli, torno subitooo");
    }



    /////////////////////////////////////////////////////////////////////////////////////////
    // Connessione e disconnessione del Client --> da fare probabilmente
    /////////////////////////////////////////////////////////////////////////////////////////

    /*public void clientConnection() {
        PlayerController controller = getController();
        controller.clientConnection();
    }

    public void clientDisconnection() {
        PlayerController controller = getController();
        controller.clientDisconnection();
    }*/

    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta D: posizionare un dado sullo schema
    /////////////////////////////////////////////////////////////////////////////////////////

    public void handleUseDice(Match match, boolean toolCard9) throws RemoteException {
        int dice, row, col;
        do {
            System.out.println("Digita l'indice del dado che vuoi posizionare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
            row = scanner.nextInt();
        } while (row < 1 || row > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
            col = scanner.nextInt();
        } while (col < 1 || col > Constants.NUM_COLS);

        if (toolCard9)
            controller.useToolCard(9, dice - 1, -1,row - 1, col - 1, -1, -1);
        else
            controller.useDice(dice - 1, row - 1, col - 1);
        //System.out.println("Ho scelto il dado nella cli, torno subitooo");

    }


    public void retryPlaceDice() throws RemoteException {
        int row, col;
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
            row = scanner.nextInt();
        } while (row < 1 || row > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
            col = scanner.nextInt();
        } while (col < 1 || col > Constants.NUM_COLS);

        controller.useDice(-1, row - 1, col - 1);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta T: utilizzare una toolcard
    /////////////////////////////////////////////////////////////////////////////////////////


    public void handleUseToolCard(Match match) throws RemoteException {
        int num;
        do {
            System.out.println("Digita il numero della carta utensile che vuoi utilizzare, tra 1 e 3");
            num = scanner.nextInt();
        } while (num < 1 || num > 3);
        int id = match.getToolCards().get(num - 1).getId();
        useToolCard(id, match);
    }


    public void useToolCard(int id, Match match) throws RemoteException {
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


    public void useToolCard1(Match match) throws RemoteException {
        int dice, operation;
        do {
            System.out.println("Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        do {
            System.out.println("Digita 0 se vuoi aumentare il numero del dado di 1, 1 se vuoi diminuirlo");
            operation = scanner.nextInt();
        } while ((operation!=0) && (operation!=1));
        controller.useToolCard(1, dice-1, operation, -1, -1, -1, -1);
    }


    public void useToolCard23412(int id) throws RemoteException {
        int sourceRow, sourceCol, destRow, destCol;
        do {
            System.out.println("Digita il numero della riga dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_ROWS);
            sourceRow = scanner.nextInt();
        } while (sourceRow < 1 || sourceRow > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema del dado che vuoi spostare, tra 1 e " + Constants.NUM_COLS);
            sourceCol = scanner.nextInt();
        } while (sourceCol < 1 || sourceCol > Constants.NUM_COLS);
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_ROWS);
            destRow = scanner.nextInt();
        } while (destRow < 1 || destRow > Constants.NUM_ROWS);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi spostare il dado, tra 1 e " + Constants.NUM_COLS);
            destCol = scanner.nextInt();
        } while (destCol < 1 || destCol > Constants.NUM_COLS);
        controller.useToolCard(id, -1,-1,sourceRow - 1, sourceCol - 1, destRow - 1, destCol - 1);
    }


    public void useToolCard5(Match match) throws RemoteException {
        int dice, round, indexInRound;
        boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
        if (!roundTrackIsFull) {
            System.out.println("Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round");
            chooseAction(match, controller.getNickname());
        }
        else {
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


    public void useToolCard6(Match match) throws RemoteException {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi tirare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        controller.useToolCard(6, dice - 1, -1, -1, -1, -1, -1);
    }


    public void useToolCard9(Match match) throws RemoteException {
        handleUseDice(match, true);
    }


    public void useToolCard10(Match match) throws RemoteException {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi cambiare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        controller.useToolCard(10,dice - 1, -1, -1, -1, -1, -1);
    }


    public void useToolCard11(Match match) throws RemoteException {
        int dice;
        do {
            System.out.println("Digita l'indice del dado che vuoi riporre nel sacchetto, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        controller.useToolCard(11, dice - 1, -1, -1, -1, -1, -1);
    }


    public void useToolCard12(Match match) throws RemoteException {
        boolean roundTrackIsFull = controller.checkIfRoundTrackIsFull();
        if (!roundTrackIsFull) {
            System.out.println("Non puoi utilizzare questa carta perché ancora non ci sono dadi sul tracciato dei round");
            chooseAction(match, controller.getNickname());
        }
        else {
            useToolCard23412(12);
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta I: visualizzare le informazioni degli altri giocatori (nome, schema, segnalini favore)
    /////////////////////////////////////////////////////////////////////////////////////////

    public void printOtherPlayersInfo(Match match, String nickname) throws RemoteException {
        ArrayList<Player> otherPlayers = match.getOtherPlayers(nickname);
        for (Player player : otherPlayers) {
            System.out.println(player.getNickname());
            System.out.println(player.getNumOfToken());
            ShowScheme scheme = new ShowScheme(player.getScheme());
            System.out.println("");
            chooseAction(match, nickname);

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
        login(message);
    }

    @Override
    public void onActionNotValid(String errorCode) {
        System.out.println(errorCode);

    }

    @Override
    public void onChooseNetwork (String message) {
            chooseNetwork(message);
    }

    @Override
    public void onTurnStart(Match match, String nickname) throws RemoteException {
        chooseAction(match, nickname);
    }

    @Override
    public void onPlaceDiceNotValid(NotValidException e) throws RemoteException {
        System.out.println(e);
        retryPlaceDice();
    }

    @Override
    public void onGameUpdate(Match match, String nickname) {
        ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());
        ShowPublicObjectives pub = new ShowPublicObjectives(match.getPublicObjectives());
        ShowPrivateObjectiveCard priv = new ShowPrivateObjectiveCard(match.getPlayer(nickname).getPrivateObjective());
        ShowToolCards tool = new ShowToolCards(match.getToolCards());
        System.out.println("Hai " + match.getPlayer(nickname).getNumOfToken() + " segnalini favore");
        ShowDraftPool draft = new ShowDraftPool(match.getDraftPool());
        ShowScheme scheme = new ShowScheme(match.getPlayer(nickname).getScheme());
    }

    @Override
    public void onGameEnd(Match match) {
        ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());   //TODO: mettere pedine su roundtrack
        for (int i = 0; i < match.getNumPlayers(); i++) {
            System.out.print(i + 1 + ") " + match.getRanking().get(i).getNickname() + " con ");
            System.out.println(match.getRanking().get(i).getScore() + " punti");
        }
    }

    @Override
    public void onSchemeToChoose(Match match, String nickname, String message) throws RemoteException {
        chooseScheme(match, nickname, message);
    }

    @Override
    public void onUseToolCardNotValid(int id, Match match, String e) throws RemoteException {
        System.out.println(e);
        switch (id) {
            case 6:
                onOtherInfoToolCard(6, match);    // perché tanto la 6 può lanciare una notValidException solo nel secondo step
                break;
            case 11:
                onOtherInfoToolCard(11, match);   // perché tanto la 11 può lanciare una notValidException solo nel secondo step
                break;
            case 12:
                onOtherInfoToolCard(12, match);   //TODO: distingui eccezione se ti trovi nel primo step o nel secondo
                break;
            default:
                useToolCard(id, match);
                break;
        }
    }


    @Override
    public void onOtherInfoToolCard(int id, Match match) throws RemoteException {
        switch (id) {
            case 4: {
                System.out.println("Primo dado mosso correttamente, ora muovi il secondo");
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
                do {
                    System.out.println("Digita il valore del nuovo dado, tra 1 e 6");
                    dice = scanner.nextInt();
                } while (dice < 1 || dice > 6);
                do {
                    System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_ROWS);
                    row = scanner.nextInt();
                } while (row < 1 || row > Constants.NUM_ROWS);
                do {
                    System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e " + Constants.NUM_COLS);
                    col = scanner.nextInt();
                } while (col < 1 || col > Constants.NUM_COLS);
                controller.useToolCard(11, -1, dice, row-1, col-1, -1, -1);
            }
            break;
            case 12: {
                int choice;
                do {
                    System.out.println("Primo dado mosso correttamente, digita 0 se non vuoi spostare più dadi o 1 se vuoi spostarne un altro");
                    choice = scanner.nextInt();
                } while (choice!=0 && choice!=1);
                if (choice==0)
                    controller.useToolCard(12, -1, -1, -1, -1, -1, -1);
                else
                    useToolCard23412(12);
            }
        }

    }


    @Override
    public void onSuccess(String message) {
        System.out.println("Arriva notifica schema alla cli");
        System.out.println(message);
    }

}