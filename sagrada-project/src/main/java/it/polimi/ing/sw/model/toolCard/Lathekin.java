package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;

public class Lathekin extends ToolCard {

    private final int id=4;
    private boolean firstExecutionDone;


    public Lathekin() {
        super();
        firstExecutionDone = false;
    }

    @Override
    public void execute4(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if(!sourceBox.isFull())
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        else {
            if (destBox.isFull())
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            else {
                Dice dice = sourceBox.getDice();

                if (scheme.checkBox(destRow,destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow,destCol, dice, 1)) {
                    destBox.placeDice(dice);
                    sourceBox.removeDice();
                    if (!firstExecutionDone)
                        firstExecutionDone = true;
                    else {
                        firstExecutionDone = false;
                        incrementNumOfTokens();
                    }
                } else
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }


}
