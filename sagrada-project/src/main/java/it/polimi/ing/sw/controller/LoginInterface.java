package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.network.RMI.PlayerInterfaceRMI;
import it.polimi.ing.sw.controller.network.RMI.RemotePlayerRMI;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote {

    public PlayerInterfaceRMI connectRMI(String nickname, RemotePlayerRMI remotePlayerRMI) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException, NotValidNicknameException;

    public void connectSocket() throws RemoteException;
}
