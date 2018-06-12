package it.polimi.ing.sw.network.server.rmi;

import it.polimi.ing.sw.network.controller.RemotePlayer;
import it.polimi.ing.sw.network.controller.UpdateStates;
import it.polimi.ing.sw.network.protocol.rmi.RMIClientInterface;
import it.polimi.ing.sw.network.protocol.rmi.RMIServerInterface;
import it.polimi.ing.sw.network.server.IServer;

/**
 * Estende {@link AbstractServer} per consentire di implementare la
 * comunicazione Client/Server con i {@link RMIClient}.
 */
public class RMIServer implements RMIServerInterface {

    private IServer controller;

    public RMIServer(IServer controller){
        this.controller=controller;
    }

    public IServer getController() {
        return controller;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati dal Client (vedi RMIServerInterface)
    /////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void sendLoginRequest(String nickname, RMIClientInterface player) {
        getController().loginPlayer(nickname,(RemotePlayer) player);
    }

    @Override
    public void sendMoveDiceRequest(UpdateStates update) {

    }

    @Override
    public void sendActionRequest(String actionName, RemotePlayer remotePlayer) {
        remotePlayer.getGame().handleRequest(actionName,remotePlayer);
    }


}
