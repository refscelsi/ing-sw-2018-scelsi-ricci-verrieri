package Progetto;

import Progetto.Model.Color;
import Progetto.Model.Dice;

import static org.junit.Assert.*;

public class DiceTest {
    private Dice dice;
    @org.junit.Before
    public void setUp() throws Exception {
        dice=new Dice();
    }

    @org.junit.Test
    public void throwDice() {
        dice.throwDice();
        int mynum=dice.getNumFacciaUp();
        assertTrue(1 <= mynum && mynum <= 6);
    }

    @org.junit.Test
    public void setDiceColor() {
        dice.setDiceColor(Color.RED);
        assertEquals(Color.RED,dice.getDiceColor());
    }
}