package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

import java.io.IOException;
import java.rmi.RemoteException;

//interfaccia implementata sia da PlayerControllerInterfaceSocket sia da PlayerControllerInterfaceRMI , controller della view
//garantisce la trasparenza della rete

public interface PlayerControllerInterface {

    void joinMatch() throws IOException, ToolCardException, NotValidException, NotValidPlayException;

    void checkAllReady() throws RemoteException, NotValidPlayException;

    void setChosenScheme(int id) throws NetworkException, RemoteException, NotValidPlayException;

    void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

    void endTurn() throws NetworkException, RemoteException, NotValidPlayException;

    void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException, RemoteException;

}


