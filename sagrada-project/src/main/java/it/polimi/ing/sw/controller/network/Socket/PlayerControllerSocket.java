package it.polimi.ing.sw.controller.network.socket;

//classe che riceve i pacchetti da socket e chiama i metodi di PlayerController

import com.google.gson.Gson;
import it.polimi.ing.sw.controller.LoginController;
import it.polimi.ing.sw.controller.PlayerController;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotPossibleConnection;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NetworkException;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;
import it.polimi.ing.sw.util.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static java.lang.String.valueOf;

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
            case "connectSocket":
                String nickname= params.get(0);
                try {
                    this.controller = loginController.connectSocket(nickname, this  );
                } catch (NotPossibleConnection notPossibleConnection) {
                    notPossibleConnection.printStackTrace();
                }
                break;

            case "joinMatch": controller.joinMatch();
                break;

            case Constants.CHECKREADY: controller.checkAllReady();
                break;

            case Constants.SETCHOSENSCHEME: controller.setChosenScheme(Integer.valueOf(params.get(0)));
                break;

            case Constants.ENDTURN: controller.endTurn();
                break;

            case Constants.USEDICEREQUEST: controller.sendUseDiceRequest(Integer.valueOf(params.get(0)),Integer.valueOf(params.get(1)),Integer.valueOf(params.get(2)) );
                break;

            case Constants.TOOLCARD: controller.sendUseToolCard(Integer.valueOf(params.get(0)), params.get(1));
                break;
        }

    }

    @Override
    public void onSchemeToChoose(Match match) throws RemoteException, NotValidPlayException {
        String matchToSend= new MatchToSend(match).convertStartMatch();
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.ONSCHEMETOCHOOSE, matchToSend));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(String message) throws RemoteException {

    }

    @Override
    public void onGameUpdate(Match match) throws RemoteException {
        String matchToSend= new MatchToSend(match).convertMatch();
        Gson gson=new Gson();
        String json= gson.toJson(new Data(Constants.ONGAMEUPDATE, matchToSend));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTurnEnd() throws RemoteException {

    }

    @Override
    public void onGameEnd(Match match) throws RemoteException {

    }

    @Override
    public void onPlayerLogged() throws RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new SimpleData("onPlayerLogged"));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSetPlaying() throws RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new Data(Constants.ONSETPLAYING, null));
        try {
            out.writeObject(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOtherInfoToolCard4(Match match) throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard11(Match match) throws RemoteException {

    }

    @Override
    public void onOtherInfoToolCard12(Match match) throws RemoteException {

    }
    class Data {
        String method;
        String match;

        Data(String method, String match) {
            this.method = method;
            this.match = match;
        }
    }

    class SimpleData{
        String method;

        SimpleData(String method){
            this.method=method;
        }
    }
}
