package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TaglierinaManuale extends ToolCard {

    private final int id=12;
    private boolean firstExecutionDone;
    private Color color;

/*
    public TaglierinaManuale() {
        super();
        firstExecutionDone = false;
        color = Color.WHITE;
    }

    @Override
    public void execute(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol, RoundTrack roundTrack) throws NotValidException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if(!sourceBox.isFull())
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        else {
            if (destBox.isFull())
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            else {
                Dice dice = sourceBox.getDice();
                Color diceColor = dice.getDiceColor();
                if (!firstExecutionDone) {
                    for (Color c: roundTrack.getColorsInRoundTrack()) {
                        if (c == diceColor)
                            color = c;
                    }
                    if (color == Color.WHITE)
                        throw new NotValidException("Non esiste un dado di questo colore sul tracciato dei round!");
                }
                else {
                    if (diceColor != color)
                        throw new NotValidException("Puoi spostare solo un dado dello stesso colore del precedente");
                }
                if (scheme.checkBox(destBox, dice) && scheme.checkIfHasDiceAdjacent(destBox, dice, 1)) {
                    destBox.placeDice(dice);
                    sourceBox.removeDice();
                    if (!firstExecutionDone)
                        firstExecutionDone = true;
                    else {
                        firstExecutionDone = false;
                        color = Color.WHITE;
                        incrementNumOfTokens();
                    }
                } else
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }
*/

}
