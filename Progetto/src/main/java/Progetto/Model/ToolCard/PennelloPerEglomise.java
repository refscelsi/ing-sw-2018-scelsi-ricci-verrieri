package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;

public class PennelloPerEglomise extends ToolCard{
    private Dice dice;
    private Player player;
    private Box source;
    private Box destination;

    public PennelloPerEglomise(Dice dice, Player player, Box box1, Box box2)throws ToolCardException, NotValidException {
        this.dice=dice;
        this.player=player;
        this.source=box1;
        this.destination=box2;
    }

    public void execute() throws ToolCardException{
        Scheme scheme=player.getScheme();
        if(scheme.isEmpty()){
            throw new ToolCardException("Non hai dadi da spostare babbazzo");
        }
        source.removeDice();
        if(!destination.isFull() && scheme.checkDiceAdjacent(destination,dice) &&
                ((scheme.checkShade(destination.getShade(),dice.getNumFacciaUp()))
                || (scheme.checkShade(dice.getNumFacciaUp(),0)))){
            destination.placeDice(dice);
        }
        else
            source.placeDice(dice);
    }

}

