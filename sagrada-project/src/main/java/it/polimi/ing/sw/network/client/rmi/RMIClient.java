package it.polimi.ing.sw.network.client.rmi;

import it.polimi.ing.sw.Client;
import it.polimi.ing.sw.network.client.AbstractClient;
import it.polimi.ing.sw.network.client.IClient;
import it.polimi.ing.sw.network.controller.UpdateStates;
import it.polimi.ing.sw.network.protocol.rmi.RMIClientInterface;
import it.polimi.ing.sw.network.protocol.rmi.RMIServerInterface;
import it.polimi.ing.sw.network.server.IServer;

/**
 * Classe che gestisce la connessione di rete con RMI. Estende
 * {@link AbstractClient}, implementa {@link RMIClientInterface}.
 */

public class RMIClient extends AbstractClient implements RMIClientInterface{
    String nickname;
    /**
     * Interfaccia remota del client che verra' memorizzata dal server per la
     * comunicazione SERVER-->CLIENT.
     */
    private RMIServerInterface server;

    //client controller
    IClient controller;

    RMIClient(IClient controller, String address, int port) {
        super(controller,address,port);
    }

    public IClient getController(){
        return this.controller;
    }
    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati dal Client (GUI) (vedi AbstractClient)
    /////////////////////////////////////////////////////////////////////////////////////////
    //metodi del gioco (scegli dado etc)

    @Override
    public void connect() {

    }

    @Override
    public void sendLoginRequest() {
        server.sendLoginRequest(nickname,this);
    }

    @Override
    public void sendActionRequest() {
        //server.sendActionRequest();
    }




////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati dal Server (vedi RMIClientInterface)
    /////////////////////////////////////////////////////////////////////////////////////////
    //le NOTIFY
    @Override
    public void notifyGameUpdate(UpdateStates update) {
        controller.onGameUpdate(update);
    }
}