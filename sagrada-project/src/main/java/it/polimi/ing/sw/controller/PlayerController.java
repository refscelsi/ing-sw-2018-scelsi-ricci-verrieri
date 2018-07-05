package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.util.Constants;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe che implementa i metodi di PlayerControllerInterface.
 * Sia in caso di connessione Socket che di connessione RMI è il Controller delle azioni del giocatore.
 * In base allo stato in cui si trova il giocatore autorizza o meno le mosse.
 * In RMI i metodi sono chiamati da remoto sull'interfaccia PlayerControllerInterfaceRMI ed implementati in questa classe.
 * In Socket i metodi sono chiamati da @PlayerControllerSocketServer, che a sua volta riceve messaggi dal Client.
 */

public class PlayerController extends UnicastRemoteObject implements PlayerControllerInterfaceRMI, Remote {

    /**
     * riferimento alla partita in corso
     */
    private Match match;
    /**
     * riferimento al Player associato a questo PlayerController
     */
    private Player player;
    /**
     * riferimento al RemotePlayer associato a questo PlayerController
     *
     */
    private RemotePlayer remotePlayer;
    /**
     * Stato del giocatore
     */
    private PlayerState state;
    /**
     * Riferimento al nickname in caso il giocatore si riconnetta
     */
    private String nickname;
    /**
     * Riferimento all'ultimo timer del giocatore lanciato
     */
    Timer timer = new Timer();
    TimerTask lastTimer;
    /**
     * Indice del giocatore nel round corrispondente all'ultimo timer del giocatore lanciato
     */
    int indexInRound;


    /**
     * Costruttore della classe , cui passano i riferimenti alla partita in corso e al RemotePlayer associato
     * @param match
     * @param remotePlayer
     * @throws RemoteException
     */
    public PlayerController(Match match, RemotePlayer remotePlayer) throws RemoteException {
        super();
        this.match = match;
        this.remotePlayer = remotePlayer;
    }

    /**
     * GETTERS
     *
     */

    public RemotePlayer getRemotePlayer() {
        return this.remotePlayer;
    }

    public String getNickname() {
        return this.nickname;
    }

    /**
     * Metodo per il Login del giocatore. In caso di successo salva il riferimento al Player appena creato,
     * notificando il RemotePlayer associato, altrimenti lancia eccezione.
     * @param nickname
     * @throws RemoteException
     */
    @Override
    public void login(String nickname) throws RemoteException {
        lastTimer = new TurnTimer(this);
        timer.schedule(lastTimer, Constants.timerTime);
        try {
            match.login(nickname, remotePlayer);
            this.player=match.getPlayer(nickname);
            player.setOnline();
            remotePlayer.onLogin(player.getNickname());
        } catch ( NotValidNicknameException e ) {
            match.notifyNotValidNicknameException(this.remotePlayer ,e.getMessage());
        } catch ( NotPossibleConnectionException e ) {
            match.notifyNotPossibleConnectionException(this.remotePlayer,e.getMessage());
        }

    }

    @Override
    public void startingMyTurn() throws RemoteException {
        lastTimer = new TurnTimer(this);
        timer.schedule(lastTimer, Constants.timerTime);
        indexInRound = match.getPlayersRoundIndex();
    }


    @Override
    public void reconnectPlayer() throws RemoteException {
        if (!player.isOnline()) {
            if (match.isMatchStarted()) {
                player.setOnline();
                match.incrementNumPlayersPlaying();
                match.notifySuccess(player, "Sei rientrato in partita");
            }
            else
                match.notifyNotValidPlayException(player, "Spiacenti, la partita che stavi giocando è già terminata.");
        }
    }

    /**
     * Metodo con cui i giocatori prendono effettivamente parte alla partita
     * @throws RemoteException
     */
    @Override
    public void joinMatch() throws RemoteException {
        if (player.isOnline()) {
            try {
                if (player.getState().equals(PlayerState.INIZIALIZED)) {
                    match.joinMatch();
                } else
                    throw new NotValidPlayException("Errore sullo stato");     // se succede questa cosa c'è un errore del server
            } catch (NotValidPlayException e) {
                match.notifyNotValidPlayException(player, e.getMessage());
            }
        }
    }

    /**
     * Metodo chiamato dopo aver scelto lo schema per controllare lo stato degli altri giocatori
     * @throws RemoteException
     */
    @Override
    public synchronized void checkAllReady() throws RemoteException {
        if (player.isOnline()) {
            try {
                if (player.getState().equals(PlayerState.READYTOPLAY)) {
                    match.checkAllReady();
                } else
                    throw new NotValidPlayException("Errore sullo stato");     // se succede questa cosa c'è un errore del server
            } catch (NotValidPlayException e) {
                match.notifyNotValidPlayException(player, e.getMessage());
            }
        }
    }

    /**
     * Metodo chiamato per settare la Carta Schema scelta
     * @param id
     * @throws RemoteException
     */
    @Override
    public synchronized void setChosenScheme(int id) throws RemoteException {
        lastTimer = new TurnTimer(this);
        timer.schedule(lastTimer, Constants.timerTime);
        try {
            if (player.getState().equals(PlayerState.SCHEMETOCHOOSE)) {
                match.chooseScheme(this.player, id);
            } else
                throw new NotValidPlayException("Non puoi fare questa mossa ora!");
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        }
    }

