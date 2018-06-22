package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.server.NotValidNicknameException;

import java.rmi.NoSuchObjectException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

public class GameController extends UnicastRemoteObject implements Remote, GameInterface {

    //riferimento alla partita
    private Match match;

    //contatore per i players (poi sarà il timer)
    private int numberOfClients;

    //corrispondenza tra view e playercontroller
    private HashMap<RemotePlayer,PlayerController> clients;

    public GameController(Match match) throws RemoteException{
        this.match=match;
        this.numberOfClients=0;
        clients=new HashMap<RemotePlayer, PlayerController>();
    }


    @Override
    public PlayerInterface connect(RemotePlayer player) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException {
        if(numberOfClients<5){
            numberOfClients++;
            PlayerController playerController= new PlayerController(this.match, player);
            clients.put(player,playerController);
            return playerController;
        }
        else
            throw new NotPossibleConnection("La partita è piena! Sorry");
    }

    @Override
    public void sendLoginRequest(String nickname, RemotePlayer remotePlayer) throws NotValidNicknameException, NetworkException, ToolCardException, RemoteException, NotValidException {
        clients.get(remotePlayer).setPlayer(match.login(nickname,remotePlayer));
        if(clients.size()==4){
            start();
        }
    }

    public void  start() throws ToolCardException, RemoteException, NotValidException {
        match.startMatch();
        match.startRound();
    }
    //metodo che quando scade il timer da match.start();

}
