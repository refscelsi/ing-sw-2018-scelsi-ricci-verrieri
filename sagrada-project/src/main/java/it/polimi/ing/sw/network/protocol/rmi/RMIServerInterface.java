package it.polimi.ing.sw.network.protocol.rmi;

import it.polimi.ing.sw.network.client.rmi.RMIClient;
import it.polimi.ing.sw.network.controller.RemotePlayer;
import it.polimi.ing.sw.network.controller.UpdateStates;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaccia remota per eseguire Invocazione a Metodi Remoti da CLIENT a
 * SERVER.
 */

public interface RMIServerInterface extends Remote {

    //tutte le REQUEST

    public void sendLoginRequest(String nickname, RMIClientInterface player);

    public void sendMoveDiceRequest(UpdateStates update);

    public void sendActionRequest(String actionName, RemotePlayer remotePlayer);

}
