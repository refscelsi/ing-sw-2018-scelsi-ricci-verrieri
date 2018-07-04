package it.polimi.ing.sw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoundTrackTest {
    private RoundTrack roundTrack;
    private DraftPool[] draftPools;
    private DraftPool draftPool;
    private int round;

    @Before
    public void setUp() throws Exception {
        roundTrack=new RoundTrack();
        draftPools= new DraftPool[1];
        draftPool= new DraftPool();
        draftPools[0]=draftPool;
        draftPool.addDice(new Dice(1,Color.BLUE));
        round=0;

    }

    @Test
    public void addDicesRound() {
    }

    @Test
    public void setRoundTrack() {
        
    }

    @Test
    public void setRound() {
        roundTrack.setRound(5);
        assertTrue(roundTrack.getRound()==5);
    }

    @Test
    public void getRoundTrack() {
        roundTrack.setRoundTrack(draftPools);
        assertTrue(roundTrack.getRoundTrack().equals(draftPools));
    }

    @Test
    public void getDicesRound() {
        roundTrack.setRound(1);
        roundTrack.addDicesRound(draftPool);
        assertTrue(roundTrack.getRound()==2);
    }

    @Test
    public void getNumberOfDices() {
        roundTrack.addDicesRound(draftPool);
        assertTrue(roundTrack.getNumberOfDices(1)==1);
    }

    @Test
    public void getRoundTrackSize() {
        roundTrack.addDicesRound(draftPool);
        assertTrue(roundTrack.getRoundTrackSize()==1);
    }

    @Test
    public void getMaxNumberOfDices() {
    }

    @Test
    public void getColorsInRoundTrack() {
    }

    @Test
    public void cloneRoundTrack() {
    }
}