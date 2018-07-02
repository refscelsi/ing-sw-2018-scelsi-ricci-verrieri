package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;

import java.util.concurrent.SynchronousQueue;


public class TaglierinaManuale extends ToolCard {

    private final int id = 12;
    private boolean firstExecutionDone;
    private Color color;
    private int sourceRow1, sourceCol1, destRow1, destCol1;


    public TaglierinaManuale() {
        super(12);
        firstExecutionDone = false;
        color = Color.WHITE;
    }

    public boolean getFirstExecutionDone() {
        return firstExecutionDone;
    }


    @Override
    public void execute(DraftPool neverUsed1, RoundTrack roundTrack, Scheme scheme, Player[] neverUsed2, Bag neverUsed3, int dice1, int neverUsed5, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        System.out.println("Non entro");
        if (dice1!=-2) {
            System.out.println("Entro");
            Box sourceBox = scheme.getBox(sourceRow, sourceCol);
            Box destBox = scheme.getBox(destRow, destCol);
            Dice dice = sourceBox.getDice();
            if (!sourceBox.isFull()) {
                throw new NotValidException("Hai scelto come origine una casella vuota!");
            } else {
                sourceBox.removeDice();
                if (destBox.isFull()) {
                    sourceBox.placeDice(dice);
                    throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
                } else {
                    Color diceColor = dice.getDiceColor();
                    if (!firstExecutionDone) {
                        for (Color c : roundTrack.getColorsInRoundTrack()) {
                            if (c == diceColor)
                                color = c;
                        }
                        if (color == Color.WHITE) {
                            sourceBox.placeDice(dice);
                            throw new NotValidException("Non esiste un dado di questo colore sul tracciato dei round!");
                        }
                    } else {
                        if (diceColor != color) {
                            sourceBox.placeDice(dice);
                            throw new NotValidException("Puoi spostare solo un dado dello stesso colore del precedente");
                        }
                    }
                    if (scheme.checkBox(destRow, destCol, dice) && scheme.checkIfHasDiceAdjacent(destRow, destCol, dice, 1)) {
                        destBox.placeDice(dice);
                        if (!firstExecutionDone) {
                            firstExecutionDone = true;
                            sourceRow1 = sourceRow;
                            sourceCol1 = sourceCol;
                            destRow1 = destRow;
                            destCol1 = destCol;
                        } else {
                            firstExecutionDone = false;
                            color = Color.WHITE;
                        }
                    } else {
                        sourceBox.placeDice(dice);
                        throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
                    }
                }
            }
        }
        else {
            firstExecutionDone = false;
            color = Color.WHITE;
        }
    }

    @Override
    public ToolCard toolCardClone(){
        ToolCard toolCardClone=new TaglierinaManuale();
        toolCardClone.setName(this.getName());
        toolCardClone.setDescription(this.getDescription());
        toolCardClone.setId(id);
        toolCardClone.setNumOfTokens(this.getNumOfTokens());
        return toolCardClone;
    }


}
