package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class PennelloPerEglomiseTest {

    private PennelloPerEglomise pennelloPerEglomise;
    private Dice dice1, dice2;
    private Scheme scheme;
    private SchemeCardDeck deck;

    @Before
    public void before() {
        pennelloPerEglomise = new PennelloPerEglomise();
        dice1 = new Dice(6,Color.RED);
        dice2 = new Dice(1, Color.GREEN);
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
    }

    @Test
    public void executeTest() {
        try {
            // deve andare a buon fine
            pennelloPerEglomise.execute(null, null, scheme, null, null, 0, 0, 1, 0, 0, 0);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            pennelloPerEglomise.execute(null, null, scheme, null, null, 0, 0, 0, 0, 3, 0);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            pennelloPerEglomise.execute(null, null, scheme, null, null, 0, 0, 0, 0, 1, 1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            pennelloPerEglomise.execute(null, null, scheme, null, null, 0, 0, 0, 0, 3, 1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            pennelloPerEglomise.execute(null, null, scheme, null, null, 0, 0, 1, 1, 0, 1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(scheme.getBox(3,1).isFull()&&scheme.getBox(0,1).isFull()&&!scheme.getBox(1,1).isFull());
    }

}
