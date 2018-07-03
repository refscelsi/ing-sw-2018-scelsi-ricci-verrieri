package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import static java.lang.String.valueOf;

//classe che viene istanziata come controller in caso di socket e chiama i metodi di PlayerControllerSocket a cui passa
//i dati impacchettati in file json


public class PlayerControllerInterfaceSocket implements PlayerControllerInterface {
    private final ObjectOutputStream out;



    public PlayerControllerInterfaceSocket(Socket socket) throws IOException {
        this.out= new ObjectOutputStream(socket.getOutputStream());
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.CONNECT));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            stopMatch();
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
            stopMatch();
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
            stopMatch();
        }
    }

    public void setChosenScheme(int id) {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.SETCHOSENSCHEME,id));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.USEDICEREQUEST, indexOfDiceInDraftPool,row,col));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    public void endTurn() {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.ENDTURN));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void useToolCard(int id, int dice, int operation, int sourceRow, int sourceCol, int destRow, int destCol) {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.TOOLCARD, null));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void stopPlayer() throws RemoteException {

    }

    @Override
    public void login(String nickname) throws RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.LOGIN, nickname));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    public void stopMatch(){
        System.out.println("Il server è caduto, la partita è finita");
        System.exit(0);
    }

}





