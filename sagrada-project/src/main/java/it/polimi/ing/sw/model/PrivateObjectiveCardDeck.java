package it.polimi.ing.sw.model;

import it.polimi.ing.sw.model.objectiveCard.PrivateObjectiveCard;

import java.util.*;

public class PrivateObjectiveCardDeck {
    private ArrayList<PrivateObjectiveCard> deck;
    private ArrayList<PrivateObjectiveCard> drawnCards;

    public PrivateObjectiveCardDeck() {
        deck = new ArrayList<PrivateObjectiveCard>();
        drawnCards= new ArrayList<PrivateObjectiveCard>();
        setDeck();
    }

    public void setDeck () {
        PrivateObjectiveCard oc=new PrivateObjectiveCard(Color.RED);
        deck.add(oc);
        oc=new PrivateObjectiveCard(Color.YELLOW);
        deck.add(oc);
        oc=new PrivateObjectiveCard(Color.GREEN);
        deck.add(oc);
        oc=new PrivateObjectiveCard(Color.BLUE);
        deck.add(oc);
        oc=new PrivateObjectiveCard(Color.PURPLE);
        deck.add(oc);
    }

    public ArrayList<PrivateObjectiveCard> drawObjectiveCard (int numPlayers){
        Collections.shuffle(deck);
        for (int i=0; i<numPlayers; i++)
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

