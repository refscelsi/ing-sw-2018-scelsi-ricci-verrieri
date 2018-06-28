package it.polimi.ing.sw.controller.network.Socket;

import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.rmi.RemoteException;

//classe che viene istanziata come controller in caso di socket e chiama i metodi di PlayerControllerSocket a cui passa
//i dati impacchettati in file json


public class PlayerInterfaceSocket implements PlayerInterface {
    public void joinMatch(){
        //manda solo il metodo della chiamata
    }

    public void checkAllReady(){

    }

    public void setChosenScheme(int id) throws NetworkException ,NotValidPlayException {

    }

    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void endTurn() throws NetworkException, NotValidPlayException{

    }

    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException{

    }

    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException{

    }

    public void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, NotValidPlayException{

    }

    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, NotValidPlayException{

    }

    public void useToolCard78(int id) throws NetworkException, NotValidException, NotValidPlayException{

    }

    public void sendUseToolCard9Request(int dice, int row, int col) throws NetworkException, NotValidException, NotValidPlayException{

    }

    public void useToolCard10(int dice) throws NetworkException, NotValidPlayException, NotValidException{

    }

    public void useToolCard11(int dice) throws NetworkException, NotValidPlayException, NotValidException{

    }










}
