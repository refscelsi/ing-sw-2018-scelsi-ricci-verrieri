package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;


public class TamponeDiamantato extends ToolCard {

    private final int id = 10;


    public TamponeDiamantato() {
        super(10);
    }


    @Override
    public void execute(DraftPool draftPool, RoundTrack neverUsed1, Scheme neverUsed2, Player[] neverUsed3, Bag neverUsed4, int indexInDraftPool, int neverUsed5, int neverUsed6, int neverUsed7, int neverUsed8, int neverUsed9) {
        Dice dice = draftPool.getDice(indexInDraftPool);
        switch (dice.getNumFacciaUp()) {
            case 1:
                dice.setNumFacciaUp(6);
                break;
            case 2:
                dice.setNumFacciaUp(5);
                break;
            case 3:
                dice.setNumFacciaUp(4);
                break;
            case 4:
                dice.setNumFacciaUp(3);
                break;
            case 5:
                dice.setNumFacciaUp(2);
                break;
            case 6:
                dice.setNumFacciaUp(1);
                break;
            default:
                break;
        }
    }


}
