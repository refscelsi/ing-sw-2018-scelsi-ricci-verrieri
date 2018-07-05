package it.polimi.ing.sw.controller.network.socket;

/**
 * Classe che viene istanziata come Controller lato Server in caso di connessione Socket e si occupa sia di ricevere
 * i messaggi del Client (PlayerControllerSocketClient) e inoltrarli al PlayerController, sia di ricevere gli aggiornamenti
 * dal Model (Match) e inoltrarli al Client.
 *
 * Nel primo caso legge dei Json nel secondo crea un'istanza della classe MessageFromServer, con i riferimenti alle informazioni da inviare,
 * la serializza e la inoltra al Client.
 *
 * Tutti i metodi di questa classe sollevano e catturano la IOException. In caso di cattura e quindi di disconnessione lato Client,
 * notifica il Controller di sospendere il giocatore.
 */

import it.polimi.ing.sw.controller.ConnectionController;
import it.polimi.ing.sw.controller.PlayerController;
import it.polimi.ing.sw.controller.RemotePlayer;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.util.Constants;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;
import java.util.concurrent.ConcurrentNavigableMap;

import static java.lang.String.CASE_INSENSITIVE_ORDER;
import static java.lang.String.format;
import static java.lang.String.valueOf;

public class PlayerControllerSocketServer implements RemotePlayer, Runnable {

    /**
     * riferimento all'OutputStream
     */
    private ObjectOutputStream out;
    /**
     * Riferimento al socket
     */
    private Socket clientSocket;
    /**
     * Oggetti per la creazione/traduzione dei JSON
     */
    private JSONParser parser= new JSONParser();
    private JSONObject jsonObject;
    /**
     * riferimento al PlayerController del giocatore
     */
    private PlayerController controller;
    /**
     * riferimento necessario per la connessione
     */
    private ConnectionController connectionController;



    /**
     * Costruttore della classe
     * @param clientSocket, riferimento al socket associato
     * @param connectionController, riferimento al connectionController a cui verrà richiesta la connessione
     */
    public PlayerControllerSocketServer(Socket clientSocket, ConnectionController connectionController) {
        try {
            this.connectionController = connectionController;
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

    /**
     * Metodo che rimane in attesa di messaggi da parte del Client e in base al campo "method" del Json
     * chiama diversi metodi sul PlayerController, di cui ha salvato il riferimento
     */
    @Override
    public void run() {
        int i =0;
        try (ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream())) {
            while (true) {
                String input = (String) in.readObject();
                try {
                    jsonObject = (JSONObject) parser.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String method= (String) jsonObject.get(Constants.METHOD);
                switch (method) {
                    case Constants.CONNECT:
                        this.controller = connectionController.connectSocket(this);
                        break;
                    case Constants.LOGIN:
                        String nickname = (String ) jsonObject.get(Constants.NICKNAME);
                        controller.login(nickname);
                        break;
                    case Constants.JOINMATCH:
                        controller.joinMatch();
                        break;
                    case Constants.CHECKREADY:
                        controller.checkAllReady();
                        break;
                    case Constants.SETCHOSENSCHEME:
                        int id= ((Long) jsonObject.get(Constants.ID)).intValue();
                        controller.setChosenScheme(id);
                        break;
                    case Constants.ENDTURN:
                        controller.endTurn();
                        break;
                    case Constants.USEDICEREQUEST:
                        int indexDiceDraftPool= Integer.valueOf (((Long) jsonObject.get(Constants.INDEXDICE)).intValue());
                        int row= ((Long) jsonObject.get(Constants.ROW)).intValue();
                        int col= ((Long) jsonObject.get(Constants.COL)).intValue();
                        controller.sendUseDiceRequest(indexDiceDraftPool,row,col);
                        break;
                    case Constants.TOOLCARD:
                        int idCard =Integer.valueOf (((Long) jsonObject.get(Constants.ID)).intValue());
                        int dice= ((Long) jsonObject.get(Constants.INDEXDICE)).intValue();
                        int operation=((Long) jsonObject.get(Constants.OPERATION)).intValue();
                        int sourceRow= ((Long) jsonObject.get(Constants.ROW)).intValue();
                        int sourceCol=((Long) jsonObject.get(Constants.COL)).intValue();
                        int destRow=((Long) jsonObject.get(Constants.DESTROW)).intValue();
                        int destCol=((Long) jsonObject.get(Constants.DESTCOL)).intValue();
                        controller.useToolCard(idCard,dice,operation,sourceRow,sourceCol,destRow,destCol);
                        break;
                    case Constants.STARTINGTURN:
                        controller.startingMyTurn();
                        break;
                    case Constants.RECONNECT:
                        controller.reconnectPlayer();
                        break;

                    default: throw new IOException();
                }
            }
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
        }

    }

    /**
     * Metodi chiamati dal Model per notificare la View , incapsulati insieme ai parametri in un oggetto di tipo MessageFromServer
     * e inviati al Client
     *
     * Se viene catturata una IOException, quindi la connessioni è caduta lato Client, viene chiamato il metodo per sospendere il giocatore
     */

    @Override
    public void onLogin(String nickname) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONLOGIN, nickname);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }


    @Override
    public void onSchemeToChoose(Match match) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONSCHEMETOCHOOSE, match);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onSuccess(String message) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONSUCCES);
        messageFromServer.setMessage(message);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onGameUpdate(Match match) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONGAMEUPDATE,match);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onTurnEnd() throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONTURNEND);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onGameEnd(Match match) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONGAMEEND, match);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }


    @Override
    public void onSetPlaying() throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONSETPLAYING);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onOtherInfoToolCard(int id) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONOTHERINFOTOOLCARD);
        messageFromServer.setId(id);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidUseDiceException(String message) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONNOTVALIDUSEDICEEXCEPTION, message);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidToolCardException(int id, String message) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONNOTVALIDTOOLCARDEXCEPTION, message, id);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidPlayException(String message) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONNOTVALIDPLAYEXCEPTION, message);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotValidNicknameException(String message) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONNOTVALIDNICKNAMEEXCEPTION, message);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }

    @Override
    public void onNotPossibleConnectionException(String message) throws RemoteException {
        MessageFromServer messageFromServer =new MessageFromServer(Constants.ONNOTPOSSIBLECONNECTIONEXCEPTION, message);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }

    }

    @Override
    public void onPlayerDisconnection(String nickname) throws RemoteException {
        MessageFromServer messageFromServer= new MessageFromServer((Constants.ONPLAYERDISCONNECT),nickname);
        try {
            out.writeObject(messageFromServer);
            out.flush();
        } catch (IOException e) {
            controller.stopPlayer();
        }
    }


}
