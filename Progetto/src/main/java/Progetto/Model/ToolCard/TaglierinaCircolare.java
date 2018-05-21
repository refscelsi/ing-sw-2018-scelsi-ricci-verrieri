package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;

public class TaglierinaCircolare extends ToolCard {
    private Dice dice;
    private DraftPool draftPool;
    private RoundTrack roundTrack;

    public TaglierinaCircolare()throws ToolCardException, NotValidException {
        super();
    }

    public void execute (Dice dice, DraftPool draftPool, RoundTrack roundTrack) throws ToolCardException{
        draftPool.removeDice(dice);

        //sistemare roundtrack per permettere lo switch

    }

}
