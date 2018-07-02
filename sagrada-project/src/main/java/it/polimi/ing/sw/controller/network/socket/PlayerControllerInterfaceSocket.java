package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;
import com.google.gson.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.lang.String.valueOf;

//classe che viene istanziata come controller in caso di socket e chiama i metodi di PlayerControllerSocket a cui passa
//i dati impacchettati in file json


public class PlayerControllerInterfaceSocket implements PlayerControllerInterface {
    private final ObjectOutputStream out;



    public PlayerControllerInterfaceSocket(String nickname, Socket socket) throws IOException {
        this.out= new ObjectOutputStream(socket.getOutputStream());
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.CONNECT, nickname));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void joinMatch() throws IOException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.JOINMATCH));
        try {
            out.writeObject(json);
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void checkAllReady() {System.out.println("lato client");
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.CHECKREADY));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setChosenScheme(int id) throws NetworkException, NotValidPlayException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.SETCHOSENSCHEME,id));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) throws NetworkException, NotValidException, NotValidPlayException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.USEDICEREQUEST, indexOfDiceInDraftPool,row,col));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void endTurn() throws NetworkException, NotValidPlayException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.ENDTURN));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) throws NetworkException, NotValidException, NotValidPlayException, RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.TOOLCARD, null));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}





