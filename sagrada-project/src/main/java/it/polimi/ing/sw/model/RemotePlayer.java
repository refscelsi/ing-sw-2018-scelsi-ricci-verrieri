package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePlayer extends Remote {

    public void onSchemeToChoose(Match match) throws RemoteException;

    public void onGameUpdate(Match match) throws RemoteException;

    public void onTurnStart(Match match, String nickname) throws RemoteException;

    public void onTurnEnd() throws RemoteException;

    public void onGameEnd(Match match) throws RemoteException;

    public void onPlayerLogged() throws RemoteException;

    public void onSetPlaying() throws RemoteException;
}
