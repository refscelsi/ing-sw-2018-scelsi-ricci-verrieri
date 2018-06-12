package it.polimi.ing.sw.network.client.rmi;

import it.polimi.ing.sw.Client;
import it.polimi.ing.sw.network.client.IClient;
import it.polimi.ing.sw.network.controller.UpdateStates;
import it.polimi.ing.sw.network.protocol.rmi.RMIClientInterface;
import it.polimi.ing.sw.network.server.IServer;

/**
 * Classe che gestisce la connessione di rete con RMI. Estende
 * {@link AbstractClient}, implementa {@link RMIClientInterface}.
 */

public class RMIClient extends Client implements RMIClientInterface{
    String nickname;
    IServer server;
    IClient controller;

    public RMIClient(IClient controller, String address, int port) {
       this.controller=controller;
    }

    public IClient getController(){
        return this.controller;
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati dal Client (GUI) (vedi AbstractClient)
    /////////////////////////////////////////////////////////////////////////////////////////
    //metodi del gioco (scegli dado etc)



////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati dal Server (vedi RMIClientInterface)
    /////////////////////////////////////////////////////////////////////////////////////////
    //le NOTIFY
    @Override
    public void notifyGameUpdate(UpdateStates update) {
        controller.onGameUpdate(update);
    }
}