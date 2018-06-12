package it.polimi.ing.sw.network.server;

import it.polimi.ing.sw.network.controller.RemotePlayer;
import it.polimi.ing.sw.network.server.exceptions.*;

import java.rmi.Remote;
/**
 * Interfaccia usata come server controller in {@link AbstractServer} (invio di
 * richieste al server da parte del client).
 */
public interface IServer{

    public void loginPlayer(String nickname, RemotePlayer remotePlayer);

    public void joinMatch();

    public void exitMatch();

}
