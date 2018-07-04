package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SchemeTest {
    private Scheme scheme, whiteScheme;
    private SchemeCardDeck deck;
    private Dice dice1, dice2, dice3;
    private Box box;
    private Box[][] boxes;


    @Before
    public void Before() {
        dice1 = new Dice(6, Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(3, Color.GREEN);
        deck = new SchemeCardDeck();
        scheme = deck.getSchemeWithId(2);
        boxes = new Box[4][5];
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                boxes[i][j]=new Box(i,j);
            }
        }
        whiteScheme = new Scheme(4, 7, boxes);
    }


    @Test
    public void setDifficulty() {
        whiteScheme.setDifficulty(5);
        assertTrue(whiteScheme.getDifficulty()==5);
    }


    @Test
    public void setId() {
        whiteScheme.setId(2);
        assertTrue(whiteScheme.getId()==2);
    }


    @Test
    public void setIdRetro() {
        whiteScheme.setIdRetro(1);
        assertTrue(whiteScheme.getIdRetro()==1);
    }


    @Test
    public void setBoxes() {
        scheme.setBoxes(boxes);
        assertTrue(scheme.isEmpty());
    }


    @Test
    public void setNotEmpty() {
        scheme.setNotEmpty();
        assertFalse(scheme.getIsEmpty());
    }


    @Test
    public void getBoxes(){
        assertTrue(boxes == whiteScheme.getBoxes());
    }


    @Test
    public void getId(){
        assertTrue(scheme.getId() == 2);
    }


    @Test
    public void getDifficulty(){
        assertTrue(scheme.getDifficulty() == 4);
    }


    @Test
    public void getIdRetro(){
        assertTrue(scheme.getIdRetro() == 1);
    }


    @Test
    public void getBox(){
        assertTrue(boxes[1][1] == whiteScheme.getBox(1, 1));
    }


    @Test
    public void getIsEmpty(){
        assertTrue(whiteScheme.getIsEmpty());
    }


    @Test
    public void countFreeBoxes(){
        assertTrue(whiteScheme.countFreeBoxes() == 20);
    }


    @Test
    public void isEmpty(){
        assertTrue(whiteScheme.isEmpty());
    }


    @Test
    public void checkFirst(){
        assertTrue(whiteScheme.checkFirst(1, 0, dice1) && !whiteScheme.checkFirst(1, 2, dice1));
    }


    @Test
    public void checkColor(){
        assertTrue(scheme.checkColor(Color.RED, dice1.getDiceColor()));
    }


    @Test
    public void checkShade(){
        assertTrue(scheme.checkShade(6, dice1.getNumFacciaUp()));
    }


    @Test
    public void checkBoxColor(){
        assertTrue(!scheme.checkBoxColor(2, 2, dice2) && scheme.checkBoxColor(2, 2, dice1));
    }


    @Test
    public void checkBoxShade(){
        assertTrue(!scheme.checkBoxShade(1, 2, dice1) && scheme.checkBoxColor(0, 4, dice2));
    }


    @Test
    public void checkBox(){
        assertTrue(!scheme.checkBoxColor(2, 2, dice2) && scheme.checkBoxColor(2, 2, dice1));
    }

    @Test
    public void checkIfHasDiceAdjacent(){
        try {
            // deve andare a buon fine
            scheme.placeDice(1, 0, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        // deve tornare false
        Boolean res1 = scheme.checkIfHasDiceAdjacent(3, 3, dice1, 0);

        // deve tornare true
        Boolean res2 = scheme.checkIfHasDiceAdjacent(1, 1, dice1, 0);

        // deve tornare true
        Boolean res3 = scheme.checkIfHasDiceAdjacent(3, 3, dice1, 1);

        // deve tornare true
        Boolean res4 = scheme.checkIfHasDiceAdjacent(1, 1, dice1, 1);

        // deve tornare true
        Boolean res5 = scheme.checkIfHasDiceAdjacent(3, 3, dice1, 2);

        // deve tornare false
        Boolean res6 = scheme.checkIfHasDiceAdjacent(1, 1, dice1, 2);

        assertTrue(!res1 && res2 && res3 && res4 && res5 && !res6);
    }


    @Test
    public void checkOrthogonal(){
        try {
            // deve andare a buon fine
            scheme.placeDice(1, 0, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(!scheme.checkOrthogonal(1, 0, dice3)&&scheme.checkOrthogonal(1, 0, dice1));
    }


    @Test
    public void placeDice(){
        try {
            // non deve andare a buon fine
            scheme.placeDice(1, 1, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            scheme.placeDice(1, 0, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            scheme.placeDice(2, 2, dice1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            scheme.placeDice(1, 1, dice1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        assertTrue(scheme.getBox(1, 1).isFull() && !scheme.getBox(2, 2).isFull() && scheme.getBox(1, 0).isFull());
    }


    @Test
    public void cloneTest() {

        try {
            // non deve andare a buon fine
            scheme.placeDice(1, 0, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        Scheme schemeClone = scheme.schemeClone();

        assertTrue(schemeClone.getId()==2);
    }

}