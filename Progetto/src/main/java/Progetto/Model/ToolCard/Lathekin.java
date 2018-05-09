package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.ToolCardException;

public class Lathekin implements ToolCard {
    private Dice dice1;
    private Dice dice2;
    private Player player;
    private Box source1;
    private Box destination1;
    private Box source2;
    private Box destination2;

    public Lathekin(Dice dice1, Dice dice2, Player player, Box source1, Box destination1, Box source2, Box destination2){
        this.dice1=dice1;
        this.dice2=dice2;
        this.player=player;
        this.source1=source1;
        this.destination1=destination1;
        this.source2=source2;
        this.destination2=destination2;
    }

    public void execute() throws ToolCardException{
        Scheme scheme=player.getScheme();
        if(scheme.isEmpty()){
            throw new ToolCardException("il tuo schema è vuoto!");
        }

        source1.removeDice();
        if(!destination1.isFull() && scheme.checkBox(destination1,dice1) && scheme.checkDiceAdjacent(destination1,dice1)){
            destination1.placeDice(dice1);
        }
        else {
            source1.placeDice(dice1);
        }

        source2.removeDice();
        if(!destination2.isFull() && scheme.checkBox(destination2,dice2) && scheme.checkDiceAdjacent(destination2,dice2)){
            destination2.placeDice(dice2);
        }
        else {
            source2.placeDice(dice2);
        }

    }
}
