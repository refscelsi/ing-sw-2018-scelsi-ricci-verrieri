package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PinzaSgrossatriceTest {

    private PinzaSgrossatrice pinzaSgrossatrice;
    private Dice dice1, dice2, dice3;
    private DraftPool draftPool;

    @Before
    public void before() {
        pinzaSgrossatrice = new PinzaSgrossatrice();
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
        try {
            pinzaSgrossatrice.execute(draftPool, null, null, null, null, 0, 0, -1, -1, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            pinzaSgrossatrice.execute(draftPool, null, null, null, null, 1, 1, -1, -1, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            pinzaSgrossatrice.execute(draftPool, null, null, null, null, 2, 0, -1, -1, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(draftPool.getDice(0).getNumFacciaUp()==6&&draftPool.getDice(1).getNumFacciaUp()==1&&draftPool.getDice(2).getNumFacciaUp()==3);
    }

}
