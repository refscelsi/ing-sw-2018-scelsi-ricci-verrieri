package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.controller.LoginInterface;
import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.util.Constants;
import it.polimi.ing.sw.ui.cli.CLI;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class View extends UnicastRemoteObject implements RemotePlayer {
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


    public View(LoginInterface controller) throws RemoteException {
        super();
        this.gameController=controller;
        isLogged = false;
        isGameStarted = false;
        isPlaying = false;
    }


    public void start() throws RemoteException{
        System.out.println("Benvenuto in Sagrada, vuoi giocare con la Cli [c] o con la Gui [g]?");
        do{
            input=scanner.nextLine().toLowerCase();
            if(input.equals("c")){
                ui = new CLI(this);
            }
            else if(input.equals("g")){
                //ui = new GUI(this);
            }
            else {
                System.out.println("Inserisci una lettera valida");
            }
        } while (!input.equals("c")&&!input.equals("g"));

        //ui.onChooseNetwork("Vuoi giocare con la RMI [r] o Socket [s]?");

        ui.onLogin("Scegli il tuo nickname: ");

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
    public void onSuccess(String message) throws RemoteException, NotValidException {
        ui.onSuccess(message);
    }


    @Override
    public void onGameUpdate(Match match) {
        this.match=match;
        ui.onGameUpdate(match, nickname);
    }

    @Override
    public void onTurnEnd() {
        isPlaying=false;
        System.out.println("il mio turno è finito");
        ui.onTurnEnd();
    }

    @Override
    public void onGameEnd(Match match) {
        this.match=match;
        ui.onGameEnd(match);
    }

    @Override
    public void onPlayerLogged() {

    }

    @Override
    public void onSetPlaying() {
        this.match=match;
        isPlaying=true;
        System.out.println("il mio turno è iniziato");
        ui.onTurnStart(match, nickname);
    }

    @Override
    public void onOtherInfoToolCard4(Match match) {
        ui.onGameUpdate(match, nickname);
        ui.onOtherInfoToolCard4(match);
    }

    @Override
    public void onOtherInfoToolCard11(Match match) throws RemoteException {
        ui.onGameUpdate(match, nickname);
        ui.onOtherInfoToolCard11(match);
    }



    /////////////////////////////////////////////////////////////////////////////////////////
    // "Senders" (per l'invio di informazioni verso il Server, in Remoto).
    /////////////////////////////////////////////////////////////////////////////////////////


    /*public void chooseNetwork (String choice) {
        if(choice.equals('r')){
            try {
                controller = gameController.connect(nickname,this);
            } catch (RemoteException e) {
                System.err.println(e.getMessage());
            } catch (NotPossibleConnection notPossibleConnection) {
                notPossibleConnection.printStackTrace();
            } catch (NotValidException e) {
                e.printStackTrace();
            } catch (ToolCardException e) {
                e.printStackTrace();
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
    }*/


    /**
     *
     * Metodo per effettuare il login presso il Server.
     *
     * @param nickname
     *            nickname da usare per il login presso il Server.
     */

    public void loginPlayer(String nickname) {
        try {
            controller = gameController.connectRMI(nickname,this);
            this.isLogged = true;
            ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
            this.nickname = nickname;
        } catch (NotValidNicknameException e) {
            ui.onLogin(e.getMessage() + ". Inserisci un nickname differente");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ToolCardException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotPossibleConnection notPossibleConnection) {
            notPossibleConnection.printStackTrace();
        }
        try {
            controller.joinMatch(); //TODO: il controller mi notifica l'indice del giocatore
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ToolCardException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }

        // devo notificare anche il colore del giocatore

    }


    public void setChosenScheme (int id) {
        try {
            controller.setChosenScheme(id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
        try {
            controller.checkAllReady();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
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
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }


    public void endTurn () {
        try {
            controller.endTurn();
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
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
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void useToolCard234 (int id, int sourceRow, int sourceCol, int destRow, int destCol) {
        try {
            controller.sendUseToolCard234Request(id, sourceRow, sourceCol, destRow, destCol);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard234NotValid(id, match, e);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }


    public void useToolCard5 (int indexInDraftPool, int round, int indexInRound) {
        try {
            controller.useToolCard5(indexInDraftPool, round, indexInRound);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }


    public void useToolCard6 (int indexInDraftPool) {
        dice = indexInDraftPool;
        try {
            controller.useToolCard6(indexInDraftPool);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }


    public void useToolCard78 (int id) {
        try {
            controller.useToolCard78(id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }


    public void useToolCard9 (int dice, int row, int col) {
        try {
            controller.sendUseToolCard9Request(dice, row, col);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard9NotValid(match, e);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }

    public void useToolCard10 (int dice) {
        try {
            controller.useToolCard10(dice);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }

    public void useToolCard11 (int dice) {
        try {
            controller.useToolCard11(dice);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }

    public void useToolCard11b(int i, int i1, int i2) {
        try {
            controller.useToolCard11(dice);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            ui.onUseToolCard11bNotValid(match, e);
        }
    }
}
