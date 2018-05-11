package Progetto.Model;

import static org.junit.Assert.*;
import Progetto.Model.ObjectiveCard.*;
import Progetto.Model.ObjectiveCardDeck;

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