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
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        } else {
            if (destBox.isFull()) {
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            } else {
                Dice dice = sourceBox.getDice();

                if (!firstExecutionDone) {
                    sourceRow1 = sourceRow;
                    sourceCol1 = sourceCol;
                    destRow1 = destRow;
                    destCol1 = destCol;
                }

                else {
                    Box sourceBox1 = scheme.getBox(sourceRow1, sourceCol1);
                    Box destBox1 = scheme.getBox(destRow1, destCol1);
                    destBox1.placeDice(sourceBox1.getDice());
                    sourceBox1.removeDice();
                }

                if (scheme.checkBox(destRow, destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow, destCol, dice, 1)) {
                    if (!firstExecutionDone) {
                        firstExecutionDone = true;
                    }
                    else {
                        firstExecutionDone = false;
                        destBox.placeDice(dice);
                        sourceBox.removeDice();
                    }
                }
                else {
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
