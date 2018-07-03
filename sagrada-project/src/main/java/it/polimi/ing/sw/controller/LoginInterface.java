package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnectionException;
import it.polimi.ing.sw.controller.network.RMI.PlayerControllerInterfaceRMI;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote, Serializable {

    public PlayerControllerInterfaceRMI connectRMI(String nickname, RemotePlayer remotePlayer) throws RemoteException;

}
