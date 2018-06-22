package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.server.NotValidNicknameException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {

    public PlayerInterface connect(RemotePlayer player) throws RemoteException, NotPossibleConnection, ToolCardException, NotValidException;

}
