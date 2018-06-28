package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.DraftPool;


public class PennelloPerPastaSalda extends ToolCard {

    private final int id = 6;


    public PennelloPerPastaSalda() {
        super(6);
    }


    @Override
    public void execute6(DraftPool draftPool, int indexInDraftPool) {
        draftPool.getDice(indexInDraftPool).throwDice();
        incrementNumOfTokens();
    }

}
