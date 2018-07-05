package it.polimi.ing.sw.client;

//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import it.polimi.ing.sw.controller.ConnectionInterface;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerSocketClient;
import it.polimi.ing.sw.controller.network.socket.ServerUpdateHandler;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.ui.cli.CLI;
//import it.polimi.ing.sw.ui.gui.GUI;
import it.polimi.ing.sw.ui.gui.GUI;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.net.Socket;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.exit;

public class View extends UnicastRemoteObject implements RemotePlayer {
    /**
     * ogni giocatore è identificato dal valore dell'attributo index nel model: il giocatore con index=0 avrà come
     * nickname nicknames.get(0), come schema schemesOfAllPlayers.get(0), come colore di pedina playersColor[0],
     * come punteggio di fine partita playersScore[0] e occuperà la posizione contenuta in playersRanking[0]
     * nella classifica finale.
     */

    private static String SERVER_ADDRESS = Constants.SERVER_ADDRESS;
    private static final int SERVER_SOCKET_PORT = Constants.SOCKET_PORT;
    private static final int SERVER_RMI_PORT = Constants.RMI_PORT;
    private boolean isLogged;
    private boolean isPlaying;     // flag per vedere se è il turno del giocatore a cui appartiene questa view
    private Match match;
    private boolean isGameStarted;     // flag per vedere se la partita è iniziata: non so se sarà utile o meno
    private boolean isOnline;
    private PlayerControllerInterface controller;//il client può chiamare solo i metodi di PlayerInterfaceSocket
    private ConnectionInterface loginController;
    private UiUpdate ui;
    private String input;
    private static Scanner scanner = new Scanner(System.in);
    private String nickname;
    private int dice;
    private String networkChoice;
    private ArrayList<String> playersDisconnected;


    public View(String ip) throws RemoteException {
        super();
        //this.ip=ip;
        SERVER_ADDRESS=ip;
        isLogged = false;
        isGameStarted = false;
        isPlaying = false;
        playersDisconnected = new ArrayList<String>();
    }


