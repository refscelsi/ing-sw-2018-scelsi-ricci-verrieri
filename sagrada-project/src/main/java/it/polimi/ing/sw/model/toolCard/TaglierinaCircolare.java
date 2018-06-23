package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.RoundTrack;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class TaglierinaCircolare extends ToolCard {

    private final int id=5;


    public TaglierinaCircolare () {
        super();
    }


    public void execute (DraftPool draftpool, int indexInDraftPool, RoundTrack roundTrack, int round, int indexInRound) {
        Dice diceRoundtrack = roundTrack.getDicesRound(round).getDice(indexInRound);
        Dice diceDraftPool = draftpool.getDice(indexInDraftPool);
        roundTrack.getDicesRound(round).getDice(indexInRound).setDice(diceDraftPool.getNumFacciaUp(), diceDraftPool.getDiceColor());
        draftpool.getDice(indexInDraftPool).setDice(diceRoundtrack.getNumFacciaUp(), diceRoundtrack.getDiceColor());
        incrementNumOfTokens();
    }

}
