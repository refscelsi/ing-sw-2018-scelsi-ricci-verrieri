package it.polimi.ing.sw.model;
import it.polimi.ing.sw.model.objectiveCard.*;

import java.util.*;

public class ObjectiveCardDeck {
    private ArrayList<ObjectiveCard> deck;
    private ArrayList<ObjectiveCard> drawnCards;

    public ObjectiveCardDeck() {
        deck = new ArrayList<ObjectiveCard>();
        drawnCards= new ArrayList<ObjectiveCard>();
        setDeck();
    }

    public void setDeck () {
        ObjectiveCard oc=new DifferentColorRow();
        oc.setScore(6);
        oc.setName("Colori diversi - Riga");
        oc.setDescription("Righe senza colori ripetuti");
        deck.add(oc);
        oc=new DifferentColorColumn();
        oc.setScore(5);
        oc.setName("Colori diversi - Colonna");
        oc.setDescription("Colonne senza colori ripetuti");
        deck.add(oc);
        oc=new DifferentShadeRow();
        oc.setScore(5);
        oc.setName("Sfumature diverse - Riga");
        oc.setDescription("Righe senza sfumature ripetute");
        deck.add(oc);
        oc=new DifferentShadeColumn();
        oc.setScore(4);
        oc.setName("Sfumature diverse - Colonna");
        oc.setDescription("Colonne senza sfumature ripetute");
        deck.add(oc);
        oc=new Shades(1);
        oc.setScore(2);
        oc.setName("Sfumature Chiare");
        oc.setDescription("Set di 1 & 2 ovunque");
        deck.add(oc);
        oc=new Shades(3);
        oc.setScore(2);
        oc.setName("Sfumature Medie");
        oc.setDescription("Set di 3 & 4 ovunque");
        deck.add(oc);
        oc=new Shades(5);
        oc.setScore(2);
        oc.setName("Sfumature Scure");
        oc.setDescription("Set di 5 & 6 ovunque");
        deck.add(oc);
        oc=new DifferentShades();
        oc.setScore(5);
        oc.setName("Sfumature Diverse");
        oc.setDescription("Set di dadi di ogni valore ovunque");
        deck.add(oc);
        oc=new DifferentColors();
        oc.setScore(4);
        oc.setName("Varietà di Colore");
        oc.setDescription("Set di dadi di ogni colore ovunque");
        deck.add(oc);
        oc=new ColoredDiagonals();
        oc.setScore(0);
        oc.setName("Diagonali Colorate");
        oc.setDescription("Numero di dadi dello stesso colore diagonalmente adiacenti");
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
