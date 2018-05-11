package Progetto.Model;

import static org.junit.Assert.*;

public class PrivateObjectiveCardDeckTest {
    private PrivateObjectiveCardDeck deck;

    @org.junit.Before
    public void setUp() throws Exception {
        deck=new PrivateObjectiveCardDeck();
        assertEquals(5,deck.getSize());
    }

    @org.junit.Test
    public void drawObjectiveCard() {
        deck.setDeck();
        deck.drawObjectiveCard(3);
        assertEquals(3,deck.getDrawnCardsSize());
    }

}