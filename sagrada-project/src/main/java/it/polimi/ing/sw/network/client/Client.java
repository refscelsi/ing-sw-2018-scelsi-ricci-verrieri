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

    public void startClient() {

    }

    public void startRMIClient() {

    }
    /////////////////////////////////////////////////////////////////////////////////////////
    // Metodi invocati sul Client Controller (vedi RMIClient, SocketClient)
    ////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onGameUpdate(UpdateStates update) {

    }


    /////////////////////////////////////////////////////////////////////////////////////////
    // "Senders" (per l'invio di informazioni verso il Server, in Remoto).
    /////////////////////////////////////////////////////////////////////////////////////////



}
