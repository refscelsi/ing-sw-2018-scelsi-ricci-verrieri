package progetto.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class SchemeTest {
    private Scheme scheme;
    private Dice dice1, dice2, dice3;
    private Box box;
    private Box[][] boxes;

    @org.junit.Before
    public void setUp() throws Exception {
        int i, j;
        boxes = new Box[4][5];;
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
        dice3= new Dice();
        dice3.setDiceColor(Color.BLUE);
        dice3.setNumFacciaUp(6);
        assertTrue(scheme.checkBox(boxes[0][0],dice1)&&!scheme.checkBox(boxes[0][1],dice2));
        assertTrue(!scheme.checkBox(boxes[0][3],dice1));
        assertTrue(scheme.checkBox(boxes[1][3], dice1));
        assertTrue(scheme.checkBox(boxes[1][3], dice2));
    }

    @org.junit.Test
    public void checkDiceAdjacent() {
        dice1=new Dice();
        dice1.setDiceColor(Color.RED);
        dice1.setNumFacciaUp(3);
        dice2=new Dice();
        dice2.setDiceColor(Color.GREEN);
        dice2.setNumFacciaUp(4);
        dice3=new Dice();
        dice3.setNumFacciaUp(4);
        dice3.setDiceColor(Color.BLUE);
        boxes[0][3].placeDice(dice2);
        boxes[3][2].placeDice(dice3);
        boxes[1][1].placeDice(dice1);
        assertEquals(false,scheme.isEmpty());
        assertTrue(!scheme.checkDiceAdjacent(boxes[0][1],dice1) &&scheme.checkDiceAdjacent(boxes[0][2],dice1)&&!scheme.checkDiceAdjacent(boxes[0][2],dice2));
        assertTrue(scheme.checkDiceAdjacent(boxes[1][2],dice2));
        assertTrue(!scheme.checkDiceAdjacent(boxes[1][3],dice3));
        assertTrue(!scheme.checkDiceAdjacent(boxes[0][4],dice2));
        assertTrue(!scheme.checkDiceAdjacent(boxes[3][3],dice2) && scheme.checkDiceAdjacent(boxes[2][1],dice3));
        assertTrue(scheme.checkDiceAdjacent(boxes[2][0],dice1) && !scheme.checkDiceAdjacent(boxes[1][0],dice1));
    }

    @Test
    public void isEmpty(){
        dice2=new Dice();
        dice2.setDiceColor(Color.GREEN);
        dice2.setNumFacciaUp(4);
        assertEquals(true,scheme.isEmpty());
        boxes[0][3].placeDice(dice2);
        assertEquals(false,scheme.isEmpty());
        assertEquals(19,scheme.countFreeBoxes());
        boxes[0][3].removeDice();
        assertEquals(true,scheme.isEmpty());
        assertEquals(20,scheme.countFreeBoxes());
    }

    @Test
    public void checkFirst(){
        assertEquals(true, scheme.checkFirst(boxes[0][2],dice1));
        assertEquals(true,scheme.checkFirst(boxes[3][3],dice2));
        assertEquals(false,scheme.checkFirst(boxes[2][3],dice1));
    }

    @Test
    public void getBoxes(){
        assertTrue(boxes.equals(scheme.getBoxes()));
    }

    @Test
    public void getId(){
        assertTrue(1==scheme.getId());
    }

    @Test
    public void getDifficulty(){
        assertTrue(7==scheme.getDifficulty());
    }

    @Test
    public void setId(){
        scheme.setId((short)2);
        assertTrue(2==scheme.getId());
    }

    @Test
    public void setDifficulty(){
        scheme.setDifficulty((short)4);
        assertTrue(4==scheme.getDifficulty());
    }
}