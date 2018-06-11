package it.polimi.ing.sw.network.server;

import it.polimi.ing.sw.network.server.exceptions.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;

import it.polimi.ing.sw.network.server.rmi.RMIServer;

public class Server implements ServerInterface {

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
        {
            try {
                registry = LocateRegistry.createRegistry(RMI_PORT);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            try {
                registry.bind("userController", (Remote) this);
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (AlreadyBoundException e) {
                e.printStackTrace();
            }
            try {
                UnicastRemoteObject.exportObject(this, RMI_PORT);
            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }
    }


    @Override
    public void login(String nickname, RemotePlayer player) throws NotValidNicknameException {
        synchronized (PLAYERS_MUTEX) {
            System.out.println("New login request: " + nickname);
            String player_id = "[" + nickname.toUpperCase() + "]";

            if (!players.containsKey(nickname) && nickname.length() > 0) {

                //setto il nickname
                players.put(nickname, player);
                System.out.println(player_id + " Succesfully logged in!");
            } else {
                System.out.println(player_id + " Already logged in!");
                throw new NotValidNicknameException("il nickname è già in uso");
            }
        }
    }

    @Override
    public void joinMatch() {
    }

    @Override
    public void exitMatch() {

    }
}



