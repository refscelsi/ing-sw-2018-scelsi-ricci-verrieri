package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;

import java.rmi.Remote;
import java.rmi.RemoteException;


/**
 * Interfaccia implementata sia dalla View che dal PlayerControllerSocketServer, contiene i metodi di
 * aggiornamento chiamati da Model (@Match) a View.
 * In caso di connessione RMI i metodi sono direttamente implementati dalla View, in caso di Socket
 * il PlayerControllerSocketServer manderà un messaggio contenente tutte le informazioni
 */

public interface RemotePlayer extends Remote {

    void onLogin(String nickname) throws RemoteException;

    void onSchemeToChoose(Match match) throws RemoteException, NotValidPlayException;

    void onSuccess(String message) throws RemoteException;

    void onGameUpdate(Match match) throws RemoteException;

    void onTurnEnd() throws RemoteException;

    void onGameEnd(Match match) throws RemoteException;

    void onSetPlaying() throws RemoteException;

    void onOtherInfoToolCard(int id) throws RemoteException;

    void onNotValidUseDiceException(String message) throws RemoteException;

    void onNotValidToolCardException(int id, String message) throws RemoteException;

    void onNotValidPlayException(String message) throws RemoteException;

    void onNotValidNicknameException(String message) throws RemoteException;

    void onNotPossibleConnectionException(String message) throws RemoteException;

    void onPlayerDisconnection(String nickname) throws RemoteException;
}
