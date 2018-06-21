package it.polimi.ing.sw.model;

import static org.junit.Assert.*;
import it.polimi.ing.sw.model.ToolCards;
import it.polimi.ing.sw.model.toolCard.ToolCard;
import it.polimi.ing.sw.model.ToolCards;

import java.util.ArrayList;

public class ToolCardsTest {

    private ToolCards toolCards;


    @org.junit.Before
    public void setUp() throws Exception {
        toolCards = new ToolCards();
    }


    @org.junit.Test
    public void getSize() {
        assertEquals(3,toolCards.getSize());
    }

}
