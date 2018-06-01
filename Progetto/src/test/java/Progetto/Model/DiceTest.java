package Progetto.Model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {
    private Dice dice;
    @org.junit.Before
    public void setUp() throws Exception {
        dice=new Dice();
    }


    @Test
    public void throwDice() {
        dice.throwDice();
        int mynum=dice.getNumFacciaUp();
        assertTrue(1 <= mynum && mynum <= 6);
    }

    @Test
    public void setDiceColor() {
        dice.setDiceColor(Color.RED);
        assertEquals(Color.RED,dice.getDiceColor());
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
}
