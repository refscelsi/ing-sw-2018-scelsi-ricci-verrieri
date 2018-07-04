package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class DifferentShadeColumnTest {

    private DifferentShadeColumn differentShadeColumn;
    private Dice dice1, dice2, dice3, dice4, dice5, dice6;
    private Scheme scheme;
    private Box[][] boxes;
    private int score;

    @Before
    public void before() {
        differentShadeColumn = new DifferentShadeColumn();
        dice1 = new Dice(6, Color.RED);
        dice2 = new Dice(1, Color.GREEN);
        dice3 = new Dice(3, Color.YELLOW);
        dice4 = new Dice(5, Color.PURPLE);
        dice5 = new Dice(2, Color.BLUE);
        dice6 = new Dice(6, Color.YELLOW);
        boxes = new Box[4][5];
        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++){
                boxes[i][j]=new Box(i,j);
            }
        }
        scheme = new Scheme(60, 4, boxes);
        try {
            scheme.placeDice(0, 0, dice1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(1, 0, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(2, 0, dice3);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(3, 0, dice4);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(0, 1, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(1, 1, dice1);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(2, 1, dice4);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(3, 1, dice6);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void executeTest() {

        score = differentShadeColumn.calculateScore(scheme);

        assertTrue(score == 4);
    }

}




