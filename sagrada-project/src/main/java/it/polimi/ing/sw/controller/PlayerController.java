package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.server.NotValidNicknameException;
import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

//classe che gestisce gli input dei client e chiama i metodi di PlayerInterface (sulla rete)

public class PlayerController implements PlayerInterface{
    //riferimento alla partita
    private Match match;
    //riferimento al player
    private Player player;
    //riferimento alla view
    private RemotePlayer remotePlayer;
    //stato del giocatore
    private PlayerState state;

    public PlayerController(Match match, RemotePlayer remotePlayer) {
        this.match=match;
        this.remotePlayer=remotePlayer;
        this.state=PlayerState.INIZIALIZED;
    }

    public void setPlayer(Player player){
        this.player=player;
    }

    @Override
    public void setChosenScheme(int index, int id) throws NetworkException {
        if(state.equals(PlayerState.INIZIALIZED)){
        }
    }
    @Override
    public void sendUseDiceRequest(int index, int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException {
    }

    @Override
    public void removeDice(int index, int row, int col) throws NetworkException {
    }

    @Override
    public void endTurn(int index) throws NetworkException {

    }
}
