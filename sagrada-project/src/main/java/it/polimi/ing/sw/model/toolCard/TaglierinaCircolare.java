package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.RoundTrack;


public class TaglierinaCircolare extends ToolCard {

    private final int id = 5;


    public TaglierinaCircolare() {
        super(5);
    }

    @Override
    public void execute5(DraftPool draftpool, int indexInDraftPool, RoundTrack roundTrack, int round, int indexInRound) throws NotValidPlayException {
        if (roundTrack.getRoundTrackSize() < 1)
            throw new NotValidPlayException("Non puoi utilizzare questa carta durante il primo round perché non ci sono dadi sul tracciato dei round!");
        else {
            Dice diceRoundtrack = roundTrack.getDicesRound(round).getDice(indexInRound);
            Dice diceDraftPool = draftpool.getDice(indexInDraftPool);
            roundTrack.getDicesRound(round).getDice(indexInRound).setDice(diceDraftPool.getNumFacciaUp(), diceDraftPool.getDiceColor());
            draftpool.getDice(indexInDraftPool).setDice(diceRoundtrack.getNumFacciaUp(), diceRoundtrack.getDiceColor());
            incrementNumOfTokens();
        }
    }

}
