package Progetto;

import static org.junit.Assert.*;
import Progetto.Model.ObjectiveCard.*;
import Progetto.Model.ObjectiveCardDeck;

public class ObjectiveCardDeckTest {
    private ObjectiveCardDeck deck;
    //private ObjectiveCard objectiveCard;

    @org.junit.Before
    public void setUp() throws Exception {
        deck=new ObjectiveCardDeck();
        //objectiveCard=new ObjectiveCard("Prova", 7, false);
    }

    @org.junit.Test
    public void setDeck() {
        deck.setDeck();
        assertEquals(15,deck.getSize());
    }


    @org.junit.Test
    public void drawObjectiveCard() {
        deck.setDeck();
        deck.drawObjectiveCard(3, true);
        assertEquals(3,deck.getDrawnCardsSize());
    }

}