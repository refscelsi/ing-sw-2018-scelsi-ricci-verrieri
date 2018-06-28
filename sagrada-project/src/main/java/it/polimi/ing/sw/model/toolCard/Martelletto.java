package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;

public class Martelletto extends ToolCard {

    private final int id = 7;


    public Martelletto() {
        super(7);
    }

    @Override
    public void execute7(DraftPool draftPool) {
        for (Dice dice : draftPool.getDraftPool()) {
            dice.throwDice();
        }
        incrementNumOfTokens();
    }


}
