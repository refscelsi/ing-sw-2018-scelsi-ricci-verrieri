package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class Martelletto extends ToolCard {
    private Box destination;
    private Dice dice;
    final int id=7;



    public Martelletto() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(DraftPool draftPool, Player player) throws NotValidException {
        //controllo che sia il secondo turno
        for(Dice dice: draftPool.getDraftPool()){
            dice.throwDice();
        }
        player.useDice(destination,dice/*,draftPool*/);
    }

    public void setDice(Dice dice){
        this.dice=dice;
    }

    public void setDestination(Box box){
        this.destination=box;
    }
}
