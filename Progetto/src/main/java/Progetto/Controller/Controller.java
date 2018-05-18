package Progetto.Controller;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.ToolCard.ToolCard;
import Progetto.View.*;

import javax.swing.text.View;

public class Controller {

    private Match match;
    private View view;       // ci va?
    private Player player;

    public Controller(Match match, View view, Player player){
        this.match=match;
        //view.registerObserver(this);
        this.view=view;        // se non ci va lo cambio col comando sopra
        this.player=player;
    }

    public void changePlayer (String changePlayer) {
        if (changePlayer.equals("change player"))
            match.changePlayer();
    }

    public void setScheme (Scheme scheme) {  // il giocatore sceglie lo schema
        player.setScheme(scheme);
        player.setNumOfToken(player.getScheme().getDifficulty());
    }

    public void useDice (Dice dice, Box box) throws NotValidException {  // il giocatore usa un dado
        match.useDice(box, dice, player);
    }

    public void useToolCard (ToolCard toolCard) {

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

    // aggiungo una riga per committare e provare a sovrascrivere l'ultimo commit di Arianna involontario



}


