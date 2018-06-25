package it.polimi.ing.sw.client;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.controller.LoginInterface;
import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.util.Constants;
import it.polimi.ing.sw.ui.cli.CLI;

import java.rmi.RemoteException;
import java.util.Scanner;

public class View implements RemotePlayer {
    /**
     *  ogni giocatore è identificato dal valore dell'attributo index nel model: il giocatore con index=0 avrà come
     *  nickname nicknames.get(0), come schema schemesOfAllPlayers.get(0), come colore di pedina playersColor[0],
     *  come punteggio di fine partita playersScore[0] e occuperà la posizione contenuta in playersRanking[0]
     *  nella classifica finale.
     */

    private static final String SERVER_ADDRESS = Constants.SERVER_ADDRESS;
    private static final int SERVER_SOCKET_PORT = Constants.SOCKET_PORT;
    private static final int SERVER_RMI_PORT = Constants.RMI_PORT;
    private boolean isLogged;
    private boolean isPlaying;     // flag per vedere se è il turno del giocatore a cui appartiene questa view
    private Match match;
    private boolean isGameStarted;     // flag per vedere se la partita è iniziata: non so se sarà utile o meno
    private boolean isOnline;
    private PlayerInterface controller; //il client può chiamare solo i metodi di PlayerInterface
    private LoginInterface gameController;
    private UiUpdate ui;
    private String input;
    private static Scanner scanner = new Scanner(System.in);
    private String nickname;
    private int dice;


    public View(LoginInterface controller){
        this.gameController=controller;
        isLogged = false;
        isGameStarted = false;
        isPlaying = false;
    }


    public void start() throws RemoteException{
        System.out.println("Benvenuto in Sagrada, vuoi giocare con la Cli [c] o con la Gui [g]?");
        input=scanner.nextLine().toLowerCase();
        do{
            if(input.equals('c')){
                ui = new CLI(this);
            }
            else if(input.equals('g')){
                //ui = new GUI(this);
            }
            else {
                System.out.println("Inserisci una lettera valida");
                input = scanner.nextLine().toLowerCase();
            }
        } while (!input.equals('c')&&!input.equals('g'));

        ui.onChooseNetwork("Vuoi giocare con la RMI [r] o Socket [s]?");

    }



    /////////////////////////////////////////////////////////////////////////////////////////
    // "Getters" (per verificare lo stato del Client, in Locale).
    /////////////////////////////////////////////////////////////////////////////////////////


    public boolean isGameStarted() {
        return isGameStarted;
    }

    public boolean isLogged() {
        return isLogged;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public String getNickname() {
        return nickname;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Metodi con cui il model notifica la view in seguito ad un aggiornamento (vedi interfaccia RemotePlayer)
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onSchemeToChoose(Match match) {
        this.match=match;
        ui.onSchemeToChoose(match, nickname, "Scegli il numero del tuo schema");
    }

    @Override
    public void onChosenScheme() throws RemoteException {

    }

    @Override
    public void onGameUpdate(Match match) {
        this.match=match;
        ui.onGameUpdate(match, nickname);
    }

    @Override
    public void onTurnStart(Match match, String nickname) {
        this.match=match;
        isPlaying=true;
        ui.onTurnStart(match, nickname);
    }

    @Override
    public void onTurnEnd() {
        isPlaying=false;
        ui.onTurnEnd();
    }

    @Override
    public void onGameEnd(Match match) {
        this.match=match;
        ui.onGameEnd(match);
    }

    @Override
    public void onPlayerLogged() throws RemoteException {

    }

    @Override
    public void onSetPlaying() throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard4(Match match) throws RemoteException {
        ui.onGameUpdate(match, nickname);
        ui.onOtherInfoToolCard4(match);
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // "Senders" (per l'invio di informazioni verso il Server, in Remoto).
    /////////////////////////////////////////////////////////////////////////////////////////


    public void chooseNetwork (String choice) {
        if(choice.equals('r')){
            try {
                controller = gameController.connect(this);
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
            }
            ui.onSuccess("Giocherai con RMI");
            ui.onLogin("Scegli il tuo nickname: ");
        }
        else if(input.equals('g')){
            //ui = new GUI(this);
            ui.onSuccess("Giocherai con Socket");
        }
        else
            ui.onChooseNetwork("Inserisci una lettera valida");
    }


    /**
     *
     * Metodo per effettuare il login presso il Server.
     *
     * @param nickname
     *            nickname da usare per il login presso il Server.
     */

    public void loginPlayer(String nickname) {
        try {
            controller.sendLoginRequest(nickname, this); //TODO: il controller mi notifica l'indice del giocatore
            this.isLogged = true;
            ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
            this.nickname = nickname;
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidNicknameException e) {
            ui.onLogin(e.getMessage() + ". Inserisci un nickname differente");
        }

        // devo notificare anche il colore del giocatore

    }


    public void setChosenScheme (int id) {
        try {
            controller.setChosenScheme(id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }
    }


    public void useDice (int indexOfDiceInDraftPool, int row, int col) {
        if (indexOfDiceInDraftPool == -1)
            indexOfDiceInDraftPool = dice;
        else
            dice = indexOfDiceInDraftPool;
        try {
            controller.sendUseDiceRequest (indexOfDiceInDraftPool, row, col);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onPlaceDiceNotValid(e);
        }
    }


    public void endTurn () {
        try {
            controller.endTurn();
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        }
    }


    // SENDERS ToolCards


    public void useToolCard1 (int indexInDraftPool, String operation) {
        try {
            controller.sendUseToolCard1Request(indexInDraftPool, operation);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard1NotValid(match, e);
        }
    }


    public void useToolCard234 (int id, int sourceRow, int sourceCol, int destRow, int destCol) {
        try {
            controller.sendUseToolCard234Request(id, sourceRow, sourceCol, destRow, destCol);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard234NotValid(id, match, e);
        }
    }
}
