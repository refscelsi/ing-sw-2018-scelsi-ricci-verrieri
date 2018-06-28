package it.polimi.ing.sw.model;

import it.polimi.ing.sw.ui.gui.SchemeListFileConverter;

import java.io.Serializable;
import java.util.*;
import java.util.Random;

public class SchemeCardDeck implements Serializable{

    private ArrayList<Scheme> deck;


    public SchemeCardDeck() {
        deck = new ArrayList<Scheme>();
        setDeck();
    }


   public void setDeck () {
        SchemeListFileConverter schemes = new SchemeListFileConverter();
        deck = schemes.readFromFile();
        int size = deck.size();
        if (size%2==0) {
            for (Scheme scheme: deck) {
                if (scheme.getId() % 2 == 0)
                    scheme.setIdRetro(scheme.getId() - 1);
                else
                    scheme.setIdRetro(scheme.getId() + 1);
            }
        }
        else {
            for (int i=0; i<size-1; i++) {
                if (deck.get(i).getId() % 2 == 0)
                    deck.get(i).setIdRetro(deck.get(i).getId() - 1);
                else
                    deck.get(i).setIdRetro(deck.get(i).getId() + 1);
            }
            Random rand = new Random();
            int id = rand.nextInt(size-1);
            deck.get(size-1).setIdRetro(deck.get(id).getId());
        }
    }

    /*public void setDeck(){
        int index=0;
        while(index<16) {
            Box[][] boxes = new Box[4][5];
            for(int i=0;i<4;i++){
                for(int j=0;j<5;j++){
                    boxes[i][j]=new Box(i,j);
                }
            }

            Scheme scheme = new Scheme(index, 2, boxes);
            deck.add(scheme);
            index++;
        }

    }*/

    public ArrayList<Scheme> drawSchemeCard (){
        ArrayList<Scheme> drawnCards = new ArrayList<Scheme>();
        for (int i=0; i<2; i++) {
            Collections.shuffle(deck);
            drawnCards.add(deck.get(0));
            drawnCards.add(getSchemeWithId(deck.get(0).getIdRetro()));
            deck.remove(getSchemeWithId(deck.get(0).getIdRetro()));
            deck.remove(0);
        }
        return drawnCards;
    }


    public int getSize(){
        return deck.size();
    }


    public Scheme getSchemeWithId ( int id ) {
        for (Scheme scheme : deck ) {
            if (scheme.getId()==id) {
                return scheme;
            }
        }
        return null;
    }
}


