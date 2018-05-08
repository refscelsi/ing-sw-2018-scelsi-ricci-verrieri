package Progetto.Model;

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
        ObjectiveCard oc=new ObjectiveCard("Colori diversi - Riga", 6, false);
        deck.add(oc);
        oc=new ObjectiveCard("Colori diversi - Colonna", 5, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature diverse - Riga", 5, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature diverse - Colonna", 4, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature chiare", 2, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature medie", 2, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature scure", 2, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature diverse", 5, false);
        deck.add(oc);
        oc=new ObjectiveCard("Diagonali colorate", 0, false);
        deck.add(oc);
        oc=new ObjectiveCard("Varietà di colore", 4, false);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature rosse", 0, true);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature gialle", 0, true);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature verdi", 0, true);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature blu", 0, true);
        deck.add(oc);
        oc=new ObjectiveCard("Sfumature viola", 0, true);
        deck.add(oc);

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
