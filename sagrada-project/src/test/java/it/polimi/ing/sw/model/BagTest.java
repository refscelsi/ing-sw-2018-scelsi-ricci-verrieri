package it.polimi.ing.sw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class BagTest {
    private Bag bag;
    private Dice dice;
    private Color color;

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

    @Test
    public void drawDice(){
        int green=0;
        int yellow=0;
        int red=0;
        int blu=0;
        int purple=0;


        bag.setDices();
        for(int i=0; i<90; i++){
            dice=bag.drawDice();
            if(dice.getDiceColor().equals(Color.RED)){
                red++;
            }
            if(dice.getDiceColor().equals(Color.PURPLE)){
                purple++;
            }
            if(dice.getDiceColor().equals(Color.BLUE)){
                blu++;
            }
            if(dice.getDiceColor().equals(Color.YELLOW)){
                yellow++;
            }
            if(dice.getDiceColor().equals(Color.GREEN)){
                green++;
            }
        }
        assertEquals(true , bag.getSize()==0);
        assertEquals(true, red==18);
        assertEquals(true, purple==18);
        assertEquals(true, green==18);
        assertEquals(true, blu==18);
        assertEquals(true, yellow==18);

    }

    @org.junit.Test
    public void draw() {
        bag.setDices();
        bag.draw(3);
        assertEquals(true,bag.getSize()==83);
    }

    @org.junit.Test
    public void getSize() {

    }
}