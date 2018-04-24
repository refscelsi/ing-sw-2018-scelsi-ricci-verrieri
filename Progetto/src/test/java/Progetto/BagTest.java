package Progetto;

import static org.junit.Assert.*;

public class BagTest {
    private Bag bag;

    @org.junit.Before
    public void setUp() throws Exception{
        Bag bag=new Bag();

    }

    @org.junit.Test
    public void setDices() {
        bag.setDices();
        assertTrue(90==bag.getSize());
    }


    @org.junit.Test
    public void draw() {
    }

    @org.junit.Test
    public void getSize() {
    }
}