package it.polimi.ing.sw.controller.network.RMI;

import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;

import java.rmi.RemoteException;

public interface RemotePlayerRMI extends RemotePlayer {

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
    void onLogin(String nickname) throws RemoteException;

    //notifica che è il turno del giocatore --> settare is Playing true
    void onSetPlaying() throws RemoteException;

    void onOtherInfoToolCard(int id) throws RemoteException;

    void onNotValidUseDiceException(String message) throws RemoteException;

    void onNotValidToolCardException(int id, String message) throws RemoteException;

    void onNotValidPlayException(String message) throws RemoteException;

    void onNotValidNicknameException(String message) throws RemoteException;

    void onNotPossibleConnectionException(String message) throws RemoteException;

}
