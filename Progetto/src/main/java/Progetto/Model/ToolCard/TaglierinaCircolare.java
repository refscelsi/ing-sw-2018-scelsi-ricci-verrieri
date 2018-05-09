package Progetto.Model.ToolCard;

import Progetto.Model.*;
import Progetto.Model.Exceptions.ToolCardException;

public class TaglierinaCircolare implements ToolCard {
    private Dice dice;
    private DraftPool draftPool;
    private RoundTrack roundTrack;

    public TaglierinaCircolare(Dice dice, DraftPool draftPool, RoundTrack roundTrack){
        this.dice=dice;
        this.draftPool=draftPool;
        this.roundTrack=roundTrack;
    }

    public void execute () throws ToolCardException{
        draftPool.removeDice(dice);

        //sistemare roundtrack per permettere lo switch

    }

}
