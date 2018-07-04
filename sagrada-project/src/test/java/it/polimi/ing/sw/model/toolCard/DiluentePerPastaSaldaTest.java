package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DiluentePerPastaSaldaTest {

    private DiluentePerPastaSalda diluentePerPastaSalda;
    private Dice dice1, dice2, dice3;
    private DraftPool draftPool;
    private Bag bag;
    private Scheme scheme;
    private SchemeCardDeck deck;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        diluentePerPastaSalda = new DiluentePerPastaSalda();
        bag = new Bag();
        deck = new SchemeCardDeck();
        draftPool = new DraftPool();
        dice1 = new Dice(6,Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(3, Color.GREEN);
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
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
            diluentePerPastaSalda.execute(draftPool, null, null, null, bag, 1, -1, -1, -1, 0, 0);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            diluentePerPastaSalda.execute(draftPool, null, scheme, null, null, 1, 3, 1, 3, 1, 0);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(scheme.getBox(1, 3).isFull()&&scheme.getBox(1, 3).getDice().getNumFacciaUp()==3);
    }

    @Test
    public void cloneTest() {

        toolCardClone = diluentePerPastaSalda.toolCardClone();

        assertTrue(toolCardClone.getId()==11);
    }

}

