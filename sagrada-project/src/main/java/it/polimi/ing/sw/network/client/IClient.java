package it.polimi.ing.sw.network.client;

import it.polimi.ing.sw.network.controller.UpdateStates;

import java.util.ArrayList;

/**
 * Interfaccia usata come client controller in {@link AbstractClient} (notifiche
 * dal server verso il client stesso).
 */

//tutti i metodi onModifica..


public interface IClient {

    void onGameUpdate(UpdateStates update);

    void onAnotherUpdate(UpdateStates update);
}
