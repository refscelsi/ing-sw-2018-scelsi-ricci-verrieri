package Progetto.Controller;

import Progetto.Model.*;
import Progetto.Model.ToolCard.ToolCard;
import Progetto.View.*;
import Progetto.Model.Exceptions.*;

import javax.swing.text.View;

public class PlayerController implements Observer {

    private Player player;
    private View view;
    private Match match;

    public PlayerController (Player player, View view, Match match) {
        this.player = player;
        this.view = view;
        this.match = match;
    }

    @Override
    public void update() {
    }

    public void update (Scheme scheme) {  // il giocatore sceglie lo schema
        player.setScheme(scheme);
    }

    public void update (Dice dice, Box box) throws NotValidException {  // il giocatore usa un dado
        match.useDice(box, dice, player);
    }

    public void update (ToolCard toolCard) {

    }


    /*
        public ClientController(Client client) {
        this.client = client;
        this.view = new View(this);
    }

    tutti i vari metodi che possono servire al giocare vengono chiamati qui

    public void doAction(comando){
           //chiamo metodi del server controller che svolgano le azioni ( quindi tipo servercontroller.handle(passo gli oggetti che verranno gestiti
           in modo diverso in base a che oggetti sono (vedi affo)
           oppure in base al comando chiamo i metodi del model?

    }
     */

}
