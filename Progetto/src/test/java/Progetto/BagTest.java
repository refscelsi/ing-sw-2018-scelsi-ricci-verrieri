package Progetto;

import static org.junit.Assert.*;

public class BagTest {
    Bag bag=new Bag();

    @org.junit.Before
    public void setUp() throws Exception {
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