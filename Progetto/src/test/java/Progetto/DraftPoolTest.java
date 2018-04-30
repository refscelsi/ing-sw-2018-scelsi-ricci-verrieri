package Progetto;

import static org.junit.Assert.*;
import Progetto.Model.Dice;
import Progetto.Model.DraftPool;

public class DraftPoolTest {
    private DraftPool draftPool;
    private Dice dice;

    @org.junit.Before
    public void setUp() throws Exception {
        draftPool=new DraftPool();
        dice=new Dice();
    }

    @org.junit.Test
    public void addDice() {
        draftPool.addDice(dice);
        assertEquals(1,draftPool.getSize());
    }


    @org.junit.Test
    public void removeDice() {
        draftPool.removeDice(dice);
        assertEquals(0,draftPool.getSize());
    }

}