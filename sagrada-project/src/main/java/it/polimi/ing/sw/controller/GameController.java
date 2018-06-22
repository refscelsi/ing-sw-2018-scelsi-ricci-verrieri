package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class GameController extends UnicastRemoteObject implements Remote, GameInterface {

    private Match match;

    //mi serve un array per salvare i giocatori

    public GameController(Match match) throws RemoteException{
        this.match=match;
    }

    public void login(String nickname, RemotePlayer observer){
        match.createNewPlayer(nickname);
    }

    @Override
    public PlayerInterface connect(RemotePlayer player) throws RemoteException {
        PlayerController playerController= new PlayerController(this.match, player);
        return playerController;
    }
}
