package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

//classe che viene istanziata come controller in caso di socket e chiama i metodi di PlayerControllerSocket a cui passa
//i dati impacchettati in file json


public class PlayerControllerInterfaceSocket implements PlayerControllerInterface {
    private View view;
    private final Socket clientSocket;
    private final ObjectOutputStream out;


    public PlayerControllerInterfaceSocket(String nickname, View view) throws IOException {
        this.clientSocket= new Socket("localhost", Constants.SOCKET_PORT);
        this.out= new ObjectOutputStream(clientSocket.getOutputStream());
        new Thread(()->{
            serverUpdateHandler();
        }).start();

        Gson gson= new Gson();
        String json=gson.toJson(new Data("connectSocket", new ArrayList<>(Collections.singletonList(nickname))));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void serverUpdateHandler() {
        try {
            ObjectInputStream in= new ObjectInputStream(clientSocket.getInputStream());
            while (true){
                String input=(String) in.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
            } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        ArrayList par=new ArrayList(Arrays.asList("" + id));
        String json=gson.toJson(new Data("setChosenScheme", par));
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
    String method;
    ArrayList<String> params;

    Data(String method, ArrayList<String> params){
        this.method=method;
        this.params=params;
    }


}
