package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface LoginInterface extends Remote {

    public PlayerInterface connectRMI(String nickname, RemotePlayer remotePlayer) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException, NotValidNicknameException;

    public void connectSocket();
}
