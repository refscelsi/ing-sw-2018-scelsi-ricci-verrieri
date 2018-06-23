package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.util.Constants;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LoginController extends UnicastRemoteObject implements Remote, LoginInterface {

    //riferimento alla partita
    private Match match;


    //lista dei clients
    private ArrayList<PlayerController> clients;

    public LoginController(Match match) throws RemoteException{
        this.match=match;
        this.clients=new ArrayList<PlayerController>();
    }

    //metodo che crea un controller per ogni view che si connette
    @Override
    public synchronized PlayerInterface connect(String nickname, RemotePlayer remotePlayer) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException {
        if(clients.size()< Constants.MAX_PLAYERS){
            match.login(nickname, remotePlayer);
            PlayerController playerController= new PlayerController(this.match, remotePlayer, match.getPlayer(nickname));
            clients.add(playerController);
            return playerController;
        }
        else
            throw new NotPossibleConnection("La partita è piena! Sorry");
    }

}
