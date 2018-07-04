package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

public class PennelloPerEglomise extends ToolCard {

    private final int id = 2;


    public PennelloPerEglomise() {
        super(2);
    }


    @Override
    public void execute(DraftPool neverUsed1, RoundTrack neverUsed2, Scheme scheme, Player[] neverUsed3, Bag neverUsed4, int neverUsed5, int neverUsed6, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if (!sourceBox.isFull()) {
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        } else {
            Dice dice = sourceBox.getDice();
            sourceBox.removeDice();
            if (destBox.isFull()) {
                sourceBox.placeDice(dice);
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            } else {
                if (scheme.checkBoxShade(destRow, destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow, destCol, dice, 1)) {
                    destBox.placeDice(dice);
                } else {
                    sourceBox.placeDice(dice);
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
                }
            }
        }
    }

    @Override
    public ToolCard toolCardClone(){
        ToolCard toolCardClone=new PennelloPerEglomise();
        toolCardClone.setName(this.getName());
        toolCardClone.setDescription(this.getDescription());
        toolCardClone.setId(id);
        toolCardClone.setNumOfTokens(this.getNumOfTokens());
        return toolCardClone;
    }
}

