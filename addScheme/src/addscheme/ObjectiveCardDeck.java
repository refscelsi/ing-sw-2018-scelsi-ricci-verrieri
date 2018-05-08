package addscheme;

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
        ObjectiveCard oc1=new ObjectiveCard("Colori diversi - Riga", 6, true);
        deck.add(oc1);
        ObjectiveCard oc2=new ObjectiveCard("Colori diversi - Colonna", 5, true);
        deck.add(oc2);
        ObjectiveCard oc3=new ObjectiveCard("Sfumature diverse - Riga", 5, true);
        deck.add(oc3);
        ObjectiveCard oc4=new ObjectiveCard("Sfumature diverse - Colonna", 5, true);
        deck.add(oc4);

        // continua così per tutte le 15 carte obiettivo --> dobbiamo caricarle da file?
    }

    public ArrayList<ObjectiveCard> drawObjectiveCard (int num, boolean isPrivate){  // mettiamo num=numPlayers e isPrivate=1 quando vogliamo estrarre le carte obiettivo privato e num=3 e isPrivate=0 quando vogliamo estrarre le 3 carte obiettivo pubblico
        Collections.shuffle(deck);
        int j=0, i=0;   // j è il contatore delle posizioni di deck, i è il contatore delle carte che metto in drawnCards
        while (i<num) {
            if (deck.get(j).isPrivate()==isPrivate) {
                drawnCards.add(deck.get(j));
                //deck.remove(j);
                i++;
            }
            j++;
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
