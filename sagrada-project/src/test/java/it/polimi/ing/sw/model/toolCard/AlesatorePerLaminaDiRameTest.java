package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.SchemeCardDeck;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class AlesatorePerLaminaDiRameTest {

    private AlesatorePerLaminaDiRame alesatorePerLaminaDiRame;
    private Dice dice1, dice2, dice3;
    private Scheme scheme;
    private SchemeCardDeck deck;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        alesatorePerLaminaDiRame = new AlesatorePerLaminaDiRame();
        dice1 = new Dice(6, Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(1, Color.GREEN);
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
            // deve andare a buon fine
            alesatorePerLaminaDiRame.execute(null, null, scheme, null, null, 0, 0, 1, 0, 2, 0);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            alesatorePerLaminaDiRame.execute(null, null, scheme, null, null, 0, 0, 0, 2, 0, 4);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            alesatorePerLaminaDiRame.execute(null, null, scheme, null, null, 0, 0, 2, 0, 0, 3);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            alesatorePerLaminaDiRame.execute(null, null, scheme, null, null, 0, 0, 2, 0, 1, 1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            alesatorePerLaminaDiRame.execute(null, null, scheme, null, null, 0, 0, 2, 0, 3, 1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // deve andare a buon fine
            alesatorePerLaminaDiRame.execute(null, null, scheme, null, null, 0, 0, 1, 1, 1, 2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        toolCardClone = alesatorePerLaminaDiRame.toolCardClone();

        assertTrue(scheme.getBox(0,4).isFull()&&scheme.getBox(3,1).isFull()&&!scheme.getBox(1,1).isFull());
    }

    @Test
    public void cloneTest() {

        toolCardClone = alesatorePerLaminaDiRame.toolCardClone();

        assertTrue(toolCardClone.getId()==3);
    }

}

