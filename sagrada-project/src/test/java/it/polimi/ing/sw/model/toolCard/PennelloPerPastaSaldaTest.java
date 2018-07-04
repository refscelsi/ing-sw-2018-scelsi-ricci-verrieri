package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class PennelloPerPastaSaldaTest {

    private PennelloPerPastaSalda pennelloPerPastaSalda;
    private Dice dice1, dice2, dice3;
    private DraftPool draftPool;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        pennelloPerPastaSalda = new PennelloPerPastaSalda();
        draftPool = new DraftPool();
        dice1 = new Dice(6,Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
    }

    @Test
    public void executeTest() {

        pennelloPerPastaSalda.execute(draftPool, null, null, null, null, 1, -1, -1, -1, -1, -1);

        assertTrue(draftPool.getDice(0).getNumFacciaUp()==6&&draftPool.getDice(1).getDiceColor().equals(Color.GREEN));
    }


    @Test
    public void cloneTest() {

        toolCardClone = pennelloPerPastaSalda.toolCardClone();

        assertTrue(toolCardClone.getId()==6);
    }

}
