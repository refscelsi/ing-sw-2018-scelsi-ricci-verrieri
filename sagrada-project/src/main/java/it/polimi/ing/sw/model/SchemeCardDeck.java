package it.polimi.ing.sw.model;

import it.polimi.ing.sw.ui.gui.SchemeListFileConverter;

import java.io.Serializable;
import java.util.*;

public class SchemeCardDeck implements Serializable{

    private ArrayList<Scheme> deck;


    public SchemeCardDeck() {
        deck = new ArrayList<Scheme>();
        setDeck();
    }


    public void setDeck () {
        SchemeListFileConverter schemes = new SchemeListFileConverter();
        deck = schemes.readFromFile();
    }


    public ArrayList<Scheme> drawSchemeCard (){
        ArrayList<Scheme> drawnCards = new ArrayList<Scheme>();
        Collections.shuffle(deck);
        for (int i=0; i<2; i++) {
            drawnCards.add(deck.get(i));
            drawnCards.add(getSchemeWithId(deck.get(i).getIdRetro()));
            deck.remove(i);
            deck.remove(getSchemeWithId(deck.get(i).getIdRetro()));
        }
        return drawnCards;
    }


    public int getSize(){
        return deck.size();
    }


    public Scheme getSchemeWithId ( int id ) {
        for (Scheme scheme : deck) {
            if (scheme.getId() == id)
                return scheme;
        }
        return null;
    }
}


