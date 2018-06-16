package it.polimi.ing.sw.ui.cli;

import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.server.NotValidNicknameException;
import it.polimi.ing.sw.client.ClientUpdate;
import it.polimi.ing.sw.client.ClientController;

import java.util.*;


public class CLI implements ClientUpdate {

    public Scanner scanner = new Scanner(System.in);
    public String inText;


    private ClientController controller;
    private ClientUpdate ui;

    public CLI(ClientController controller){
        this.controller=controller;
    }

    /*public ClientController getController() {
        if (controller == null)
            controller = new ClientController(getUI());
        return controller;
    }*/

    public ClientController getController() {
        return controller;
    }

    /*public ClientUpdate getUI() {
        if (ui == null) {
            ui = new CLI();
        }
        return ui;
    }*/

    /**
     * Inizio come Client o Server.
     *
     * @param args
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
     * Avvia Client (RMI o Socket).
     *
     */
    /*public void mainClient(String serverAddress, int socketPort, int rmiPort) {
        mainClient(serverAddress, socketPort, rmiPort, null);
    }

    /**
     * Avvia Client (RMI o Socket)
     * @param clientUI
     */

    /*public ClientController mainClient(String serverAddress, int socketPort, int rmiPort, ClientUpdate clientUI) {
        ui = clientUI;

        System.out.print("Digita R per connetterti tramite RMI o S per connetterti tramite Socket (Default: RMI)");
        inText = scanner.nextLine().toUpperCase();

        if (inText.equals("S")) {
            inText = "Socket";
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
                ClientController controller = getController();
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
            System.out.println("127.0.0.1" + " (RMI: " + rmiPort + ", Socket: " + socketPort + ")");
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
    public void login() {
        ClientController controller = getController();
        boolean success = true;
        while (!controller.isLogged()) {
            System.out.print("Scegli il tuo nickname: ");
            do {
                try {
                    inText = scanner.nextLine();
                    controller.loginPlayer(inText);
                    success = true;
                } catch (NotValidNicknameException e) {
                    System.err.println(e.getMessage());
                    System.out.println("Inserisci un nuovo nickname");
                    success = false;
                }
            } while (!success);
        }

    }


    /**
     * Scelta dello schema tra i 4 schemi disponibili da parte di un giocatore
     */
    public void chooseScheme(Match match, int index) {
        int num;
        ArrayList<Scheme> schemes = match.getPlayer(index).getSchemesToChoose();
        showSchemesToChoose(schemes);
        do {
            System.out.println("Digita il numero dello schema che vuoi scegliere (1, 2, 3 o 4)");
            num = scanner.nextInt();
        } while (num < 1 || num > 4);
        controller.setChosenScheme(schemes.get(num-1).getId());   //se per esempio qui c'è un errore, se lo gestisce il ClientController
        System.out.println("Ottima scelta! Ora devi attendere che inizi il gioco");
    }

    public void showSchemesToChoose (ArrayList<Scheme> schemes) {
        for (int i=0; i<4; i++) {
            System.out.println("Schema " + i+1 + ":");
            ShowScheme show = new ShowScheme(schemes.get(i));
            System.out.println("");
        }
    }


