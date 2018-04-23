package Progetto;

import java.lang.reflect.Array;
import java.util.*;

public class ObjectiveCardDeck {
    private ArrayList<ObjectiveCard> deck;

    public ObjectiveCardDeck() {
        deck = new ArrayList<ObjectiveCard>();
        setDeck();
    }

    public void setDeck () {
        ObjectiveCard oc1=new ObjectiveCard("Colori diversi - Riga", 6, true);
        deck.add(oc1);
        ObjectiveCard oc2=new ObjectiveCard("Colori diversi - Colonna", 5, true);
        deck.add(oc2);
        ObjectiveCard oc3=new ObjectiveCard("Sfumature diverse - Riga", 5, true);
        deck.add(oc3);
        ObjectiveCard oc4=new ObjectiveCard("Sfumature diverse - Colonna", 5, true);
        deck.add(oc4);

        // continua così per tutte le 15 carte obiettivo
    }

    public ArrayList<ObjectiveCard> drawObjectiveCard (int num, boolean isPrivate){  // mettiamo num=numPlayers e isPrivate=1 quando vogliamo estrarre le carte obiettivo privato e num=3 e isPrivate=0 quando vogliamo estrarre le 3 carte obiettivo pubblico
        Collections.shuffle(deck);
        ArrayList<ObjectiveCard> drawnCards= new ArrayList<ObjectiveCard>();
        int j=0;
        for(int i=0;i<num;i++){
            if (deck.get(j).isPrivate()==isPrivate) {
                drawnCards.add(deck.get(j));
                deck.remove(deck.get(j));
            }
            j++;
        }
        return drawnCards;
    }

    public int getSize(){
        return deck.size();
    }
}
