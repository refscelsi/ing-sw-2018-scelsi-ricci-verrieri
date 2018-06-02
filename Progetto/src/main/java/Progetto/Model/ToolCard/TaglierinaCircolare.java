package progetto.model.toolCard;

import progetto.model.Dice;
import progetto.model.DraftPool;
import progetto.model.RoundTrack;
import progetto.model.exceptions.NotValidException;
import progetto.model.exceptions.ToolCardException;

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
