package Progetto.Model;

import Progetto.Model.Scheme;
import Progetto.Model.Dice;
import Progetto.Model.Box;
import Progetto.Model.Color;

import static org.junit.Assert.*;

public class SchemeTest {
    private Scheme scheme;
    private Dice dice1, dice2;
    private Box box;
    private Box[][] boxes;

    @org.junit.Before
    public void setUp() throws Exception {
        int i, j;
        boxes = new Box[4][5];
        box = new Box(0,0,Color.RED,0);
        boxes[0][0] = box;
        box = new Box(0,1,Color.WHITE,3);
        boxes[0][1] = box;
        box = new Box(0,2,Color.WHITE,0);
        boxes[0][2] = box;
        box = new Box(0,3,Color.GREEN,0);
        boxes[0][3] = box;
        box = new Box(0,4,Color.WHITE,5);
        boxes[0][4] = box;
        for (i=1; i<4; i++)
            for (j=0; j<5; j++) {
                box = new Box(i, j, Color.WHITE, 0);
                boxes[i][j] = box;
            }
        scheme=new Scheme(1,7,boxes);
    }

    @org.junit.Test
    public void checkBox() {
        dice1=new Dice();
        dice1.setDiceColor(Color.RED);
        dice1.setNumFacciaUp(4);
        dice2=new Dice();
        dice2.setDiceColor(Color.GREEN);
        dice2.setNumFacciaUp(4);
        assertTrue(scheme.checkBox(boxes[0][0],dice1)&&!scheme.checkBox(boxes[0][1],dice2));
    }

    @org.junit.Test
    public void checkDiceAdjacent() {
        dice1=new Dice();
        dice1.setDiceColor(Color.RED);
        dice1.setNumFacciaUp(3);
        dice2=new Dice();
        dice2.setDiceColor(Color.GREEN);
        dice2.setNumFacciaUp(4);
        boxes[0][3].placeDice(dice2);
        assertTrue(!scheme.checkDiceAdjacent(boxes[0][1],dice1)&&scheme.checkDiceAdjacent(boxes[0][2],dice1)&&!scheme.checkDiceAdjacent(boxes[0][2],dice2));
    }

}