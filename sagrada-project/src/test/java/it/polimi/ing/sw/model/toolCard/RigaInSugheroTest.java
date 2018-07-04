package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class RigaInSugheroTest {

    private RigaInSughero rigaInSughero;
    private Dice dice1, dice2, dice3, dice4, dice5;
    private DraftPool draftPool;
    private Scheme scheme;
    private SchemeCardDeck deck;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        rigaInSughero = new RigaInSughero();
        draftPool = new DraftPool();
        dice1 = new Dice(6,Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(3, Color.YELLOW);
        dice4 = new Dice(2, Color.BLUE);
        dice5 = new Dice(5, Color.GREEN);
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
        draftPool.addDice(dice3);
        draftPool.addDice(dice4);
        draftPool.addDice(dice5);
        deck = new SchemeCardDeck();
        scheme = deck.getSchemeWithId(2);
        try {
            scheme.placeDice(1, 0, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(1, 1, dice1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(0, 2, dice3);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void executeTest() {
        try {
            // non deve andare a buon fine
            rigaInSughero.execute(draftPool, null, scheme, null, null, 0, -1, 0, 3, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            rigaInSughero.execute(draftPool, null, scheme, null, null, 0, -1, 0, 3, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            rigaInSughero.execute(draftPool, null, scheme, null, null, 0, -1, 2, 2, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            rigaInSughero.execute(draftPool, null, scheme, null, null, 0, -1, 3, 2, -1, -1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }


        assertTrue(!scheme.getBox(0,3).isFull()&&!scheme.getBox(2,2).isFull()&&scheme.getBox(3,2).isFull());
    }


    @Test
    public void cloneTest() {

        toolCardClone = rigaInSughero.toolCardClone();

        assertTrue(toolCardClone.getId()==9);
    }

}



