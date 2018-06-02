package progetto.model.toolCard;

import progetto.model.Box;
import progetto.model.Dice;
import progetto.model.DraftPool;
import progetto.model.Player;
import progetto.model.exceptions.NotValidException;
import progetto.model.exceptions.ToolCardException;

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
