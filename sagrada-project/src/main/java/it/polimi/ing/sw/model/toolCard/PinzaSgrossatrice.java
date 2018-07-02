package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;


public class PinzaSgrossatrice extends ToolCard {

    private final int id = 1;


    public PinzaSgrossatrice() {
        super(1);
    }

    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed1, Scheme neverUsed2, Player[] neverUsed3, Bag neverUsed4, int indexInDraftPool, int operation, int neverUsed5, int neverUsed6, int neverUsed7, int neverUsed8) throws NotValidException {
        int value = draftPool.getDice(indexInDraftPool).getNumFacciaUp();

        switch (operation) {
            case 0:
                if (value < 6 && value > 0) {
                    draftPool.getDice(indexInDraftPool).setNumFacciaUp(value+1);
                    //incrementNumOfTokens();
                } else
                    throw new NotValidException("Non puoi cambiare un 6 in 1");
                break;

            case 1:
                if (value > 1 && value < 7) {
                    draftPool.getDice(indexInDraftPool).setNumFacciaUp(value-1);
                    //incrementNumOfTokens();
                } else
                    throw new NotValidException("Non puoi cambiare un 1 in 6");
                break;
            default:
                break;
        }
    }

}