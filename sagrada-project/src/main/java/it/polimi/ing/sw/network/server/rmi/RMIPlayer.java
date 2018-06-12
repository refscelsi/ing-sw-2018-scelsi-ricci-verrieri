package it.polimi.ing.sw.network.server.rmi;


import it.polimi.ing.sw.network.controller.RemotePlayer;
import it.polimi.ing.sw.network.controller.UpdateStates;
import it.polimi.ing.sw.network.protocol.rmi.RMIClientInterface;

/**
 * Estende {@link RemotePlayer} implementando le funzionalita' di comunicazione
 * al {@link Giocatore} Client associatogli.
 **/

public class RMIPlayer extends RemotePlayer {
    private transient RMIClientInterface clientInterface;

    public RMIPlayer(RMIClientInterface playerInterface, String nickname) {
        super(nickname);
        clientInterface = playerInterface;
    }

    //poichè estende RemotePlayer chiama gli on

    public void onGameUpdate(UpdateStates updateStates){
        clientInterface.notifyGameUpdate(updateStates);
    }

}
