package it.polimi.ing.sw.network.server;

import it.polimi.ing.sw.network.controller.RemotePlayer;
import it.polimi.ing.sw.network.server.exceptions.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import it.polimi.ing.sw.network.server.rmi.RMIServer;

//SagradaServer: gestisce le connessioni

public class Server implements IServer{

    private static final int RMI_PORT = 1099;

    private HashMap<String, RemotePlayer> players;

    RMIServer rmiServer;

    private static final Object PLAYERS_MUTEX = new Object();

    public Server() throws ServerException {
        players = new HashMap<>();
        //rmiServer = new RMIServer(this);
    }

    Registry registry;


    public void startServer() {
        //implementare
    }

    public void startRMIServer(){
        //implementare
    };

    @Override
    public void loginPlayer(String nickname, RemotePlayer remotePlayer) {

    }

    @Override
    public void joinMatch() {

    }

    @Override
    public void exitMatch() {

    }
}



