package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//classe che gestisce gli input dei client e chiama i metodi di PlayerInterface (sulla rete)

public class PlayerController extends UnicastRemoteObject implements PlayerInterface, Remote{
    //riferimento alla partita
    private Match match;
    //riferimento al player
    private Player player;
    //riferimento alla view
    private RemotePlayer remotePlayer;
    //stato del giocatore
    private PlayerState state;

    public PlayerController(Match match, RemotePlayer remotePlayer, Player player) throws RemoteException {
        super();
        this.match=match;
        this.remotePlayer=remotePlayer;
        this.player=player;
        this.state=PlayerState.INIZIALIZED;
    }

    public void setState(PlayerState state){
        this.state=state;
    }

    public RemotePlayer getRemotePlayer() {
        return this.remotePlayer;
    }


    @Override
    public void checkIsready() throws ToolCardException, RemoteException, NotValidException {
        match.checkIsReady();
    }

    @Override
    public void checkAllReady() throws RemoteException {
        match.checkAllReady();
    }

    @Override
    public void setChosenScheme(int id) throws NetworkException {
        if (state.equals(PlayerState.INIZIALIZED)) {
            player.setScheme(getSchemeWithId(id));
            setState(PlayerState.CHOOSENSCHEME);
        }
    }

    @Override
    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException {

    }

    @Override
    public void endTurn() throws NetworkException {

    }
}
