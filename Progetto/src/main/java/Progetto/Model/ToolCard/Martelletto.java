package Progetto.Model.ToolCard;

import Progetto.Model.Box;
import Progetto.Model.Dice;
import Progetto.Model.DraftPool;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Player;


public class Martelletto implements ToolCard {
    private DraftPool draftPool;
    private Player player;
    private Box destination;
    private Dice dice;



    public Martelletto(DraftPool draftPool, Player player){
        this.draftPool=draftPool;
        this.player=player;
    }

    public void execute() throws NotValidException {
        //controllo che sia il secondo turno
        for(Dice dice: draftPool.getDraftPool()){
            dice.throwDice();
        }
        player.useDice(destination,dice,draftPool);
    }

    public void setDice(Dice dice){
        this.dice=dice;
    }

    public void setDestination(Box box){
        this.destination=box;
    }
}
