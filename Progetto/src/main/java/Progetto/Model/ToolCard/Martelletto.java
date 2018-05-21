package Progetto.Model.ToolCard;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.DraftPool;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.Player;


public class Martelletto extends ToolCard {
    private Box destination;
    private Dice dice;



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
