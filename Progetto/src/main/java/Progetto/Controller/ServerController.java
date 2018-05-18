package Progetto.Controller;

import Progetto.Model.*;
import Progetto.View.*;

import javax.swing.text.View;

public class ServerController {

    private Match match;
    private View view;       // ci va?

    public ServerController(Match match, View view){
        this.match=match;
        //view.registerObserver(this);
        this.view=view;        // se non ci va lo cambio col comando sopra
    }

    //@Override
    public void update() {
    }

    /*@Override
    public <Object> void update(Object o) {
        if (o.equals(State.START_MATCH.toString())) {
            match.initializeTable();
            match.inizializePlayer();
        } else
            if (o.equals(State.SCHEME_CH OOSEN.toString()))
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


<<<<<<< HEAD
    // aggiungo una riga per committare e provare a sovrascrivere l'ultimo commit di Arianna involontario

=======
}
     */
>>>>>>> c4248cee6cd73d90adc88b44cab1aa6eea42bf60



