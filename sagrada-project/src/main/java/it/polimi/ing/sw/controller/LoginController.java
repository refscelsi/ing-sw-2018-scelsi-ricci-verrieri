package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
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
    public synchronized PlayerControllerInterfaceRMI connectRMI(String nickname, it.polimi.ing.sw.controller.RemotePlayer remotePlayer) throws RemoteException {
        if (!checkReconnection(nickname)) {
            try {
                match.login(nickname, remotePlayer);
            } catch (NotPossibleConnectionException e) {
                PlayerController playerController = new PlayerController(this.match, (RemotePlayer) remotePlayer, match.getPlayerLogged(nickname));
                playerControllers.add(playerController);
                clients.add(playerController);
                match.notifyNotPossibleConnectionException(match.getPlayerLogged(nickname), e.getMessage());
            } catch (NotValidNicknameException e) {
                match.notifyNotValidNicknameException(match.getPlayerLogged(nickname), e.getMessage());
            }
            PlayerController playerController = new PlayerController(this.match, (RemotePlayer) remotePlayer, match.getPlayer(nickname));
            playerControllers.add(playerController);
            clients.add(playerController);
            return playerController;
        } else if (checkReconnection(nickname)) {
            for (PlayerController playerController : playerControllers) {
                if (playerController.getNickname().equals(nickname)) {
                    if (playerController.getState().equals(PlayerState.OFFLINE))
                        return playerController;
                }
            }
        }
        throw new RemoteException();
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

    public  synchronized PlayerController connectSocket(String nickname, PlayerControllerSocket playerControllerSocket) throws RemoteException {
            if (!checkReconnection(nickname)) {
                try {
                    match.login(nickname,playerControllerSocket );
                } catch (NotPossibleConnectionException e) {
                    PlayerController playerController= new PlayerController(match, playerControllerSocket,  match.getPlayerLogged(nickname));
                    playerControllers.add(playerController);
                    match.notifyNotPossibleConnectionException(match.getPlayerLogged(nickname), e.getMessage());
                } catch (NotValidNicknameException e) {
                    match.notifyNotValidNicknameException(match.getPlayerLogged(nickname), e.getMessage());
                }
                PlayerController playerController= new PlayerController(match, playerControllerSocket, match.getPlayer(nickname));
                playerControllers.add(playerController);
                return playerController;
            } else if (checkReconnection(nickname)) {
                for (PlayerController playerController : playerControllers) {
                    if (playerController.getNickname().equals(nickname)) {
                        if (playerController.getState().equals(PlayerState.OFFLINE))
                            return playerController;
                    }
                }
            }
        throw new RemoteException();
    }


}

