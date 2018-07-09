package it.polimi.ing.sw.controller.network.socket;

import com.google.gson.Gson;
import it.polimi.ing.sw.controller.PlayerControllerInterface;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

import static java.lang.String.valueOf;

/**
 * Classe che viene istanziata come controller lato Client in caso di socket e chiama i metodi di PlayerControllerSocketServer a cui passa
 * i dati impacchettati in file json.
 *
 * Tutti metodi di questa classe lanciano e cattura IOException. Nel caso venga catturata tale eccezione,
 * poichè significa caduta di connessione lato Server viene notificato il Client e poi viene chiusa la connessione.
 */


public class PlayerControllerSocketClient implements PlayerControllerInterface {

    /**
     * riferimento all'OutputStream
     */
    private final ObjectOutputStream out;


    /**
     * Quando il client si connette in Socket, viene istanziato un PlayerControllerSocketClient, per l'invio
     * dei messaggi da Client a Server, al cui costruttore viene passato il riferimento al socket.
     * Viene subito chiamato il metodo per richiedere la connessione e istanziare lato Server un PlayerControllerSocketServer
     * che riceva i messaggi del Client
     * @param socket
     * @throws IOException
     */
    public PlayerControllerSocketClient(Socket socket) throws IOException {
        this.out= new ObjectOutputStream(socket.getOutputStream());
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.CONNECT));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    /**
     * Metodi di PlayerControllerInterface.
     *Per ogni metodo viene creato un Json di tipo @MessageFromClient, contenente il nome del metodo e gli eventuali parametri
     * del metodo stesso.
     */

    @Override
    public void joinMatch() throws IOException {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.JOINMATCH));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void checkAllReady() {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.CHECKREADY));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    public void setChosenScheme(int id) {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.SETCHOSENSCHEME,id));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    public void sendUseDiceRequest(int indexOfDiceInDraftPool, int row, int col) {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.USEDICEREQUEST, indexOfDiceInDraftPool,row,col));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    public void endTurn() {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.ENDTURN));
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
        String json=gson.toJson(new MessageFromClient(Constants.TOOLCARD, id, dice, operation, sourceRow, sourceCol, destRow, destCol));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void stopPlayer() throws RemoteException {
        Gson gson=new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.STOPPLAYER));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void login(String nickname) throws RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.LOGIN, nickname));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void reconnectPlayer() throws RemoteException {
        Gson gson= new Gson();
        String json=gson.toJson(new MessageFromClient(Constants.RECONNECT));
        try {
            out.writeObject(json);
            out.flush();
        } catch (IOException e) {
            stopMatch();
        }
    }

    @Override
    public void startingMyTurn() throws RemoteException {
        Gson gson=new Gson();
        String json= gson.toJson(new MessageFromClient(Constants.STARTINGTURN));
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





