package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.io.IOException;
import java.rmi.RemoteException;

/**
 * interfaccia implementata sia da PlayerControllerSocketClient sia da PlayerControllerInterfaceRMI,
 * contiene i metodi chiamati da View a Controller e garantisce un ulteriore livello di trasparenza della rete.
 */

public interface PlayerControllerInterface {

    void joinMatch() throws IOException;

    void checkAllReady() throws RemoteException;

    void setChosenScheme(int id) throws RemoteException;

    void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws RemoteException;

    void endTurn() throws RemoteException;

    void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws RemoteException;

    void stopPlayer() throws RemoteException;

    void login(String nickname) throws RemoteException;

    void reconnectPlayer() throws RemoteException;

    void startingMyTurn() throws RemoteException;
}


