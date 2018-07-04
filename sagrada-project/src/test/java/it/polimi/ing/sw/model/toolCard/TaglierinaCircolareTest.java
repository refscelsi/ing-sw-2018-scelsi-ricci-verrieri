package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.controller.exceptions.NotValidPlayException;
import it.polimi.ing.sw.model.*;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class TaglierinaCircolareTest {

    private TaglierinaCircolare taglierinaCircolare;
    private DraftPool draftPool, draftPool1;
    private RoundTrack roundTrack, roundTrack1;
    private Dice dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        taglierinaCircolare = new TaglierinaCircolare();
        dice1 = new Dice(6,Color.RED);
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
    }

    @Test
    public void executeTest() {

        try {
            // deve andare a buon fine
            taglierinaCircolare.execute(draftPool, roundTrack, null, null, null, 1, -1, 1, 2, -1, -1);
        } catch (NotValidPlayException e) {
            System.out.println(e.getMessage());
        }

        assertTrue(draftPool.getDice(1).getNumFacciaUp()==2&&roundTrack.getDicesRound(1).getDice(2).getNumFacciaUp()==1);
    }


    @Test
    public void cloneTest() {

        toolCardClone = taglierinaCircolare.toolCardClone();

        assertTrue(toolCardClone.getId()==5);
    }

}



