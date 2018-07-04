package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//classe che gestisce gli input dei client e implementa i metodi di PlayerControllerInterfaceSocket (sulla rete)

public class PlayerController extends UnicastRemoteObject implements PlayerControllerInterfaceRMI, Remote {
    //riferimento alla partita
    private Match match;
    //riferimento al player
    private Player player;
    //riferimento alla view
    private RemotePlayer remotePlayer;
    //stato del giocatore
    private PlayerState state;
    //tengo traccia del nickname nel caso lo stronzo si riconnettesse
    private String nickname;

    public PlayerController(Match match, RemotePlayer remotePlayer) throws RemoteException {
        super();
        this.match = match;
        this.remotePlayer = remotePlayer;
    }


    public PlayerState getState() {
        return this.player.getState();
    }

    public RemotePlayer getRemotePlayer() {
        return this.remotePlayer;
    }

    public String getNickname() {
        return this.nickname;
    }

    @Override
    public void login(String nickname) throws RemoteException {
        try {
            match.login(nickname, remotePlayer);
            this.player=match.getPlayer(nickname);
            System.out.println("assegno il player");
            remotePlayer.onLogin(player.getNickname());
        } catch ( NotValidNicknameException e ) {
            match.notifyNotValidNicknameException(this.remotePlayer ,e.getMessage());
        } catch ( NotPossibleConnectionException e ) {
            match.notifyNotPossibleConnectionException(this.remotePlayer,e.getMessage());
        }

    }

    @Override
    public void joinMatch() throws RemoteException {
        try {
            if (player.getState().equals(PlayerState.OFFLINE)) {
                //gestisci lo stronzo che ritorna in partita
            } else if (player.getState().equals(PlayerState.INIZIALIZED)) {
                match.joinMatch();
            }
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        }
    }

    @Override
    public void checkAllReady() throws RemoteException {
        try {
            if (player.getState().equals(PlayerState.READYTOPLAY)) {
                match.checkAllReady();
            } else
                throw new NotValidPlayException("Errore sullo stato");
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        }
    }


    @Override
    public void setChosenScheme(int id) throws RemoteException {
        try {
            if (player.getState().equals(PlayerState.SCHEMETOCHOOSE)) {
                match.chooseScheme(this.player, id);
            } else
                throw new NotValidPlayException("Non puoi fare questa mossa ora!");
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        }
    }

    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws RemoteException {
        try {
            switch (player.getState()) {
                case USEDDICE:
                    throw new NotValidPlayException("Hai già usato un dado in questo turno!");
                case FINISHTURN:
                    throw new NotValidPlayException("Non puoi più fare mosse, passa il turno");
                case USEDTOOLCARD:
                case TURNSTARTED: {
                    match.useDice(player, indexOfDiceInDraftPool, row, col);
                    break;
                }
                case ENDEDTURN:
                    throw new NotValidPlayException("Non è il tuo turno!");
                default:
                    throw new NotValidPlayException("Non puoi fare questa mossa ora");

            }
        } catch (NotValidPlayException e) {
            System.out.println("Vorrei lanciare eccezione");
            match.notifyNotValidPlayException(player, e.getMessage());
        } catch (NotValidException e) {
            match.notifyNotValidUseDiceException(player, e.getMessage());
        }
    }

    //quando passo il turno poi sono pronto a giocare il prossimo turno--> tanto non sarò attivo quindi non potrò chiamare
    //i metodi. Oppure facciamo un altro stato per essere più sicuri e quando vieni notificato isPlaying passi allo stato READYTOPLAY???
    @Override
    public void endTurn() throws RemoteException {
        try {
            System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
            if (player.getState().equals(PlayerState.READYTOPLAY) || player.getState().equals(PlayerState.INIZIALIZED) || player.getState().equals(PlayerState.OFFLINE)) {
                throw new NotValidPlayException("Finisci il turno!");
            } else {
                match.notifyChangement();
                match.changePlayer();
            }
        } catch (NotValidPlayException e) {
            match.notifyNotValidPlayException(player, e.getMessage());
        }
    }

    @Override
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws RemoteException {
        try {
            switch (id) {

                // carte che si possono utilizzare in qualsiasi momento
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

                // carte che si possono utilizzare in qualsiasi momento ma si eseguono in 2 step
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


                // carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
                case 6: {
                    if (player.getState().equals(PlayerState.TURNSTARTED)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");

                }

                // carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
                // il controller setta destRow a 0 o 1 a seconda che sia la prima o la seconda esecuzione
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


                // carta che può essere utilizzata solo durante il secondo turno e prima di scegliere il secondo dado
                case 7: {
                    if (player.getState().equals(PlayerState.TURNSTARTED) && !match.getIfFirstTurn(player)) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }

                // carta che può essere utilizzata solo durante il primo turno
                case 8: {
                    if ((player.getState().equals(PlayerState.TURNSTARTED) && match.getIfFirstTurn(player)) || (player.getState().equals(PlayerState.USEDDICE) && match.getIfFirstTurn(player))) {
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }


                // carta che può essere utilizzata solo se non si è già piazzato un dado
                case 9: {
                    if (player.getState().equals(PlayerState.TURNSTARTED)) {
                        System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
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

    @Override
    public void stopPlayer() throws RemoteException {
        match.exitPlayer(player);
    }



}
