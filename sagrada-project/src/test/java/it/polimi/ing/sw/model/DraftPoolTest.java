package it.polimi.ing.sw.model;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class DraftPoolTest {
    private DraftPool draftPool;
    private Dice dice;

    @Before
    public void setUp() throws Exception {
        draftPool=new DraftPool();
        dice=new Dice();
    }

    @Test
    public void addDice() {
        draftPool.addDice(dice);
        assertEquals(1,draftPool.getSize());
    }
    @Test
    public void setDraftPool() {
        ArrayList<Dice> dices= new ArrayList<Dice>();
        Dice dice1=new Dice();
        for(int i=0; i<20;i++){
            dices.add(dice1);
        }
        draftPool.setDraftPool(dices);
        assertTrue(draftPool.getSize()==dices.size());
        assertTrue(draftPool.getDraftPool().contains(dice1));
        assertTrue(!draftPool.getDraftPool().contains(dice));
    }

    @Test
    public void addDraftPool() {
        DraftPool draftPool1=new DraftPool();
        ArrayList<Dice> dices= new ArrayList<Dice>();
        Dice dice1=new Dice();
        for(int i=0; i<20;i++){
            dices.add(dice1);
        }
        draftPool.setDraftPool(dices);
        draftPool.addDraftPool(draftPool1);
        assertTrue(draftPool.getDraftPool().containsAll(dices));
    }

    @Test
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

    @Test
    public void getDraftPool() {
    }

    @Test
    public void getSize() {
    }

    @Test
    public void diceInDraftpool() {
        draftPool.addDice(dice);
        draftPool.addDice(dice);
        draftPool.addDice(dice);
        assertTrue(draftPool.getSize()==3);
        Dice dice1=new Dice();
        draftPool.setDice(dice,2);
        assertTrue(draftPool.diceInDraftpool(dice));
    }

    @Test
    public void getDice() {
        draftPool.addDice(dice);
        draftPool.addDice(dice);
        draftPool.addDice(dice);
        assertTrue(draftPool.getSize()==3);
        Dice dice1=new Dice(3,Color.BLUE);
        draftPool.setDice(dice1,1);
        assertTrue(draftPool.getDice(1).getDiceColor().equals(dice1.getDiceColor()));
        assertTrue(draftPool.getDice(1).getNumFacciaUp()==dice1.getNumFacciaUp());
        assertTrue(draftPool.getDice(2).equals(dice));
    }



    @Test
    public void getColorsInDraftPool() {
        Dice dice1=new Dice(2,Color.GREEN);
        Dice dice2= new Dice(3,Color.BLUE);
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
        assertTrue(draftPool.getColorsInDraftPool().contains(Color.GREEN));
        assertTrue(draftPool.getColorsInDraftPool().contains(Color.BLUE));
    }

    @Test
    public void cloneDraftPool() {
        DraftPool draftPool1= new DraftPool();
        assertTrue(draftPool1.getSize()==0);
        assertTrue(!draftPool1.diceInDraftpool(dice));
        draftPool1=draftPool.cloneDraftPool();
        assertTrue(draftPool1.getSize()==draftPool.getSize());
        assertTrue(draftPool1.getDraftPool().equals(draftPool.getDraftPool()));
    }
}




