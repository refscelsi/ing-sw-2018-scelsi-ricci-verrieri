package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayerInterface extends Remote {

    public void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException;

    public void checkAllReady() throws RemoteException, NotValidPlayException;

    public void setChosenScheme (int id) throws NetworkException, RemoteException, NotValidPlayException;

    public void sendUseDiceRequest (int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    public void endTurn () throws NetworkException, RemoteException, NotValidPlayException;

    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    public void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    public void useToolCard78(int id) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;
}
