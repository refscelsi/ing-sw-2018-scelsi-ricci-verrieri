package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TaglierinaManuale extends ToolCard {

    private final int id=12;
    private boolean firstExecutionDone;
    private Color color;
    private int sourceRow1, sourceCol1, destRow1, destCol1;


    public TaglierinaManuale() {
        super(12);
        firstExecutionDone = false;
        color = Color.WHITE;
    }

    public boolean getFirstExecutionDone () {
        return firstExecutionDone;
    }

    @Override
    public void execute(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol, RoundTrack roundTrack) throws NotValidException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if(!sourceBox.isFull()) {
            if (firstExecutionDone)
                replaceDice(scheme);
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        }
        else {
            if (destBox.isFull()) {
                if (firstExecutionDone)
                    replaceDice(scheme);
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            }
            else {
                Dice dice = sourceBox.getDice();
                sourceBox.removeDice();
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
                    if (diceColor != color) {
                        if (firstExecutionDone)
                            replaceDice(scheme);
                        throw new NotValidException("Puoi spostare solo un dado dello stesso colore del precedente");
                    }
                }
                if (scheme.checkBox(destRow, destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow, destCol, dice, 1)) {
                    destBox.placeDice(dice);
                    if (!firstExecutionDone) {
                        firstExecutionDone = true;
                        sourceRow1 = sourceRow;
                        sourceCol1 = sourceCol;
                        destRow1 = destRow;
                        destCol1 = destCol;
                    }
                    else {
                        firstExecutionDone = false;
                        color = Color.WHITE;
                        incrementNumOfTokens();
                    }
                } else {
                    sourceBox.placeDice(dice);
                    if (firstExecutionDone)
                        replaceDice(scheme);
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
                }
            }
        }
    }


    public void replaceDice(Scheme scheme) {
        Box sourceBox = scheme.getBox(sourceRow1, sourceCol1);
        Box destBox = scheme.getBox(destRow1, destCol1);
        sourceBox.placeDice(destBox.getDice());
        destBox.removeDice();
    }


}
