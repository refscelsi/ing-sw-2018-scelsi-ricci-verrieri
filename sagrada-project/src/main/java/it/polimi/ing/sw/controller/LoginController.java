package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.controller.network.socket.PlayerControllerSocket;
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
    //lista clients
    private ArrayList<PlayerControllerInterface> clients;
    //lista dei PlayerController (x riconnessione)
    private ArrayList<PlayerController> playerControllers;

    public LoginController(Match match) throws RemoteException {
        this.match = match;
        this.playerControllers = new ArrayList<PlayerController>();
        this.clients = new ArrayList<PlayerControllerInterface>();
    }

    //metodo che crea un controller per ogni view che si connette
    @Override
    public synchronized PlayerControllerInterfaceRMI connectRMI(String nickname, it.polimi.ing.sw.controller.RemotePlayer remotePlayer) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException, NotValidNicknameException {
        if (clients.size() < Constants.MAX_PLAYERS) {
            if (!checkReconnection(nickname)) {
                match.login(nickname, remotePlayer);
                PlayerController playerController = new PlayerController(this.match, (RemotePlayer) remotePlayer, match.getPlayer(nickname));
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

    public  synchronized PlayerController connectSocket(String nickname, PlayerControllerSocket playerControllerSocket) throws NotPossibleConnection {
        if (playerControllers.size() < Constants.MAX_PLAYERS) {
            if (!checkReconnection(nickname)) {
                try {
                    match.login(nickname,playerControllerSocket );
                    PlayerController playerController= null;
                    playerController = new PlayerController(match, playerControllerSocket, match.getPlayer(nickname));
                    playerControllers.add(playerController);
                    return playerController;
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (NotValidNicknameException e) {
                    e.printStackTrace();
                }
            } else if (checkReconnection(nickname)) {
                //sbatti
            }
        }

        throw new NotPossibleConnection("la partita è piena");
    }

}
