package it.polimi.ing.sw.controller.network.socket;

import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.RemoteException;

/**
 * Classe che gestisce gli aggiornamenti da Server (PlayerControllerSocketServer) a Client in caso di connessione Socket, lato Client.
 * Riceve dei messaggi di tipo MessageFromServer, da cui estrapola le informazioni necessarie per chiamare i metodi della View, a cui
 * ha un riferimento.
 *
 * Tutti metodi di questa classe lanciano e cattura IOException. Nel caso venga catturata tale eccezione,
 * poichè significa caduta di connessione lato Server viene notificato il Client e poi viene chiusa la connessione.
 */


public class ServerUpdateHandler implements Runnable {
    /**
     * Riferimento alla View, sui cui chiamare i metodi
     */
    private View view;
    /**
     * riferimento al socket da cui si ricevono i messaggi.
     */
    private Socket socket;
    /**
     * Attributo di tipo Match in cui viene salvato il clone ricevuto dal Server, in caso sia necessario alla View per gli
     * aggiornamenti
     */
    private Match match;

    /**
     * Costruttore a cui passo il riferimento alla View associata e al socket associato
     * @param view
     * @param socket
     */
    public ServerUpdateHandler(View view, Socket socket){
        this.view = view;
        this.socket = socket;
    }

    /**
     * Metodo per ricevere in continuazione i messaggi del Server, in base al nome del metodo vengono
     * chiamati diversi aggiornamenti sulla View
     */
    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                MessageFromServer messageFromServer = (MessageFromServer) in.readObject();
                String method = messageFromServer.getMethod();
                this.match= messageFromServer.getMatch();
                switch (method) {
                    case Constants.ONTURNEND: {
                        view.onTurnEnd();
                        break;
                    }
                    case Constants.ONSUCCES: {
                        try {
                            view.onSuccess(messageFromServer.getMessage());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                    case Constants.ONSETPLAYING: {
                        view.onSetPlaying();
                        break;
                    }
                    case Constants.ONSCHEMETOCHOOSE: {
                        view.onSchemeToChoose(match);
                        break;
                    }
                    case Constants.ONGAMEUPDATE: {
                        view.onGameUpdate(match);
                        break;
                    }
                    case Constants.ONGAMEEND: {
                        view.onGameEnd(match);
                        break;
                    }
                    case Constants.ONNOTPOSSIBLECONNECTIONEXCEPTION: {
                        view.onNotPossibleConnectionException(messageFromServer.getMessage());
                        break;
                    }

                    case Constants.ONNOTVALIDNICKNAMEEXCEPTION:{
                        view.setLogin(false);
                        view.onNotValidNicknameException(messageFromServer.getMessage());
                        break;
                    }

                    case Constants.ONNOTVALIDPLAYEXCEPTION: {
                        view.onNotValidPlayException(messageFromServer.getMessage());
                        break;
                    }

                    case Constants.ONNOTVALIDUSEDICEEXCEPTION: {
                        view.onNotValidUseDiceException(messageFromServer.getMessage());
                        break;
                    }

                    case Constants.ONNOTVALIDTOOLCARDEXCEPTION: {
                        view.onNotValidToolCardException(messageFromServer.getId(), messageFromServer.getMessage());
                        break;
                    }

                    case Constants.ONLOGIN:
                        view.setLogin(true);
                        view.onLogin(messageFromServer.getMessage());
                        break;
                    case Constants.ONPLAYERDISCONNECT:
                        view.onPlayerDisconnection(messageFromServer.getMessage());
                        break;
                    case Constants.ONOTHERINFOTOOLCARD:
                        view.onOtherInfoToolCard(messageFromServer.getId());
                        break;
                    default:
                        System.out.println("Ho ricevuto un oggetto che non sono stato in grado di interpretare");
                }
            }
        } catch(IOException e){
        } catch(ClassNotFoundException e){
        }
    }
}
