package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;


public class PennelloPerPastaSalda extends ToolCard {

    final int id=6;


    public PennelloPerPastaSalda() throws ToolCardException, NotValidException {
        super();
    }


    /* ancora meglio se lo faccio nel controller direttamente

    public void execute(DiceGUI dice) throws ToolCardException, NotValidException {
        dice.throwDice();
    }*/

}
