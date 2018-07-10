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
import java.util.Properties;
import java.util.Scanner;

import static it.polimi.ing.sw.util.Constants.SERVER_ADDRESS;
import static java.lang.System.exit;

public class View extends UnicastRemoteObject implements RemotePlayer {
    /**
     * ogni giocatore è identificato dal valore dell'attributo index nel model: il giocatore con index=0 avrà come
     * nickname nicknames.get(0), come schema schemesOfAllPlayers.get(0), come colore di pedina playersColor[0],
     * come punteggio di fine partita playersScore[0] e occuperà la posizione contenuta in playersRanking[0]
     * nella classifica finale.
     */

    private static final int SERVER_SOCKET_PORT = Constants.SOCKET_PORT;
    private static final int SERVER_RMI_PORT = Constants.RMI_PORT;

    /**
     * Flag per vedere se è il turno del giocatore a cui appartiene questa view
     */
    private boolean isPlaying;

    /**
     * Riferimento al model
     */
    private Match match;

    /**
     * Riferimento al PlayerController di questa view
     */
    private PlayerControllerInterface controller;

    /**
     * Riferimento alla user interface collegata a questa view, che può essere una CLI o una GUI
     */
    private UiUpdate ui;
    private String input;
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Riferimento al nickname del giocatore a cui è assegnata questa view
     */
    private String nickname;
    private int dice;

    /**
     * Riferimento alla scelta del giocatore per quanto riguarda la rete: RMI o Socket
     */
    private String networkChoice;

    /**
     * Riferimento al thread dove vengono lanciati i metodi per la scelta della mossa sulla user interface
     */
    private Thread thread3;

    /**
     * Flag per capire se il giocatore si è appena riconnesso
     */
    private Boolean recentOnline;

    /**
     * Metodo costruttore
     */
    public View() throws RemoteException {
        super();
        isPlaying = false;
    }

    /**
     * Metodo per la scelta della user interface
     */
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


    public boolean isPlaying() {
        return isPlaying;
    }

    public String getNickname() {
        return nickname;
    }

    /**
     * Controlla se il tracciato dei round è pieno
     */
    public boolean checkIfRoundTrackIsFull() {
        return match.getRoundTrack().getRoundTrackSize()!=0;
    }

