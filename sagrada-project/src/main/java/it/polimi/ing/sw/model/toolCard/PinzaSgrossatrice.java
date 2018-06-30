package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.exceptions.NotValidException;


public class PinzaSgrossatrice extends ToolCard {

    private final int id = 1;


    public PinzaSgrossatrice() {
        super(1);
    }

    @Override
    public void execute(DraftPool draftPool, int indexInDraftPool, int operation, int neverUsed1, int neverUsed2, int neverUsed3, int neverUsed4) throws NotValidException {
        int value = draftPool.getDice(indexInDraftPool).getNumFacciaUp();

        switch (operation) {
            case 0:
                if (value < 6 && value > 0) {
                    draftPool.getDice(indexInDraftPool).setNumFacciaUp(value+1);
                    incrementNumOfTokens();
                } else
                    throw new NotValidException("Non puoi cambiare un 6 in 1");
                break;

            case 1:
                if (value > 1 && value < 7) {
                    draftPool.getDice(indexInDraftPool).setNumFacciaUp(value-1);
                    incrementNumOfTokens();
                } else
                    throw new NotValidException("Non puoi cambiare un 1 in 6");
                break;
            default:
                break;
        }
    }

}