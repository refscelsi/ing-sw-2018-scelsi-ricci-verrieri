package Progetto;

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
    }

    @org.junit.Test
    public void getDiceColor() {
    }

    @org.junit.Test
    public void isRiserva() {
    }

    @org.junit.Test
    public void isUsatoSuSchema() {
    }

    @org.junit.Test
    public void getNumFacciaUp() {
    }

    @org.junit.Test
    public void setNumFacciaUp() {
    }

    @org.junit.Test
    public void setRiserva() {
    }

    @org.junit.Test
    public void setUsatoSuSchema() {
    }
}