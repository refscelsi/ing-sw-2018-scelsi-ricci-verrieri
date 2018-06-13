package it.polimi.ing.sw.controller;

import it.polimi.ing.sw.model.Match;

public class Controller implements ControllerInterface{

    private Match match;

    public Controller(Match match){
        this.match=match;
    }

    @Override
    public void loginPlayer(String nickname) {

    }
}
