package it.polimi.ing.sw.model;
import java.util.*;

public class SchemeCardDeck {
    private ArrayList<SchemeCard> deck;
    private ArrayList<SchemeCard> drawnCards;

    public SchemeCardDeck() {
        deck = new ArrayList<SchemeCard>();
        drawnCards= new ArrayList<SchemeCard>();
        setDeck();
    }

    public void setDeck () {
        // qui si caricheranno tutte le carte schema
    }

    public ArrayList<SchemeCard> drawSchemeCard (){
        Collections.shuffle(deck);
        for (int i=0; i<2; i++) {
            drawnCards.add(deck.get(i));
            deck.remove(i);
        }
        return drawnCards;
    }

    public int getSize(){
        return deck.size();
    }

    public int getDrawnCardsSize(){
        return drawnCards.size();
    }
}


