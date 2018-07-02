package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

public class RigaInSughero extends ToolCard {
    private final int id = 9;

    public RigaInSughero() {
        super(9);
    }


    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed2, Scheme scheme, Player[] neverUsed3, Bag neverUsed4, int indexInDraftPool, int neverUsed5, int row, int col, int neverUsed6, int neverUsed7) throws NotValidException {
        Box destBox = scheme.getBox(row, col);
        Dice dice = draftPool.getDice(indexInDraftPool);
        if (destBox.isFull())
            throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
        else {
            if (scheme.checkBox(row, col, dice) && scheme.checkIfHasDiceAdjacent(row, col, dice, 2)) {
                destBox.placeDice(dice);
                draftPool.removeDice(dice);
                //incrementNumOfTokens();
            } else
                throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
        }
    }

}