    public void start() throws RemoteException {
        try {
            System.out.println("Benvenuto in Sagrada, vuoi giocare con la Cli [c] o con la Gui [g]?");
            do {
                input = scanner.nextLine().toLowerCase();
                if (input.equals("c")) {
                    ui = new CLI(this);
                } else if (input.equals("g")) {
                    ui = new GUI(this);
                } else {
                    System.out.println("Inserisci una lettera valida");
                }
            } while (!input.equals("c") && !input.equals("g"));

            ui.onChooseNetwork("Vuoi giocare con la RMI [r] o socket [s]?");
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
            System.out.println("Digita un carattere valido");
        } catch (IndexOutOfBoundsException e) {
        }

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

    public boolean checkIfRoundTrackIsFull() {
        return match.getRoundTrack().getRoundTrackSize()!=0;
    }

    public void setLogin(boolean isLogged){
        this.isLogged=isLogged;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Metodi con cui il model notifica la view in seguito ad un aggiornamento (vedi interfaccia RemotePlayerRMI)
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onLogin(String nickname) throws RemoteException {
        this.nickname = nickname;
        this.isLogged = true;
        ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
        if(this.nickname==nickname){
            Runnable taskJoin = ()->{
                try {
                    controller.joinMatch();
                } catch ( IOException e ) {
                    e.printStackTrace();
                }
            };
            new Thread(taskJoin).start();
        }
    }

    @Override
    public void onSchemeToChoose(Match match) {
        this.match = match;
        Runnable task2 = () -> {
            ui.onSchemeToChoose(match, nickname, "Scegli il numero del tuo schema");
        };
        new Thread(task2).start();
    }

    @Override
    public void onSuccess(String message) throws RemoteException {
        ui.onSuccess(message);
    }


    @Override
    public void onGameUpdate(Match match) {
        this.match = match;
        /*while (playersDisconnected.size()!=0) {
            ui.onPlayerDisconnection("Il giocatore " + playersDisconnected.get(playersDisconnected.size()-1) + " non è più in partita.", nickname);
            playersDisconnected.remove(playersDisconnected.get(playersDisconnected.size()-1));
        }*/
        ui.onGameUpdate(match, nickname);
    }

    @Override
    public void onTurnEnd() {
        isPlaying = false;
        ui.onSuccess("In attesa che giochino gli altri giocatori...");
    }

    @Override
    public void onGameEnd(Match match) {
        this.match = match;
        ui.onGameEnd(match);
    }


    @Override
    public void onSetPlaying() {
        Runnable task1 = () -> {
            ui.onTurnStart(match, nickname);
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
    }

    @Override
    public void onOtherInfoToolCard(int id) {
        /*Runnable task3 = () -> {*/
            ui.onOtherInfoToolCard(id, match);
        /*};
        Thread thread3 = new Thread(task3);
        thread3.start();*/
    }

    @Override
    public void onNotValidUseDiceException(String message) {
        ui.onPlaceDiceNotValid(message);
    }

    @Override
    public void onNotValidToolCardException(int id, String message) {
        ui.onUseToolCardNotValid(id, match, message);
    }

    @Override
    public void onNotValidPlayException(String message) {
        ui.onActionNotValid(message);
        ui.onTurnStart(match, nickname);
    }

    @Override
    public void onNotValidNicknameException(String message) {
        ui.onLogin(message + " Inserisci un nickname differente");
    }

    @Override
    public void onNotPossibleConnectionException(String message) {
        ui.onActionNotValid(message);
    }

    @Override
    public void onPlayerDisconnection(String nickname) throws RemoteException {
        if (nickname.equals(this.nickname)) {
            Runnable task3 = () -> {
                ui.onPlayerDisconnection("Sei uscito dalla partita, digita 0 per rientrare", nickname);
            };
            Thread thread3 = new Thread(task3);
            thread3.start();
        }
        else
            playersDisconnected.add(nickname);
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
        else
            loginPlayerSocket(nickname);
    }


    /**
     * Metodo per effettuare il login presso il Server.
     *
     * @param nickname nickname da usare per il login presso il Server.
     */

    public void loginPlayerRMI(String nickname) {
        try {
            Registry reg = LocateRegistry.getRegistry();
            ConnectionInterface loginController = (ConnectionInterface) reg.lookup("ConnectionController");
            controller = loginController.connectRMI(nickname, this);
            controller.login(nickname);
            System.out.println("forza chievo");

        } catch ( AccessException e ) {
            e.printStackTrace();
        } catch ( RemoteException e ) {
            ui.onActionNotValid(e.getMessage());
        } catch ( NotBoundException e ) {
            e.printStackTrace();
        }
    }

        public void loginPlayerSocket(String nickname) {
        PlayerControllerSocketClient playerInterfaceSocket = null;
        try {
            Socket socket = new Socket("localhost", Constants.SOCKET_PORT);
            new Thread(new ServerUpdateHandler(this, socket )).start();
            playerInterfaceSocket = new PlayerControllerSocketClient(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller = playerInterfaceSocket;
        try {
            controller.login(nickname);
            System.out.println("forza chievo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setChosenScheme(int id) {
        System.out.println("Ho scelto schema nella view");
        try {
            controller.setChosenScheme(id);
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
        System.out.println("Controllo se sono tutti pronti");
        Runnable taskScheme=()->{
        try {
            controller.checkAllReady();
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
        };
        new Thread(taskScheme).start();
    }


    public void useDice(int indexOfDiceInDraftPool, int row, int col) {
        if (indexOfDiceInDraftPool == -1)
            indexOfDiceInDraftPool = dice;
        else
            dice = indexOfDiceInDraftPool;
        try {
            controller.sendUseDiceRequest(indexOfDiceInDraftPool, row, col);
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
    }


    public void endTurn() {
        try {
            controller.endTurn();
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
    }


    public void stopPlayer() {
        try {
            controller.stopPlayer();
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
    }


    public void reconnectPlayer() {
        try {
            controller.reconnectPlayer();
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
    }


    // SENDERS ToolCards


    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) {
        if (id==6)
            this.dice = dice;
        if (dice==-1)
            dice = this.dice;
        try {
            controller.useToolCard(id, dice, operation, sourceRow, sourceCol, destRow, destCol);
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
    }

    public Match getMatch() {
        return match;
    }

}
