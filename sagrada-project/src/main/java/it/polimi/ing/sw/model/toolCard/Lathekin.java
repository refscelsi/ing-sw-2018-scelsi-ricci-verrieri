package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class Lathekin extends ToolCard {

    final int id=4;
    boolean firstExecutionDone;


    public Lathekin() throws ToolCardException, NotValidException {
        super();
        firstExecutionDone = false;
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
                if (scheme.checkBox(destBox, dice) && scheme.checkDiceAdjacent(destBox, dice, true)) {
                    destBox.placeDice(dice);
                    sourceBox.removeDice();
                    if (!firstExecutionDone) {
                        firstExecutionDone = true;
                        throw new ToolCardException("Primo dado spostato correttamente. Sposta il secondo");  // non lo stampo a video, mi serve per richiedere al client il secondo spostamento
                    }
                    else
                        firstExecutionDone = false;
                } else
                    throw new ToolCardException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }


}
