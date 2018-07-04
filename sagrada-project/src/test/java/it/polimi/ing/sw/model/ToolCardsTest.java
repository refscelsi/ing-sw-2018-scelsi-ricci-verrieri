package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.toolCard.AlesatorePerLaminaDiRame;
import it.polimi.ing.sw.model.toolCard.PinzaSgrossatrice;
import it.polimi.ing.sw.model.toolCard.ToolCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToolCardsTest {
    private ToolCards toolCards;
    private ToolCard toolCard;

    @Before
    public void setUp() throws Exception {
        toolCards = new ToolCards();

    }

    @Test
    public void getToolCards() {
        assertTrue(toolCards.getToolCards().size()==3);
    }

    @Test
    public void getSize() {
        assertTrue(toolCards.getSize()==3);
    }

    @Test
    public void addCard() {
        toolCards.addCard(1);
        toolCards.addCard(2);
        toolCards.addCard(3);
        toolCards.addCard(4);
        toolCards.addCard(5);
        toolCards.addCard(6);
        toolCards.addCard(7);
        toolCards.addCard(8);
        toolCards.addCard(9);
        toolCards.addCard(10);
        toolCards.addCard(11);
        toolCards.addCard(12);

        assertTrue(toolCards.getSize()==15);


    }

}

