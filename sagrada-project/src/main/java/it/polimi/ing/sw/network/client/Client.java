package it.polimi.ing.sw.network.client;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.SchemeCard;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.network.controller.UpdateStates;

import java.util.ArrayList;

/**
 *
 * Client
 *
 */

public class Client implements IClient{
    private int indice;
    private IClient cli;
    private AbstractClient client;

    public void startClient() {

    }

    public void startRMIClient() {

    }
    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati sul Client Controller (vedi RMIClient, SocketClient)
    ////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onGameUpdate(UpdateStates update) {
        this.indice=update.getIndicePlayer();
    }

    public void onAnotherUpdate(UpdateStates update){
        cli.onAnotherUpdate(update);
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    // "Senders" (per l'invio di informazioni verso il Server, in Remoto).
    /////////////////////////////////////////////////////////////////////////////////////////

    public void moveDice(UpdateStates update){
        client.sendActionRequest();
    }

}