    public Match getMatch() {
        return match;
    }


    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Metodi con cui il model notifica la view in seguito ad un aggiornamento (vedi interfaccia RemotePlayerRMI)
    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Metodo per il login del client
     */
    @Override
    public void onLogin(String nickname) throws RemoteException {
        recentOnline = false;
        this.nickname = nickname;
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

    /**
     * Notifica per la scelta dello schema
     */
    @Override
    public void onSchemeToChoose(Match match) {
        recentOnline = false;
        this.match = match;
        Runnable task2 = () -> {
            ui.onSchemeToChoose(match, nickname, "Scegli il numero del tuo schema");
        };
        new Thread(task2).start();
    }

    /**
     * Notifica del successo di un'azione
     */
    @Override
    public void onSuccess(String message) throws RemoteException {
        recentOnline = true;
        ui.onSuccess(message);
    }

    /**
     * Notifica di un qualsiasi cambiamento nel model
     */
    @Override
    public void onGameUpdate(Match match) {
        this.match = match;
        ui.onGameUpdate(match, nickname);
    }

    /**
     * Notifica della fine del turno del giocatore
     */
    @Override
    public void onTurnEnd() {
        recentOnline = false;
        isPlaying = false;
        ui.onSuccess("In attesa che giochino gli altri giocatori...");
    }

    /**
     * Notifica di fine partita
     */
    @Override
    public void onGameEnd(Match match) {
        this.match = match;
        ui.onGameEnd(match);
    }

    /**
     * Notifica di possibilità di eseguire una mossa (viene chiamata ad inizio turno e dopo l'esecuzione di
     * qualsiasi mossa che prevede la possibilità di eseguirne un'altra)
     */
    @Override
    public void onSetPlaying() {
        recentOnline = false;
        try {
            controller.startingMyTurn();
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
        Runnable task1 = () -> {
            ui.onTurnStart(match, nickname);
        };
        Thread thread1 = new Thread(task1);
        thread1.start();
    }

    /**
     * Notifica che la carta utensile ha bisogno di altre informazioni per finire la sua esecuzione
     * @param   id  l'id della carta utensile
     */
    @Override
    public void onOtherInfoToolCard(int id) {
        recentOnline = false;
        ui.onOtherInfoToolCard(id, match);
    }

    /**
     * Notifica che il dado è stato piazzato infrangendo le condizioni di piazzamento
     * @param  message   il messaggio di errore
     */
    @Override
    public void onNotValidUseDiceException(String message) {
        ui.onPlaceDiceNotValid(message);
    }

    /**
     * Notifica che la carta utensile è stata utilizzata infrangendo le regole
     * @param  id        l'id della carta utensile
     * @param  message   il messaggio di errore
     */
    @Override
    public void onNotValidToolCardException(int id, String message) {
        ui.onUseToolCardNotValid(id, match, message);
    }

    /**
     * Notifica che la mossa è stata eseguita in un momento in cui non poteva essere eseguita
     * @param  message   il messaggio di errore
     */
    @Override
    public void onNotValidPlayException(String message) {
        ui.onActionNotValid(message);
        ui.onTurnStart(match, nickname);
    }

    /**
     * Notifica che la carta utensile è stata utilizzata infrangendo le regole
     * @param  message   il messaggio di errore
     */
    @Override
    public void onNotValidNicknameException(String message) {
        ui.onLogin(message + " Inserisci un nickname differente");
    }

    /**
     * Notifica che non è stato possibile unirsi alla partita
     * @param  message   il messaggio di errore
     */
    @Override
    public void onNotPossibleConnectionException(String message) {
        ui.onActionNotValid(message);
    }

    /**
     * Notifica che un giocatore è uscito dal gioco
     * @param  nickname   il nickname del giocatore che è uscito
     */
    @Override
    public void onPlayerDisconnection(String nickname) throws RemoteException {
        recentOnline = true;
        if (nickname.equals(this.nickname)) {
            Runnable task3 = () -> {
                ui.onPlayerDisconnection("Sei uscito dalla partita, digita 0 per rientrare", nickname);
            };
            thread3 = new Thread(task3);
            thread3.start();
        }
    }


    /**
     * "Senders" (per l'invio di informazioni verso il Server, in Remoto).
     *
     */

    /**
     * Metodo per la scelta della rete
     */
    public void chooseNetwork (String choice) {
        networkChoice = choice;
        ui.onLogin("Scegli il tuo nickname: ");
    }

    /**
     * Metodo per il login in RMI o Socket
     */
    public void login(String nickname) {
        if (networkChoice.equals("r"))
            loginPlayerRMI(nickname);
        else
            loginPlayerSocket(nickname);
    }


    /**
     * Metodo per effettuare il login in RMI presso il Server.
     * @param nickname nickname da usare per il login presso il Server.
     */
    public void loginPlayerRMI(String nickname) {
        try {
            Registry reg = LocateRegistry.getRegistry(SERVER_ADDRESS, SERVER_RMI_PORT);
            ConnectionInterface loginController = (ConnectionInterface) reg.lookup("ConnectionController");
            controller = loginController.connectRMI(nickname, this);
            controller.login(nickname);

        } catch ( AccessException e ) {
            e.printStackTrace();
        } catch ( RemoteException e ) {
            ui.onActionNotValid(e.getMessage());
        } catch ( NotBoundException e ) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per effettuare il login in Socket presso il Server.
     * @param nickname nickname da usare per il login presso il Server.
     */
    public void loginPlayerSocket(String nickname) {
        PlayerControllerSocketClient playerInterfaceSocket = null;
        try {
            Socket socket = new Socket(SERVER_ADDRESS, Constants.SOCKET_PORT);
            new Thread(new ServerUpdateHandler(this, socket )).start();
            playerInterfaceSocket = new PlayerControllerSocketClient(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.controller = playerInterfaceSocket;
        try {
            controller.login(nickname);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo per mandare al server lo schema scelto
     * @param id l'id dello schema scelto
     */
    public void setChosenScheme(int id) {
        if (!recentOnline) {
            try {
                controller.setChosenScheme(id);
            } catch (RemoteException e) {
                ui.onActionNotValid(e.getMessage());
                exit(0);
            }
            Runnable taskScheme = () -> {
                try {
                    controller.checkAllReady();
                } catch (RemoteException e) {
                    ui.onActionNotValid(e.getMessage());
                    exit(0);
                }
            };
            new Thread(taskScheme).start();
        }
        else
            ui.onActionNotValid("Non è il tuo turno.");
    }


    /**
     * Metodo per mandare la richiesta di piazzamento di un dado al server
     * @param indexOfDiceInDraftPool  indice del dado nella riserva
     * @param row                     la riga dello schema in cui si vuole piazzare il dado
     * @param col                     la colonna dello schema in cui si vuole piazzare il dado
     */
    public void useDice(int indexOfDiceInDraftPool, int row, int col) {
        if (!recentOnline) {
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
        else
            ui.onActionNotValid("Non è il tuo turno.");
    }

    /**
     * Metodo per terminare il proprio turno presso il server
     */
    public void endTurn() {
        if (!recentOnline) {
            try {
                controller.endTurn();
            } catch (RemoteException e) {
                ui.onActionNotValid(e.getMessage());
                exit(0);
            }
        }
        else
            ui.onActionNotValid("Non è il tuo turno.");
    }

    /**
     * Metodo per uscire dalla partita
     */
    public void stopPlayer() {
        if (!recentOnline) {
            try {
                controller.stopPlayer();
            } catch (RemoteException e) {
                ui.onActionNotValid(e.getMessage());
                exit(0);
            }
        }
        else
            ui.onActionNotValid("Non è il tuo turno.");
    }

    /**
     * Metodo per rientrare in partita
     */
    public void reconnectPlayer() {
        try {
            controller.reconnectPlayer();
        } catch (RemoteException e) {
            ui.onActionNotValid(e.getMessage());
            exit(0);
        }
    }


    /**
     *  SENDERS ToolCards

     */

    /**
     * Metodo per inviare al server la richiesta di utilizzo di una carta utensile
     * @param id l'id della carta utensile
     */
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) {
        if (!recentOnline) {
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
        else
            ui.onActionNotValid("Non è il tuo turno.");
    }


}
