package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;


public class PennelloPerPastaSalda extends ToolCard {

    private final int id = 6;


    public PennelloPerPastaSalda() {
        super(6);
    }


    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed1, Scheme neverUsed2, Player[] neverUsed3, Bag neverUsed4, int indexInDraftPool, int neverUsed5, int neverUsed6, int neverUsed7, int neverUsed8, int neverUsed9) {
        draftPool.getDice(indexInDraftPool).throwDice();
    }

}
