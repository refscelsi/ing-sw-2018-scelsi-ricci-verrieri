package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.ToolCardException;


public class AlesatorePerLaminaDiRame implements ToolCard{
    private Dice dice;
    private Player player;
    private Box source;
    private Box destination;

    public AlesatorePerLaminaDiRame(Dice dice, Player player, Box box1, Box box2){
        this.dice=dice;
        this.player=player;
        this.source=box1;
        this.destination=box2;
    }


    public void execute() throws ToolCardException {
        Scheme scheme=player.getScheme();
        if(scheme.isEmpty()){
            throw new ToolCardException("Non hai dadi da spostare babbazzo");
        }

        source.removeDice();
        if(!destination.isFull() && scheme.checkDiceAdjacent(destination,dice) &&
                ((scheme.checkColor(destination.getColor(),dice.getDiceColor()))
                        || (scheme.checkColor(dice.getDiceColor(),Color.WHITE)))){
            destination.placeDice(dice);
        }
        else
            source.placeDice(dice);
    }
}
