package Progetto.Model;

import Progetto.Model.Exceptions.NotValidException;
import Progetto.Model.Exceptions.ToolCardException;
import Progetto.Model.ToolCard.*;
import java.util.*;

public class ToolCardDeck {
    private ArrayList<ToolCard> deck;

    public ToolCardDeck() throws ToolCardException, NotValidException {
        deck = new ArrayList<ToolCard>();
        setDeck();
    }

    public void setDeck () throws ToolCardException, NotValidException {
        ToolCard tc=new AlesatorePerLaminaDiRame();
        deck.add(tc);
        tc=new TamponeDiamantato();
        deck.add(tc);
    }

}

