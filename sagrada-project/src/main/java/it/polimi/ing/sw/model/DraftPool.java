package it.polimi.ing.sw.model;

import java.util.ArrayList;
import java.util.Collections;

public class DraftPool {
    private ArrayList<Dice> draftPool;

    public DraftPool(){
        draftPool=new ArrayList<Dice>();
    }

    public void addDice(Dice dice){
        draftPool.add(dice);
    }

    public void addDraftPool(DraftPool draft){
        for(Dice dice : draft.getDraftPool()){
            addDice(dice);
        }
    }

    public void removeDice(Dice dice){
        draftPool.remove(dice);
    }

    public ArrayList<Dice> getDraftPool() {
        return draftPool;
    }

    public int getSize(){
        return draftPool.size();
    }

    public boolean diceInDraftpool(Dice diceToFind){
        for(Dice dice: draftPool){
            if(diceToFind.equals(dice)){
                return true;
            }
        }
        return false;
    }

    public Dice getDice(int index) {
        return draftPool.get(index);
    }

}

