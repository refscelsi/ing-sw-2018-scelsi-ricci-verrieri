package it.polimi.ing.sw.server.controller;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Match;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.toolCard.ToolCard;


import javax.swing.text.View;

public class TurnController {

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

    public TurnController(Match match, View view, Player player){
        this.match=match;
        //view.registerObserver(this);
        this.view=view;        // se non ci va lo cambio col comando sopra
        this.player=player;

        chosenCard= new ChosenCard(this);
        chosenDice= new ChosenDice(this);
        startedTurn = new StartedTurn(this);

        this.state = startedTurn;
    }


    //gestione oggetti del gioco
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

}


