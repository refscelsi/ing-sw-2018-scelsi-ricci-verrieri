package it.polimi.ing.sw.client;

import it.polimi.ing.sw.controller.LoginInterface;
import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.RMI.RemotePlayerRMI;
import it.polimi.ing.sw.controller.network.Socket.PlayerInterfaceSocket;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.ui.cli.CLI;
//import it.polimi.ing.sw.ui.gui.GUI;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class View extends UnicastRemoteObject implements RemotePlayer, RemotePlayerRMI {
    /**
     * ogni giocatore è identificato dal valore dell'attributo index nel model: il giocatore con index=0 avrà come
     * nickname nicknames.get(0), come schema schemesOfAllPlayers.get(0), come colore di pedina playersColor[0],
     * come punteggio di fine partita playersScore[0] e occuperà la posizione contenuta in playersRanking[0]
     * nella classifica finale.
     */

    private static final String SERVER_ADDRESS = Constants.SERVER_ADDRESS;
    private static final int SERVER_SOCKET_PORT = Constants.SOCKET_PORT;
    private static final int SERVER_RMI_PORT = Constants.RMI_PORT;
    private boolean isLogged;
    private boolean isPlaying;     // flag per vedere se è il turno del giocatore a cui appartiene questa view
    private Match match;
    private boolean isGameStarted;     // flag per vedere se la partita è iniziata: non so se sarà utile o meno
    private boolean isOnline;
    private PlayerInterface controller;//il client può chiamare solo i metodi di PlayerInterfaceSocket
    private LoginInterface loginController;
    private UiUpdate ui;
    private String input;
    private static Scanner scanner = new Scanner(System.in);
    private String nickname;
    private int dice;
    private String networkChoice;


    public View() throws RemoteException {
        super();
        isLogged = false;
        isGameStarted = false;
        isPlaying = false;
    }


    public void start() throws RemoteException {
        System.out.println("Benvenuto in Sagrada, vuoi giocare con la Cli [c] o con la Gui [g]?");
        do {
            input = scanner.nextLine().toLowerCase();
            if (input.equals("c")) {
                ui = new CLI(this);
            } else if (input.equals("g")) {
                //ui = new GUI(this);
            } else {
                System.out.println("Inserisci una lettera valida");
            }
        } while (!input.equals("c") && !input.equals("g"));

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
    // Metodi con cui il model notifica la view in seguito ad un aggiornamento (vedi interfaccia RemotePlayerRMI)
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onSchemeToChoose(Match match) {
        this.match = match;
        Runnable task = () -> {
            ui.onSchemeToChoose(match, nickname, "Scegli il numero del tuo schema");
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public void onSuccess(String message) throws RemoteException {
        ui.onSuccess(message);
    }


    @Override
    public void onGameUpdate(Match match) {
        this.match = match;
        ui.onGameUpdate(match, nickname);
        if (isPlaying == false)
            onTurnEnd();
    }

    @Override
    public void onTurnEnd() {
        isPlaying = false;
        Runnable task = () -> {
            ui.onTurnEnd();
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public void onGameEnd(Match match) {
        this.match = match;
        ui.onGameEnd(match);
    }

    @Override
    public void onPlayerLogged() {

    }

    @Override
    public void onSetPlaying() {
        this.match = match;
        isPlaying = true;
        Runnable task = () -> {
            ui.onTurnStart(match, nickname);
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public void onOtherInfoToolCard4(Match match) {
        ui.onGameUpdate(match, nickname);
        Runnable task = () -> {
            ui.onOtherInfoToolCard4(match);
        };
        Thread thread = new Thread(task);
        thread.start();
    }

    @Override
    public void onOtherInfoToolCard11(Match match) throws RemoteException {
        ui.onGameUpdate(match, nickname);
        Runnable task = () -> {
            ui.onOtherInfoToolCard11(match);
        };
        Thread thread = new Thread(task);
        thread.start();
    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // "Senders" (per l'invio di informazioni verso il Server, in Remoto).
    /////////////////////////////////////////////////////////////////////////////////////////


    public void chooseNetwork (String choice) {
        networkChoice = choice;
        ui.onLogin("Scegli il tuo nickname: ");
    }


    public void login(String nickname) {
        if (networkChoice.equals("r"))
            loginPlayerRMI(nickname);
        /*else
            loginPlayerSocket();*/
    }


    /**
     * Metodo per effettuare il login presso il Server.
     *
     * @param nickname nickname da usare per il login presso il Server.
     */

    public void loginPlayerRMI(String nickname) {
        try {
            Registry reg = LocateRegistry.getRegistry();
            LoginInterface loginController = (LoginInterface) reg.lookup("LoginController");
            controller = loginController.connectRMI(nickname, this);
            this.isLogged = true;
            ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
            this.nickname = nickname;
        } catch (NotValidNicknameException e) {
            ui.onLogin(e.getMessage() + " Inserisci un nickname differente");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (ToolCardException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotPossibleConnection notPossibleConnection) {
            notPossibleConnection.printStackTrace();
        } catch (NotBoundException e) {
            e.printStackTrace();
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        // devo notificare anche il colore del giocatore

    }

    /*public void loginPlayerSocket() {
        //metodi per creare la connessione socket
        PlayerInterfaceSocket playerInterfaceSocket = null;
        try {
            playerInterfaceSocket = new PlayerInterfaceSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller = playerInterfaceSocket;

    }*/

    public void setChosenScheme(int id) {
        try {
            controller.setChosenScheme(id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
        try {
            controller.checkAllReady();
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }

    }


    public void useDice(int indexOfDiceInDraftPool, int row, int col) {
        if (indexOfDiceInDraftPool == -1)
            indexOfDiceInDraftPool = dice;
        else
            dice = indexOfDiceInDraftPool;
        try {
            controller.sendUseDiceRequest(indexOfDiceInDraftPool, row, col);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onPlaceDiceNotValid(e);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
    }


    public void endTurn() {
        try {
            controller.endTurn();
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
    }


    // SENDERS ToolCards


    public void useToolCard1(int indexInDraftPool, String operation) {
        try {
            controller.sendUseToolCard1Request(indexInDraftPool, operation);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard1NotValid(match, e);
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }


    public void useToolCard234(int id, int sourceRow, int sourceCol, int destRow, int destCol) {
        try {
            controller.sendUseToolCard234Request(id, sourceRow, sourceCol, destRow, destCol);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard234NotValid(id, match, e);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
    }


    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) {
        try {
            controller.useToolCard5(indexInDraftPool, round, indexInRound);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
    }


    public void useToolCard6(int indexInDraftPool) {
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
            onNotValidPlay(e.getMessage());
        }
    }


    public void useToolCard78(int id) {
        try {
            controller.useToolCard78(id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
    }


    public void useToolCard9(int dice, int row, int col) {
        try {
            controller.sendUseToolCard9Request(dice, row, col);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard9NotValid(match, e);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
    }

    public void useToolCard10(int dice) {
        try {
            controller.useToolCard10(dice);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        } catch (NotValidException e) {
            e.printStackTrace();
        }
    }

    public void useToolCard11(int dice) {
        try {
            controller.useToolCard11(dice);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        } catch (NotValidException e) {
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
            onNotValidPlay(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCard11bNotValid(match, e);
        }
    }


    public void onNotValidPlay(String e) {
        ui.onActionNotValid(e);
        ui.onTurnStart(match, nickname);
    }

}
