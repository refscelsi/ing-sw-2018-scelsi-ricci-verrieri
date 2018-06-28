package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.network.RMI.PlayerInterfaceRMI;
import it.polimi.ing.sw.controller.network.RMI.RemotePlayerRMI;
import it.polimi.ing.sw.controller.network.Socket.PlayerInterfaceSocket;
import it.polimi.ing.sw.controller.network.Socket.ViewSocket;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class LoginController extends UnicastRemoteObject implements Remote, LoginInterface {

    //riferimento alla partita
    private Match match;
    //lista clients
    private ArrayList<PlayerInterface> clients;
    //lista dei PlayerController (x riconnessione)
    private ArrayList<PlayerController> playerControllers;

    public LoginController(Match match) throws RemoteException {
        this.match = match;
        this.playerControllers = new ArrayList<PlayerController>();
        this.clients = new ArrayList<PlayerInterface>();
    }

    //metodo che crea un controller per ogni view che si connette
    @Override
    public synchronized PlayerInterfaceRMI connectRMI(String nickname, RemotePlayer remotePlayer) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException, NotValidNicknameException {
        if (clients.size() < Constants.MAX_PLAYERS) {
            if (!checkReconnection(nickname)) {
                match.login(nickname, remotePlayer);
                PlayerController playerController = new PlayerController(this.match, (RemotePlayerRMI) remotePlayer, match.getPlayer(nickname));
                playerControllers.add(playerController);
                clients.add(playerController);
                return playerController;
            } else if (checkReconnection(nickname)) {
                for (PlayerController playerController : playerControllers) {
                    if (playerController.getNickname().equals(nickname)) {
                        return playerController;
                    }
                }
            }
        }
        throw new NotPossibleConnection("la partita è piena");
    }


    public boolean checkReconnection(String nickname) {
        if (playerControllers.size() == 0) {
            return false;
        }
        for (PlayerController playerController : playerControllers) {
            if (playerController.getNickname().equals(nickname) && playerController.getState().equals(PlayerState.OFFLINE)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public  synchronized PlayerInterfaceSocket connectSocket(String nickname, RemotePlayer remotePlayer) throws IOException, ToolCardException, NotValidNicknameException, NotValidException, NotPossibleConnection {
        if (playerControllers.size() < Constants.MAX_PLAYERS) {
            if (!checkReconnection(nickname)) {
                try(Socket socketClient= new Socket("localhost", 1098)){
                    new Thread(()-> {
                        try {
                            new ViewSocket(socketClient,new ObjectInputStream(socketClient.getInputStream()));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();
                }




                match.login(nickname, remotePlayer);


                PlayerInterfaceSocket playerController = new PlayerInterfaceSocket(new Socket("localhost", 1098));
                clients.add(playerController);
                return playerController;
            } else if (checkReconnection(nickname)) {
                //sbatti
            }
        }

        throw new NotPossibleConnection("la partita è piena");
    }

}
