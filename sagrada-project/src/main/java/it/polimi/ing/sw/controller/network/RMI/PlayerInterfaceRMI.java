package it.polimi.ing.sw.controller.network.RMI;

import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interfaccia per chiamare i metodi del controller in RMI

public interface PlayerInterfaceRMI extends Remote, PlayerInterface {

    void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException;

    void checkAllReady() throws RemoteException, NotValidPlayException;

    void setChosenScheme(int id) throws NetworkException, RemoteException, NotValidPlayException;

    void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    void endTurn() throws NetworkException, RemoteException, NotValidPlayException;

    void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    void useToolCard78(int id) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    void sendUseToolCard9Request(int dice, int row, int col) throws NetworkException, NotValidException, RemoteException, NotValidPlayException;

    void useToolCard10(int dice) throws NetworkException, RemoteException, NotValidPlayException, NotValidException;

    void useToolCard11(int dice) throws NetworkException, RemoteException, NotValidPlayException, NotValidException;
}
