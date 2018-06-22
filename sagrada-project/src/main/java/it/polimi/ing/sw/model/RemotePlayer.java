package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePlayer extends Remote {

    public void onGameUpdate(Match match) throws RemoteException;
}
