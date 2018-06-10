package it.polimi.ing.sw.model;


import java.util.ArrayList;

public class DraftPool {
    private ArrayList<Dice> draftPool;

    public DraftPool(){
        draftPool=new ArrayList<Dice>();
    }

    public void addDice(Dice dice){
        draftPool.add(dice);
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

}

