package it.polimi.ing.sw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class DraftPool implements Serializable{

    private ArrayList<Dice> draftPool;


    public DraftPool(){
        draftPool=new ArrayList<Dice>();
    }


    // aggiunge un dado alla draftPool

    public void addDice(Dice dice){
        draftPool.add(dice);
    }

    public void setDraftPool(ArrayList<Dice> draftPool) {
        this.draftPool = draftPool;
    }

    // aggiunge un set di dadi ad una draftPool

    public void addDraftPool(DraftPool draft){
        for(Dice dice : draft.getDraftPool()){
            addDice(dice);
        }
    }


    // rimuove un dado dalla draftPool

    public void removeDice(Dice dice){
        draftPool.remove(dice);
    }


    // ritorna tutta la draftPool

    public ArrayList<Dice> getDraftPool() {
        return draftPool;
    }


    public int getSize(){
        return draftPool.size();
    }


    // cerca un dado nella draftPool

    public boolean diceInDraftpool(Dice diceToFind){
        for(Dice dice: draftPool){
            if(diceToFind.equals(dice)){
                return true;
            }
        }
        return false;
    }


    // ritorna il dado che si trova in una determinata posizione

    public Dice getDice(int index) {
        return draftPool.get(index);
    }


    // inserisce un dado in una determinata posizione

    public void setDice(Dice dice, int position) {
        draftPool.get(position).setDice(dice.getNumFacciaUp(), dice.getDiceColor());
    }


    // ritorna tutti i colori dei dadi presenti nella draftpool

    public ArrayList<Color> getColorsInDraftPool() {
        ArrayList<Color> colors = new ArrayList<Color>();
        for (Dice dice: draftPool)
            colors.add(dice.getDiceColor());
        return colors;
    }

    public DraftPool cloneDraftPool(){
        DraftPool draftPoolClone=new DraftPool();
        ArrayList<Dice> dicesClone=new ArrayList<Dice>();
        for(Dice dice: draftPool){
            dicesClone.add(dice.cloneDice());
        }
        draftPoolClone.setDraftPool(dicesClone);
        return draftPoolClone;
    }

}

