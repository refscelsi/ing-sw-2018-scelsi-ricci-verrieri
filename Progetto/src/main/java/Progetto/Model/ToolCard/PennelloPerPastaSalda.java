package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;


import java.io.DataInput;

public class PennelloPerPastaSalda extends ToolCard {
    private Box destination;
    final int id=6;

    public PennelloPerPastaSalda() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, Player player, DraftPool draftPool) throws ToolCardException, NotValidException {
        try {
            player.useDice(destination,dice);
        } catch (NotValidException e) {
            e.printStackTrace();
        }
    }

    public void SetDestination(Box box){
        this.destination=box;
    }

}
