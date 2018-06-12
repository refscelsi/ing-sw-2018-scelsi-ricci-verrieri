package it.polimi.ing.sw.network.controller;

import it.polimi.ing.sw.model.Match;

public class Game {

    private Match match;

    private int i;

    public void handleRequest(String actionName, RemotePlayer remotePlayer){
        if(actionName.equals("passTurn")){
            i=match.changePlayer();
        }

        UpdateStates updateStates= new UpdateStates(i);
        remotePlayer.onGameUpdate(updateStates);
    }
}
