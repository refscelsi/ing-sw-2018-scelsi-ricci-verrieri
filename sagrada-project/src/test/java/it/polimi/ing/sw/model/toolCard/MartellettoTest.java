package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MartellettoTest {

    private Martelletto martelletto;
    private Dice dice1, dice2, dice3;
    private DraftPool draftPool;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        martelletto = new Martelletto();
        draftPool = new DraftPool();
        dice1 = new Dice(6,Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(2, Color.YELLOW);
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
        draftPool.addDice(dice3);
    }

    @Test
    public void executeTest() {

        martelletto.execute(draftPool, null, null, null, null, -1, -1, -1, -1, -1, -1);

        assertTrue(draftPool.getSize()==3&&draftPool.getDice(0).getDiceColor().equals(Color.RED)&&draftPool.getDice(1).getDiceColor().equals(Color.GREEN)&&draftPool.getDice(2).getDiceColor().equals(Color.YELLOW));
    }


    @Test
    public void cloneTest() {

        toolCardClone = martelletto.toolCardClone();

        assertTrue(toolCardClone.getId()==7);
    }

}
