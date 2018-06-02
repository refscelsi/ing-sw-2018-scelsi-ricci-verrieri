package progetto.model.toolCard;

import progetto.model.Box;
import progetto.model.Dice;
import progetto.model.Player;
import progetto.model.Scheme;
import progetto.model.exceptions.NotValidException;
import progetto.model.exceptions.ToolCardException;

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
