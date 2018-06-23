package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {

    public void checkIsready() throws ToolCardException, RemoteException, NotValidException;

    public void checkAllReady() throws RemoteException;

    public void setChosenScheme (int id) throws NetworkException;

    public void sendUseDiceRequest (int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException;

    public void endTurn () throws NetworkException;

}
