package it.polimi.ing.sw.controller.network.RMI;

import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.rmi.Remote;
import java.rmi.RemoteException;

//interfaccia per chiamare i metodi del controller in RMI

public interface PlayerControllerInterfaceRMI extends Remote, PlayerControllerInterface {

    void joinMatch() throws RemoteException, ToolCardException, NotValidException, NotValidPlayException;

    void checkAllReady() throws RemoteException, NotValidPlayException;

    void setChosenScheme(int id) throws NetworkException, RemoteException, NotValidPlayException;

    void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    void endTurn() throws NetworkException, RemoteException, NotValidPlayException;

    void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;
}
