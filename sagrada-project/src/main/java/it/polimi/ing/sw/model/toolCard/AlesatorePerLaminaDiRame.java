package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class AlesatorePerLaminaDiRame extends ToolCard{

    private final int id=3;


    public AlesatorePerLaminaDiRame () {
        super();
    }


    public void execute(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol) throws ToolCardException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if(!sourceBox.isFull())
            throw new ToolCardException("Hai scelto come origine una casella vuota!");
        else {
            if (destBox.isFull())
                throw new ToolCardException("Non puoi posizionare un dado in una casella già piena!");
            else {
                Dice dice = sourceBox.getDice();
                if (scheme.checkBoxColor(destBox, dice) && scheme.checkIfHasDiceAdjacent(destBox, dice, 1)) {
                    destBox.placeDice(dice);
                    sourceBox.removeDice();
                    incrementNumOfTokens();
                } else
                    throw new ToolCardException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }

}
