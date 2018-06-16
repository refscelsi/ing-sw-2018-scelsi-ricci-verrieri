package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.client.ClientController;
import it.polimi.ing.sw.model.ClientObserver;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.server.NotValidNicknameException;

import java.rmi.Remote;

public interface ControllerInterface extends Remote {

    public int sendLoginRequest(String nickname, ClientObserver client) throws NotValidNicknameException, NetworkException;

    public void setChosenScheme (int index, int id) throws NetworkException;

    public void sendUseDiceRequest (int index, int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException;

    public void removeDice (int index, int row, int col) throws NetworkException;

    public void endTurn (int index) throws NetworkException;


}
