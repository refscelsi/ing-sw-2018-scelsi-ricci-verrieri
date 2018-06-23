package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.NotValidException;

public class PennelloPerEglomise extends ToolCard{

    private final int id=2;


    public PennelloPerEglomise() {
        super();
    }


    public void execute(Scheme scheme, int sourceRow, int sourceCol, int destRow, int destCol) throws NotValidException {
        Box sourceBox = scheme.getBox(sourceRow, sourceCol);
        Box destBox = scheme.getBox(destRow, destCol);
        if(!sourceBox.isFull())
            throw new NotValidException("Hai scelto come origine una casella vuota!");
        else {
            if (destBox.isFull())
                throw new NotValidException("Non puoi posizionare un dado in una casella già piena!");
            else {
                Dice dice = sourceBox.getDice();
                if (scheme.checkBoxShade(destBox, dice) && scheme.checkIfHasDiceAdjacent(destBox, dice, 1)) {
                    destBox.placeDice(dice);
                    sourceBox.removeDice();
                    incrementNumOfTokens();
                } else
                    throw new NotValidException("Non stai rispettando le condizioni di piazzamento!");
            }
        }
    }


}

