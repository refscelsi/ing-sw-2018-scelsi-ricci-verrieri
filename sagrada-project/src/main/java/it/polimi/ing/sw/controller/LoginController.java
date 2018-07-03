package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerInterfaceSocket;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerSocket;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LoginController extends UnicastRemoteObject implements Remote, LoginInterface {

    //riferimento alla partita
    private Match match;
    //lista clients --> mi serve??
    private ArrayList<PlayerControllerInterface> clients;
    //lista dei PlayerController (x riconnessione)
    private ArrayList<PlayerController> playerControllers;

    public LoginController(Match match) throws RemoteException {
        this.match = match;
        this.playerControllers = new ArrayList<PlayerController>();
        this.clients = new ArrayList<PlayerControllerInterface>();
    }

    @Override
    public synchronized PlayerControllerInterfaceRMI connectRMI(String nickname, RemotePlayer remotePlayer) throws RemoteException {
        PlayerController playerController = new PlayerController(this.match, (RemotePlayer) remotePlayer);
        playerControllers.add(playerController);
        clients.add(playerController);
        return playerController;
    }

    public synchronized PlayerController connectSocket(PlayerControllerSocket playerControllerSocket){
        PlayerController playerController= null;
        try {
            playerController = new PlayerController(match, playerControllerSocket);
        } catch ( RemoteException e ) {
            e.printStackTrace();
        }
        playerControllers.add(playerController);
        return playerController;
    }



}

