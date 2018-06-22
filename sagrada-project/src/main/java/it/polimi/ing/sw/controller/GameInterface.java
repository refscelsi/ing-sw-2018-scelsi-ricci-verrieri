package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.RemotePlayer;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameInterface extends Remote {

    public PlayerInterface connect(RemotePlayer player) throws RemoteException;
}
