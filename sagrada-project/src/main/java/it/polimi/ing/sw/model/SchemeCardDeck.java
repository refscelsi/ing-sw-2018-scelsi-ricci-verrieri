package it.polimi.ing.sw.model;

import java.io.Serializable;
import java.util.*;

public class SchemeCardDeck implements Serializable{

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


    public ArrayList<Scheme> drawSchemeCard (){
        Collections.shuffle(deck);
        for (int i=0; i<2; i++) {
            drawnCards.add(deck.get(i));
            deck.remove(i);
        }
        ArrayList<Scheme> schemes = new ArrayList<Scheme>();
        schemes.add(drawnCards.get(0).getA());
        schemes.add(drawnCards.get(0).getBack());
        schemes.add(drawnCards.get(1).getA());
        schemes.add(drawnCards.get(1).getBack());
        return schemes;
    }


    public int getSize(){
        return deck.size();
    }


    public int getDrawnCardsSize(){
        return drawnCards.size();
    }
}


