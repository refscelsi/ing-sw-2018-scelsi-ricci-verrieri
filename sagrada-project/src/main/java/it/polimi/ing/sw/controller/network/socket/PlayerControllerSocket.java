package it.polimi.ing.sw.controller.network.socket;

//classe che riceve i pacchetti da socket e chiama i metodi di PlayerController

import it.polimi.ing.sw.controller.LoginController;
import it.polimi.ing.sw.controller.PlayerController;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidNicknameException;
import it.polimi.ing.sw.util.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class PlayerControllerSocket implements RemotePlayer, Runnable {
    private ObjectOutputStream out;
    private Socket clientSocket;
    private PlayerController controller;
    private LoginController loginController;
    private JSONParser parser= new JSONParser();
    private JSONObject jsonObject;


    public PlayerControllerSocket(Socket clientSocket, LoginController loginController) {
        try {
            this.loginController= loginController;
            this.out= new ObjectOutputStream(clientSocket.getOutputStream());
            this.clientSocket = clientSocket;
        } catch (IOException e) {
            try {
                controller.stopPlayer();
            } catch ( RemoteException e1 ) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        int i =0;
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            while (true) {
                String input = (String) in.readObject();
                System.out.println(input);
                try {
                    jsonObject = (JSONObject) parser.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String method= (String) jsonObject.get("method");
                switch (method) {
                    case Constants.CONNECT:
                        this.controller = loginController.connectSocket(this);
                        break;
                    case Constants.LOGIN:
                        String nickname = (String ) jsonObject.get("nickname");
                        controller.login(nickname);
                        break;
                    case Constants.JOINMATCH:
                        controller.joinMatch();
                        break;
                    case Constants.CHECKREADY:
                        controller.checkAllReady();
                        break;
                    case Constants.SETCHOSENSCHEME:
                        int id= ((Long) jsonObject.get("id")).intValue();
                        controller.setChosenScheme(id);
                        break;
                    case Constants.ENDTURN:
                        controller.endTurn();
                        break;
                    case Constants.USEDICEREQUEST:
                        int indexDiceDraftPool= Integer.valueOf (((Long) jsonObject.get("indexDice")).intValue());
                        int row= ((Long) jsonObject.get("row")).intValue();
                        int col= ((Long) jsonObject.get("col")).intValue();
                        System.out.println("mando richiesta dado");
                        controller.sendUseDiceRequest(indexDiceDraftPool,row,col);
                        break;
                    case Constants.TOOLCARD:
                        int idCard =Integer.valueOf (((Long) jsonObject.get("id")).intValue());
                        int dice= ((Long) jsonObject.get("dice")).intValue();
                        int operation=((Long) jsonObject.get("operation")).intValue();
                        int sourceRow= ((Long) jsonObject.get("row")).intValue();
                        int sourceCol=((Long) jsonObject.get("col")).intValue();
                        int destRow=((Long) jsonObject.get("destRow")).intValue();
                        int destCol=((Long) jsonObject.get("destCol")).intValue();
                        controller.useToolCard(idCard,dice,operation,sourceRow,sourceCol,destRow,destCol);
                        break;
                    case Constants.STOPPLAYER:

                     default: throw new IOException();
                }
            }
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
            try {
                controller.stopPlayer();
            } catch ( RemoteException e1 ) {
                System.out.println("ma che sbatti");
            }
        }

    }

    @Override
    public void onLogin(String nickname) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend("onLogin", nickname);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }


    @Override
    public void onSchemeToChoose(Match match) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONSCHEMETOCHOOSE, match);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onSuccess(String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONSUCCES);
        matchToSend.setMessage(message);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();

        }
    }

    @Override
    public void onGameUpdate(Match match) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONGAMEUPDATE,match);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onTurnEnd() throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONTURNEND);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onGameEnd(Match match) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONGAMEEND, match);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }


    @Override
    public void onSetPlaying() throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONSETPLAYING);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
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
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidUseDiceException(String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONNOTVALIDUSEDICEEXCEPTION, message);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidToolCardException(int id, String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONNOTVALIDTOOLCARDEXCEPTION, message, id);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidPlayException(String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONNOTVALIDPLAYEXCEPTION, message);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidNicknameException(String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONNOTVALIDNICKNAMEEXCEPTION, message);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotPossibleConnectionException(String message) throws RemoteException {
        MatchToSend matchToSend=new MatchToSend(Constants.ONNOTPOSSIBLECONNECTIONEXCEPTION, message);
        try {
            out.writeObject(matchToSend);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }

    }


}
