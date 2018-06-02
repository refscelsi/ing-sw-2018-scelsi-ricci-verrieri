package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.RoundTrack;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class TaglierinaCircolare extends ToolCard {
    private Dice dice;
    private DraftPool draftPool;
    private RoundTrack roundTrack;
    final int id=5;

    public TaglierinaCircolare()throws ToolCardException, NotValidException {
        super();
    }

    public void execute (Dice dice, DraftPool draftPool, RoundTrack roundTrack) throws ToolCardException{
        draftPool.removeDice(dice);

        //sistemare roundtrack per permettere lo switch

    }

}
