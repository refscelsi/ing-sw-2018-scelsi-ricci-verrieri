package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;


import java.io.DataInput;

public class PennelloPerPastaSalda extends ToolCard {
    private Dice dice;
    private Player player;
    private DraftPool draftPool;
    private Box destination;
    //private Play play;

    public PennelloPerPastaSalda(Dice dice, Player player, DraftPool draftPool)throws ToolCardException, NotValidException{
        this.dice=dice;
        this.draftPool=draftPool;
        this.player=player;
    }

    public void execute() throws ToolCardException, NotValidException {
        this.dice=this.getDice();
        try {
            player.useDice(destination,dice/*,draftPool*/);
        } catch (NotValidException e) {
            e.printStackTrace();
        }
    }

    public void SetDestination(Box box){
        this.destination=box;
    }

    public Dice getDice() {
        dice.throwDice();
        return dice;
    }
}
