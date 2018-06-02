package it.polimi.ing.sw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoxTest{
    private Dice dice;
    private Box box;
    private Color color;

    @Before
    public void setUp() throws Exception {
        dice = new Dice();
        box = new Box();
    }

    @Test
    public void setX(){
        box.setX(1);
        box.setY(1);
        box.setColor(Color.BLUE);
        box.setShade(6);

        assertEquals(true,box.getX()==1);
        assertEquals(true,box.getY()==1);
        assertEquals(true,box.getColor()==Color.BLUE);
        assertEquals(true,box.getShade()==6);
    }

    @Test
    public void isFull() {
        box.placeDice(dice);
        assertEquals(true,box.isFull());
    }

    @Test
    public void removeDice() {
        box.placeDice(dice);
        box.removeDice();
        assertEquals(false,box.isFull());
    }


    @Test
    public void placeDice() {
        assertEquals(false,box.isFull());
        box.placeDice(dice);
        assertEquals(true, box.isFull());
        assertEquals(true, box.getDice().equals(dice));
    }

    @Test
    public void getDice(){
        box.placeDice(dice);
        Dice dice= new Dice();
        dice=box.getDice();
        assertEquals(true, dice.equals(this.dice) );
    }
}