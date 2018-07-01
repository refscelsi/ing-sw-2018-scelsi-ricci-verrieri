package it.polimi.ing.sw.client;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import it.polimi.ing.sw.controller.LoginInterface;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerInterfaceSocket;
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

public class View extends UnicastRemoteObject implements RemotePlayer {
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
    private PlayerControllerInterface controller;//il client può chiamare solo i metodi di PlayerInterfaceSocket
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

        ui.onChooseNetwork("Vuoi giocare con la RMI [r] o socket [s]?");

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

    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Metodi con cui il model notifica la view in seguito ad un aggiornamento (vedi interfaccia RemotePlayerRMI)
    /////////////////////////////////////////////////////////////////////////////////////////////////////////


    @Override
    public void onSchemeToChoose(Match match) {
        this.match = match;
        System.out.println("Devo scegliere lo schema (prima di lancio thread");
        Runnable task2 = () -> {
            System.out.println("Devo scegliere lo schema (dopo di lancio thread");
            if(match==null){
                System.out.println("sbatti");
            }
            ui.onSchemeToChoose(match, nickname, "Scegli il numero del tuo schema");
        };
        new Thread(task2).start();

    }

    @Override
    public void onSuccess(String message) throws RemoteException {
        System.out.println("Arriva notifica schema alla view");
        ui.onSuccess(message);
    }


    @Override
    public void onGameUpdate(Match match) {
        this.match = match;
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
        isPlaying = true;
        System.out.println("Notifico inizio turno nella view");
        Runnable task1 = () -> {
            System.out.println("Inizio turno nel thread");
            ui.onTurnStart(match, nickname);
            System.out.println("Fine thread");
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
    }

    @Override
    public void onOtherInfoToolCard(int id) {
        Runnable task3 = () -> {
            ui.onOtherInfoToolCard(id, match);
        };
        Thread thread3 = new Thread(task3);
        thread3.start();
    }



    public void onNotValidPlay(String e) {
        ui.onActionNotValid(e);
        ui.onTurnStart(match, nickname);
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


    public void loginPlayerSocket(String nickname) {
        PlayerControllerInterfaceSocket playerInterfaceSocket = null;
        try {
            playerInterfaceSocket = new PlayerControllerInterfaceSocket(nickname, this );

        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller = playerInterfaceSocket;
        this.isLogged = true;
        ui.onSuccess("Complimenti, ti sei loggato come " + nickname);
        this.nickname = nickname;
        try {
            controller.joinMatch();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ToolCardException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        }
    }

    public void setChosenScheme(int id) {
        System.out.println("Ho scelto schema nella view");
        try {
            controller.setChosenScheme(id);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            onNotValidPlay(e.getMessage());
        }
        System.out.println("Controllo se sono tutti pronti");
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
            System.out.println("Uso dado da view");
            controller.sendUseDiceRequest(indexOfDiceInDraftPool, row, col);
            System.out.println("Dado usato da view");
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


    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) {
        if (id==6)
            this.dice = dice;
        if (dice==-1)
            dice = this.dice;
        try {
            controller.useToolCard(id, dice, operation, sourceRow, sourceCol, destRow, destCol);
        } catch (NetworkException e) {
            System.err.println(e.getMessage());
        } catch (NotValidException e) {
            ui.onUseToolCardNotValid(id, match, e.getMessage());
        } catch (NotValidPlayException e) {
                onNotValidPlay(e.getMessage());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

}
