package it.polimi.ing.sw.model;


import static org.junit.Assert.*;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;

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
        for(int i=0; i<20;i++){
            draftPool.addDice(dice);
        }
        draftPool.removeDice(dice);
        assertEquals(19,draftPool.getSize());
        for(int i=0; i<19; i++){
            draftPool.removeDice(dice);
        }
        assertEquals(0, draftPool.getSize());

        Dice dice= new Dice();
        Dice dice1= new Dice();
        draftPool.addDice(dice);
        draftPool.addDice(dice1);
        draftPool.removeDice(dice);
        assertEquals(draftPool.getDraftPool().get(0),dice1 );
        assertEquals(draftPool.diceInDraftpool(dice),false);
        assertEquals(draftPool.diceInDraftpool(dice1),true);
    }

}