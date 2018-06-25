package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemotePlayer extends Remote {

    //notifica gli schemi da scegliere
    public void onSchemeToChoose(Match match) throws RemoteException;

    //notifica il successo di qualcosa
    public void onSuccess(String message) throws RemoteException;

    //notifica un generico cambiamento
    public void onGameUpdate(Match match) throws RemoteException;

    //notifica che il turno è finito--> settare isPlaying a false
    public void onTurnEnd() throws RemoteException;

    //notifica che la partita è finita
    public void onGameEnd(Match match) throws RemoteException;

    //notifica il successo del login --> settare logged a true
    public void onPlayerLogged() throws RemoteException;

    //notifica che è il turno del giocatore --> settare is Playing true
    public void onSetPlaying() throws RemoteException;

    public void onOtherInfoToolCard4(Match match) throws RemoteException;
}
