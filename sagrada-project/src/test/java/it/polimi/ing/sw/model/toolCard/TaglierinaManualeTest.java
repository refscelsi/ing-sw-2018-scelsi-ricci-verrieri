package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TaglierinaManualeTest {

    private TaglierinaManuale taglierinaManuale;
    private Dice dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8;
    private DraftPool draftPool, draftPool1;
    private Scheme scheme;
    private SchemeCardDeck deck;
    private RoundTrack roundTrack;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        taglierinaManuale = new TaglierinaManuale();
        dice1 = new Dice(6,Color.BLUE);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(2,Color.PURPLE);
        dice4 = new Dice(6,Color.YELLOW);
        dice5 = new Dice(1, Color.BLUE);
        dice6 = new Dice(3,Color.PURPLE);
        dice7 = new Dice(5,Color.GREEN);
        dice8 = new Dice(4, Color.YELLOW);
        draftPool = new DraftPool();
        draftPool.addDice(dice1);
        draftPool.addDice(dice2);
        draftPool.addDice(dice3);
        draftPool1 = new DraftPool();
        draftPool1.addDice(dice4);
        draftPool1.addDice(dice5);
        draftPool1.addDice(dice6);
        draftPool1.addDice(dice7);
        draftPool1.addDice(dice8);
        roundTrack = new RoundTrack();
        roundTrack.addDicesRound(draftPool);
        roundTrack.addDicesRound(draftPool1);
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
            taglierinaManuale.execute(draftPool, roundTrack, scheme, null, null, -1, 0, 1, 1, 2, 1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            // non deve andare a buon fine
            taglierinaManuale.execute(draftPool, roundTrack, scheme, null, null, -1, 1, 0, 2, 0, 3);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(scheme.getBox(2, 1).isFull()&&scheme.getBox(0, 2).isFull());
    }


    @Test
    public void cloneTest() {

        toolCardClone = taglierinaManuale.toolCardClone();

        assertTrue(toolCardClone.getId()==12);

    }

}

