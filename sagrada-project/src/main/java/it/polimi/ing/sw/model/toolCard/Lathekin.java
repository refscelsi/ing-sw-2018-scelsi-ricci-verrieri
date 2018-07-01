package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

public class Lathekin extends ToolCard {

    private final int id = 4;
    private boolean firstExecutionDone;
    private int sourceRow1, sourceCol1, destRow1, destCol1;


    public Lathekin() {
        super(4);
        firstExecutionDone = false;
    }


    public boolean getFirstExecutionDone() {
        return firstExecutionDone;
    }


    @Override
    public void execute(DraftPool neverUsed1, RoundTrack neverUsed2, Scheme scheme, Player[] neverUsed3, Bag neverUsed4, int neverUsed5, int neverUsed6, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if (!sourceBox.isFull()) {
            if (firstExecutionDone)
                replaceDice(scheme);
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        } else {
            if (destBox.isFull()) {
                if (firstExecutionDone)
                    replaceDice(scheme);
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            } else {
                Dice dice = sourceBox.getDice();
                sourceBox.removeDice();

                if (scheme.checkBox(destRow, destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow, destCol, dice, 1)) {
                    destBox.placeDice(dice);
                    if (!firstExecutionDone) {
                        firstExecutionDone = true;
                        sourceRow1 = sourceRow;
                        sourceCol1 = sourceCol;
                        destRow1 = destRow;
                        destCol1 = destCol;
                    } else {
                        //incrementNumOfTokens();
                        firstExecutionDone = false;
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
