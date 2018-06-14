package it.polimi.ing.sw.client;

import it.polimi.ing.sw.controller.ClientUpdate;
import it.polimi.ing.sw.controller.ControllerInterface;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.RoundTrack;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;
import it.polimi.ing.sw.model.toolCard.ToolCard;
import it.polimi.ing.sw.server.NotValidNicknameException;
import it.polimi.ing.sw.ui.cli.CLI;
import it.polimi.ing.sw.util.Constants;
import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.rmi.RemoteException;
import java.util.ArrayList;

//classe che gestisce gli input dei client e chiama i metodi di ControllerInterface (sulla rete)

public class ClientController implements ClientUpdate {

    /**
     *  ogni giocatore è identificato dal valore dell'attributo index nel model: il giocatore con index=0 avrà come
     *  nickname nicknames.get(0), come schema schemesOfAllPlayers.get(0), come colore di pedina playersColor[0],
     *  come punteggio di fine partita playersScore[0] e occuperà la posizione contenuta in playersRanking[0]
     *  nella classifica finale.
     */

    private int index;
    private static final String SERVER_ADDRESS = Constants.SERVER_ADDRESS;
    private static final int SERVER_SOCKET_PORT = Constants.SOCKET_PORT;
    private static final int SERVER_RMI_PORT = Constants.RMI_PORT;
    private boolean isLogged;
    private Match match;
    private boolean isGameStarted;     // flag per vedere se la partita è iniziata: non so se sarà utile o meno
    private ControllerInterface controller; //il client può chiamare solo i metodi di ControllerInterface
    private ClientUpdate ui;


    public ClientController(ClientUpdate ui/*ControllerInterface controllerInterface*/) {
        //this.controller=controllerInterface;
        this.ui = ui;
        isLogged = false;
        isGameStarted = false;
    }

    /*public void mainLoop(){
        System.out.println("inserisci il nickname");
        String nickname="ari";
        controller.loginPlayer(nickname);
    }*/


    /**
     * Metodo statico per eseguire il client.
     *
     * @param args
     *            parametri per la connessione.
     */
    /*public static void main(String[] args) {
        String serverAddress = SERVER_ADDRESS;
        int socketPort = SERVER_SOCKET_PORT, rmiPort = SERVER_RMI_PORT;

        // Check if arguments were passed in
        if (args.length != 0) {
            try {
                serverAddress = args[0];
                socketPort = Integer.parseInt(args[1]);
                rmiPort = Integer.parseInt(args[0]);
            } catch (Exception e) {
                System.exit(0);
            }
        }

        // Debugging purpose
        try {
            CLI.mainClient(serverAddress, socketPort, rmiPort);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public boolean isLogged() {
        return this.isLogged;
    }

    public int getIndex() {
        return index;
    }


    public void startClient(String connectionType, String serverAddress, int socketPort, int rmiPort)
            throws ClientException {
        if (connectionType.equals("RMI")) {
            startRMIClient(serverAddress, rmiPort);
        } else if (connectionType.equals("Socket")) {
            //startSocketClient(serverAddress, socketPort); //TODO: decommentare questo e eliminare quello sotto quando si implementerà la Socket
            startRMIClient(serverAddress, rmiPort);
        } else {
            throw new ClientException("Errore nel lanciare il client");
        }
    }


    private void startRMIClient(String serverAddress, int rmiPort) throws ClientException {
        System.out.println("Iniziando connessione RMI...");
        //controller = new RMIClient(this, serverAddress, rmiPort);   //TODO: sistemare qui
        //controller.connect();
    }


    /*private void startSocketClient(String serverAddress, int socketPort) throws ClientException {
        System.out.println("Iniziando connessione Socket...");
        client = new SocketClient(this, serverAddress, socketPort);
        client.connect();

        System.out.println();
    }*/                             //TODO: decommentare questo metodo quando si implementerà la Socket


    /////////////////////////////////////////////////////////////////////////////////////////
    // "Getters" (per verificare lo stato del Client, in Locale).
    /////////////////////////////////////////////////////////////////////////////////////////


    public Match getMatch() {
        return match;
    }

    public int getIndex() {
        return index;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public boolean isLogged() {
        return isLogged;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati sul Client Controller (vedi RMIClient, SocketClient)
    ////////////////////////////////////////////////////////////////////////////////////////

    /*@Override
    public void onGameUpdate(UpdateStates update) {
        this.index=update.getIndicePlayer();
    }

    public void onAnotherUpdate(UpdateStates update){
        cli.onAnotherUpdate(update);
    }

    /**
     * Metodo invocato dal Server ogni qualvolta si presenta un errore (es.
     * azione illegale) a seguito di una richiesta del giocatore
     *
     */

    @Override
    public void onActionNotValid(String errorCode) {
        ui.onActionNotValid(errorCode);
    }

    /**
     * Metodo invocato dal Server ogni qualvolta l'azione richiesta dal
     * giocatore è stata accettata oppure si è verificato
     * un'avanzamento nello stato della logica della partita
     *
     */

    @Override
    public void onSchemeToChoose(Match match, int index) {
        ui.onSchemeToChoose(match, index);
    }

    @Override
    public void onGameUpdate(Match match, int index) {
        ui.onGameUpdate(match, index);
    }

    @Override
    public void onTurnStarted (Match match, int index) {
        ui.onTurnStarted(match, index);
    }

    @Override
    public void onGameEnd(Match match) {
        ui.onGameEnd(match);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // "Senders" (per l'invio di informazioni verso il Server, in Remoto).
    /////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * Metodo per effettuare il login presso il Server.
     *
     * @param nickname
     *            nickname da usare per il login presso il Server.
     */
    public void loginPlayer(String nickname) throws NotValidNicknameException{
        boolean success = false;
        try {
            index = controller.sendLoginRequest(nickname); //TODO: il controller mi notifica l'indice del giocatore
            success = true;
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }

        if (success) {
            this.isLogged = true;
            System.out.println("Hai effettuato il login come " + nickname);
            // devo notificare anche il colore del giocatore
        }
    }


    public void setChosenScheme (int id) {
        try {
            controller.setChosenScheme(index, id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }
    }


    public void useDice (int indexOfDiceInDraftPool, int row, int col) throws NotValidException{
        try {
            controller.sendUseDiceRequest (index, indexOfDiceInDraftPool, row, col);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }
    }

    public void removeDice (int row, int col) throws NotValidException{
        try {
            controller.removeDice (index, row, col);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }
    }

    public void endTurn () {
        try {
            controller.endTurn(index);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }
    }

}
