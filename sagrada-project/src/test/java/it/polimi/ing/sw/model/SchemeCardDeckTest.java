package it.polimi.ing.sw.model;


import org.junit.Test;

import java.util.ArrayList;

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
        ArrayList<Scheme> drawn=new ArrayList<>();
        drawn= deck.drawSchemeCard();
        assertEquals(20,deck.getSize());
        assertEquals(4,drawn.size());

    }

}