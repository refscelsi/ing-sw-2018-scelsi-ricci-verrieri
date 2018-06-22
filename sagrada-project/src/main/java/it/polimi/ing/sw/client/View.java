package it.polimi.ing.sw.client;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.controller.GameInterface;
import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.server.NotValidNicknameException;
import it.polimi.ing.sw.util.Constants;
import it.polimi.ing.sw.ui.cli.CLI;

import java.rmi.RemoteException;
import java.util.Scanner;

public class View implements UiUpdate, RemotePlayer {
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
    private PlayerInterface controller; //il client può chiamare solo i metodi di PlayerInterface
    private GameInterface gameController;
    private UiUpdate ui;
    private String input;
    private static Scanner scanner = new Scanner(System.in);


    public View(GameInterface controller){
        this.gameController=controller;
        isLogged = false;
        isGameStarted = false;
    }


    public void start() throws RemoteException{
        System.out.println("Benvenuto in Sagrada, vuoi giocare con la Cli [c] o con la Gui [g]?");
        input=scanner.nextLine();
        if(input.equals('c')){
            ui = new CLI(this);
        }
        else if(input.equals('g')){
            //ui = new GUI(this);
        }

        System.out.println("Vuoi giocare con la RMI [r] o Socket [s]?");
        input=scanner.nextLine();
        if(input.equals('r')){
            controller = gameController.connect(this);
        }
        else if(input.equals('g')){
            //ui = new GUI(this);
        }

        ui.onLogin("Scegli il tuo nickname: ");
    }



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
    // Metodi invocati sul Client Game (vedi RMIClient, SocketClient)
    ////////////////////////////////////////////////////////////////////////////////////////


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
    public void loginPlayer(String nickname) {
        boolean success = false;
        do {
            try {
                controller.sendLoginRequest(nickname, this); //TODO: il controller mi notifica l'indice del giocatore
                success = true;
                ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
            } catch (NetworkException e) {
                System.err.println(e.getMessage());
            } catch (NotValidNicknameException e) {
                ui.onLogin(e.getMessage() + ". Inserisci un nickname differente");
            }

        } while (!success);

        this.isLogged = true;
        ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
        // devo notificare anche il colore del giocatore

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


        @Override
        public void onGameUpdate(Match match) throws RemoteException {

        }
    }
