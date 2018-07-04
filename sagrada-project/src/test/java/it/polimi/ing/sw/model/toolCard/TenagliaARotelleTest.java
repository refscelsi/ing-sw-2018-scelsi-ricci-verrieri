package it.polimi.ing.sw.model.toolCard;

import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.DraftPool;
import it.polimi.ing.sw.model.Player;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TenagliaARotelleTest {

    private TenagliaARotelle tenagliaARotelle;
    private Player[] playersRound;
    private Player p1, p2, p3;
    private ToolCard toolCardClone;

    @Before
    public void before() {
        tenagliaARotelle = new TenagliaARotelle();
        playersRound = new Player[6];
        p1 = new Player("Ludo");
        p2 = new Player("Ari");
        p3 = new Player("Ricky");
        playersRound[0] = p1;
        playersRound[1] = p2;
        playersRound[2] = p3;
        playersRound[3] = p3;
        playersRound[4] = p2;
        playersRound[5] = p1;
    }

    @Test
    public void executeTest() {

        tenagliaARotelle.execute(null, null, null, playersRound, null, 1, -1, -1, -1, -1, -1);

        assertTrue(playersRound[2]==p2&&playersRound[4]==p3&&playersRound[5]==p1);
    }


    @Test
    public void cloneTest() {

        toolCardClone = tenagliaARotelle.toolCardClone();

        assertTrue(toolCardClone.getId()==8);
    }

}