    /**
     * Richiesta di utilizzo del dado, in base allo stato del giocatore può essere autorizzata o meno
     * in caso sia l'ultima mossa possibile del giocatore il turno verrà passato automaticamente su Match
     * @param indexOfDiceInDraftPool
     * @param row
     * @param col
     * @throws RemoteException
     */
    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws RemoteException {
        lastTimer = new TurnTimer(this);
        timer.schedule(lastTimer, Constants.timerTime);
        indexInRound = match.getPlayersRoundIndex();
        try {
            switch (player.getState()) {
                case USEDDICE:
                    throw new NotValidPlayException("Hai già usato un dado in questo turno!");
                case USEDTOOLCARD:
                case TURNSTARTED: {
                    match.useDice(player, indexOfDiceInDraftPool, row, col);
                    break;
                }
                default:
                    throw new NotValidPlayException("Non puoi fare questa mossa ora");
            }
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        } catch (NotValidException e) {
            match.notifyNotValidUseDiceException(player, e.getMessage());
        }
    }

    /**
     * Metodo chiamato dal giocatore per terminare il proprio turno
     * @throws RemoteException
     */
    @Override
    public void endTurn() throws RemoteException {
        lastTimer = new TurnTimer(this);
        timer.schedule(lastTimer, Constants.timerTime);
        indexInRound = match.getPlayersRoundIndex();
        try {
            if (player.getState().equals(PlayerState.READYTOPLAY) || player.getState().equals(PlayerState.INIZIALIZED) || player.getState().equals(PlayerState.INIZIALIZED) || !player.isOnline()) {
                throw new NotValidPlayException("Impossibile terminare il turno in questo momento");
            } else {
                match.notifyChangement();
                match.changePlayer();
            }
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        }
    }

    /**
     * Metodo di gestione delle ToolCard in base all'id e ai parametri ricevuti
     * @param id
     * @param dice
     * @param operation
     * @param sourceRow
     * @param sourceCol
     * @param destRow
     * @param destCol
     * @throws RemoteException
     */
    @Override
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws RemoteException {
        lastTimer = new TurnTimer(this);
        timer.schedule(lastTimer, Constants.timerTime);
        indexInRound = match.getPlayersRoundIndex();
        try {
            switch (id) {
                /**
                 * carte che si possono utilizzare in qualsiasi momento
                 */
                case 1:
                case 2:
                case 3:
                case 5:
                case 10: {
                    if (player.getState().equals(PlayerState.TURNSTARTED) || player.getState().equals(PlayerState.USEDDICE)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
                /**
                 * carte che si possono utilizzare in qualsiasi momento ma si eseguono in 2 step
                 */
                case 4:
                case 12: {
                    if (player.getState().equals(PlayerState.TURNSTARTED) || player.getState().equals(PlayerState.USEDDICE)) {
                        match.useToolCard(player, id, dice, 0, sourceRow, sourceCol, destRow, destCol);
                        break;
                    }
                    else {
                        if (player.getState().equals(PlayerState.FIRSTSTEPTOOLCARD) || player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                            match.useToolCard(player, id, dice, 1, sourceRow, sourceCol, destRow, destCol);
                            break;
                        }
                        else
                            throw new NotValidPlayException("Non puoi usare questa carta");
                    }
                }
                /**
                 * carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
                 */
                case 6: {
                    if (player.getState().equals(PlayerState.TURNSTARTED)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");

                }
                /**
                 * carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
                 *il controller setta destRow a 0 o 1 a seconda che sia la prima o la seconda esecuzione
                 */
                case 11: {
                    if (player.getState().equals(PlayerState.TURNSTARTED)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, 0, destCol);
                        break;
                    }
                    else {
                        if (player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                            match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, 1, destCol);
                            break;
                        } else
                            throw new NotValidPlayException("Non puoi usare questa carta");
                    }
                }
                /**
                 * carta che può essere utilizzata solo durante il secondo turno e prima di scegliere il secondo dado
                 */
                case 7: {
                    if (player.getState().equals(PlayerState.TURNSTARTED) && !match.getIfFirstTurn(player)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
                /**
                 * carta che può essere utilizzata solo durante il primo turno
                 */
                case 8: {
                    if ((player.getState().equals(PlayerState.TURNSTARTED) && match.getIfFirstTurn(player)) || (player.getState().equals(PlayerState.USEDDICE) && match.getIfFirstTurn(player))) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
                /**
                 * carta che può essere utilizzata solo durante il primo turno
                 */
                case 9: {
                    if (player.getState().equals(PlayerState.TURNSTARTED)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        endTurn();
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
                default:
                    break;
            }
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        } catch (NotValidException e) {
            match.notifyNotValidToolCardException(player, id, e.getMessage());
        }
    }

    /**
     * Metodo chiamato in caso di disconnessione, quindi di cattura di un'eccezione di rete
     * @throws RemoteException
     */
    @Override
    public void stopPlayer() throws RemoteException {
        if (player.isOnline()) {
            match.exitPlayer(player);
        }
    }


    public void timeout(TurnTimer turnTimer) throws RemoteException {
        if (turnTimer == lastTimer) {
            if (player.getState().equals(PlayerState.INIZIALIZED) || player.getState().equals(PlayerState.SCHEMETOCHOOSE)) {
                stopPlayer();
            }
            else {
                if (!player.getState().equals(PlayerState.READYTOPLAY) && indexInRound == match.getPlayersRoundIndex() && match.getPlayerPlaying().equals(player)) {
                    stopPlayer();
                }
            }
        }

    }
}
