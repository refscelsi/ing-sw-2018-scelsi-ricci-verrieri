package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.NetworkException;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.RemotePlayer;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {

    public void joinMatch() throws RemoteException, ToolCardException, NotValidException;

    public void checkAllReady() throws RemoteException;

    public void setChosenScheme (int id) throws NetworkException, RemoteException, NotValidPlayException;

    public void sendUseDiceRequest (int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    public void endTurn () throws NetworkException, RemoteException, NotValidPlayException;

    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException;

    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException;;
}
