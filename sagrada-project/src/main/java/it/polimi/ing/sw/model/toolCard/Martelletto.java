package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import it.polimi.ing.sw.model.exceptions.ToolCardException;

public class Martelletto extends ToolCard {

    final int id=7;


    public Martelletto() throws ToolCardException, NotValidException {
        super();
    }


    // il controller prima di eseguirla si assicurerà che il giocatore stia nel secondo turno

    /* ancora meglio se lo faccio nel controller direttamente

    public void execute(DraftPool draftPool, View player) throws NotValidException {
        for(DiceGUI dice: draftPool.getDraftPool()){
            dice.throwDice();
        }
    }*/


}
