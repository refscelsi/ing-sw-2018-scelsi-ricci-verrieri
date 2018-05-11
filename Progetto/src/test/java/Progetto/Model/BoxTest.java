package Progetto.Model;

import Progetto.Model.Dice;
import Progetto.Model.Box;

import static org.junit.Assert.*;

public class BoxTest {
    private Dice dice;
    private Box box;

    @org.junit.Before
    public void setUp() throws Exception {
        dice = new Dice();
        box = new Box();
    }

    @org.junit.Test
    public void isFull() {
        box.placeDice(dice);
        assertEquals(true,box.isFull());
    }

}