package it.polimi.ing.sw.client;

import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteView extends Remote {

    public void onGameUpdate(Match match) throws RemoteException;
}
