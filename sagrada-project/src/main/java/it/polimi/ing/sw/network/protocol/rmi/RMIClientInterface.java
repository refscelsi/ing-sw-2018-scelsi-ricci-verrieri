package it.polimi.ing.sw.network.protocol.rmi;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.SchemeCard;
import it.polimi.ing.sw.model.objectiveCard.ObjectiveCard;
import it.polimi.ing.sw.network.controller.UpdateStates;

import java.rmi.Remote;
import java.util.ArrayList;
import java.util.List;


/**
 * Interfaccia remota per eseguire Invocazione a Metodi Remoti da SERVER a
 * CLIENT.
 */

public interface RMIClientInterface extends Remote {

//tutte le NOTIFY
    public void notifyGameUpdate(UpdateStates update);

}