    /**
     * Scelta dell'azione da parte del giocatore
     */
    public void chooseAction(Match match, int index) {
        boolean quit = false;
        boolean diceAlreadyUsed = false;
        boolean toolCardAlreadyUsed = false;

        while (!quit) {

            System.out.print("Digita: \n- D se vuoi posizionare un dado sul tuo schema; \n- T se vuoi utilizzare una carta utensile; \n- I se vuoi visualizzare le informazioni degli altri giocatori; \n- E se vuoi terminare il tuo turno; \n- Q se vuoi uscire dalla partita.\n");
            inText = scanner.nextLine();

            switch (inText.toLowerCase()) {

                case "q":
                    System.out.println("Sei sicuro che vuoi uscire dalla partita? Digita S per sì o N per no.");
                    if (scanner.nextLine().toLowerCase().equalsIgnoreCase("s")) {
                        quit = true;
                        // TODO: gestire terminazione corretta del programma!
                        System.out.println("Uscendo dalla partita...");
                        System.exit(0);
                    }
                    break;

                case "d":
                    if (!diceAlreadyUsed) {
                        diceAlreadyUsed = handleUseDice(match, index);
                    }
                    else {
                        System.out.println("Hai già utilizzato un dado, non puoi utilizzarne un altro!");
                    }
                    break;

                case "t":
                    if (!toolCardAlreadyUsed) {
                        //toolCardAlreadyUsed = true;
                        //handleUseToolCard(match);   //TODO: metodi per le carte utensili
                    }
                    else {
                        System.out.println("Hai già utilizzato una carta utensile, non puoi utilizzarne un'altra!");
                    }
                    break;

                case "i":
                    printOtherPlayersInfo(match, index);
                    break;

                case "e":
                    quit = true;
                    endTurn();
                    break;

                default:
                    System.out.println("Scelta non valida");
                    break;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Connessione e disconnessione del Client --> da fare probabilmente
    /////////////////////////////////////////////////////////////////////////////////////////

    /*public void clientConnection() {
        ClientController controller = getController();
        controller.clientConnection();
    }

    public void clientDisconnection() {
        ClientController controller = getController();
        controller.clientDisconnection();
    }*/

    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta D: posizionare un dado sullo schema
    /////////////////////////////////////////////////////////////////////////////////////////

    public boolean handleUseDice (Match match, int index) {
        int dice, row, col;
        do {
            System.out.println("Digita il numero del dado che vuoi posizionare, tra 1 e " + match.getDraftPool().getSize());
            dice = scanner.nextInt();
        } while (dice < 1 || dice > match.getDraftPool().getSize());
        do {
            System.out.println("Digita il numero della riga dello schema in cui vuoi posizionarlo, tra 1 e 4");
            row = scanner.nextInt();
        } while (row < 1 || row > 4);
        do {
            System.out.println("Digita il numero della colonna dello schema in cui vuoi posizionarlo, tra 1 e 5");
            col = scanner.nextInt();
        } while (col < 1 || col > 5);
        try {
            controller.useDice(dice - 1, row, col);     // qui comunque i dadi verranno piazzati
            return true;
        } catch (NotValidException e) {             //TODO: gestire eccezione
            System.err.println(e.getMessage());
            removeDices(match, index);
        }
        return false;
    }


    public void removeDices (Match match, int index) {
        int row, col;
        boolean success = true;
        System.out.println("Togli dadi fino a tornare a soddisfare le restrizioni di piazzamento");
        do {
            do {
                System.out.println("Digita il numero della riga dello schema dove si trova il dado che vuoi togliere, tra 1 e 4");
                row = scanner.nextInt();
            } while (row < 1 || row > 4);
            do {
                System.out.println("Digita il numero della colonna dello schema dove si trova il dado che vuoi togliere, tra 1 e 5");
                col = scanner.nextInt();
            } while (col < 1 || col > 5);
            try {
                controller.removeDice(row, col);
            } catch (NotValidException e) {
                success = false;
            }
        } while (!success);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta I: visualizzare le informazioni degli altri giocatori (nome, schema, segnalini favore)
    /////////////////////////////////////////////////////////////////////////////////////////

    public void printOtherPlayersInfo (Match match, int index) {
        for (int i=0; i<match.getNumPlayers(); i++) {
            if (i!=index) {
                System.out.println(match.getPlayer(i).getNickname());
                System.out.println(match.getPlayer(i).getNumOfToken());
                ShowScheme scheme = new ShowScheme(match.getPlayer(i).getScheme());
                System.out.println("");
            }
        }

    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // Scelta E: terminare il turno
    /////////////////////////////////////////////////////////////////////////////////////////


    public void endTurn() {
        controller.endTurn();
    }




    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi che invoca ClientController su ClientUpdate
    /////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onActionNotValid (String errorCode) {
        System.out.println(errorCode);
    }

    @Override
    public void onTurnStarted (Match match, int index) {
        chooseAction(match, index);
    }

    @Override
    public void onGameUpdate (Match match, int index) {
        ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());
        ShowPublicObjectives pub = new ShowPublicObjectives(match.getPublicObjectives());
        ShowToolCards tool = new ShowToolCards(match.getToolCards());
        ShowDraftPool draft = new ShowDraftPool(match.getDraftPool());
        ShowScheme scheme = new ShowScheme(match.getPlayer(index).getScheme());
    }

    @Override
    public void onGameEnd (Match match) {
        ShowRoundTrack roundTrack = new ShowRoundTrack(match.getRoundTrack());   //TODO: mettere pedine su roundtrack
        for (int i=0; i<match.getNumPlayers(); i++) {
            System.out.print(i + 1 + ") " + match.getRanking().get(i).getNickname() + " con ");
            System.out.println(match.getRanking().get(i).getScore() + " punti");
        }
    }

    @Override
    public void onSchemeToChoose (Match match, int index) {
        chooseScheme(match, index);
    }

}


