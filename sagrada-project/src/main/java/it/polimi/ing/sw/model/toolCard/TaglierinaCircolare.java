package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.*;


public class TaglierinaCircolare extends ToolCard {

    private final int id = 5;


    public TaglierinaCircolare() {
        super(5);
    }


    @Override
    public void execute(DraftPool draftPool, RoundTrack roundTrack, Scheme neverUsed1, Player[] neverUsed2, Bag neverUsed3, int indexInDraftPool, int neverUsed4, int round, int indexInRound, int neverUsed5, int neverUsed6) throws NotValidPlayException {
        if (roundTrack.getRoundTrackSize() < 1)
            throw new NotValidPlayException("Non puoi utilizzare questa carta durante il primo round perché non ci sono dadi sul tracciato dei round!");
        else {
            Dice diceRoundTrack = roundTrack.getDicesRound(round).getDice(indexInRound);
            Dice diceDraftPool = draftPool.getDice(indexInDraftPool);
            Dice diceRT = new Dice(diceRoundTrack.getNumFacciaUp(), diceRoundTrack.getDiceColor());
            roundTrack.getDicesRound(round).getDice(indexInRound).setDice(diceDraftPool.getNumFacciaUp(), diceDraftPool.getDiceColor());
            draftPool.getDice(indexInDraftPool).setDice(diceRT.getNumFacciaUp(), diceRT.getDiceColor());
            //incrementNumOfTokens();
        }
    }

}
