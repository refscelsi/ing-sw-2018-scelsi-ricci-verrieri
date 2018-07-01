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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.ArrayList;

import static java.lang.String.format;
import static java.lang.String.valueOf;
import com.google.gson.*;
public class PlayerControllerSocket implements RemotePlayer {
    private ObjectOutputStream out;
    private Socket clientSocket;
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
                Gson gson = new Gson();
                Data data=gson.fromJson(input, Data.class);
                String method=data.getMethod();
                ArrayList<String > params= data.getParams();
                handleInput(method, params);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }  catch (IOException e) {
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

            case Constants.JOINMATCH: controller.joinMatch();
                break;

            case Constants.CHECKREADY: controller.checkAllReady();
                System.out.println("t'appo");
                break;

            case Constants.SETCHOSENSCHEME: controller.setChosenScheme(Integer.valueOf(params.get(0)));
                break;

            case Constants.ENDTURN: controller.endTurn();
                break;

            case Constants.USEDICEREQUEST: controller.sendUseDiceRequest(Integer.valueOf(params.get(0)),Integer.valueOf(params.get(1)),Integer.valueOf(params.get(2)) );
                break;

            case Constants.TOOLCARD: controller.useToolCard(Integer.valueOf(params.get(0)),Integer.valueOf(params.get(1)),Integer.valueOf(params.get(2)),Integer.valueOf(params.get(3)),Integer.valueOf(params.get(4)),Integer.valueOf(params.get(5)),Integer.valueOf(params.get(6)));
                break;
        }

    }

    @Override
    public void onSchemeToChoose(Match match) throws RemoteException, NotValidPlayException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONSCHEMETOCHOOSE);
        matchToSend.setMatch(match);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccess(String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONSCHEMETOCHOOSE);
        matchToSend.setMessage(message);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGameUpdate(Match match) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONGAMEUPDATE);
        matchToSend.setMatch(match);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTurnEnd() throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONTURNEND);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onGameEnd(Match match) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONGAMEEND);
        matchToSend.setMatch(match);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onSetPlaying() throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONSETPLAYING);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onOtherInfoToolCard(int id) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONOTHERINFOTOOLCARD);
        matchToSend.setId(id);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
