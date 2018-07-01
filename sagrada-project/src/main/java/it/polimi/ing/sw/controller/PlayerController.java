package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

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

    public PlayerController(Match match, RemotePlayer remotePlayer, Player player) throws RemoteException {
        super();
        this.match = match;
        this.remotePlayer = remotePlayer;
        this.player = player;
        this.nickname = player.getNickname();
        //this.state=player.getState();
    }

    /*public void setState(PlayerState state){
        this.state=player.getState();
    }*/

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
    public void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException {
        if (player.getState().equals(PlayerState.OFFLINE)) {
            //gestisci lo stronzo che ritorna in partita
        } else if (player.getState().equals(PlayerState.INIZIALIZED)) {
            match.joinMatch();

        }
    }

    @Override
    public void checkAllReady() throws RemoteException, NotValidPlayException {
        if (player.getState().equals(PlayerState.READYTOPLAY)) {
            match.checkAllReady();
        } else throw new NotValidPlayException("non va bene !");
    }


    @Override
    public void setChosenScheme(int id) throws NetworkException, RemoteException, NotValidPlayException {
        System.out.println("Ho scelto schema nel controller");
        if (player.getState().equals(PlayerState.SCHEMETOCHOOSE)) {
            match.chooseScheme(this.player, id);
        } else throw new NotValidPlayException("non puoi fare questa mossa ora!" + player.getState().toString());
    }

    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        //System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
        System.out.println("uso dado da controller");
        switch (player.getState()) {
            case USEDDICE:
                throw new NotValidPlayException("hai già usato un dado in questo turno!");
            case FINISHTURN:
                throw new NotValidPlayException("non puoi più fare mosse, passa il turno");
            case USEDTOOLCARD: {
                System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
                match.useDice(player, indexOfDiceInDraftPool, row, col);
                endTurn();
                break;
            }
            case TURNSTARTED: {
                System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
                match.useDice(player, indexOfDiceInDraftPool, row, col);
                player.setState(PlayerState.USEDDICE);
                match.usedToolCard(player, 0);
                break;
            }
            case ENDEDTURN:
                throw new NotValidPlayException("non è il tuo turno!");
            default:
                throw new NotValidPlayException("non puoi fare questa mossa ora");

        }
    }

    //quando passo il turno poi sono pronto a giocare il prossimo turno--> tanto non sarò attivo quindi non potrò chiamare
    //i metodi. Oppure facciamo un altro stato per essere più sicuri e quando vieni notificato isPlaying passi allo stato READYTOPLAY???
    @Override
    public void endTurn() throws NetworkException, RemoteException, NotValidPlayException {
        System.out.println("giocatore: "+ nickname+ "\n stato:"+ player.getState().toString());
        if (player.getState().equals(PlayerState.READYTOPLAY) || player.getState().equals(PlayerState.INIZIALIZED) || player.getState().equals(PlayerState.OFFLINE)) {
            throw new NotValidPlayException("finisci il turno caro!");
        } else {
            match.notifyChangement();
            match.changePlayer();
            System.out.println("turno finito");
        }
    }

    @Override
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        switch(id) {

            // carte che si possono utilizzare in qualsiasi momento
            case 1:
            case 2:
            case 3:
            case 5:
            case 10: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                    match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                    player.setState(PlayerState.USEDTOOLCARD);
                    match.usedToolCard(player, id);
                    break;
                } else {
                    if (player.getState().equals(PlayerState.USEDDICE)) {
                        System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        endTurn();
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
            }

            // carte che si possono utilizzare in qualsiasi momento ma si eseguono in 2 step
            case 4:
            case 12: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                    match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                    player.setState(PlayerState.FIRSTSTEPTOOLCARD);
                    match.usedToolCard(player, id);
                    break;
                }
                else {
                    if (player.getState().equals(PlayerState.FIRSTSTEPTOOLCARD)) {
                        System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        player.setState(PlayerState.USEDTOOLCARD);
                        match.usedToolCard(player, id);
                        break;
                    }
                    else {
                        if (player.getState().equals(PlayerState.USEDDICE)) {
                            System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                            match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                            player.setState(PlayerState.USEDDICETOOLCARD);
                            match.usedToolCard(player, id);
                            break;
                        }
                        else {
                            if (player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                                System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                                match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                                endTurn();
                                break;
                            } else
                                throw new NotValidPlayException("Non puoi usare questa carta");
                        }
                    }
                }
            }

            // carte utilizzabili solo se non si è già utilizzato un dado nel turno e che prevedono 2 step
            case 6:
            case 11: {
                if (player.getState().equals(PlayerState.TURNSTARTED)) {
                    System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                    match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                    player.setState(PlayerState.USEDDICETOOLCARD);
                    System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                    match.usedToolCard(player, id);
                    break;
                }
                else {
                    if (player.getState().equals(PlayerState.USEDDICETOOLCARD)) {
                        System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        endTurn();
                        break;
                    }
                    else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
            }

            // carta che può essere utilizzata solo durante il secondo turno e prima di scegliere il secondo dado
            case 7: {
                if (player.getState().equals(PlayerState.TURNSTARTED)&&!match.getIfFirstTurn(player)) {
                    System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                    match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                    player.setState(PlayerState.USEDTOOLCARD);
                    match.usedToolCard(player, id);
                    break;
                } else
                    throw new NotValidPlayException("Non puoi usare questa carta");
            }

            // carta che può essere utilizzata solo durante il primo turno
            case 8: {
                if (player.getState().equals(PlayerState.TURNSTARTED)&&match.getIfFirstTurn(player)) {
                    System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                    match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                    player.setState(PlayerState.USEDTOOLCARD);
                    match.usedToolCard(player, id);
                    break;

                } else {
                    if (player.getState().equals(PlayerState.USEDDICE)&&match.getIfFirstTurn(player)) {
                        System.out.println("giocatore: " + nickname + "\n stato:" + player.getState().toString());
                        match.useToolCard(player, id, dice, operation, sourceRow, sourceCol, destRow, destCol);
                        endTurn();
                        break;
                    } else
                        throw new NotValidPlayException("Non puoi usare questa carta");
                }
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
    }

}
