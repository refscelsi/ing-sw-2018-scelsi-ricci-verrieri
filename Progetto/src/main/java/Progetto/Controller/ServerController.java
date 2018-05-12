package Progetto.Controller;

import Progetto.Model.Match;

import javax.swing.text.View;

public class ServerController implements Observer {
    private Match match;
    private View view;

    public ServerController(Match match, View view){
        this.match=match;
        this.view=view;
    }

    @Override
    public void update() {

    }

    /*
    public void handle(Dice dice, Box box){
        match.usedice(dice,box)}

    public void handle (Toolcard card){
        match.useCard(card);
    }

    etc..

    
     */



}

