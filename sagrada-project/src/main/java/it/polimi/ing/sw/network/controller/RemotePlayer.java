package it.polimi.ing.sw.network.controller;

import it.polimi.ing.sw.model.Player;

//mi serve davvero??

public abstract class RemotePlayer extends Player {
    private boolean isOnline;
    private transient Game game;

    public RemotePlayer(String nickname) {
        super(nickname);
        setOnline(true);
    }

    public Game getGame() {
        return this.game;
    }

    public void setOnline(boolean online) {
        this.isOnline = online;
    }


    public abstract void onGameUpdate(UpdateStates update);
}
