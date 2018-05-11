package Progetto.Model;

import Progetto.Model.ObjectiveCard.*;
import java.util.*;

public class ObjectiveCardDeck {
    private ArrayList<ObjectiveCard> deck;
    private ArrayList<ObjectiveCard> drawnCards;

    public ObjectiveCardDeck() {
        deck = new ArrayList<ObjectiveCard>();
        drawnCards= new ArrayList<ObjectiveCard>();
        //setDeck();
    }

    public void setDeck () {
        ObjectiveCard oc=new DifferentColorRow();
        deck.add(oc);
        oc=new DifferentColorColumn();
        deck.add(oc);
        oc=new DifferentShadeRow();
        deck.add(oc);
        oc=new DifferentShadeColumn();
        deck.add(oc);
        oc=new Shades(1);
        deck.add(oc);
        oc=new Shades(3);
        deck.add(oc);
        oc=new Shades(5);
        deck.add(oc);
        oc=new DifferentShades();
        deck.add(oc);
        oc=new DifferentColors();
        deck.add(oc);
        oc=new ColoredDiagonals();
        deck.add(oc);
    }

    public ArrayList<ObjectiveCard> drawObjectiveCard (){
        Collections.shuffle(deck);
        for (int i=0; i<3; i++)
            drawnCards.add(deck.get(i));
        return drawnCards;
    }

    public int getSize(){
        return deck.size();
    }

    public int getDrawnCardsSize(){
        return drawnCards.size();
    }
}
