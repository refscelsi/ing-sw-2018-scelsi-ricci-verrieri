package it.polimi.ing.sw.controller.network.Socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.controller.PlayerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

//classe che viene istanziata come controller in caso di socket e chiama i metodi di PlayerControllerSocket a cui passa
//i dati impacchettati in file json


public class PlayerInterfaceSocket implements PlayerInterface {
    private Socket clientSocket;
    private final ObjectOutputStream out;


    public  PlayerInterfaceSocket(Socket clientSocket) throws IOException {
        this.clientSocket=clientSocket;
        out=new ObjectOutputStream(clientSocket.getOutputStream());
    }



    public void joinMatch() throws IOException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data("joinMatch", null));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkAllReady() {
        Gson gson= new Gson();
        String json=gson.toJson(new Data("checkAllReady", null));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setChosenScheme(int id) throws NetworkException, NotValidPlayException {
        Gson gson= new Gson();
        ArrayList<Integer> par=new ArrayList<Integer>(Arrays.asList(new Integer[]{id}));
        String json=gson.toJson(new Data("setChosenScheme", null));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void endTurn() throws NetworkException, NotValidPlayException {

    }

    public void sendUseToolCard1Request(int indexInDraftPool, String operation) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void sendUseToolCard234Request(int id, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void useToolCard6(int indexInDraftPool) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void useToolCard5(int indexInDraftPool, int round, int indexInRound) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void useToolCard78(int id) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void sendUseToolCard9Request(int dice, int row, int col) throws NetworkException, NotValidException, NotValidPlayException {

    }

    public void useToolCard10(int dice) throws NetworkException, NotValidPlayException, NotValidException {

    }

    public void useToolCard11(int dice) throws NetworkException, NotValidPlayException, NotValidException {

    }


}

class Data{
    String name;
    ArrayList<Integer> params;

    Data(String name, ArrayList<Integer> params){
        this.name=name;
        this.params=params;
    }


}
