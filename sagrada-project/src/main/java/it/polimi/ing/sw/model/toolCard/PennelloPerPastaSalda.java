package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


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
