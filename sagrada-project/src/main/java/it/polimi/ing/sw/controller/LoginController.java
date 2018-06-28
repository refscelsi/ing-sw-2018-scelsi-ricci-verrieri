package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.network.RMI.PlayerInterfaceRMI;
import it.polimi.ing.sw.controller.network.RMI.RemotePlayerRMI;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
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

    public LoginController(Match match) throws RemoteException {
        this.match = match;
        this.clients = new ArrayList<PlayerController>();
    }

    //metodo che crea un controller per ogni view che si connette
    @Override
    public synchronized PlayerInterfaceRMI connectRMI(String nickname, RemotePlayerRMI remotePlayerRMI) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException, NotValidNicknameException {
        if (clients.size() < Constants.MAX_PLAYERS) {
            if (!checkReconnection(nickname)) {
                match.login(nickname, remotePlayerRMI);
                PlayerController playerController = new PlayerController(this.match, remotePlayerRMI, match.getPlayer(nickname));
                clients.add(playerController);
                return playerController;
            } else if (checkReconnection(nickname)) {
                for (PlayerController playerController : clients) {
                    if (playerController.getNickname().equals(nickname)) {
                        return playerController;
                    }
                }
            }
        }
        throw new NotPossibleConnection("la partita è piena");
    }

    @Override
    public void connectSocket() throws RemoteException {
        return;
    }

    public boolean checkReconnection(String nickname) {
        if (clients.size() == 0) {
            return false;
        }
        for (PlayerController playerController : clients) {
            if (playerController.getNickname().equals(nickname) && playerController.getState().equals(PlayerState.OFFLINE)) {
                return true;
            }
        }
        return false;
    }

}
