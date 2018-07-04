package it.polimi.ing.sw.model.objectiveCard;

import it.polimi.ing.sw.model.Box;
import it.polimi.ing.sw.model.Color;
import it.polimi.ing.sw.model.Dice;
import it.polimi.ing.sw.model.Scheme;
import it.polimi.ing.sw.model.exceptions.NotValidException;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ShadesTest {

    private Shades lightShades, shades, darkShades;
    private Dice dice1, dice2, dice3, dice4, dice5, dice6, dice7, dice8, dice9, dice10, dice11, dice12, dice13;
    private Scheme scheme;
    private Box[][] boxes;
    private int score;

    @Before
    public void before() {
        lightShades = new Shades(1);
        shades = new Shades(3);
        darkShades = new Shades(5);
        dice1 = new Dice(3, Color.RED);
        dice2 = new Dice(5, Color.RED);
        dice3 = new Dice(1, Color.RED);
        dice4 = new Dice(4, Color.RED);
        dice5 = new Dice(5, Color.YELLOW);
        dice6 = new Dice(6, Color.BLUE);
        dice7 = new Dice(2, Color.YELLOW);
        dice8 = new Dice(3, Color.BLUE);
        dice9 = new Dice(6, Color.GREEN);
        dice10 = new Dice(4, Color.PURPLE);
        dice11 = new Dice(1, Color.GREEN);
        dice12 = new Dice(2, Color.PURPLE);
        dice13 = new Dice(1, Color.BLUE);
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
            scheme.placeDice(1, 1, dice2);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(2, 2, dice3);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(3, 1, dice4);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(3, 3, dice5);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(3, 4, dice6);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(2, 4, dice7);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(2, 3, dice8);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(1, 3, dice9);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(1, 4, dice10);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(0, 4, dice11);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(0, 3, dice12);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
        try {
            scheme.placeDice(0, 2, dice13);
        } catch (NotValidException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void executeTest() {

        score = lightShades.calculateScore(scheme) + shades.calculateScore(scheme) + darkShades.calculateScore(scheme);

        assertTrue(score == 12);
    }

}
