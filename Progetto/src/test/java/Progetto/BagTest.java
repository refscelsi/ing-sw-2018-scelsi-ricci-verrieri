package Progetto;

import Progetto.Model.Bag;
import Progetto.Model.Dice;

import static org.junit.Assert.*;

public class BagTest {
    private Bag bag;
    private Dice dice;

    @org.junit.Before
    public void setUp() throws Exception {
        bag=new Bag();
        dice=new Dice();
    }

    @org.junit.Test
    public void setDices() {
        bag.setDices();
        assertTrue(90==bag.getSize());
    }


    @org.junit.Test
    public void draw() {
        bag.setDices();
        bag.draw(3);
        assertTrue(bag.getSize()==83);
    }

    @org.junit.Test
    public void getSize() {

    }
}