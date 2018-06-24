package it.polimi.ing.sw.model;


import org.junit.Test;

import static org.junit.Assert.*;

public class SchemeCardDeckTest {
    private SchemeCardDeck deck;

    @org.junit.Before
    public void setUp() throws Exception {
        deck=new SchemeCardDeck();
        assertEquals(24,deck.getSize());
    }

    @org.junit.Test
    public void drawSchemeCard() {
        deck.drawSchemeCard();
        assertEquals(20,deck.getSize());
    }

}