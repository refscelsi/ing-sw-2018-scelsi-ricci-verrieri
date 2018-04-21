package Progetto;

import java.lang.reflect.Array;
import java.util.*;

public class ObjectiveCardDeck {
    private ArrayList<ObjectiveCard> deck;

    public ObjectiveCardDeck() {
        deck = new ArrayList<ObjectiveCard>();
    }

    public void setDeck () {
        ObjectiveCard oc1=new ObjectiveCard("Colori diversi - Riga", 6, false);
        deck.add(oc1);
        ObjectiveCard oc2=new ObjectiveCard("Colori diversi - Colonna", 5, false);
        deck.add(oc2);
        ObjectiveCard oc3=new ObjectiveCard("Sfumature diverse - Riga", 5, false);
        deck.add(oc3);

        // continua così per tutte le 15 carte obiettivo
    }

    public ArrayList<ObjectiveCard> drawObjectiveCard (int num, boolean isPrivate){  // mettiamo num=numPlayers e isPrivate=1 quando vogliamo estrarre le carte obiettivo privato e num=3 e isPrivate=0 quando vogliamo estrarre le 3 carte obiettivo pubblico
        Collections.shuffle(deck);
        ArrayList<ObjectiveCard> drawnCards= new ArrayList<ObjectiveCard>();
        for(int i=0;i<num;i++){
            drawnCards.add(deck.get(i));
        }
        for(int i=0;i<num;i++){
            deck.remove(i);
        }
        return drawnCards;
    }

    public int getSize(){
        return deck.size();
    }
}
