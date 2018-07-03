package it.polimi.ing.sw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {
    private Dice dice;
    @Before
    public void setUp() throws Exception {
        dice = new Dice();
    }

    @Test
    public void throwDice() {
        dice.throwDice();
        int mynum = dice.getNumFacciaUp();
        assertTrue(1 <= mynum && mynum <= 6);
    }

    @Test
    public void setDiceColor() {
        dice.setDiceColor(Color.RED);
        assertEquals(Color.RED, dice.getDiceColor());
    }

    @Test
    public void getDiceColor() {

    }

    @Test
    public void getNumFacciaUp() {
    }

    @Test
    public void setNumFacciaUp() {
    }

    @Test
    public void setDice() {
    }

    @Test
    public void cloneDice() {
        Dice dice1= new Dice(2,Color.BLUE);
        assertTrue(dice1.getDiceColor().equals(Color.BLUE));
        assertTrue(dice1.getNumFacciaUp()==2);
        dice1.setNumFacciaUp(5);
        assertTrue(dice1.getNumFacciaUp()==5);
        Dice dice2=new Dice();
        dice2 =dice1.cloneDice();
        assertTrue(dice1.getNumFacciaUp()==5);
        assertTrue(dice1.getDiceColor().equals(Color.BLUE));
    }
}

