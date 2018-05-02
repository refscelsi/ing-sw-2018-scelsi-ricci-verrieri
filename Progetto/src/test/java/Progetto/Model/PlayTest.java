package Progetto.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class PlayTest {
    private Dice dice;
    private Scheme scheme;
    private Box box;
    private Box boxes[][];

    @Before
    public void setUp() throws Exception {
    this.box=new Box();
    this.dice=new Dice();
    boxes=new Box[4][5];
    this.scheme=new Scheme(1,2, boxes);
    box.setColor(Color.WHITE);
    dice.setNumFacciaUp(4);
    }

    @Test
    public void useDice() {
    }
}