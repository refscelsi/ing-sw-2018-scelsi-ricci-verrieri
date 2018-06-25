package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class RigaInSughero extends ToolCard {
    private final int id = 9;

    public RigaInSughero() {
        super();
    }
/*
    @Override
    public void execute(Scheme scheme, Dice dice, int row, int col) throws NotValidException {
        Box destBox = scheme.getBox(row, col);
        if (destBox.isFull())
            throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
        else {
            if (scheme.checkBox(destBox, dice) && scheme.checkIfHasDiceAdjacent(destBox, dice, 2)) {
                destBox.placeDice(dice);
                incrementNumOfTokens();
            } else
                throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
        }
    }
*/
}
