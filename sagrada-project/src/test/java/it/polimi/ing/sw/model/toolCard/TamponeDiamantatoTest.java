package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TamponeDiamantatoTest {

    private TamponeDiamantato tamponeDiamantato;
    private Dice dice1, dice2, dice3;
    private DraftPool draftPool;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        tamponeDiamantato = new TamponeDiamantato();
        dice1 = new Dice(6,Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(2,Color.PURPLE);
        draftPool = new DraftPool();
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
        draftPool.addDice(dice3);
    }

    @Test
    public void executeTest() {

        tamponeDiamantato.execute(draftPool, null, null, null, null, 1, -1, -1, -1, -1, -1);
        tamponeDiamantato.execute(draftPool, null, null, null, null, 0, -1, -1, -1, -1, -1);
        tamponeDiamantato.execute(draftPool, null, null, null, null, 2, -1, -1, -1, -1, -1);

        assertTrue(draftPool.getDice(1).getNumFacciaUp()==6&&draftPool.getDice(0).getNumFacciaUp()==1&&draftPool.getDice(2).getNumFacciaUp()==5);
    }


    @Test
    public void cloneTest() {

        toolCardClone = tamponeDiamantato.toolCardClone();

        assertTrue(toolCardClone.getId()==10);
    }

}
