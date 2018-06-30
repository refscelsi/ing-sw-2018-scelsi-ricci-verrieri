package it.polimi.ing.sw.controller.network.socket;

//classe che riceve i pacchetti da socket e chiama i metodi di PlayerController

import it.polimi.ing.sw.controller.LoginController;
import it.polimi.ing.sw.controller.PlayerController;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class PlayerControllerSocket implements RemotePlayer {
    private ObjectOutputStream out;
    private Socket clientSocket;
    private JSONParser parser= new JSONParser();
    private JSONObject jsonObject;
    private PlayerController controller;
    private LoginController loginController;


    public PlayerControllerSocket(Socket clientSocket, LoginController loginController) {
        try {
            this.loginController= loginController;
            this.out= new ObjectOutputStream(clientSocket.getOutputStream());
            handleSocket(clientSocket);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void handleSocket(Socket socket) {
        try(ObjectInputStream in= new ObjectInputStream( socket.getInputStream())) {
            while (true) {
                String input = (String) in.readObject();
                jsonObject = (JSONObject) parser.parse(input);
                String method= (String) jsonObject.get("method");
                ArrayList<String > params= (ArrayList<String>) jsonObject.get("params");
                handleInput(method, params);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ToolCardException e) {
            e.printStackTrace();
        } catch (NotValidException e) {
            e.printStackTrace();
        } catch (NotValidPlayException e) {
            e.printStackTrace();
        } catch (NetworkException e) {
            e.printStackTrace();
        }
    }


    public void handleInput(String method, ArrayList<String> params) throws RemoteException, NotValidException, ToolCardException, NotValidPlayException, NetworkException {

        switch (method){
            case "joinMatch": controller.joinMatch();
            break;

            case "checkAllReady": controller.checkAllReady();
            break;

            case "setChosenScheme": controller.setChosenScheme(Integer.valueOf(params.get(0)));
            break;

            case "connectSocket":
                String nickname= params.get(0);
                try {
                    this.controller = loginController.connectSocket(nickname, this  );
                } catch (NotPossibleConnection notPossibleConnection) {
                    notPossibleConnection.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onSchemeToChoose(Match match) throws RemoteException, NotValidPlayException {

    }

    @Override
    public void onSuccess(String message) throws RemoteException {

    }

    @Override
    public void onGameUpdate(Match match) throws RemoteException {

    }

    @Override
    public void onTurnEnd() throws RemoteException {

    }

    @Override
    public void onGameEnd(Match match) throws RemoteException {

    }

    @Override
    public void onPlayerLogged() throws RemoteException {
    }

    @Override
    public void onSetPlaying() throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard4(Match match) throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard11(Match match) throws RemoteException {

    }
}
