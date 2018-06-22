package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.server.NotValidNicknameException;
import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

//classe che gestisce gli input dei client e chiama i metodi di PlayerInterface (sulla rete)

public class PlayerController implements PlayerInterface{
    private Match match;
    private Player player;
    private RemotePlayer remotePlayer;

    public PlayerController(Match match, RemotePlayer remotePlayer) {
        this.match=match;
        this.remotePlayer=remotePlayer;
    }

    @Override
    public int sendLoginRequest(String nickname, RemotePlayer client) throws NotValidNicknameException, NetworkException {
        //creo un giocatore con quel nickname
        //aggiungo il giocatore ai players e ti ritorno l'indice nell'array players
        //ti aggiugno alla lista degli observer
        return 0;
    }

    @Override
    public void setChosenScheme(int index, int id) throws NetworkException {
    }
    //è lecita questa implementazione o gli oggetti devo tirarli fuori nel match??
    @Override
    public void sendUseDiceRequest(int index, int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException {
        match.useDice(match.getPlayer(index).getScheme().getBoxes()[row][col], match.getDraftPool().getDice(indexOfDiceInDraftPool),match.getPlayer(index).getScheme());
    }

    @Override
    public void removeDice(int index, int row, int col) throws NetworkException {
        match.getPlayer(index).getScheme().getBoxes()[row][col].removeDice();
    }

    @Override
    public void endTurn(int index) throws NetworkException {

    }
}
