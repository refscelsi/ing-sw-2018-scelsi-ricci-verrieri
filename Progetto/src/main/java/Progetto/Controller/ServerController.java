package Progetto.Controller;

import Progetto.Model.*;
import Progetto.View.*;

import javax.swing.text.View;

public class ServerController implements Observer {

    private Match match;
    private View view;       // ci va?

    public ServerController(Match match, View view){
        this.match=match;
        //view.registerObserver(this);
        this.view=view;        // se non ci va lo cambio col comando sopra
    }

    @Override
    public void update() {
    }

    public void update (String changePlayer) {
        if (changePlayer.equals("change player"))
            match.changePlayer();
    }







    /*public <Object> void update(Object o) {
        switch (o) {
        }
            match.initializeTable();
            match.inizializePlayer();
        } else
            if (o.equals(State.SCHEME_CHOOSEN.toString()))
                match.startRound();

            else
                if (o.equals(State.USE_DICE.toString()))
                    //match.useDice();    devo passare dadi e


    public void handle(Dice dice, Box box){
        match.usedice(dice,box)}

    public void handle (Toolcard card){
        match.useCard(card);
    }

    etc..

     */



}

