package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

public class PennelloPerEglomise extends ToolCard {

    private final int id = 2;


    public PennelloPerEglomise() {
        super(2);
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
                sourceBox.removeDice();
                if (scheme.checkBoxShade(destRow, destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow, destCol, dice, 1)) {
                    destBox.placeDice(dice);
                    //incrementNumOfTokens();
                } else {
                    sourceBox.placeDice(dice);
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
                }
            }
        }
    }
}

