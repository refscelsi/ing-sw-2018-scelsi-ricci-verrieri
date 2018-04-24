package Progetto;

import static org.junit.Assert.*;

public class BagTest {
    private Bag bag;
<<<<<<< HEAD
    private Dice dice;

    @org.junit.Before
    public void setUp() throws Exception {
        bag=new Bag();
        dice=new Dice();
=======

    @org.junit.Before
    public void setUp() throws Exception{
        bag=new Bag();

>>>>>>> 1e69e5cb97eb6d26fb505e9ba9ea41868cb3be02
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