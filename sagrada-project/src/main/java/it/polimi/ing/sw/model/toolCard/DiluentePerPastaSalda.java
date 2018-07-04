package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;


public class DiluentePerPastaSalda extends ToolCard {

    private final int id = 11;
    private int indexInDraftPool;
    private boolean firstExecutionDone;


    public DiluentePerPastaSalda() {
        super(11);
        firstExecutionDone = false;

    }


    public boolean getFirstExecutionDone() {
        return firstExecutionDone;
    }


    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed1, Scheme scheme, Player[] neverUsed2, Bag bag, int indexInDraftPool, int numFacciaUp, int row, int col, int firstExecution, int neverUsed4) throws NotValidException {

        if (firstExecution==1)
            firstExecutionDone = true;
        else
            firstExecutionDone = false;

        if (!firstExecutionDone) {
            bag.addDice(draftPool.getDice(indexInDraftPool));
            draftPool.setDice(bag.drawDice(), indexInDraftPool);
            this.indexInDraftPool = indexInDraftPool;
            firstExecutionDone = true;
        }
        else {
            draftPool.getDice(this.indexInDraftPool).setNumFacciaUp(numFacciaUp);
            Dice dice = draftPool.getDice(this.indexInDraftPool);
            Box destBox = scheme.getBox(row, col);
            if (destBox.isFull())
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            else {
                if (scheme.checkBox(row, col, dice) && scheme.checkIfHasDiceAdjacent(row, col, dice, 0)) {
                    destBox.placeDice(dice);
                    draftPool.removeDice(dice);
                    firstExecutionDone = false;
                } else
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }

    @Override
    public ToolCard toolCardClone(){
        ToolCard toolCardClone=new DiluentePerPastaSalda();
        toolCardClone.setName(this.getName());
        toolCardClone.setDescription(this.getDescription());
        toolCardClone.setId(id);
        toolCardClone.setNumOfTokens(this.getNumOfTokens());
        return toolCardClone;
    }


}
