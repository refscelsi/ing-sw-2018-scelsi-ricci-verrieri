package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerSocketServer;
import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Classe che gestisce la connessione sia RMI che Socket.
 * In base alla connessione scelta dal Client, ritorna una PlayerInterface differente alla View.
 */

public class ConnectionController extends UnicastRemoteObject implements Remote, ConnectionInterface {

    /**
     * Riferimento alla partita, da passare al PlayerController
     */
    private Match match;

    /**
     * Costruttore a cui viene passato, dal Server , il riferimento al Match
     * @param match
     * @throws RemoteException
     */
    public ConnectionController(Match match) throws RemoteException {
        this.match = match;
    }

    /**
     * Metodo per la connessione in RMI, crea un nuovo PlayerController che ritorna al Client
     * @param nickname
     * @param remotePlayer
     * @return PlayerController di tipo RMI
     * @throws RemoteException
     */
    @Override
    public synchronized PlayerControllerInterfaceRMI connectRMI(String nickname, RemotePlayer remotePlayer) throws RemoteException {
        PlayerController playerController = new PlayerController(this.match, (RemotePlayer) remotePlayer);
        return playerController;
    }

    /**
     * Metodo per la connessione in Socket, crea un nuovo PlayerController, a cui passa il riferimento alla View e alla
     * clientSocket, e ritorna il PlayerController al Client
     * @param playerControllerSocketServer
     * @return
     */
    public synchronized PlayerController connectSocket(PlayerControllerSocketServer playerControllerSocketServer){
        PlayerController playerController= null;
        try {
            playerController = new PlayerController(match, playerControllerSocketServer);
        } catch ( RemoteException e ) {
            e.printStackTrace();
        }
        return playerController;
    }



}

