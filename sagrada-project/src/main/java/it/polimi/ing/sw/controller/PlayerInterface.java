package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.server.NotValidNicknameException;

import java.rmi.Remote;

public interface PlayerInterface extends Remote {

    public int sendLoginRequest(String nickname, RemotePlayer client) throws NotValidNicknameException, NetworkException;

    public void setChosenScheme (int id) throws NetworkException;

    public void sendUseDiceRequest (int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException;

    public void endTurn () throws NetworkException;


}
