package Progetto.Controller;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.ToolCard.ToolCard;
import Progetto.View.*;

import javax.swing.text.View;

public class Controller {

    private Match match;
    private View view;
    private Player player;

    private Dice dice;
    private Box box;
    private ToolCard card;

    private State chosenDice;
    private State chosenCard;
    private State startedTurn;

    private State state;

    public Controller(Match match, View view, Player player){
        this.match=match;
        //view.registerObserver(this);
        this.view=view;        // se non ci va lo cambio col comando sopra
        this.player=player;

        chosenCard= new ChosenCard(this);
        chosenDice= new ChosenDice(this);
        startedTurn = new StartedTurn(this);

        this.state= startedTurn;
    }

    public void setDice(Dice dice) {
        this.dice = dice;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setCard(ToolCard card) {
        this.card = card;
    }
// gestione gioco
    public Dice getDice() {
        return dice;
    }
    public Box getBox() {
        return box;
    }
    public Match getMatch() {
        return match;
    }

    public ToolCard getCard() {
        return card;
    }

    public Player getPlayer() {
        return player;
    }

    //gestione stati

    public State getChosenCard() {
        return chosenCard;
    }

    public State getChosenDice() {
        return chosenDice;
    }

    public State getStartedTurn() {
        return startedTurn;
    }

    public void setState(State state) {
        this.state = state;
    }



    /*public void changePlayer (String changePlayer) {
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

    }*/



}


