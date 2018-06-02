package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class RigaInSughero extends ToolCard {
    final int id=9;

    public RigaInSughero() throws ToolCardException, NotValidException {
        super();
    }

    public void execute(Dice dice, Box box, Player player) throws NotValidException {
        Scheme scheme=player.getScheme();

        if(scheme.isEmpty()){
            if(scheme.checkFirst(box, dice)){
                box.placeDice(dice);
                //scheme.setNotEmpty();
            }
        }
        else if(scheme.checkBox(box, dice) && !scheme.checkDiceAdjacent(box,dice)){
            box.placeDice(dice);
        }
    }

}
