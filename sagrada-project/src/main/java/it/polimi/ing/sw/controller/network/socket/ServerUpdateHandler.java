package it.polimi.ing.sw.controller.network.socket;

import it.polimi.ing.sw.client.View;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.util.Constants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class ServerUpdateHandler implements Runnable {
    private View view;
    private Socket socket;
    private Match match;

    public ServerUpdateHandler(View view, Socket socket){
        this.view = view;
        this.socket = socket;
    }

    @Override
    public void run() {
        try (ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
            while (true) {
                MatchToSend matchToSend = (MatchToSend) in.readObject();
                String method = matchToSend.getMethod();
                this.match=matchToSend.getMatch();
                switch (method) {
                    case Constants.ONTURNEND: {
                        view.onTurnEnd();
                        break;
                    }
                    case Constants.ONSUCCES: {
                        try {
                            view.onSuccess(matchToSend.getMessage());
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
                    default:
                        System.out.println("Ho ricevuto un oggetto che non sono stato in grado di interpretare");
                }
            }
            } catch(IOException e){
            } catch(ClassNotFoundException e){
            }
        }

}
