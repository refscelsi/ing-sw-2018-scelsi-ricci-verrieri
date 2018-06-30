package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interfaccia che accomuna i due RemotePlayer e viene implementata dalla View
public interface RemotePlayer extends Remote {

    //notifica gli schemi da scegliere
    void onSchemeToChoose(Match match) throws RemoteException, NotValidPlayException;

    //notifica il successo di qualcosa
    void onSuccess(String message) throws RemoteException;

    //notifica un generico cambiamento
    void onGameUpdate(Match match) throws RemoteException;

    //notifica che il turno è finito--> settare isPlaying a false
    void onTurnEnd() throws RemoteException;

    //notifica che la partita è finita
    void onGameEnd(Match match) throws RemoteException;

    //notifica il successo del login --> settare logged a true
    void onPlayerLogged() throws RemoteException;

    //notifica che è il turno del giocatore --> settare is Playing true
    void onSetPlaying() throws RemoteException;

    void onOtherInfoToolCard4(Match match) throws RemoteException;

    void onOtherInfoToolCard11(Match match) throws RemoteException;

    void onOtherInfoToolCard12(Match match) throws RemoteException;
}
