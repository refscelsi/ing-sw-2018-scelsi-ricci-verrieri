package it.polimi.ing.sw.model;

import static junit.framework.TestCase.assertEquals;

public class ObjectiveCardDeckTest {
    private ObjectiveCardDeck deck;

    @org.junit.Before
    public void setUp() throws Exception {
        deck=new ObjectiveCardDeck();
        assertEquals(10,deck.getSize());
    }

    @org.junit.Test
    public void drawObjectiveCard() {
        deck.setDeck();
        deck.drawObjectiveCard();
        assertEquals(3,deck.getDrawnCardsSize());
    }

}