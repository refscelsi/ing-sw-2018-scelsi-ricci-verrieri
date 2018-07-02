package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;

public class Martelletto extends ToolCard {

    private final int id = 7;


    public Martelletto() {
        super(7);
    }

    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed1, Scheme neverUsed2, Player[] neverUsed3, Bag neverUsed4, int neverUsed5, int neverUsed6, int neverUsed7, int neverUsed8, int neverUsed9, int neverUsed10) {
        for (Dice dice : draftPool.getDraftPool()) {
            dice.throwDice();
        }
    }

}
